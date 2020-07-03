package com.ptg.algorithm.ll;

import java.util.HashMap;

public class Leetcode_146_LRU {

    private static class LRU<K, V> {
        private HashMap<K, Node<K, V>> map;
        private DualLinkedList<K, V> cacheList;
        private int capacity;

        public LRU(int capacity) {
            this.map = new HashMap<>();
            this.cacheList = new DualLinkedList();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (!map.containsKey(key))
                return null;
            Node<K, V> found = map.get(key);
            // 把该数据提到链表最前面前
            // 1. 先把found从原链表删除
            cacheList.remove(found);
            // 2. 再把found提到链表头部
            cacheList.addFirst(found);
            return found.value;
        }

        public void put(K key, V value) {
            Node<K, V> node = new Node<>(key, value); // 新添加的节点
            if (map.containsKey(key)) {
                Node<K, V> existNode = map.get(key);

                cacheList.remove(existNode); //先移除原来的节点
                // cacheList.addFirst(node);
                // map.put(key, node); //覆盖掉map里面原来的kv

                //existNode.value = value;
            } // else {
            if (capacity == cacheList.size) {// 容量满了
                Node<K,V> last = cacheList.removeLast();
                map.remove(last.key);
            }
            // 不管容量满还是没满, 都要添加到头部
            // map.put(key, node);
            // cacheList.addFirst(node);
            // }
            map.put(key, node);
            cacheList.addFirst(node);
        }
    }

    private static class DualLinkedList<K, V> {
        private Node<K, V> head, tail;
        private int size;

        public DualLinkedList() {
            head = new Node<>(null, null);
            tail = new Node<>(null, null);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void addFirst(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next = node;
            node.next.prev = node;
            size++;
        }

        public Node<K, V> removeLast() {
            if (head.next == tail) {
                return null;
            }
            Node<K, V> last = tail.prev;
            remove(last);
            return last;
        }

        public void remove(Node<K, V> node) { // node在链表中一定存在
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    private static class Node<K, V> {
        public K key;
        public V value;

        Node<K, V> next, prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

}
