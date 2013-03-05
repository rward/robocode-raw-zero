package raw;



import static org.junit.Assert.assertTrue;
import robocode.Rules;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IBulletSnapshot;
import robocode.control.testing.RobotTestBed;

/**
 * This tests for variability in bullet power administered by the robot DaCruzer.
 *
 * @author  Robert Ward
 */
public class TestZeroFiring extends RobotTestBed {

  // True if DaCruzer has fired a bullet with power less than 1.4;
  private transient boolean firePowerMaxReach = false;
  // True if DaCruzer has fired a bullet with power between 1.5 to 1.9;
  private transient boolean firePowerMin = false;
  //True if DaCruzer has fired a bullet with power less than 1.4;
  private transient boolean firePower2 = false;
  // True if DaCruzer has fired a bullet with power between 1.5 to 1.9;
  private transient boolean firePower3 = false;
  
  /**
   * Specifies that SittingDuck and DaCruzer are to be matched up in this test case.
   * 
   * @return The comma-delimited list of robots in this match.
   */
  @Override 
  public String getRobotNames() {
    return "sample.Tracker,raw.Zero";
  }
  
  /**
   * This test runs for 20 rounds.
   * 
   * @return The number of rounds. 
   */
  @Override 
  public int getNumRounds() {
    return 20;
  }
  
  /**
   * At the end of each turn, checks the power of all bullets moving across the 
   * battlefield. Checks to see if there is any variability in bullet power, proving
   * that DaCruzer is using proportional firing based on distance of detected enemy robot. 
   * 
   * @param event Info about the current state of the battle.
   */
  @Override 
  public void onTurnEnded (TurnEndedEvent event) {
    
    // All active bullets belong to DaCruzer since SittingDuck does not fire.
    IBulletSnapshot bullets[] = event.getTurnSnapshot().getBullets();
    double bulletPower;
    
    for (int i = 0; i < bullets.length; i++) {
    
      bulletPower = bullets[i].getPower();
      
      if (robocode.util.Utils.isNear(bulletPower, Rules.MAX_BULLET_POWER )) {
        firePowerMaxReach = true;
      }
      else if (robocode.util.Utils.isNear(bulletPower, Rules.MIN_BULLET_POWER + 1 )) {
        firePower2 = true;
      }
      else if (robocode.util.Utils.isNear(bulletPower, Rules.MIN_BULLET_POWER + .5 )) {
        firePower3 = true;
      }
      else if (robocode.util.Utils.isNear(bulletPower, Rules.MIN_BULLET_POWER  )) {
        firePowerMin = true;
      }
     
    }
  }
  
  /**
   * After running all matches, determine if DaCruzer has had variability in its bullet power.
   * 
   * @param event Details about the completed battle.
   */
  @Override 
  public void onBattleCompleted(final BattleCompletedEvent event) {
    assertTrue("Bullet Power Max Not Reached", firePowerMaxReach);
    assertTrue("Bullet Power Min Not Reached", firePowerMin);
    assertTrue("Bullet Power 2 Not Reached", firePower2);
    assertTrue("Bullet Power 3 Not Reached", firePower3);
    
  }
}