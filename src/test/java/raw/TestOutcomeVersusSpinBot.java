package raw;
/**
 * Performance test between sample.SpinBot and raw.Zero.
 * @author Robert Ward
 *
 */
public class TestOutcomeVersusSpinBot extends TestBattleOutcomes {
  /**
   * 
   * @return the name of the robot to fight
   */
  public String getEnemyRobot() {
    return "sample.SpinBot";
  }
  
  /**
   * 
   * @return the name of the robot to test
   */
  public String getTestRobot() {
    return "raw.Zero";
  }
}
