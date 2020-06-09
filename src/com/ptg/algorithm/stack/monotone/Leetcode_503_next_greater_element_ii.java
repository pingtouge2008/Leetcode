package com.ptg.algorithm.stack.monotone;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Leetcode_503_next_greater_element_ii {

    public int[] nextGreaterElements2(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[0];
        int[] res = new int[len];
        Arrays.fill(res, -1);
        // 不再使用两倍的数组空间
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2*len; i++) {
            while (!stack.isEmpty() && nums[i%len] > nums[stack.peek()%len]) {
                int pre = stack.pop()%len;
                res[pre] = nums[i%len];
            }
            stack.push(i);
        }
        return res;
    }

    public int[] nextGreaterElements(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[0];
        int[] res = new int[len*2];
        Arrays.fill(res, -1);

        int[] copy = new int[2*len];
        for (int i = 0; i < len; i ++) {
            copy[i] = copy[i+len] = nums[i];
        }

        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 2*len; i++) {
            while (!stack.isEmpty() && copy[i] > copy[stack.peek()] ) {
                int pre = stack.pop();
                res[pre] = copy[i];
            }
            stack.push(i);
        }
        return Arrays.copyOf(res,len);
    }
}
