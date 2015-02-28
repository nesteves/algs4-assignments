import java.util.Iterator;


public class RandomizedQueue<Item> implements Iterable<Item> {
  
  private int totalItems;
  
  public RandomizedQueue() {
    
    totalItems = 0;
  }
  
  public boolean isEmpty() {
    return totalItems == 0;
  }
  
  public int size() {
    return totalItems;
  }
  
  public void enqueue(Item item) {
    
    totalItems++;
  }
  
  public Item dequeue() {
    totalItems--;
    
    return null;
  }
  
  public Item sample() {
    return null;
  }
  
  public Iterator<Item> iterator() {
    return null;
  }
  
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
