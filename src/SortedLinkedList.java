public class SortedLinkedList extends LinkedList {


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

    public void removeMin() {
        if (this.head != null) {
            this.head = this.head.getNext();
            if (this.head != null)
                this.min = this.head.getValue();
            else {
                this.min = Integer.MAX_VALUE;
                this.tail = null;
            }
            System.out.println(this);
        }
        else
            System.out.println("The heap is already empty.");
    }

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
        } else if (other.head != null) {
            this.head = other.head;
            this.tail = other.tail;
            this.min = other.min;
        }
        System.out.println(this);
    }

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
