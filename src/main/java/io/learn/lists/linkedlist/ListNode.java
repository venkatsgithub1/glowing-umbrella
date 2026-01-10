package io.learn.lists.linkedlist;

import java.util.List;

public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode buildLinkedList(List<Integer> intList) {
        ListNode root = new ListNode();
        if (!intList.isEmpty()) {
            root.val = intList.get(0);
            ListNode current = root;
            for (int i = 1; i < intList.size(); i++) {
                current.next = new ListNode(intList.get(i));
                current = current.next;
            }
        }
        return root;
    }

    @Override
    public String toString() {
        ListNode current = this;
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
