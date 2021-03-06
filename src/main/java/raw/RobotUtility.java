package raw;

import java.awt.geom.Point2D;
import robocode.AdvancedRobot;
import robocode.Robot;
import robocode.Rules;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;

/**
 *
 * @author Robert Ward
 *
 */
public class RobotUtility { 


  /**
  * 
  * @param enemy robot to fire at.
  * @param turnsInSamePlace home many turns has this robot stood still.
  * @param numberofEnemy number of robots fighting.
  * @return the power level to fire at.
  */
  public double getPowerLevel(final ScannedRobotEvent enemy, final int turnsInSamePlace, 
      int numberofEnemy) {
    double powerLevel = 0;
    //Stationary TARGET!!! or Close TARGET Max fire power!!!
    if (enemy.getDistance() < 150 || turnsInSamePlace > 20 || numberofEnemy > 2 ) {
      powerLevel =  Rules.MAX_BULLET_POWER;
      
    }
    else if (enemy.getDistance() < 200) {
      powerLevel =  Rules.MIN_BULLET_POWER + 1 ;
      
    }
    else if (enemy.getDistance() < 300) {
      powerLevel =  Rules.MIN_BULLET_POWER + .5 ;
      
    }
    else if (enemy.getDistance() < 400) {
      powerLevel =  Rules.MIN_BULLET_POWER;
      
    }
    return powerLevel;
  }
  /**
   * 
   * @param centerX Center of the Grid X
   * @param centerY Center of the Grid X
   * @param offset offset to place positions at.
   * @return The list of all the moves.
   */
   public double[][][] loadStandardMoveLocation(double centerX, double centerY, double offset) {
      
     int X = 0;
     int Y = 1;
     double innerOffset = 100;
     double[][][] moveLocations = new double[4][4][2];
     //load move locations
     // Quadrant 0 diagonal
     moveLocations[0][0][X] = centerX - offset;
     moveLocations[0][0][Y] = centerY + offset;
     moveLocations[0][1][X] = centerX + offset;
     moveLocations[0][1][Y] = centerY - offset;
     moveLocations[0][2][X] = centerX + innerOffset;
     moveLocations[0][2][Y] = centerY + innerOffset;
     
     
     // Quadrant 1 diagonal
     moveLocations[1][0][X] = centerX - offset;
     moveLocations[1][0][Y] = centerY - offset;
     moveLocations[1][1][X] = centerX + offset;
     moveLocations[1][1][Y] = centerY + offset;
     moveLocations[1][2][X] = centerX - innerOffset;
     moveLocations[1][2][Y] = centerY + innerOffset;
     
     // Quadrant 2 diagonal
     moveLocations[2][0][X] = centerX - offset;
     moveLocations[2][0][Y] = centerY + offset;
     moveLocations[2][1][X] = centerX + offset;
     moveLocations[2][1][Y] = centerY - offset;
     moveLocations[2][2][X] = centerX - innerOffset;
     moveLocations[2][2][Y] = centerY - innerOffset;
     
     // Quadrant 3 diagonal
     moveLocations[3][0][X] = centerX - offset;
     moveLocations[3][0][Y] = centerY - offset;
     moveLocations[3][1][X] = centerX + offset;
     moveLocations[3][1][Y] = centerY + offset;
     moveLocations[3][2][X] = centerX - innerOffset;
     moveLocations[3][2][Y] = centerY + innerOffset;
     
     // Quadrant 1 diagonal
     moveLocations[1][0][X] = centerX - offset;
     moveLocations[1][0][Y] = centerY - offset;
     moveLocations[1][1][X] = centerX + offset;
     moveLocations[1][1][Y] = centerY - offset;
     moveLocations[1][2][X] = centerX + innerOffset;
     moveLocations[1][2][Y] = centerY + innerOffset;
     
     // Quadrant 3 diagonal
     moveLocations[3][0][X] = centerX - offset;
     moveLocations[3][0][Y] = centerY - offset;
     moveLocations[3][1][X] = centerX - offset;
     moveLocations[3][1][Y] = centerY + offset;
     moveLocations[3][2][X] = centerX + innerOffset;
     moveLocations[3][2][Y] = centerY + innerOffset;
     
     
     return moveLocations;
   }
   
   
   /**
    * 
    * @param centerX Center of the Grid X
    * @param centerY Center of the Grid X
    * @param offset offset to place positions at.
    * @return The list of all the moves.
    */
    public double[][][] loadMultiMoveLocation(double centerX, double centerY, double offset) {
      
      double quadXCenter = centerX + centerX / 2;
      double quadYCenter = centerY / 2;
      int X = 0;
      int Y = 1;
      double innerOffset = 20;
      double[][][] moveLocations = new double[4][4][2];
      //load move locations
      // Quadrant 0 diagonal
      moveLocations[0][0][X] = quadXCenter - offset;
      moveLocations[0][0][Y] = quadYCenter - offset;
      moveLocations[0][1][X] = quadXCenter + offset;
      moveLocations[0][1][Y] = quadYCenter + offset;
      moveLocations[0][2][X] = quadXCenter - innerOffset;
      moveLocations[0][2][Y] = quadYCenter + innerOffset;
      
      
      // Quadrant 1 diagonal
      moveLocations[1][0][X] = quadXCenter - offset;
      moveLocations[1][0][Y] = quadYCenter - offset;
      moveLocations[1][1][X] = quadXCenter + offset;
      moveLocations[1][1][Y] = quadYCenter + offset;
      moveLocations[1][2][X] = quadXCenter - innerOffset;
      moveLocations[1][2][Y] = quadYCenter + innerOffset;
      
      // Quadrant 2 diagonal
      moveLocations[2][0][X] = quadXCenter - offset;
      moveLocations[2][0][Y] = quadYCenter - offset;
      moveLocations[2][1][X] = quadXCenter + offset;
      moveLocations[2][1][Y] = quadYCenter + offset;
      moveLocations[2][2][X] = quadXCenter - innerOffset;
      moveLocations[2][2][Y] = quadYCenter + innerOffset;
      
      // Quadrant 3 diagonal
      moveLocations[3][0][X] = quadXCenter - offset;
      moveLocations[3][0][Y] = quadYCenter - offset;
      moveLocations[3][1][X] = quadXCenter + offset;
      moveLocations[3][1][Y] = quadYCenter + offset;
      moveLocations[3][2][X] = quadXCenter - innerOffset;
      moveLocations[3][2][Y] = quadYCenter + innerOffset;
      
      return moveLocations;
    }

   
   

