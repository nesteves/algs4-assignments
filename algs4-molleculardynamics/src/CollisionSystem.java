
/**
 * Implements a system that simulates
 * elastic collisions within a group of
 * Particles.
 */
public class CollisionSystem {
  
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
}
