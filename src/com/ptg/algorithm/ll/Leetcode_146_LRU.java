package com.ptg.algorithm.ll;

import java.util.HashMap;

public class Leetcode_146_LRU {

    private static class LRU<K, V> {
        private HashMap<K, V> map;
        private DualLinkedList cache;
        private int capacity;

        public LRU(int capacity) {
            this.map = new HashMap<>();
            this.cache = new DualLinkedList();
            this.capacity = capacity;
        }

        public V get(K key) {
            if (!map.containsKey(key))
                return null;
            V val = map.get(key);
            // 利用 put 方法把该数据提前
            put(key, val);
            return val;
        }

        public void put(K key, V value) {
            Node<K, V> node = new Node<>(key, value);
            if (map.containsKey(key)) {
                cache.remove(node); //先移除原来的节点
                cache.addFirst(node);
                map.put(key, value); //覆盖掉map里面原来的kv
            } else {
                if (capacity == cache.size) {// 容量满了
                    cache.removeLast();
                    map.remove(key);
                }
                // 不管容量满还是没满, 都要添加到头部
                map.put(key, value);
                cache.addFirst(node);
            }
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

        public void remove(Node<K, V> last) {
            last.prev.next = tail;
            tail.prev = last.prev;
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
