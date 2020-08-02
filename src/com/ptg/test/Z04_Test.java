package com.ptg.test;

import java.util.*;

public class Z04_Test {
    public static void main(String[] args) {

        LinkedList list = new LinkedList();

        /*Stack<Object> objects = new Stack<>();
        objects.peek();
        Queue<Integer> q = new LinkedList<>();
        q.peek();
        getKthMagicNumber(15);*/
        charsToAnInt("4321".toCharArray());
    }

    private static int charsToAnInt(char[] chars) {
        if (chars.length == 0) return 0;
        // chars = '0' '0' '1' '2' '3' '6'  '0' '0' '5' '0' '1'
        //  0 * 10 = 0'
        // (0'+0'') * 10 = 0'''
        // (0''' + 1) * 10 = 10
        // (10 + 2) * 10 = 120
        // (120 + 3) * 10 = 1230
        // (1230 + 6) * 10 = 12360
        // (12360 + 0) * 10 = 123600
        // (123600 + 0) * 10 = 1236000
        // (1236000 + 5) * 10 = 12360050
        // (12360050 + 0) * 10 = 123600500
        // (123600500 + 1) * 10 = 1236005010
        // if (lastResult > 0 && thisresult < 0) return max;
        // return 1236005010 / 10
        int flag = 1;
        int startIndex = 0;
        if (chars[0] == '-') {
            flag = -1;
            startIndex = 1;
        }

        int lastResult = 0;
        int thisResult = 0;
        int sum = 0;
        for (int i = startIndex; i < chars.length - 1; i+=2) {
            lastResult = (flag * (chars[i] - '0') + thisResult) * 10;
            thisResult = (lastResult + flag * (chars[i + 1] - '0')) * 10;
            if (flag == 1 && lastResult > 0 && thisResult < 0) return Integer.MAX_VALUE;
            if (flag == -1 && lastResult < 0 && thisResult > 0) return Integer.MIN_VALUE;
        }

        return thisResult;

    }

    public static int getKthMagicNumber(int k) {

        Set<Long> values = new HashSet<>();
        Queue<Long> queue = new PriorityQueue();
        queue.add(1L);
        while (true) {
            Long value = queue.poll();
            //处理重复数据
            if (!values.contains(value)) {
                values.add(value);
                queue.add(value * 3);
                queue.add(value * 5);
                queue.add(value * 7);
            }

            if (values.size() == k) {
                return value.intValue();
            }
        }
    }
}
