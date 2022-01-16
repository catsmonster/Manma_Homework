import java.util.HashSet;
import java.util.Set;

public class ForeignLinkedList extends LinkedList{
    /*
    the global set holds all the numbers added so far in up to 2 lists.
    the current set holds the numbers added in the current list
     */
    private static Set<Integer> globalSet = new HashSet<>();
    private static Set<Integer> currentSet = new HashSet<>();
    private static short numOfLists = 0;

    /**
     * constructs a foreign linked list, together with a hashtable as a tool to keep track of whether a number was added to the list.
     * if more than 1 list already exists, the global set needs to be refreshed, so that items that are no longer relevant
     * to the union function don't influence the new list. Time complexity is O(1).
     */
    public ForeignLinkedList() {
        super();
        if (numOfLists < 2) {
            numOfLists++;
        } else {
            globalSet = currentSet;
            numOfLists = 1;
        }
        currentSet = new HashSet<>();
    }

    /**
     * allows inserting items that either don't belong to the global set, or they originate from the current list
     * (in which case no restrictions apply and the item is added to the start of the list with time complexity of O(1)).
     * if the new item to be added already exists in a previous list, it won't be added to the current list (as long as
     * the previous list is relevant to the union function). In rare cases, the time complexity will depend on the size
     * of the set- due to the set reallocating memory to accommodate more items (as its size is flexible - and handled by
     * the Java.util.Set library).
     * in the worst case, time complexity is O(n) (n being the size of the set), in the average, and best cases,
     * the time complexity will be O(1).
     * @param node given node to be added to list
     */
    public void insertNode(Node node) {
//        long start = System.nanoTime();
        if (!globalSet.contains(node.getValue()) || currentSet.contains(node.getValue())) {
            currentSet.add(node.getValue());
            globalSet.add(node.getValue());
            super.insertNode(node);
//            long end = System.nanoTime();
//            System.out.println("Time it took to insert a new node in nanoseconds: " + (end - start));
        }
        else {
            System.out.println("The number " + node.getValue() + " cannot be added, as it already exists in another list");
        }
    }

    /**
     * unites two lists, as the lists are foreign, connects the head of one to the tail of the other with a time complexity
     * of O(1). updates the number of lists back to 1.
     * @param other given list to unite with current list
     */
    public void uniteList(LinkedList other) {
        super.uniteList(other);
        numOfLists = 1;
    }

    /**
     * removes the item with the minimum value from the list, and from the global and local sets. Time complexity is
     * similar to that of the original removeMin function defined in the LinkedList parent class.
     */
    public void removeMin() {
//        long start = System.nanoTime();
        globalSet.remove(this.min);
        currentSet.remove(this.min);
//        long end = System.nanoTime();
//        System.out.println("Time it took for the minimum to be removed from the set in nanoseconds: " + (end - start));
        super.removeMin();
    }

}