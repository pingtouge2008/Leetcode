package com.ptg.algorithm.stack.monotone;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Leetcode_496_next_greater_element_i {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        if (nums2.length == 0) return new int[0];

        Stack<Integer> stack = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>(nums2.length);

        int[] res = new int[nums1.length];

        stack.push(nums2[0]);
        for (int i = 1; i < nums2.length; i++) {
            while (!stack.isEmpty() && nums2[i] > stack.lastElement()) {
                map.put(stack.pop(), nums2[i]);
            }

            stack.push(nums2[i]);
        }
        while (!stack.isEmpty()) {
            map.put(stack.pop(), -1);
        }

        for (int i = 0; i < nums1.length; i++) {
            res[i] = map.get(nums1[i]);
        }
        return res;
    }
}
