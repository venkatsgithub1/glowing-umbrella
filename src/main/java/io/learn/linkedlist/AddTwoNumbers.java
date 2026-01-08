package io.learn.linkedlist;

public class AddTwoNumbers {
    public static void main(String[] args) {

    }

    private static ListNode add(ListNode node1, ListNode node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }

        ListNode current1 = node1;
        ListNode current2 = node2;
        ListNode sentinel = new ListNode(-1);
        ListNode res = null;
        int carry = 0;

        while (current1 != null && current2 != null) {
            int sum = carry + current1.val + current2.val;
            int num = sum % 10;
            carry = sum / 10;
            if (res == null) {
                res = new ListNode(num);
                sentinel.next = res;
            } else {
                ListNode currentHead = sentinel.next;
                sentinel.next = new ListNode(num);
                sentinel.next.next = currentHead;
            }
            current1 = current1.next;
            current2 = current2.next;
        }

        while (current1 != null) {
            int sum = carry + current1.val;
            int num = sum % 10;
            carry = sum / 10;
            ListNode currentHead = sentinel.next;
            sentinel.next = new ListNode(num);
            sentinel.next.next = currentHead;
            current1 = current1.next;
        }
        while (current2 != null) {
            int sum = carry + current2.val;
            int num = sum % 10;
            carry = sum / 10;
            ListNode currentHead = sentinel.next;
            sentinel.next = new ListNode(num);
            sentinel.next.next = currentHead;
            current2 = current2.next;
        }

        if (carry > 0) {
            ListNode currentHead = sentinel.next;
            sentinel.next = new ListNode(carry);
            sentinel.next.next = currentHead;
        }
        return sentinel.next;
    }
}
