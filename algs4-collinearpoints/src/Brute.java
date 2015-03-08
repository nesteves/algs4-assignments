
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Class used to find collinear points
 */
public class Brute {
  
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
    if (args.length != 1) throw new java.lang.IllegalArgumentException();
    
    LinkedList<Point[]> segments = new LinkedList<Point[]>();
       
    // Read in all the Points from the input file into an array
    In input = new In(args[0]);
    Point[] points = new Point[input.readInt()];
    
    for (int i = 0; i < points.length; i++) {
      points[i] = new Point(input.readInt(), input.readInt());
    }
    
    // Find all sets of 4 collinear points in the array
    for (int p = 0; p < points.length - 3; p++) {
      
      for (int q = p + 1; q < points.length - 2; q++) {
        
        for (int r = q + 1; r < points.length - 1; r++) {
          
          if (points[p].SLOPE_ORDER.compare(points[q], points[r]) == 0) {
            
            for (int s = r + 1; s < points.length; s++) {
              
              if (points[p].SLOPE_ORDER.compare(points[q], points[s]) == 0) {
                Point[] segment = new Point[]{points[p], points[q], points[r], points[s]};
                Arrays.sort(segment);
                segments.add(segment);
              }
            }
          }
        }
      }
    }
    
    // Set up the grid and draw the points
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    drawPoints(points);
    
    // Print out the collinear segments and draw them
    for (Point[] s : segments) {
      System.out.println(stringSegment(s));
      drawSegments(s);
    }
    
  }
}
