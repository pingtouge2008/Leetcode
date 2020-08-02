package com.ptg.algorithm.backtrace;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴
//都要用到。
//
// 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
//
// 示例 1:
//
//
//输入: [1,1,2,2,2]
//输出: true
//
//解释: 能拼成一个边长为2的正方形，每边两根火柴。
//
//
// 示例 2:
//
//
//输入: [3,3,3,3,4]
//输出: false
//
//解释: 不能用所有火柴拼成一个正方形。
//
//
// 注意:
//
//
// 给定的火柴长度和在 0 到 10^9之间。
// 火柴数组的长度不超过15。
//
// Related Topics 深度优先搜索


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

//leetcode submit region begin(Prohibit modification and deletion)
class Leetcode_473_matchsticks_to_square {
    public boolean makesquare(int[] nums) {
        if (nums.length < 4) return false;
        long total = 0;
        for (int d : nums) total += d;
        if (total % 4 != 0) return false;
        int t = (int) (total / 4);
        for (int d : nums) {
            if (d > t) return false;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];
        for (int k = 0; k < 3; k++) {
            for (int i = nums.length - 1; i >= 0; i--) {
                if (!used[i]) {
                    used[i] = true;
                    boolean r = find(i - 1, nums, used, t - nums[i]);
                    if (!r) return false;
                }
            }
        }
        return true;
    }

    boolean find(int idx, int[] nums, boolean[] used, int t) {
        if (t == 0) {
            return true;
        }
        for (int i = idx; i >= 0; i--) {
            if (!used[i] && nums[i] <= t) {
                used[i] = true;
                boolean r = find(i - 1, nums, used, t - nums[i]);
                if (r) return true;
                used[i] = false;
            }
        }
        return false;
    }


    // =================================================
    private List<Integer> nums;
    private final int[] sums;
    private int squareSide;

    public Leetcode_473_matchsticks_to_square() {
        this.sums = new int[4];
    }

    public boolean makesquare_2(int[] nums) {
        int perimeter = Arrays.stream(nums).sum();
        if (nums == null || nums.length == 0 || perimeter % 4 != 0) {
            return false;
        }
        this.squareSide = perimeter / 4;
        this.nums = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(this.nums, Collections.reverseOrder());

        return this.dfs(0);
    }

    public boolean dfs(int index) {
        if (index == this.nums.size()) {
            return sums[0] == sums[1] && sums[1] == sums[2] && sums[2] == sums[3];
        }

        int element = this.nums.get(index);

        for (int i = 0; i < 4; i++) {
            if (this.sums[i] + element <= this.squareSide) {
                this.sums[i] += element;
                if (this.dfs(index + 1)) {
                    return true;
                }
                this.sums[i] -= element;
            }
        }

        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

