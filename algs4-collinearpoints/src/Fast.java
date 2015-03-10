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
        ArrayList<Point> segment = new ArrayList<Point>();
        for (int i = sMax; i < sMax + lMax; i++) {
          segment.add(sortedPoints[i]);
        }
        segment.add(p);
        Collections.sort(segment);
        segments.add(segment);
        //System.out.println("The result is " + Arrays.toString(segment));
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
