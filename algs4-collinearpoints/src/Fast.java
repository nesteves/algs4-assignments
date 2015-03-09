import java.util.Arrays;
import java.util.LinkedList;

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
    if (args.length != 1) throw new java.lang.IllegalArgumentException();
    
    LinkedList<Point[]> segments = new LinkedList<Point[]>();
       
    // Read in all the Points from the input file into an array
    In input = new In(args[0]);
    Point[] points = new Point[input.readInt()];
    Point[] sortedPoints = new Point[points.length];
    
    for (int i = 0; i < points.length; i++) {
      points[i] = new Point(input.readInt(), input.readInt());
      sortedPoints[i] = points[i]; // Point is immutable, and we only need the reference
    }
    
    for (Point p : points) {
      Arrays.sort(sortedPoints, p.SLOPE_ORDER);
      //System.out.println("The slope ordered array: " + Arrays.toString(sortedPoints));
      
      // find the longest segment in the sortedPoints array
      int s = 0; // start point
      int l = 0; // length
      int sMax = 0;
      int lMax = 0;
      
      //System.out.println("Origin point: " + p + ".");
      
      for (int i = 0; i < sortedPoints.length; i++) {
        
        if (i == s || p.slopeTo(sortedPoints[s]) == p.slopeTo(sortedPoints[i])) {
          l++;
          //System.out.println("Comparing " + p + " and " + sortedPoints[s] + " and " + sortedPoints[i] + ". (" + s + ", " + i + ").");
        }
        else {
          if (l >= lMax) {
            sMax = s;
            lMax = l;
          }
          s = i;
          l = 1;
        }
      }
      // Put the points into an array
      if (lMax >= 2) {
        //System.out.println("Start point: " + sMax + ". Length of semgent: " + lMax + ".");
        Point[] segment = new Point[lMax + 1];
        for (int i = sMax; i < sMax + lMax; i++) {
          segment[i - sMax] = sortedPoints[i];
        }
        segment[lMax] = p;
        Arrays.sort(segment);
        segments.add(segment);
        //System.out.println("The result is " + Arrays.toString(segment));
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
