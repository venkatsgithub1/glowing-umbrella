package io.learn.lists.linkedlist;

import java.util.List;

public class MergeTwoSortedLists {
    public static void main(String[] args) {
        assert merge(ListNode.buildLinkedList(List.of(1, 2, 4)), ListNode.buildLinkedList(List.of(1, 3, 4))).toString().equals("1-1-2-3-4-4") : "Wrong merge";
        assert merge(ListNode.buildLinkedList(List.of(5)), ListNode.buildLinkedList(List.of(1, 3, 4))).toString().equals("1-3-4-5") : "Wrong merge";
    }

    private static ListNode merge(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        ListNode sentinel = new ListNode(-1);
        sentinel.next = node1;
        ListNode current1 = node1;
        ListNode current2 = node2;
        ListNode prev = sentinel;

        while (current1 != null && current2 != null) {
            if (current2.val < current1.val) {
                ListNode current2Next = current2.next;
                ListNode prevNext = prev.next;

                current2.next = null;
                prev.next = current2;
                prev.next.next = prevNext;
                prev = prev.next;

                current2 = current2Next;
            } else {
                prev = current1;
                current1 = current1.next;
            }
        }

        while (current2 != null) {
            prev.next = current2;
            current2 = current2.next;
            prev = prev.next;
        }
        System.out.println(sentinel.next);

        return sentinel.next;
    }
}
