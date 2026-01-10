package io.learn.lists.linkedlist;

import java.util.List;

public class LinkedListCycle {
    public static void main(String[] args) {
        assert !hasCycle(ListNode.buildLinkedList(List.of(1, 2, 4, 5))) : "Got incorrect result";
        ListNode root = ListNode.buildLinkedList(List.of(1,2,3,4,5));
        ListNode current = root;
        while (current.next != null) {
            current = current.next;
        }
        current.next = root;
        assert hasCycle(root) : "Got incorrect result";
    }

   private static boolean hasCycle(ListNode head) {
       if (head == null) {
           return false;
       }

       // 1-2-4-5-1(cycle)
       // slow 1-2, fast 1-2-4
       // slow 2-4 fast 4-5-1
       // slow 4-5 fast 1-2-4
       // slow 5-1 fast 4-5-1

       ListNode slow = head;
       ListNode fast = head;

       while (slow != null && fast != null && fast.next != null) {
           slow = slow.next;
           fast = fast.next.next;
           if (slow == fast) {
               return true;
           }
       }
       return false;
   }
}
