/**
 * Class used to find collinear points faster than the Brute
 * class.
 * It groups points by the slope they make against an origin
 * p, then selects the longest sequence as the biggest 
 * segment that can be built with p.
 *
 */
public class Fast {
  

  /**
   * Converts the given array of points into a String
   * @param segment array of points
   * @return a string in the format point1 -> point2 -> point3
   */
  private static String stringSegment(Point[] segment) {
    StringBuilder s = new StringBuilder();
    
    for (int i = 0; i < segment.length - 1; i++) {
      s.append(segment[i]);
      s.append(" -> ");
    }
    
    s.append(segment[segment.length - 1]);
    
    return s.toString();
  }
  
  /**
   * Draws the points from an array of points
   * @param points the set of points to draw
   */
  private static void drawPoints(Point[] points) {
    for (Point p : points) {
      p.draw();
    }
  }
  
  /**
   * Draw the lines represented by the given segment
   * @param segment a set of points representing a segment
   */
  private static void drawSegments(Point[] segment) {
    segment[0].drawTo(segment[segment.length-1]);
  }

  public static void main(String[] args) {
    
  }

}
