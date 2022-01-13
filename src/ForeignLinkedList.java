import java.util.HashSet;
import java.util.Set;

public class ForeignLinkedList extends LinkedList{

    private final static Set<Integer> globalSet = new HashSet<>();
    private static short numOfLists = 0;
    private final Set<Integer> localSet;

    public ForeignLinkedList() {
        super();
        this.localSet = new HashSet<>();
        if (numOfLists < 2) {
            numOfLists++;
        } else {
            globalSet.clear();
            numOfLists = 1;
        }
    }

    public void insertNode(Node node) {
        if (!globalSet.contains(node.getValue()) || this.localSet.contains(node.getValue())) {
            this.localSet.add(node.getValue());
            globalSet.add(node.getValue());
            super.insertNode(node);
        }
        else {
            System.out.println("This number cannot be added, as it already exists in another list");
        }
    }

    public void uniteList(LinkedList other) {
        super.uniteList(other);
        numOfLists = 1;
    }

    public void removeMin() {
        globalSet.remove(this.min);
        localSet.remove(this.min);
        super.removeMin();
    }

}