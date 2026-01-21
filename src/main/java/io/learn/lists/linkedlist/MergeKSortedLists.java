package io.learn.lists.linkedlist;

import java.util.List;

public class MergeKSortedLists {
    public static void main(String[] args) {
        ListNode result = merge(new ListNode[]{ListNode.buildLinkedList(List.of(1, 4, 5)), ListNode.buildLinkedList(List.of(1, 3, 4)), ListNode.buildLinkedList(List.of(2, 6))});
        assert result != null && result.toString().equals("1-1-2-3-4-4-5-6") : "Wrong result";
        result = merge((new ListNode[]{ListNode.buildLinkedList(List.of(1, 2, 3)), ListNode.buildLinkedList(List.of(4, 5, 6))}));
        assert result != null && result.toString().equals("1-2-3-4-5-6") : "Wrong result";
    }

    private static ListNode merge(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        if (lists.length == 1) {
            return lists[0];
        }

        ListNode parentList = lists[0];

        for (int i = 1; i < lists.length; i++) {
            parentList = mergeLists(parentList, lists[i]);
        }
        return parentList;
    }

    private static ListNode mergeLists(ListNode list1, ListNode list2) {
        ListNode sentinel = new ListNode(-1);
        sentinel.next = list1;
        ListNode current1 = list1;
        ListNode current2 = list2;
        ListNode prev = sentinel;

        while (current1 != null && current2 != null) {
            if (current2.val <= current1.val) {
                ListNode current2Next = current2.next;
                current2.next = null;

                ListNode prevNext = prev.next;
                prev.next = null;

                prev.next = current2;
                prev.next.next = prevNext;

                current1 = prev.next;
                current2 = current2Next;
            } else {
                prev = current1;
                current1 = current1.next;
            }
        }

        current1 = prev;

        while (current2 != null) {
            ListNode current2Next = current2.next;
            current2.next = null;

            current1.next = current2;
            current1 = current1.next;
            current2 = current2Next;
        }
        ListNode result = sentinel.next;
        sentinel.next = null;
        return result;
    }

}
