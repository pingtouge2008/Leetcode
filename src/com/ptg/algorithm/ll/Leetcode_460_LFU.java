package com.ptg.algorithm.ll;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_460_LFU {


    public static void main(String[] args) {
        LFU<Integer, Integer> lfu = new LFU<>(2);
        lfu.put(3, 1);
        lfu.put(2, 1);
        lfu.put(2, 2);
        lfu.put(4, 4);

        System.out.println(lfu.get(2));
    }

    private static class LFU<K, V> {
        private HashMap<K, Node<K, V>> keyMap;
        private HashMap<Integer, DualLinkedList<K, V>> freqMap; //K:记录访问次数 V:具有相同访问次数的Node的链表
        private int capacity;
        private int minFreq;// 全局最少的访问次数

        public LFU(int capacity) {
            this.keyMap = new HashMap<>();
            this.freqMap = new HashMap<>();
            this.capacity = capacity;
            this.minFreq = 0;
        }


        public V get(K key) {
            if (capacity == 0 || !keyMap.containsKey(key)) {
                return null;
            }
            Node<K, V> visitedNode = keyMap.get(key); // 获取到访问的节点, 比如这个节点被访问了2次
            removeNodeFromDualLinkedListInFreqMap(visitedNode); //将这个节点从freqMap中 访问次数为2的队列中删除
            visitedNode.freq += 1;// visitedNode的访问次数+1
            addNodeToDualLinkedListInFreqMap(visitedNode);  //将这个节点添加到freqMap中 访问次数为3的队列
            return visitedNode.value;
        }

        public void put(K key, V value) {
            if (capacity == 0) return;
            Node<K, V> node = new Node<>(key, value);
            if (keyMap.containsKey(key)) { //如果key存在, 就更新插入的node的value;
                Node<K, V> oldNode = keyMap.get(key);
                removeNodeFromDualLinkedListInFreqMap(oldNode);
                node.freq = oldNode.freq + 1;
                addNodeToDualLinkedListInFreqMap(node);

            } else { //插入操作
                // 如果满了
                if (keyMap.size() == capacity) {
                    // 1. 剔除一个节点, 先获取访问次数最少的DualLinkedList
                    DualLinkedList<K, V> minVisitedDualLinkedList = freqMap.get(minFreq);
                    // 2. 将访问次数最少的DualLinkedList最后面的元素删除
                    Node<K, V> nodeDeleted = minVisitedDualLinkedList.removeLast();
                    // 3. 最后将nodeDeleted从map移除
                    keyMap.remove(nodeDeleted.key);
                }
                // 然后插入, 插入的元素一定是没有被访问过的, 所以
                DualLinkedList<K, V> linkedList_0 = freqMap.get(0);
                if (linkedList_0 == null) {
                    linkedList_0 = new DualLinkedList<>();
                    freqMap.put(0, linkedList_0);
                }
                linkedList_0.addFirst(node);
                keyMap.put(key, node);
                // 由于是有新插入的节点, 所以
                this.minFreq = 0;
            }

        }


        private void removeNodeFromDualLinkedListInFreqMap(Node<K, V> nodeToRemove) {
            int freq = nodeToRemove.freq;
            DualLinkedList<K, V> dualLinkedList = freqMap.get(freq);
            dualLinkedList.remove(nodeToRemove);
            // 删除一个节点 要从keyMap中也将该节点删掉
            keyMap.remove(nodeToRemove.key);
            //删除一个节需要点更新全局最小访问次数
            if (freq == minFreq && freqMap.get(freq).size == 0) {
                minFreq++;
            }
        }

        private void addNodeToDualLinkedListInFreqMap(Node<K, V> nodeToAdd) {
            int freq = nodeToAdd.freq;
            DualLinkedList<K, V> dualLinkedList = freqMap.get(freq);
            if (dualLinkedList == null) {
                dualLinkedList = new DualLinkedList<>();
                freqMap.put(freq, dualLinkedList);
            }
            dualLinkedList.addFirst(nodeToAdd);
            // 添加到keyMap里面
            keyMap.put(nodeToAdd.key, nodeToAdd);
        }
    }


    // ========================= 下面是Node 和 DualLinkedList ========================
    // ========================= 下面是Node 和 DualLinkedList ========================
    // ========================= 下面是Node 和 DualLinkedList ========================

    private static class DualLinkedList<K, V> {
        private Node<K, V> head, tail;
        private int size;

        public DualLinkedList() {
            this.head = new Node<>(null, null);
            this.tail = new Node<>(null, null);
            this.head.next = tail;
            this.tail.prev = head;
            this.size = 0;
        }

        /**
         * 添加到 head 后面
         *
         * @param node
         */
        public void addFirst(Node<K, V> node) {
            node.next = head.next;
            node.prev = head;
            head.next = node;
            node.next.prev = node;
            size++;
        }

        /**
         * 移除到 tail 前面的元素
         *
         * @return
         */
        public Node<K, V> removeLast() {
            if (head.next == tail) {
                return null;
            }
            Node<K, V> last = tail.prev;
            remove(last);
            return last;
        }

        public void remove(Node<K, V> node) { // node在链表中一定存在
            Node<K, V> next = node.next;
            Node<K, V> prev = node.prev;
            prev.next = next;
            next.prev = prev;
            size--;
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

