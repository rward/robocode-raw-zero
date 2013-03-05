package raw;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Checks if x,y coordinate returns the correct corner.
 * @author Robert Ward
 *
 */
public class TestRobotUtilCornerCheck {
  private double fieldWidth = 800;
  private double fieldHeight = 600;
  /**
   * 
   */
  @Test
  public void test() {
    RobotUtility robotUtil = new  RobotUtility();
    assertEquals("Test 0, 0  ",1, robotUtil.corner(0, 0, fieldWidth, fieldHeight));
    
    assertEquals("Test 0, width ",2, robotUtil.corner(0, fieldHeight, fieldWidth, fieldHeight));
    
    assertEquals("Test height, width ",3, 
        robotUtil.corner(fieldWidth, fieldHeight, fieldWidth, fieldHeight));
    assertEquals("Test width, 0 ",4, robotUtil.corner(fieldWidth,0, fieldWidth, fieldHeight));
   
    assertEquals("Test 75, 75  ",1, robotUtil.corner(75, 75, fieldWidth, fieldHeight));
    
    assertEquals("Test 75, height - 75 ",2, robotUtil.corner(75, fieldHeight - 75, fieldWidth,
        fieldHeight));
    
    assertEquals("Test height -75, width-75 ",3, 
        robotUtil.corner(fieldWidth - 75, fieldHeight - 75, fieldWidth, fieldHeight));
    assertEquals("Test width - 75, 75 ",4, robotUtil.corner(fieldWidth - 75,75, fieldWidth,
        fieldHeight));
    
    
    assertEquals("Test 126, 0 ",0, robotUtil.corner(126, 0, fieldWidth, fieldHeight));
    assertEquals("Test 126, width  ",0, robotUtil.corner(126, fieldWidth, fieldWidth, fieldHeight));
    assertEquals("Test 126, 126 corner ",0, robotUtil.corner(126,126, fieldWidth, fieldHeight));
   
  }

}
