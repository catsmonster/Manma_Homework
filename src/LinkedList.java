public class LinkedList {
    protected Node head;
    protected Node tail;
    protected int size;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public LinkedList(int value) {
        this.head = new Node(value);
        this.tail = this.head;
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
        if (this.head.getNext() == null)
            this.tail = this.head;
        this.size++;
        System.out.println(this);
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
        System.out.println(this);
    }

    protected void removeFromStart() {
        this.head = this.head.getNext();
        this.size--;
        if (this.head == null)
            this.tail = null;
    }

    protected void removeNext(Node prev) {
        if (prev != null && prev.getNext() != null && prev.getNext().getNext() != null) {
            prev.setNext(prev.getNext().getNext());
            this.size--;
        }
        else if (prev != null && prev.getNext() != null) {
            prev.setNext(null);
            this.size--;
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
            this.size = this.size + other.size;
        } else if (other.head != null && this.tail != other.tail) {
            this.head = other.head;
            this.tail = other.tail;
            this.size = other.size;
        }
        System.out.println(this);
    }
}
