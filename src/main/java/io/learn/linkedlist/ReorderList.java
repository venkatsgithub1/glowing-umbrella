package io.learn.linkedlist;

import java.util.List;

public class ReorderList {
    public static void main(String[] args) {
        reorderList(ListNode.buildLinkedList(List.of(1,2,3,4,5,6)));
        reorderList(ListNode.buildLinkedList(List.of(1,2,3,4,5)));
        reorderList(ListNode.buildLinkedList(List.of(1,2,3,4)));
        reorderList(ListNode.buildLinkedList(List.of(1)));
        reorderList(ListNode.buildLinkedList(List.of(1, 2)));
    }

    private static void reorderList(ListNode node) {
        if (node == null || node.next == null) {
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

        prev.next = null;

        slow = reverse(slow);
        prev = null;

        ListNode current = node;
        while (current != null && slow != null) {
            ListNode nextNodeOfCurrent = current.next;
            ListNode nextNodeOfSlow = slow.next;

            slow.next = null;

            current.next = slow;
            current.next.next = nextNodeOfCurrent;

            prev = current.next;
            current = nextNodeOfCurrent;
            slow = nextNodeOfSlow;
        }

        current = prev;

        while (slow != null) {
            ListNode nextNodeOfCurrent = current.next;
            ListNode nextNodeOfSlow = slow.next;

            slow.next = null;

            current.next = slow;
            current.next.next = nextNodeOfCurrent;

            current = nextNodeOfCurrent;
            slow = nextNodeOfSlow;
        }
        System.out.println(node);
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
