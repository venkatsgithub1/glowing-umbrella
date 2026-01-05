package io.learn.linkedlist;

import java.util.List;

public class RemoveNthFromLast {
    public static void main(String[] args) {
        assert remove(ListNode.buildLinkedList(List.of(1,2,3,4,5)), 2).toString().equals("1-2-3-5"): "Wrong result";
        assert remove(ListNode.buildLinkedList(List.of(1,2,3,4,5)), 3).toString().equals("1-2-4-5"): "Wrong result";
    }

    private static ListNode remove(ListNode node, int n) {
        if (node == null) {
            return null;
        }

        ListNode pointer1 = node;
        ListNode pointer2 = node;
        ListNode prev = null;

        while (n-- > 0) {
            pointer1 = pointer1.next;
        }

        while (pointer1 != null && pointer2 != null) {
            pointer1 = pointer1.next;
            prev = pointer2;
            pointer2 = pointer2.next;
        }

        if (prev == null) {
            if (node.next != null) {
                return node.next;
            }
            return null;
        }

        if (prev.next == null) {
            return null;
        }
        prev.next = pointer2.next;
        return node;
    }
}
