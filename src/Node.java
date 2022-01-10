public class Node {
    private int value;
    private Node next;

    public Node() {
        this.value = Integer.MAX_VALUE;
        this.next = null;
    }

    public Node(int input) {
        this.value = input;
        this.next = null;
    }


    public int getValue() {
        return this.value;
    }

    public Node getNext() {
        return this.next;
    }

    public void setNext(Node given) {
        this.next = given;
    }

    public void setValue(int input) {
        this.value = input;
    }
}
