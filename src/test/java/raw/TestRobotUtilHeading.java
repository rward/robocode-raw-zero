package raw;


import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Tests for method that calculates heading.
 * @author Robert Ward
 * 
 */
public class TestRobotUtilHeading {
  /**
   * Makes sure that the heading values are returned correctly for X,Y points.
   */
  @Test
  public void test() {
    RobotUtility robotUtil = new  RobotUtility();
    
    assertEquals("Test 0 heading failed",0.0,robotUtil.heading(50, 50, 50, 100),.1);
    assertEquals("Test 45 heading failed",45.0,robotUtil.heading(50, 50, 100, 100),.1);
    assertEquals("Test 90 heading failed",90.0,robotUtil.heading(50, 50, 100, 50),.1);
    assertEquals("Test 135 heading failed",135.0,robotUtil.heading(50, 50, 100, 0),.1);
    assertEquals("Test 180 heading failed",180.0, robotUtil.heading(50, 50, 50, 0),.1);
    assertEquals("Test 225 heading failed",225.0,robotUtil.heading(50, 50, 0, 0),.1);
    
    assertEquals("Test 270 heading failed",270.0,robotUtil.heading(50, 50, 0, 50),.1);
    assertEquals("Test 296.5 heading failed",296.5,robotUtil.heading(50, 50, 0, 75),.1);
    assertEquals("Test 315 heading failed",315.0,robotUtil.heading(50, 50, 0, 100),.1);
    assertEquals("Test 270 heading failed",270.0,robotUtil.heading(50, 50, 0, 50),.1);
    assertEquals("Test 296.5 heading failed",296.5,robotUtil.heading(50, 50, 0, 75),.1);
    assertEquals("Test 315 heading failed",315.0,robotUtil.heading(50, 50, 0, 100),.1);
    
    assertEquals("Test 333.4 heading failed",333.4,robotUtil.heading(50, 50,25, 100),.1);
    assertEquals("Test 357.7 heading failed",357.7,robotUtil.heading(50, 50,48, 100),.1);
    assertEquals("Test 0 heading failed",0,robotUtil.heading(50, 50,50, 50),.1);
    assertEquals("Test 180.0 heading failed",180,robotUtil.heading(50, 50,50, 49),.1);
    assertEquals("Test 180.0 heading failed",180,robotUtil.heading(50, 50,50, 35),.1);
    assertEquals("Test 180.0 heading failed",180,robotUtil.heading(50, 50,50, 0),.1);
  }

}
