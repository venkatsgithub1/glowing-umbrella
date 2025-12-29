package io.learn.linkedlist;

import java.util.List;

public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode root = ListNode.buildLinkedList(List.of(1, 2, 3, 4, 5));
        // 1-2-3-4-5
        ListNode newRoot = reverse(root);
        assert newRoot.toString().equals("5-4-3-2-1") : "Got incorrect reversal";
    }

    private static ListNode reverse(ListNode node) {
        if (node.next == null) {
            return node;
        }

        ListNode nextNode = node.next;
        ListNode newHead = reverse(nextNode);
        node.next = null;
        nextNode.next = node;
        return newHead;
    }
}
