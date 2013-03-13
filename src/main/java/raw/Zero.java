package raw;

import java.awt.Color;
import robocode.AdvancedRobot;

import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 * Implements the DaCruzer robot, which completes a circuit around the arena, 
 * then spins to scan for other robots. 
 * 
 * @author Robert Ward
 */
public class Zero extends AdvancedRobot {
  
  /**
   * Full Rotation.
   */
  private static final int THREESIXTY = 360;
  
  /**
   * Indicates that there is has more than one robot fighting.
   */
  private boolean multiPlayer = false;
  /**
   * Name of chosen enemy.
   */
  private String chosenEnemy = "";
  /**
   * Number of turn we have not seen chosen enemy.
   */
  private int noContactCount = 0;
  
 
  
  
  /**
   * Contains utility functions for moving firing and scanning.
   */  
  private final transient RobotUtility robotUtility = new RobotUtility();
   
  /**
   * Indicates where the sector enemy was last spotted.
   * Quadrant 0 lower left Quarter
   * Quadrant 1 upper left Quarter
   * Quadrant 2 upper right Quarter
   * Quadrant 3 lower right Quarter
   * Used to pick path perpendicular to Quadrant of enemy.
   */
  private int enemyQuadrant = 0;
  
 
  /**
   * Are we currently tracking the enemy.
   */
  private long lastTimeTracked = -10;
   
  /**
   * Last place our enemy was spotted. 
   */
  private double prevEnemyX = 0;
  /**
   * Last place our enemy was spotted .
   */
  private double prevEnemyY = 0;
  
  /**
   * Average of target's velocity.
   */
  private double averageVelocity = 0;
  
  /**
   * Number of time we have scanned robot. 
   */
  private double scanTimes = 0;
  /**
   * How long has our target remained in one spot.
   */
  private int turnsInSamePlace = 0;
 
   /**
   * Width of our offset for our diagonal.
   */
  private int movePositionOffset = 140;
  
  /**
   * Width of our offset for our diagonal.
   */
  private int moveMultiPositionOffset = 60;
  
  
  /**
   * Minimum distance to move.
   */
  private int minimumMovedistance = 30;
  
  /**
   * How many spots are there in our movement.
   */
  private int PositionsInMovment = 3;

  /**
   * Indicates to use firePowerAdjust.
   */
  private boolean firePowerAdjust = false;
  
  
  @Override
  /**
   * Runs the main method for the robot.
   * 
   */
   public void run() {
    
   
    setColors(Color.yellow ,Color.yellow, Color.red, Color.yellow, Color.yellow);
    if (this.getOthers() > 1) {
      multiPlayer = true;
    }
    
    setAdjustGunForRobotTurn(true);
    setAdjustRadarForGunTurn(true);
    setAdjustRadarForRobotTurn(true);
    double[][][] moveList;
    
    if (multiPlayer) {
      moveList =  robotUtility.loadMultiMoveLocation(this.getBattleFieldWidth() / 2, 
          this.getBattleFieldHeight() / 2, moveMultiPositionOffset); 
    }
    else {
     
      moveList =  robotUtility.loadStandardMoveLocation(this.getBattleFieldWidth() / 2, 
        this.getBattleFieldHeight() / 2, movePositionOffset); 
     
    }
    
    // used to indicate which end of the diagonal to head towards 
    int position = 0;
    while (true) {
     
      //we lost our target spin radar to find it
      if ( lastTimeTracked + 1 < this.getTime()) {
        
        setTurnRadarRight(THREESIXTY);
      }
      noContactCount++; 
      if (noContactCount > 5) {
        chosenEnemy = "";
      } 
      
     
      robotUtility.goToward(this, 
          moveList[enemyQuadrant][position][0], 
          moveList[enemyQuadrant][position][1], minimumMovedistance );
      
      position++;
      position = position < PositionsInMovment ? position : 0;
         
      //scan in case  radar didn't move;
      scan();
      if (multiPlayer && this.getOthers() == 1) {
        multiPlayer = false;
        //load single combat moves
        moveList =  robotUtility.loadStandardMoveLocation(this.getBattleFieldWidth() / 2, 
            this.getBattleFieldHeight() / 2, movePositionOffset); 
      } 
    }
  }
  /**
   * Handles scanned robot event.
   * Keeps gun pointed at chosen enemy.
   * @param scanEvent Scanned event.
   */
  public void onScannedRobot(final ScannedRobotEvent scanEvent) {
    
    
    this.enemyQuadrant = robotUtility.getQuadrant(this, scanEvent);
   
    lastTimeTracked = this.getTime();
    
    double absoluteBearing = this.getHeadingRadians() + scanEvent.getBearingRadians();
    double radarTurn = absoluteBearing - getRadarHeadingRadians();
      
    double enemyX = this.getX() + scanEvent.getDistance() * Math.sin(absoluteBearing);
    double enemyY = this.getY() + scanEvent.getDistance() * Math.cos(absoluteBearing);
    averageVelocity = (averageVelocity * scanTimes + scanEvent.getVelocity()) / (scanTimes + 1);
    
    turnsInSamePlace = robotUtility.stoppedCount(prevEnemyX, prevEnemyY, enemyX , enemyY,
        turnsInSamePlace)  ;
    double powerLevel = robotUtility.getPowerLevel(scanEvent,turnsInSamePlace,this.getOthers()); 
    boolean tracking = false;
 
    if (multiPlayer) {
     
      if (getRoundNum() == 5) {
        firePowerAdjust = true;
      }
      else {
        firePowerAdjust = false;
      }
     //are we currently following a robot?
     if (chosenEnemy.length() == 0 && scanEvent.getDistance() < 300 ) {
       //set this as the robot to attack
       chosenEnemy = scanEvent.getName();
       tracking  = true;
     }
     else if (chosenEnemy.equals(scanEvent.getName()) && scanEvent.getDistance() < 350 ) {
       noContactCount = 0; 
       //keep tracking this robot
       tracking  = true;
       setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
       
     }
     else if (chosenEnemy.equals(scanEvent.getName()) && scanEvent.getDistance() >= 350 ) {
       //stop tracking this robot
       chosenEnemy = "";
       noContactCount = 0; 
       tracking  = false;
       setTurnRadarRight(THREESIXTY);
     }
   
   }
   if (!multiPlayer || tracking ) {
     if (!multiPlayer && scanEvent.getName().contains("RedShift")) {
       firePowerAdjust = true;
     }
     chosenEnemy = scanEvent.getName();
     setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
    
     // if we are'nt to low on power shoot at target if within range
     
     if ((this.getEnergy() > 5) &&  (this.getOthers() > 2 || scanEvent.getDistance() < 400
         || turnsInSamePlace > 5 )) {
        
         //double gunTurn =  robotUtility.getGunTurn(this, scanEvent, powerLevel);
         double gunTurn =  robotUtility.linearTargetingTurn(this, scanEvent); 
         
         
         if (scanEvent.getDistance() > 150 ) {
           
           int randomShot = Utils.getRandom().nextInt(50);
         
           if (randomShot > 30)   {
             gunTurn =  gunTurn - (gunTurn - radarTurn) / 2;  
           }
           else if (randomShot > 20)  {
             gunTurn =  radarTurn;   
           }
         }
                 
         if (firePowerAdjust) {
           robotUtility.firePowerAdjust(this,scanEvent ,powerLevel) ;
         }
         else {
           setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));
           setFire(powerLevel); 
         }
         
         
     }
     
     prevEnemyX = enemyX;
     prevEnemyY = enemyY;
   }
 }
 
}
