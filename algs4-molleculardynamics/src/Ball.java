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
    this.vx = StdRandom.uniform();
    this.vy = StdRandom.uniform();
    this.radius = 0.05;
  }

  public void move(double dt) {
    
  }
  
  public void draw() {
    StdDraw.filledCircle(rx, ry, radius);
  }
}
