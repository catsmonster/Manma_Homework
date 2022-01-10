public class LinkedList {
    protected Node head;
    protected int size;

    public LinkedList() {
        this.head = null;
        this.size = 0;
    }

    public LinkedList(int value) {
        this.head = new Node(value);
        this.size = 1;
    }

    public Node getHead() {
        return this.head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public int getSize() {
        return this.size;
    }

    public void insertToStart(Node node) {
        node.setNext(this.head);
        this.head = node;
        this.size++;
    }

    public void deleteFromStart() {
        if (this.size == 1) {
            this.head = null;
            this.size = 0;
        }
        else {
            this.head = this.head.getNext();
            this.size--;
        }
    }

    public int getMin() {
        Node curr = this.head;
        int min = curr.getValue();
        while (curr != null) {
            if (min > curr.getValue())
                min = curr.getValue();
            curr = curr.getNext();
        }
        return min;
    }

    public void removeMin() {
        Node curr = this.head;
        Node prevToMin = curr;
        int min = this.head.getValue();
        while (curr.getNext() != null) {
            if (min > curr.getNext().getValue()) {
                min = curr.getNext().getValue();
                prevToMin = curr;
            }
            curr = curr.getNext();
        }
        if (prevToMin.getValue() == min && prevToMin == this.head)
            removeFromStart();
        else
            removeNext(prevToMin);

    }

    protected void removeFromStart() {
        this.head = this.head.getNext();
    }

    protected void removeNext(Node prev) {
        if (prev != null && prev.getNext() != null && prev.getNext().getNext() != null) {
            prev.setNext(prev.getNext().getNext());
        }
        else if (prev != null && prev.getNext() != null) {
            prev.setNext(null);
        }
    }
}
