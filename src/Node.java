public class Node {
    private final int value;
    private Node next;

    public Node() {
        this.value = Integer.MAX_VALUE;
        this.next = null;
    }

    public Node(int input) {
        this.value = input;
        this.next = null;
    }

    /**
     *
     * @return value of node
     */
    public int getValue() {
        return this.value;
    }

    /**
     *
     * @return next linked node
     */
    public Node getNext() {
        return this.next;
    }

    /**
     * sets the next node to a given node
     * @param given node to be set as the next one
     */
    public void setNext(Node given) {
        this.next = given;
    }

}
