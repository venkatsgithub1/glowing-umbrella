package io.learn.lists.linkedlist;

import io.learn.lists.linkedlist.random.Node;

import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
    public static void main(String[] args) {
        Node root = new Node(1);
        Node node1 = new Node(2);
        root.random = node1;
        node1.random = node1;

        Node copy = copyList(root);
        assert !(copy == root) : "Copy is not correct";
    }

    private static Node copyList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node current = head;
        while (current != null) {
            map.put(current, new Node(current.val));
            current = current.next;
        }

        Node sentinel = new Node(-1);
        current = head;
        while (current != null) {
            Node newNode = map.get(current);
            newNode.next = map.get(current.next);
            if (sentinel.next == null) {
                sentinel.next = newNode;
            }
            newNode.random = map.get(current.random);
            current = current.next;
        }
        return sentinel.next;
    }
}
