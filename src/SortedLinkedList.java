public class SortedLinkedList extends LinkedList {


    /**
     * inserts a node to the correct position, maintaining an ordered list then printing it.
     * O(n) time complexity, best case is O(1), average is O(n).
     * @param node given node to be added to list
     */
    public void insertNode(Node node) {
        if (node != null) {
            if (node.getValue() <= this.min)
                super.insertNode(node);
            else {
                Node curr = this.head;
                while (curr.getNext() != null && node.getValue() > curr.getNext().getValue()) {
                    curr = curr.getNext();
                }
                addAfterNode(curr, node);
                System.out.println(this);
            }
        }
    }

    /*
     * inserts a given node after a specified node in the list, updates the tail if necessary. O(1) time complexity.
     * @param prev node previous to the new one to be added
     * @param given node to be added
     */
    private void addAfterNode(Node prev, Node given) {
        if (prev != null && given != null) {
            if (prev.getNext() != null) {
                Node temp = prev.getNext();
                if (this.tail.getValue() < temp.getValue())
                    this.tail = temp;
                given.setNext(temp);
            }
            prev.setNext(given);
            if (given.getNext() == null)
                this.tail = given;
        }
    }

    /**
     * removes the item with the minimum value from the list and updates the minimum, then prints the list.
     * since this is an ordered list, removes the first node. O(1) time complexity
     */
    public void removeMin() {
        if (this.head != null) {
            this.head = this.head.getNext();
            if (this.head != null)
                this.min = this.head.getValue();
            else {
                this.min = Integer.MAX_VALUE;
                this.tail = null;
            }
            System.out.println("Minimum extracted, the list is now: " + this);
        }
        else
            System.out.println("The heap is already empty.");
    }

    /**
     * unites two linked lists together, if the lists are foreign, adds the lesser value head to the greater value tail
     * with O(1) time complexity, if the lists aren't foreign, merges the two lists with O(n) or O(m) (depends on max{n, m})
     * the worst and average case scenarios are O(n) or O(m) time complexity.
     * additionally, updates the minimum and tail if necessary, then prints the united list.
     * @param other given list to unite with current list
     */
    public void uniteList(LinkedList other) {
        if (other.head != null && this.head != null && other.tail.getValue() < this.head.getValue()) {
            other.tail.setNext(this.head);
            this.head = other.head;
            this.min = this.head.getValue();
        } else if (other.head != null && this.head != null && other.head.getValue() > this.tail.getValue()) {
            this.tail.setNext(other.head);
            this.tail = other.tail;
        } else if (other.head != null && this.head != null) {
            Node curr = this.head;
            this.head = merge(curr, other.head);
            if (other.tail.getValue() > this.tail.getValue())
                this.tail = other.tail;
            this.min = this.head.getValue();
        } else if (other.head != null) {
            this.head = other.head;
            this.tail = other.tail;
            this.min = other.min;
        }
        System.out.println("United list is: " + this);
    }

    /*
    merges two ordered linked lists together with a time complexity of O(n) or O(m) (depending on m>n or vice versa).
     */
    private Node merge(Node left, Node right) {
        Node temp = new Node();
        Node curr = temp;
        while (left != null && right != null) {
            if (left.getValue() < right.getValue()) {
                curr.setNext(left);
                left = left.getNext();
            } else {
                curr.setNext(right);
                right = right.getNext();
            }
            curr = curr.getNext();
        }
        if (left != null) {
            curr.setNext(left);
        }
        if (right != null) {
            curr.setNext(right);
        }
        return temp.getNext();
    }
}