  /**
   * Sets the direction of motion for the robot argument to the newHeading.
   * @param robot to set heading for.
   * @param newHeading the angle to set the direction of the robot
   * @param distance how far to move
   */
  public void setHeading(final AdvancedRobot robot, final double newHeading , double distance) {
  
    double turn =  Math.round( (newHeading + (360 - robot.getHeading())) % 360);
    if (newHeading > robot.getHeading() ) {
      
     turn = newHeading - robot.getHeading();
     
     if (turn < 90) { 
       robot.turnRight(turn);
       robot.ahead(distance);
     } 
     else {
       robot.turnLeft(180 - turn);
       robot.back(distance);
     }
    }
    else {
      
      turn = robot.getHeading() - newHeading;
      if (turn < 90) { 
        robot.turnLeft(turn);
        robot.ahead(distance);
      } 
      else {
        robot.turnRight(180 - turn);
        robot.back(distance);
      }
      
      
    }
  }
  /**
   * Gets the quadrant of the enemy robot.
   * @param robot robot that is looking for target
   * @param enemy robot to find the location of
   * @return a number 0-3 that is the quadrant of the enemy
   */
  public int getQuadrant(final Robot robot, final ScannedRobotEvent enemy) {
    
    final double absBearingDeg = (robot.getHeading() + enemy.getBearing());
    final double targetX = robot.getX() + Math.sin(Math.toRadians(absBearingDeg)) 
        * enemy.getDistance();
    final double targetY = robot.getY() + Math.cos(Math.toRadians(absBearingDeg)) 
        * enemy.getDistance();
    final double midXCoordinate =  robot.getBattleFieldWidth() / 2;
    final double midYCoordinate =  robot.getBattleFieldHeight() / 2;
        
    int quadrant = 0;
    if (targetX > midXCoordinate) {
      if (targetY < midYCoordinate) {
        quadrant = 3;
      }
      else {
        quadrant = 2;
      }
    }
    else if (targetY > midYCoordinate) {
      quadrant = 1;
    }
    return quadrant;
  }
  /**
   * Returns the corner specified by the coordinates.
   * 0 for not in a corner.
   * 1 for lower Left
   * 2 Upper Left.
   * 3 Upper Right.
   * 4 Lower Right.
   * @param xCoord of location to check if it is in a corner.
   * @param yCoord of location to check if it is in a corner.
   * @param fieldWidth width of the current battlefield.
   * @param fieldHeight height of the current battlefield.
   * @return return the number of the corner.
   */ 
  public int corner(double xCoord, double yCoord, double fieldWidth, double fieldHeight) {
    
    int corner = 0;
    if (xCoord < 125 && yCoord < 125) {
      corner = 1;
    }
    if (xCoord < 125 && yCoord >  fieldHeight - 125) {
      corner = 2;
    }
    if (xCoord > fieldWidth - 125 && yCoord > fieldHeight - 125) {
      corner = 3;
    }
    if (xCoord > fieldWidth - 125 && yCoord < 125) {
      corner = 4;
    }
        
    return corner;
  }
  
