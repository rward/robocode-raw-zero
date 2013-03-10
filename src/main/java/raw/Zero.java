package raw;

import robocode.AdvancedRobot;
import robocode.Rules;
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
  private double lastEnemyXCoordinate = 0;
  /**
   * Last place our enemy was spotted .
   */
  private double lastEnemyYCoordinate = 0;
 
  /**
   * How long has our target remained in one spot.
   */
  private int turnsInSamePlace = 0;
 
   /**
   * Width of our offset for our diagonal.
   */
  private int movePositionOffset = 120;
  
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
  
  
  @Override
  /**
   * Runs the main method for the robot.
   * 
   */
   public void run() {
    
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
    double powerLevel = 0;
    lastTimeTracked = this.getTime();
    
    double absoluteBearing = this.getHeadingRadians() + scanEvent.getBearingRadians();
    double gunTurn = absoluteBearing - getGunHeadingRadians();
    double radarTurn = absoluteBearing - getRadarHeadingRadians();
      
    double enemyX = this.getX() + scanEvent.getDistance() * Math.sin(absoluteBearing);
    double enemyY = this.getY() + scanEvent.getDistance() * Math.cos(absoluteBearing);
    
    if (Utils.isNear(lastEnemyXCoordinate, enemyX) 
        && Utils.isNear(lastEnemyYCoordinate, enemyY) ) {
      turnsInSamePlace++;
    }
    else {
      
      turnsInSamePlace = 0;
    }
       
   gunTurn =  robotUtility.linearTargetingHeading(this, scanEvent); 
   boolean tracking = false;
 
   if (multiPlayer) {
     
     //are we currently following a robot?
     if (chosenEnemy.length() == 0 && scanEvent.getDistance() < 300 ) {
       //set this as the robot to attack
       chosenEnemy = scanEvent.getName();
       tracking  = true;
       
       setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
       setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));
      
     }
     else if (chosenEnemy.equals(scanEvent.getName()) && scanEvent.getDistance() < 350 ) {
       noContactCount = 0; 
       //keep tracking this robot
       tracking  = true;
       
       setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
       setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));
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
     setTurnRadarRightRadians(Utils.normalRelativeAngle(radarTurn));
     setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));
     powerLevel = robotUtility.setPowerLevel(scanEvent,turnsInSamePlace); 
     if (this.getEnergy() > 10) {
       if (multiPlayer) {
       
         if (this.getOthers() > 2) {
           setFire(Rules.MAX_BULLET_POWER);
         }
         else {
           if (scanEvent.getDistance() < 400 || turnsInSamePlace > 5 ) {
             setFire(powerLevel);
           }
         }
       }
       else if (scanEvent.getDistance() < 400 || turnsInSamePlace > 5 ) {
         setFire(powerLevel);
       }
     }
     lastEnemyXCoordinate = enemyX;
     lastEnemyYCoordinate = enemyY;
   }
 }
  
}
