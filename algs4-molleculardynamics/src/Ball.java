/**
 * Class representing a 2-dimensional disc
 * with a defined position, velocity and radius
 */
public class Ball {
  
  private double rx, ry;        // position
  private double vx, vy;        // velocity
  private final double radius;  // radius of the ball
  
  public Ball(double rx, double ry, double vx, double vy, double radius) {
    super();
    this.rx = rx;
    this.ry = ry;
    this.vx = vx;
    this.vy = vy;
    this.radius = radius;
  }

  public void move(double dt) {
    
  }
  
  public void draw() {
    
  }
}
