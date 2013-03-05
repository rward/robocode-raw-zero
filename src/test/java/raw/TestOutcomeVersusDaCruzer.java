package raw;
/**
 * Performance test between sample.DaCruzer and raw.Zero.
 * @author Robert Ward
 *
 */
public class TestOutcomeVersusDaCruzer extends TestBattleOutcomes {
  
  /**
   * 
   * @return the name of the robot to fight
   */
  public String getEnemyRobot() {
    return "sample.Corners";
  }
  
  /**
   * 
   * @return the name of the robot to test
   */
  public String getTestRobot() {
    return "raw.Zero";
  }
 
 
}
