package com.ptg.algorithm.array;

public class Leetcode_26_remove_duplicates_from_sorted_array {

    public static int removeDuplicates02(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int p = 0;
        int q = 1;

        while (q < nums.length) {
            if (nums[p] == nums[q]) {
                q++;
            } else {
                nums[p+1] = nums[q];
                p++;
            }
        }
        return p + 1;
    }


    // 用了两个循环 不好
    public static int removeDuplicates(int[] nums) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            int j = i;
            while( j+1 < nums.length && nums[j] == nums[j+1]) {
                j++;
            }
            i = j;
            nums[len] = nums[j];
            len++;
        }
        return len;
    }


    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};

        System.out.println(removeDuplicates02(nums));
    }
}
