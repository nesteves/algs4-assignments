import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
  private static String stringSegment(ArrayList<Point> segment) {
    StringBuilder s = new StringBuilder();
    
    for (int i = 0; i < segment.size() - 1; i++) {
      s.append(segment.get(i));
      s.append(" -> ");
    }
    
    s.append(segment.get(segment.size() - 1));
    
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
  private static void drawSegments(ArrayList<Point> segment) {
    segment.get(0).drawTo(segment.get(segment.size() - 1));
  }
  
  /**
   * Creates a segment of points sorted by natural order
   * based on a given list of points ordered by their slopes
   * relative to a first point
   * @param startIndex index at which to start drawing points from
   *    pointsArray
   * @param endIndex index at which to stop drawing points from
   *    pointsArray
   * @param firstPoint point relative to which the slope of the points
   *    in pointsArray is calculated
   * @param pointsArray array of points ordered by the slope
   *    they make agains firstPoint
   * @return a list of the points from pointsArray[startIndex] up to but
   *    not including pointsArray[endIndex] plus firstPoint, all sorted
   *    by their natural order
   */
  private static ArrayList<Point> getSegment(int startIndex, int endIndex, Point firstPoint, Point[] pointsArray) {
    ArrayList<Point> segment = new ArrayList<Point>(endIndex - startIndex + 1);
    segment.add(firstPoint);
    
    for (int i = startIndex; i < endIndex; i++) {
      segment.add(pointsArray[i]);
    }
    
    Collections.sort(segment);
    
    return segment;
  }

  public static void main(String[] args) {
    if (args.length != 1) throw new java.lang.IllegalArgumentException();
    
    Set<ArrayList<Point>> segments = new HashSet<ArrayList<Point>>();
       
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
      int s = 1; // start point (The point itself is always placed at the start of the array)
      int e = 1; // end point
      while (e < sortedPoints.length) {
        if (p.slopeTo(sortedPoints[s]) == p.slopeTo(sortedPoints[e])) {
          e++;
        }
        else {
          if (e - s > 2) {
            segments.add(getSegment(s, e, p, sortedPoints));
          }
          s = e;
          e++;
        }
      }
      if (e - s > 2) {
        segments.add(getSegment(s, e, p, sortedPoints));
      }
    }
    
    // Set up the grid and draw the points
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    drawPoints(points);
    
    // Print out the collinear segments and draw them
    for (ArrayList<Point> s : segments) {
      System.out.println(stringSegment(s));
      drawSegments(s);
    }
    
  }

}
