package raw;


import static org.junit.Assert.assertEquals;
import org.junit.Test;


/**
 * Test method that checks if x,y coordinate is in the corner.
 * @author Robert Ward
 *
 */
public class TestRobotUtilAvoidNearWall {
  private double fieldWidth = 800;
  private double fieldHeight = 600;
  private double bufferFromWall = 40;
  /**
   * Check that values are updated if they are not too close to wall. 
   */
  @Test
  public void test() {
    
    RobotUtility robotUtil = new  RobotUtility();
    assertEquals("Test 0  ",40,robotUtil.avoidNearWall(0, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 10  ",40,robotUtil.avoidNearWall(10, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 20  ",40,robotUtil.avoidNearWall(20, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 30  ",40,robotUtil.avoidNearWall(30, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 40  ",40,robotUtil.avoidNearWall(40, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 50  ",50,robotUtil.avoidNearWall(50, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 100 ",100,robotUtil.avoidNearWall(100, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 150 ",150,robotUtil.avoidNearWall(150, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 760 ",760,robotUtil.avoidNearWall(760, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 770 ",760,robotUtil.avoidNearWall(770, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 780 ",760,robotUtil.avoidNearWall(780, fieldWidth, bufferFromWall), 0.01 );
    assertEquals("Test 790 ",760,robotUtil.avoidNearWall(790, fieldWidth, bufferFromWall), 0.01 );
    
    assertEquals("Test 0  ",40,robotUtil.avoidNearWall(0, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 10  ",40,robotUtil.avoidNearWall(10, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 20  ",40,robotUtil.avoidNearWall(20, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 30  ",40,robotUtil.avoidNearWall(30, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 40  ",40,robotUtil.avoidNearWall(40, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 50  ",50,robotUtil.avoidNearWall(50, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 100 ",100,robotUtil.avoidNearWall(100, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 150 ",150,robotUtil.avoidNearWall(150, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 560 ",560,robotUtil.avoidNearWall(560, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 570 ",560,robotUtil.avoidNearWall(570, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 580 ",560,robotUtil.avoidNearWall(580, fieldHeight, bufferFromWall), 0.01 );
    assertEquals("Test 590 ",560,robotUtil.avoidNearWall(590, fieldHeight, bufferFromWall), 0.01 );
   
  }
  


}
