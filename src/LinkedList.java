public class LinkedList {
    protected Node head;
    protected Node tail;
    protected int min;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.min = Integer.MAX_VALUE;
    }

    /**
     * adds item to the front of the list and prints the list. O(1) time complexity.
     * note that the tail is only set once to the head node, so as items are added, it correctly keeps the tail position.
     * @param node given node to be added to list
     */
    public void insertNode(Node node) {
        node.setNext(this.head);
        this.head = node;
        if (this.head.getNext() == null)
            this.tail = this.head;
        if (node.getValue() < this.min) {
            this.min = node.getValue();
        }
        System.out.println(this);
    }

    /**
     * returns the minimum value of the list in O(1)
     * @return minimum value in the list
     */
    public int getMin() {
        return this.min;
    }

    /**
     * removes the item with the minimum value from the list, updates the next minimum value. O(n) time complexity.
     */
    public void removeMin() {
        if (this.head != null) {
            Node curr = this.head;
            Node prevToMin = curr;
            int nextMin = Integer.MAX_VALUE;
            while (curr.getNext() != null) {
                if (nextMin > curr.getValue() && curr.getValue() != this.min) {
                    nextMin = curr.getValue();
                } else if (nextMin > curr.getNext().getValue() && curr.getNext().getValue() != this.min)
                    nextMin = curr.getNext().getValue();
                if (curr.getNext().getValue() == this.min)
                    prevToMin = curr;
                curr = curr.getNext();
            }
            if (prevToMin == this.head && prevToMin.getValue() == this.min) {
                removeFromStart();
            } else {
                removeNext(prevToMin);
            }
            this.min = nextMin;
            System.out.println(this);
        } else
            System.out.println("The heap is already empty.");
    }

    /*
    removes the first item from the list in O(1) time complexity, and updates the tail node if necessary.
     */
    private void removeFromStart() {
        this.head = this.head.getNext();
        if (this.head == null)
            this.tail = null;
    }

    private void removeNext(Node prev) {
        if (prev != null && prev.getNext() != null && prev.getNext().getNext() != null) {
            prev.setNext(prev.getNext().getNext());
        }
        else if (prev != null && prev.getNext() != null) {
            prev.setNext(null);
            this.tail = prev;
        }
    }

    public String toString() {
        Node curr = this.head;
        String s = "[";
        while (curr != null && curr.getNext() != null) {
            s = s.concat(curr.getValue() + ", ");
            curr = curr.getNext();
        }
        s = curr == null ? s.concat("]") : s.concat(curr.getValue() + "]");
        return s;
    }

    public void uniteList(LinkedList other) {
        if (other.head != null && this.tail != null && this.tail != other.tail) {
            this.tail.setNext(other.head);
            this.tail = other.tail;
            this.min = Math.min(this.min, other.min);
        } else if (other.head != null && this.tail != other.tail) {
            this.head = other.head;
            this.tail = other.tail;
            this.min = Math.min(this.min, other.min);
        }
        System.out.println(this);
    }

    protected void addAfterNode(Node prev, Node given) {
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
}
