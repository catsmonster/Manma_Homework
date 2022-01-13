public class LinkedList {
    protected Node head;
    protected Node tail;
    protected int min;

    public LinkedList() {
        this.head = null;
        this.tail = null;
        this.min = Integer.MAX_VALUE;
    }

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

    public int getMin() {
        return this.min;
    }

    public void removeMin() {
        if (this.head != null) {
            Node curr = this.head;
            Node prevToMin = curr;
            int min = this.head.getValue();
            int nextMin = min;
            while (curr.getNext() != null) {
                if (min > curr.getNext().getValue()) {
                    min = curr.getNext().getValue();
                    prevToMin = curr;
                }
                if (nextMin > curr.getNext().getValue() && nextMin != min)
                    nextMin = curr.getNext().getValue();
                curr = curr.getNext();
            }
            if (prevToMin.getValue() == min && prevToMin == this.head) {
                removeFromStart();
                if (nextMin > this.head.getValue())
                    this.min = this.head.getValue();
                else
                    this.min = nextMin;
            } else {
                removeNext(prevToMin);
                this.min = nextMin;
            }
            System.out.println(this);
        } else
            System.out.println("The heap is already empty.");
    }

    protected int findMin() {
        int min = Integer.MAX_VALUE;
        Node curr = this.head;
        if (this.head != null){
            min = curr.getValue();
            while (curr != null) {
                if (min > curr.getValue())
                    min = curr.getValue();
                curr = curr.getNext();
            }
        }
        return min;
    }

    protected void removeFromStart() {
        this.head = this.head.getNext();
        if (this.head == null)
            this.tail = null;
    }

    protected void removeNext(Node prev) {
        if (prev != null && prev.getNext() != null && prev.getNext().getNext() != null) {
            prev.setNext(prev.getNext().getNext());
        }
        else if (prev != null && prev.getNext() != null) {
            prev.setNext(null);
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
