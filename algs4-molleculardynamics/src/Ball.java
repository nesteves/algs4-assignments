/**
 * Class representing a 2-dimensional disc
 * with a defined position, velocity and radius
 */
public class Ball {
  
  private double rx, ry;        // position
  private double vx, vy;        // velocity
  private final double radius;  // radius of the ball
  
  public Ball() {
    this.rx = StdRandom.uniform();
    this.ry = StdRandom.uniform();
    this.vx = StdRandom.uniform() / 50;
    this.vy = StdRandom.uniform() / 50;
    this.radius = 0.005;
  }

  public void move(double dt) {
    if ((rx + vx * dt < radius) || (rx + vx * dt > 1.0 - radius)) vx = -vx;
    if ((ry + vy * dt < radius) || (ry + vy * dt > 1.0 - radius)) vy = -vy;
    rx = rx + vx * dt;
    ry = ry + vy * dt;
  }
  
  public void draw() {
    StdDraw.filledCircle(rx, ry, radius);
  }
}
