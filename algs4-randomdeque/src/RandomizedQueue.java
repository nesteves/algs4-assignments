import java.util.Iterator;

/**
 * 
 * @author nunoe
 * Implements a queue that returns elements in a random order.
 * 
 * @param <Item> generic implementation, accepts any type of object.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
  
  private Item[] q;
  private int totalItems;
  
  /**
   * Initialize the RandomizedQueue.
   */
  @SuppressWarnings("unchecked") // suppresses the warning on the unchecked cast from Object to <Item>
  public RandomizedQueue() {
    q = (Item[]) new Object[1];
    totalItems = 0;
  }
  
  /**
   * Method used to ask whether the queue is empty.
   * @return true if it is empty, false otherwise.
   */
  public boolean isEmpty() {
    return totalItems == 0;
  }
  
  /**
   * Method used to get the current size of the queue.
   * @return The current size of the queue.
   */
  public int size() {
    return totalItems;
  }
  
  /**
   * Method used to add an item to the queue.
   * @param item Item to be added to the queue.
   */
  public void enqueue(Item item) {
    if (item == null) throw new java.lang.NullPointerException();
    
    totalItems++;
  }
  
  /**
   * Method used to remove and return a random item from
   * the queue.
   * @return a random item contained in the queue. All
   *    calls are independent.
   */
  public Item dequeue() {
    if (totalItems == 0) throw new java.util.NoSuchElementException();
    
    totalItems--;
    
    return null;
  }
  
  /**
   * Method used to get a random item from the queue.
   * @return a random item contained in the queue. All calls are
   *    independent - the same item can be returned twice in a
   *    row.
   */
  public Item sample() {
    if (totalItems == 0) throw new java.util.NoSuchElementException();
    
    return null;
  }
  
  public Iterator<Item> iterator() {
    return null;
  }
  
  /** 
   * Basic tests for the class ensure the API works correctly.
   * @param args command-line arguments are not used.
   */
  public static void main(String[] args) {
    
    RandomizedQueue<Integer> testRQueue = new RandomizedQueue<Integer>();
    
    System.out.println("Is the queue empty? " + testRQueue.isEmpty());
    testRQueue.enqueue(1);
    testRQueue.enqueue(2);
    System.out.println("Queue size after adding 2 items: " + testRQueue.size());
    System.out.println("Is the queue empty? " + testRQueue.isEmpty());
    testRQueue.dequeue();
    System.out.println("Queue size after removing 1 item: " + testRQueue.size());
    testRQueue .dequeue();
    System.out.println("Is the queue empty after removing the last item? " + testRQueue.isEmpty());
    
  }
  
  
}
