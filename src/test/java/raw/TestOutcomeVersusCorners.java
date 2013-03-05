package raw;
/**
 * Performance test between sample.Corners and raw.Zero.
 * @author Robert Ward
 *
 */
public class TestOutcomeVersusCorners extends TestBattleOutcomes {
  
  /**
   * @author Robert Ward
   * @return the name of the robot to fight
   */
  public String getEnemyRobot() {
    return "sample.Corners";
  }
  
  /**
   * @author Robert Ward
   * @return the name of the robot to test
   */
  public String getTestRobot() {
    return "raw.Zero";
  }
 
 
}
