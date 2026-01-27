package io.learn.lists.linkedlist;

import java.util.List;

public class ReverseNodesInKGroup {
    public static void main(String[] args) {
        assert reverseInKGroup(ListNode.buildLinkedList(List.of(1, 2, 3, 4, 5)), 2).toString().equals("2-1-4-3-5") : "Wrong result";
    }

    private static ListNode reverseInKGroup(ListNode node, int k) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode sentinel = new ListNode(-1);
        sentinel.next = node;

        ListNode revStart = node;
        ListNode revStartPrev = sentinel;
        ListNode revEnd = null;
        int counter = 0;

        ListNode current = node;
        while (current != null) {
            counter++;
            if (counter == k) {
                revEnd = current;
                revStartPrev.next = null;
                ListNode revEndNext = revEnd.next;
                revEnd.next = null;

                ListNode reversedNode = reverse(revStart);
                revStartPrev.next = reversedNode;
                revStart.next = revEndNext;

                revStartPrev = revStart;
                revStart = revStart.next;
                counter = 0;
                current = revStart;
            } else {
                current = current.next;
            }
        }
        return sentinel.next;
    }

    private static ListNode reverse(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode nextNode = node.next;
        node.next = null;
        ListNode newHead = reverse(nextNode);
        nextNode.next = node;
        return newHead;
    }
}
