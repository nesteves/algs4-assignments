
/**
 * Class that represents a 2-dimensional
 * disc with physical properties such as
 * mass and velocity
 */
public class Particle {
  private double rx, ry;
  private double vx, vy;
  private final double radius;
  private final double mass;
  private int count;
  
  /**
   * Basic parameterized constructor
   * @param rx position in the x-axis
   * @param ry position in the y-axis
   * @param vx velocity in the x-axis
   * @param vy velocity in the y-axis
   * @param radius radius of the particle
   * @param mass mass of the particle
   */
  public Particle(double rx, double ry, double vx, double vy, double radius,
      double mass) {
    super();
    this.rx = rx;
    this.ry = ry;
    this.vx = vx;
    this.vy = vy;
    this.radius = radius;
    this.mass = mass;
  }
  
  /**
   * Moves the instance of Particle an amount
   * depending on its physical properties and the given
   * time lapse
   * @param dt amount of time lapsed
   */
  public void move(double dt) {
    
  }
  
  /**
   * Draws the particle in the board
   */
  public void draw() {
    
  }
  
  /**
   * Predicts a collision with another particle
   * @param that the particle against which to test for an eventual
   * collision
   * @return the time it will take for both particles to collide
   */
  public double timeToHit(Particle that) {
    if (this == that) return Double.POSITIVE_INFINITY;
    
    double dx = that.rx - this.rx;
    double dy = that.ry - this.ry;
    double dvx = that.vx - this.vx;
    double dvy = that.vy - this.vy;
    
    double dvdr = dx * dvx + dy * dvy;
    if (dvdr > 0) return Double.POSITIVE_INFINITY;
    
    double dvdv = dvx * dvx + dvy * dvy;
    double drdr = dx * dx + dy * dy;
    
    double sigma = that.radius + this.radius;
    double d = (dvdr * dvdr) - dvdv * (drdr - sigma*sigma);
    if (d < 0) return Double.POSITIVE_INFINITY;
    
    return -(dvdr + Math.sqrt(d)) / dvdv;
  }
  
  /**
   * Predicts a collision with a vertical wall
   * @return the time it will take for this Particle to
   * collide with a vertical wall
   */
  public double timeToHitVerticalWall() {
    if (vy > 0) {
      return (double) (1 - this.radius - ry) / vy;
    }
    else if (vy < 0) {
      return (double) (this.radius - ry) / vy;
    }
    else {
      return Double.POSITIVE_INFINITY;
    }
  }
  
  /**
   * Predicts a collision with a horizontal wall
   * @return the time it will take for this Particle to
   * collide with a vertical wall
   */
  public double timeToHitHorizontallWall() {
    return 0.0;
  }
  
  public void bounceOff(Particle that) {
    double dx = that.rx - this.rx;
    double dy = that.ry - this.ry;
    double dvx = that.vx - this.vx;
    double dvy = that.vy - this.vy;
    
    double dvdr = dx * dvx + dy * dvy;
    double dist = this.radius + that.radius;
    
    double J = 2 * this.mass * that.mass * dvdr / ((this.mass + that.mass) * dist);
    double Jx = J * dx / dist;
    double Jy = J * dy / dist;
    
    this.vx += Jx / this.mass;
    this.vy += Jy / this.mass;
    
    that.vx -= Jx / that.mass;
    that.vy -= Jy / that.mass;
    
    this.count++;
    that.count++;
  }
  
  public void bounceOffVerticallWall() {
    
  }
  
  public void bounceOffHorizontalWall() {
    
  }
}
