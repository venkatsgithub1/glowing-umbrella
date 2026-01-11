package io.learn.lists.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
    private DoublyLinkedList dllHead;
    private DoublyLinkedList dllTail;
    private int capacity;
    private Map<Integer, DoublyLinkedList> map;
    private int currentCapacity = 0;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dllHead = new DoublyLinkedList(-1, -1);
        this.dllTail = new DoublyLinkedList(-1, -1);
        dllHead.next = dllTail;
        dllTail.prev = dllHead;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        assert cache.get(1) == 1 : "Wrong result";
        System.out.println(cache.dllHead);
        cache.put(3, 3);
        assert cache.get(2) == -1 : "Wrong result";
        System.out.println(cache.dllHead);
        cache.put(4, 4);
        assert cache.get(3) == 3 : "Wrong result";
        System.out.println(cache.dllHead);
        assert cache.get(4) == 4 : "Wrong result";
        System.out.println(cache.dllHead);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            DoublyLinkedList node = map.get(key);
            moveToFront(node);
            return node.val;
        }
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            DoublyLinkedList newNode = new DoublyLinkedList(key, value);
            if (currentCapacity < capacity) {
                currentCapacity++;
            } else {
                DoublyLinkedList nodeToBeRemoved = dllTail.prev;
                DoublyLinkedList nodeToBeRemovedPrev = nodeToBeRemoved.prev;

                dllTail.prev = nodeToBeRemovedPrev;
                nodeToBeRemovedPrev.next = dllTail;
                map.remove(nodeToBeRemoved.key);
            }

            DoublyLinkedList headNext = dllHead.next;

            dllHead.next = newNode;
            headNext.prev = newNode;

            newNode.prev = dllHead;
            newNode.next = headNext;
            map.put(key, newNode);
        } else {
            DoublyLinkedList node = map.get(key);
            node.val = value;
            moveToFront(node);
        }
        System.out.println(dllHead);
    }

    private void moveToFront(DoublyLinkedList node) {
        DoublyLinkedList nodePrev = node.prev;
        DoublyLinkedList nodeNext = node.next;
        nodePrev.next = nodeNext;
        nodeNext.prev = nodePrev;

        DoublyLinkedList headNext = dllHead.next;

        dllHead.next = node;
        headNext.prev = node;

        node.prev = dllHead;
        node.next = headNext;
    }
}
