
public class Subset {
  
  public static void main(String[] args) {
    if (args.length != 1) throw new java.lang.IllegalArgumentException("This command expects a single integer argument.");
    
    int k = Integer.parseInt(args[0]);
    
    RandomizedQueue<String> allItems = new RandomizedQueue<String>();
    while (!StdIn.isEmpty()) {
      allItems.enqueue(StdIn.readString());
    }
    
    for (int i = 0; i < k; i++) {
      System.out.println(allItems.dequeue());
    }
  }
}
