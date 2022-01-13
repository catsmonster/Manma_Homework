import java.util.HashSet;
import java.util.Set;

public class ForeignLinkedList extends LinkedList{

    public void uniteList(LinkedList other) {
        Set<Integer> set = new HashSet<>();
        populateSet(set);
        this.min = Math.min(other.min, this.min);
        Node curr = other.head;
        while (curr != null) {
            if (!set.contains(curr.getValue())) {
                this.tail.setNext(curr);
                this.tail = curr;
            }
            curr = curr.getNext();
        }
        System.out.println(this);
    }

    private void populateSet(Set<Integer> set) {
        Node curr = this.head;
        while (curr != null) {
            set.add(curr.getValue());
            curr = curr.getNext();
        }
    }

}