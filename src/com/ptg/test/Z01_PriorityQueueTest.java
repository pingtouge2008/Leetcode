package com.ptg.test;

import java.util.PriorityQueue;

public class Z01_PriorityQueueTest {
    public static void main(String[] args) {
        PriorityQueue<Integer> heap =
                new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        heap.add(1);
        heap.add(5);
        heap.add(3);

        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
    }
}
