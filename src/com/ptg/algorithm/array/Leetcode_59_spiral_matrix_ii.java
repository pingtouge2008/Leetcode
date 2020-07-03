package com.ptg.algorithm.array;

public class Leetcode_59_spiral_matrix_ii {
    public int[][] generateMatrix(int n) {
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        int num = 1;
        int target = n * n;

        int[][] result = new int[n][n];

        while (num <= target) {
            for (int i = left; i <= right; i++) {
                result[top][i] = num;
                num++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                result[i][right] = num;
                num++;
            }
            right--;

            for (int i = right; i >= left; i--) {
                result[bottom][i] = num;
                num++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                result[i][left] = num;
                num++;
            }
            left++;
        }

        return result;

    }
}
