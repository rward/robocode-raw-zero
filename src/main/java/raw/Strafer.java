package raw;

import java.awt.Color;
import robocode.AdvancedRobot;
import robocode.HitWallEvent;
import robocode.ScannedRobotEvent;
import robocode.util.Utils;
/**
 * An AdvancedRobot that circles and tracks the enemy.
 *
 * @author Todd Taomae
 */
public class Strafer extends AdvancedRobot {
  private int direction = 1;

  @Override
  public void run() {
    setColors(Color.BLACK, Color.RED, Color.BLACK, Color.GREEN, Color.CYAN);
    setAdjustGunForRobotTurn(true);
    setAdjustRadarForGunTurn(true);
    setAdjustRadarForRobotTurn(true);

    setTurnRadarRightRadians(Double.POSITIVE_INFINITY);
    execute();
  }

  @Override
  public void onScannedRobot(ScannedRobotEvent event) {
    // infinity lock
    this.setTurnRadarLeftRadians(getRadarTurnRemainingRadians());


    double absoluteBearing = this.getHeadingRadians() + event.getBearingRadians();

    // turn perpendicular to enemy
    double turnHeading = absoluteBearing + (Math.PI / 2);
    setTurnRightRadians(turnHeading - this.getHeadingRadians());

    // choose bullet power
    double bulletPower = 3.0 - (event.getDistance() / 200);
    if (this.getGunTurnRemaining() == 0.0) {
        this.setFire(bulletPower);
    }

    double gunTurn = absoluteBearing - this.getGunHeadingRadians();
    setTurnGunRightRadians(Utils.normalRelativeAngle(gunTurn));

    setAhead(this.direction * 100.0);
  }

  @Override
  public void onHitWall(HitWallEvent event) {
    this.direction *= -1;
  }
}