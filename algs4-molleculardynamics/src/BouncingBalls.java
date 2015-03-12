/**
 * Class used to simulate a set of 2-dimensional discs
 * floating around, ignoring collisions with themselves
 */
public class BouncingBalls {
  
  public static void main(String args[]) {
    if (args.length > 1) { throw new IllegalArgumentException("The function requires one numeric argument."); }
    
    int n = Integer.parseInt(args[0]);
    
    Ball[] balls = new Ball[n];
    
    for (int i = 0; i < n; i++) {
      balls[i] = new Ball();
    }
    
    while (true) {
      StdDraw.clear();
      
      for (int i = 0; i < n; i++) {
        balls[i].move(0.5);
        balls[i].draw();
      }
      
      StdDraw.show(50);
    }
  }
}
