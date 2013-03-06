package raw; 
import static org.junit.Assert.assertTrue;
import robocode.control.events.BattleCompletedEvent;
import robocode.control.events.BattleMessageEvent;
import robocode.control.events.TurnEndedEvent;
import robocode.control.snapshot.IRobotSnapshot;
import robocode.control.testing.RobotTestBed;

/**
 * Tests that Zero did not run into a wall.
 *
 * @author Robert Ward
 */
public class TestZeroDoesNotHitWall extends RobotTestBed {
  
  
  /** True if the robot gets close to wall. */
  private int  timesHitWall = 0;
  private int turns = 0;

  /**
   * Specifies that Walls and Zero are to be matched up in this test case.
   *
   * @return The comma-delimited list of robots in this match.
   */
  @Override
  public String getRobotNames() {
    return "sample.Walls,raw.Zero";
  }

  /**
   * This test runs for 100 round.
   *
   * @return The number of rounds.
   */
  @Override
  public int getNumRounds() {
    return 20;
  }

  /**
   * After each turn, check to see if we're hit a wall. If so, increase the hit wall count.
   *
   * @param event Info about the current state of the battle.
   */
  @Override
  public void onTurnEnded(TurnEndedEvent event) {
    
    
    // Get the snapshot of the robot zero 
    final IRobotSnapshot robot = event.getTurnSnapshot().getRobots()[1];
    
    // Get robot's current position
    final double xPos = robot.getX();
    final double yPos = robot.getY();
    
    turns++;
    
    if (yPos > height - 20 ) {
      timesHitWall++;
      
    }
    if (yPos < 20 ) {
      timesHitWall++;
      
    }
    if (xPos > width - 20 ) {
      timesHitWall++;
      
    }
    if (xPos < 20 ) {
      timesHitWall++;
      
    }
      

  }
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
   * After the battle, check to see that we've hit a wall.
   *
   * @param event Details about the completed battle.
   */
  @Override
  public void onBattleCompleted(BattleCompletedEvent event) {
    
    
    
    assertTrue("Walls hit!" + timesHitWall, (timesHitWall / turns < .001));
   

  }

}
