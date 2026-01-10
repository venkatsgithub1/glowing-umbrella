package io.learn.lists.linkedlist.random;

public class Node {
    public int val;
    public Node next;
    public Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    @Override
    public String toString() {
        Node current = this;
        StringBuilder repBuilder = new StringBuilder();
        while (current != null) {
            repBuilder.append(current.val);
            if (current.next != null) {
                repBuilder.append("-");
            }
            current = current.next;
        }
        return repBuilder.toString();
    }
    
}
