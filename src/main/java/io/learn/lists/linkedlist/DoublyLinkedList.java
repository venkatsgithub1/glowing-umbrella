package io.learn.lists.linkedlist;

import java.util.Iterator;
import java.util.List;

public class DoublyLinkedList {
    DoublyLinkedList next;
    DoublyLinkedList prev;
    int val;
    int key;

    public DoublyLinkedList(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public static DoublyLinkedList buildFromList(List<int[]> list) {
        if (list.isEmpty()) {
            return null;
        }
        Iterator<int[]> iterator = list.iterator();

        int[] itrNext = iterator.next();
        DoublyLinkedList node = new DoublyLinkedList(itrNext[0], itrNext[1]);
        DoublyLinkedList current = node;
        while (iterator.hasNext()) {
            itrNext = iterator.next();
            DoublyLinkedList newNode = new DoublyLinkedList(itrNext[0], itrNext[1]);
            current.next = newNode;
            newNode.prev = current;
            current = newNode;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        DoublyLinkedList current = this;

        while (current.next != null) {
            builder.append(current.val + "-");
            current = current.next;
        }
        builder.append(current.val);
        return builder.toString();
    }
}
