import java.util.Comparator;


public class Point implements Comparable<Point> {
  
  private final int x;
  private final int y;
  
  public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
    public int compare(Point o1, Point o2) {
      return 0;
    }
  };
  
  /**
   * Basic constructor that takes the point's
   * Cartesian coordinates as arguments
   * @param x horizontal distance from the origin
   * @param y vertical distance from the origin
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }
  
  /**
   * Draw the point represented by the instance
   * it is called on
   */
  public void draw() {
    StdDraw.point(this.x, this.y);
  }
  
  /**
   * Draw the line connecting the point represented by
   * the instance the method is called on to the point
   * given as argument
   * @param that end point for the line segment to be drawn
   */
  public void drawTo(Point that) {
    StdDraw.line(this.x, this.y, that.x, that.y);
  }
  
  /**
   * Returns a string representation of the point instance
   */
  public String toString() {
    return "(" + x + ", " + y + ")";
  }
  
  /**
   * Lexicographic comparison between the point instance
   * and the point given as argument
   * @param that point to which the instance is compared
   * @return 0 if both are equal, -1 if the instance is lexicographically
   * smaller than the given point, 1 otherwise
   */
  public int compareTo(Point that) {
    if (that.y > this.y) {
      return -1;
    }
    else if (that.y == this.y) {
      if (that.x > this.x) 
        return -1;
      else if (that.x == this.x)
        return 0;
      else
        return 1;
    }
    else {
      return 1;
    }
  }
  
  /**
   * Computes the slope between the instance and the point
   * given as argument
   * @param that
   * @return the slope between the two points
   */
  public double slopeTo(Point that) {
    return (double)(that.y - this.y)/(that.x - this.x);
  }
  
  public static void main(String[] args) {
    
    // Test the compareTo() method
    Point a = new Point(1, 1);
    Point b = new Point(2, 2);
    
    System.out.println("Is " + b + " bigger than " + a + "? " + b.compareTo(a));
    
    b = new Point(2, 1);
    
    System.out.println("Is " + b + " bigger than " + a + "? " + b.compareTo(a));
    
    b = new Point(1, 2);
    
    System.out.println("Is " + b + " bigger than " + a + "? " + b.compareTo(a));
    
    b = new Point(0, 1);
    
    System.out.println("Is " + b + " bigger than " + a + "? " + b.compareTo(a));
    
    b = new Point(1, 1);
    
    System.out.println("Is " + b + " bigger than " + a + "? " + b.compareTo(a));
    
    b = new Point(1, 0);
    
    System.out.println("Is " + b + " bigger than " + a + "? " + b.compareTo(a));
  }
}