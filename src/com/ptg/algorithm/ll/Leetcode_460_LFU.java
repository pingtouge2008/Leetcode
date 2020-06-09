package com.ptg.algorithm.ll;

import java.util.HashMap;

public class Leetcode_460_LFU {

    private static class LFU<K, V> {
        private HashMap<K, Node<K,V>> map;
        private HashMap<Integer, DualLinkedList<K, V>> frequentMap; //K:记录访问次数 V:具有相同访问次数的Node的链表
        private int capacity;
        private int minFreq = 1;

        public LFU(int capacity) {
            this.map = new HashMap<>();
            this.frequentMap = new HashMap<>();
            this.capacity = capacity;
        }

        // TODO
        public V get(K key) {
            if (capacity == 0) {
                return null;
            }
            if (!map.containsKey(key)){
                return null;
            }

            Node<K,V> visitedNode = map.get(key);

            //changeFreqMap()
            // 访问次数+1
            int beforeVisit = ++visitedNode.freq;
            //从原来访问次数的duallist里面删除
            DualLinkedList<K, V> kvDualLinkedList = frequentMap.get(beforeVisit);
            kvDualLinkedList.removeNode(visitedNode);
            //加到新的访问次数的duallist里面, 并添加到头部
            DualLinkedList<K, V> kvDualLinkedList1 = frequentMap.get(visitedNode.freq);
            kvDualLinkedList1.addFirst(visitedNode);
            return visitedNode.value;
        }

        public void put(K key, V value) {
            Node<K, V> node = new Node<>(key, value);
            if (map.containsKey(key)) { //如果key存在, 就更新插入的node的freq;
                Node<K, V> oldNode = map.get(key);
                int oldFreq = oldNode.freq;
                frequentMap.get(oldFreq).removeNode(oldNode);
                node.freq = oldFreq++;
                DualLinkedList<K, V> kvDualLinkedList = frequentMap.get(node.freq);
                kvDualLinkedList.addFirst(node);
            } else { //插入操作
                // 如果满了
                if(map.size() == capacity) {
                    // 1. 剔除一个节点, 选择访问次数最少的
                    map.remove(key); // 从map移除
                    DualLinkedList<K, V> kvDualLinkedList = frequentMap.get(minFreq);
                    kvDualLinkedList.removeLast();//从缓存移除
                }
                // 然后插入
                frequentMap.get(minFreq).addFirst(node);
                // 由于是有新插入的节点, 所以
                this.minFreq = 0;
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
            last.prev.next = tail;
            tail.prev = last.prev;
            size--;
            return last;
        }

        public void removeNode(Node<K, V> node) {


        }
    }

    private static class Node<K, V> {
        public K key;
        public V value;
        private int freq; //该节点被访问次数
        Node<K, V> next, prev;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.freq = 0; //刚开始没有被访问
        }
    }



}
