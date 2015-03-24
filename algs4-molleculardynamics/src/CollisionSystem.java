
/**
 * Implements a system that simulates
 * elastic collisions within a group of
 * Particles.
 * 
 * Mostly copied from the implementation found at
 * http://algs4.cs.princeton.edu/61event/CollisionSystem.java.html
 * by Robert Sedgewick and Kevin Wayne
 */
public class CollisionSystem {
  
  private MinPQ<Event> pq;
  private double t = 0.0; // start time
  private double hz = 0.5; // redraw frequency
  private Particle[] particles;
  
  /**
   * Define a new collision system
   * @param particles the given set of particles to use
   * in the collision system
   */
  public CollisionSystem(Particle[] particles) {
    this.particles = particles.clone();
  }
  
  /**
   * Predict collisions with other particles or walls
   * within a time limit
   * @param a the particle for which to predict collisions
   * @param limit distance between the start time and the limit time.
   *  Collisions happening beyond this limit are ignored
   */
  private void predict(Particle a, double limit) {
    
    if (a == null) return;
    
    // predict collisions between 2 particles
    for (int i = 0; i < particles.length; i++) {
      double dt = a.timeToHit(particles[i]);
      
      if (t + dt < limit)
        pq.insert(new Event(t + dt, a, particles[i]));
    }
    
    // predict collisions with walls
    double dtX = a.timeToHitHorizontallWall();
    double dtY = a.timeToHitVerticalWall();
    if (t + dtX < limit)
      pq.insert(new Event(t + dtX, a, null));
    if (t + dtY < limit)
      pq.insert(new Event(t + dtY, null, a));
  }
  
  /**
   * Redraws all the particles
   * @param limit
   */
  private void redraw(double limit) {
    StdDraw.clear();
    
    for (int i = 0; i < particles.length; i++) {
      particles[i].draw();
    }
    
    StdDraw.show(20);
    
    if (t < limit)
      pq.insert(new Event(t + 1.0 / hz, null, null));
  }
  
  public void simulate(double limit) {
    
    pq = new MinPQ<Event>();
    for (int i = 0; i < particles.length; i++) {
        predict(particles[i], limit);
    }
    pq.insert(new Event(0, null, null));
    
    // Main loop
    while (!pq.isEmpty()) {
      Event e = pq.delMin();
      if (!e.isValid()) continue; // discard invalid collisions
      
      Particle a = e.a;
      Particle b = e.b;
      
      for (int i = 0; i < particles.length; i++) {
        particles[i].move(e.time - t);
      }
      t = e.time;
      
      if (a != null && b != null) a.bounceOff(b);
      else if (a != null && b == null) a.bounceOffHorizontalWall();
      else if (a == null && b != null) b.bounceOffVerticallWall();
      else if (a == null && b == null) redraw(limit);
      
      predict(a, limit);
      predict(b, limit);
    }
  }
  
  /**
   * Inner class that defines a possible future collision
   * between a pair of Particle instances or a Particle and
   * a wall.
   */
  private static class Event implements Comparable<Event> {
    
    private final double time;        // time at which the event occurs
    private final Particle a, b;      // particles involved
    private final int countA, countB; // collision counts when the event was created
    
    /**
     * Creates a new event
     * @param t time at which the event occurs
     * @param a the first particle, can be null - representing a vertical wall
     * @param b the second particle, can be null - representing a horizontal wall
     */
    public Event(double t, Particle a, Particle b) {
      this.time = t;
      this.a = a;
      this.b = b;
      
      if (a != null)
        countA = a.count();
      else
        countA = -1;
      
      if (b != null)
        countB = b.count();
      else
        countB = -1;
    }
    
    /**
     * Compare 2 events based on the time each
     * of them will occur
     */
    public int compareTo(Event that) {
      if (this.time > that.time)
        return 1;
      else if (this.time < that.time)
        return -1;
      else
        return 0;
    }
    
    /**
     * Determines whether a collision involving the particles has
     * occurred since the event was created 
     * @return True if no new collisions have occurred, false otherwise
     */
    public boolean isValid() {
      if (a != null && a.count() != countA)
        return false;
      if (b != null && b.count() != countB)
        return false;
      
      return true;
    }
  }
  
  public static void main(String[] args) {
    if (args.length != 1) throw new java.lang.IllegalArgumentException();
    
    StdDraw.setCanvasSize(800, 800);
    StdDraw.show(0);
    
    Particle[] particles;
    int N = Integer.parseInt(args[0]);
    particles = new Particle[N];
    for (int i = 0; i < N; i++)
      particles[i] = new Particle();

    CollisionSystem system = new CollisionSystem(particles);
    system.simulate(10000);
  }
}
