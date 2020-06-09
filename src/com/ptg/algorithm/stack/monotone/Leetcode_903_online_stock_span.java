package com.ptg.algorithm.stack.monotone;

import java.util.Stack;

public class Leetcode_903_online_stock_span {

    class StockSpanner {

        Stack<Integer> prices, weights;

        public StockSpanner() {
            prices = new Stack<>();
            weights = new Stack<>();
        }

        public int next(int price) {
            int w = 1;
            while (!prices.isEmpty() && price >= prices.peek()) {
                prices.pop();
                w += weights.pop();
            }
            prices.push(price);
            weights.push(w);
            return w;
        }

    }
}
