package io.learn.linkedlist;

public class ReorderList {
    public static void main(String[] args) {

    }

    private static void reorderList(ListNode node) {
        if (node == null) {
            return;
        }

        ListNode slow = node;
        ListNode fast = node;
        ListNode prev = null;
        while (slow != null && fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        reverse(slow);
    }

    private static ListNode reverse(ListNode node) {
        if (node.next == null) {
            return node;
        }

        ListNode next = node.next;
        ListNode newHead = reverse(next);
        node.next = null;
        next.next=node;
        return newHead;
    }
}
