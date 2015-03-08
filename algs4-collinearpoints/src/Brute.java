
public class Brute {
  
  public static void main(String[] args) {
    if (args.length != 1) throw new java.lang.IllegalArgumentException();
       
    // Read in all the Points from the input file into an array
    In input = new In(args[0]);
    Point[] points = new Point[input.readInt()];
    
    for (int i = 0; i < points.length; i++) {
      points[i] = new Point(input.readInt(), input.readInt());
    }
  }
}
