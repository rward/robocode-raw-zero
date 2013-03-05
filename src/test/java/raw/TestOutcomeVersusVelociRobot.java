package raw;
/**
 * Performance test between sample.VelociRobot and raw.Zero.
 * @author Robert Ward
 *
 */
public class TestOutcomeVersusVelociRobot extends TestBattleOutcomes {
  /**
   * 
   * @return the name of the robot to fight
   */
  public String getEnemyRobot() {
    return "sample.VelociRobot";
  }
  
  /**
   * 
   * @return the name of the robot to test
   */
  public String getTestRobot() {
    return "raw.Zero";
  }
}