  /**
   * Checks if this value is to close to the wall if it is returns value that puts it outside 
   * the buffer region.    
   * @param coordinate the position value to check if it is too close to the wall.
   * @param fieldSize the maximum (width or height) of the field.
   * @param bufferFromWall the region around the walls not to enter.
   * @return the updated value if it is inside the buffer region near wall or
   *  original value if it is not
   */
  public double avoidNearWall(double coordinate, double fieldSize, double bufferFromWall) {
    
    double offWallCoordinate = coordinate;
    // we are inside the buffer region decrease the coordinate
    if (coordinate > (fieldSize - bufferFromWall)) {
      offWallCoordinate = fieldSize - bufferFromWall;
    }
    //if we are lower that the buffer increase to the buffer
    else if (coordinate < bufferFromWall) {
      offWallCoordinate = bufferFromWall;
    }
    
    return offWallCoordinate;
    
  }
  /**
   * Sets the heading to the x,y coordinates and moves there.
   * Will not allow robot to move into wall
   * @param robot the robot to move
   * @param xCoord the x coordinate of the new position
   * @param yCoord the y coordinate of the new position
   * @param minMove the minimum amount to move
   */
  public void goToward(final AdvancedRobot robot, final double  xCoord, final double yCoord,
      double minMove) {
    // code prevents moving into a wall 
    double xCoordinate = avoidNearWall(xCoord, robot.getBattleFieldWidth() , 40);
    double yCoordinate = avoidNearWall(yCoord, robot.getBattleFieldHeight() , 40);
       
    double distance = Point2D.distance(robot.getX(), robot.getY(), xCoordinate, yCoordinate);
    
   
    double newHeading = heading(robot.getX() , robot.getY() , xCoordinate,
        yCoordinate);
    setHeading(robot, newHeading, distance);

    
   
  }
  
 

  /**
   * Calculates the heading to go to the location indicated. 
   * @param xFrom starting location x coordinate.
   * @param yFrom starting location y coordinate.
   * @param xTo ending location x coordinate.
   * @param yTo ending location y coordinate.
   * @return the heading to go to the location indicated 
   */
  public double heading(double xFrom, double yFrom ,double xTo, double yTo ) {
     
    
    final double distanceX =  xTo - xFrom;
    final double distanceY = yTo - yFrom;
    double angle = 0 ;
    if ((distanceX == 0) && distanceY >= 0 ) {
      angle = 0;
    }
    else if ( distanceX == 0 && distanceY < 0 ) {
      angle = 180;
    } 
    else if (distanceX < 0 && distanceY == 0 ) {
      angle = 270;
    }
    else if (distanceX > 0 && distanceY == 0 ) {
      angle = 90;
    }
    else {
      angle = Math.toDegrees(Math.abs(Math.atan(Math.abs(distanceY / distanceX))));
      if ( distanceX > 0  && distanceY < 0) {
        angle += 90;
      }
      else if ( distanceX < 0  && distanceY < 0) {
        angle += 180;
      }
      else if ( distanceX < 0  && distanceY > 0) {
        angle += 270;
      }
    }
    
    
   
    return angle;
    
   }
  
  

/**
 *  Increases the value of currentInSamePlace if the coordinates are close to last ones.
 *  This lets us know if a robot stopped moving.
 * @param lastX last x coordinate the robot was at.
 * @param lastY last x coordinate the robot was at. 
 * @param currentX x coordinate of the robot. 
 * @param currentY y coordinate of the robot. 
 * @param currentInSamePlace times robot has been in the same place
 * @return if this is at the same location.
 */
public int stoppedCount(double lastX, double lastY, double currentX , double currentY , 
    int currentInSamePlace) {
  
  int turnsInSamePlace = currentInSamePlace;
  
 if (Math.abs(lastX - currentX ) < 1 && Math.abs(lastY - currentY ) < 1 ) {
    turnsInSamePlace++;
  }
  else {
    turnsInSamePlace = 0;
  }
  return turnsInSamePlace;
  
}

 /**
  * Tries to target redshift.
  * @param robot the robot to change gun position of.
  * @param red the evil redshift
  * @param bulletPower power level of justice.
  */
  public void firePowerAdjust(AdvancedRobot robot,final ScannedRobotEvent red,
      double bulletPower) {
    //based on code at:
    //http://robowiki.net/wiki/Linear_Targeting (trivial) exact non-iterative solution
    double headOnBearing = robot.getHeadingRadians() + red.getBearingRadians();
    double linearBearing = headOnBearing + Math.asin(red.getVelocity() /
        Rules.getBulletSpeed(bulletPower) * Math.sin(red.getHeadingRadians() - headOnBearing));
    robot.setTurnGunRightRadians(Utils.normalRelativeAngle(linearBearing - 
        robot.getGunHeadingRadians()));
    robot.setFire(bulletPower);
  }

  /**
   * Target ahead of a moving target.
   * @param robot 
   * @param scannedRobot robot that is being targeted.
   * @return the angle to turn the gun in radians.
   */
  public double linearTargetingTurn(AdvancedRobot robot,final ScannedRobotEvent scannedRobot )
  {
    double absoluteBearing = robot.getHeadingRadians() + scannedRobot.getBearingRadians();
    
    
    double adjust = .80 - (Math.floor(scannedRobot.getDistance() / 20)) / 100.0 ;
    
    return Utils.normalRelativeAngle(absoluteBearing - 
        robot.getGunHeadingRadians() + (scannedRobot.getVelocity() * adjust
            * Math.sin(scannedRobot.getHeadingRadians() - absoluteBearing) / 13.0));
       
   }


}