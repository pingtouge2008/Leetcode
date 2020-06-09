package com.ptg.algorithm.stack.monotone;

import java.util.Arrays;
import java.util.Stack;

public class Leetcode_739_daily_temperature {

    // 利用栈
    public int[] dailyTemperatures2(int[] T) {
        int[] res = new int[T.length];
        Arrays.fill(res, 0);
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < T.length; i++) {
            while (!stack.empty() && T[i] > T[stack.peek()]) { // 一旦温度变高
                int pre = stack.pop();
                res[pre] = i - pre;
            }
            stack.push(i); //如果温度一直都是递减的就把索引一直压栈
        }
        return res;
    }

    // O(N^2)
    public int[] dailyTemperatures(int[] T) {
        int[] res = new int[T.length];
        for (int i = 0; i < T.length; i++) {
            for (int j = i + 1; j < T.length; j++) {
                if (T[j] > T[i]) {
                    res[i] = j - i;
                    break;
                } else {
                    res[i] = 0;
                }
            }
        }
        return res;
    }
}
