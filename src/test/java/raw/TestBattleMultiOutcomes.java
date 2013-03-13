package raw;

import static org.junit.Assert.assertTrue;
import robocode.BattleResults;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleMessageEvent;
import robocode.control.testing.RobotTestBed;
/**
 * Base class for testing robots to see if they can beat other robots.
 * To test other robots extend this class an override the get enemy robot method.
 * @author Robert Ward
 *
 */
public class TestBattleMultiOutcomes extends RobotTestBed {
  
  
  /**
   * Keep track of the number of messages. Print them if desired.
   *
   * @param event The BattleMessageEvent.
   */
  @Override
  public void onBattleMessage(BattleMessageEvent event) {
    //override the printout of each round
  }

  
  
  /**
   * Specifies the robots that are to be matched up in this test case.
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return getEnemyRobot() + "," + getTestRobot();
  }
  /**
   * Override this method to change the robot to Run a test battle against.
   * @return the name of the robot to fight
   */
  public String getEnemyRobot() {
    return "sample.Crazy,sample.Fire,sample.MyFirstJuniorRobot,sample.Crazy";
  }
  
  /**
   * The robot being tested.
   * Override to test a different robot.
   * @return the name of the robot to test
   */
  public String getTestRobot() {
    return "raw.Zero";
  }
  
  
  
  /**
   * The actual test, which asserts that the test robot has won over 30% of the rounds.
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    
    // Return the results in order of getRobotNames.
    BattleResults[] battleResults = event.getIndexedResults();
    
    BattleResults testRobotResults =  battleResults[0]; 
    
    
    for ( int index = 0; index < battleResults.length ; index++) {
      if ( battleResults[index].getTeamLeaderName().equals(this.getTestRobot() + "*") ) {
        testRobotResults = battleResults[index];
        
      }
  
    }
               
    String robotName = testRobotResults.getTeamLeaderName();
    
    
    System.out.println("Wins for " + robotName + " versus many " + testRobotResults.getFirsts());
    // Check to make sure test robot won over 30% of the rounds.
    assertTrue("Wins " + testRobotResults.getFirsts() , testRobotResults.getFirsts() > 70  );
  }
  
  /**
   * This test runs for 100 rounds.
   * @return The number of rounds. 
   */
  @Override
  public int getNumRounds() {
    return 100;
  }
  
  /**
   * Returns true if the battle should be deterministic and thus robots will always start
   * in the same position each time. 
   * 
   * Override to return false to support random initialization. 
   * @return True if the battle will be deterministic.
   */
  @Override
  public boolean isDeterministic() {
    return false;
  }
  
}
