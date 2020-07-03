package com.ptg.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
//
// candidates 中的每个数字在每个组合中只能使用一次。
//
// 说明：
//
//
// 所有数字（包括目标数）都是正整数。
// 解集不能包含重复的组合。
//
//
// 示例 1:
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
//
//
// 示例 2:
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//]
// Related Topics 数组 回溯算法
public class Leetcode_40_combination_sum_ii {


    // https://leetcode-cn.com/problems/combination-sum-ii/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-3/
    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) return result;
        Arrays.sort(candidates);
        dfs(candidates, new LinkedList<Integer>(), 0, target);
        return result;
    }

    private void dfs(int[] candidates, LinkedList<Integer> path, int start, int target) {
        //递归的终止条件
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (target < candidates[i]) { // 在数组有序的前提下，剪枝
                    break;
                }
                // 1、i > index 表明剪枝的分支一定不是当前层的第 1 个分支
                // 2、input[i - 1] == input[i] 表明当前选出来的数等于当前层前一个分支选出来的数
                // 因为前一个分支的候选集合一定大于后一个分支的候选集合
                // 故后面出现的分支中一定包含了前面分支出现的结果，因此剪枝
                // “剪枝”的前提是排序，升序或者降序均可
                if (i > start && candidates[i] == candidates[i-1]) {
                    continue;
                }
                path.add(candidates[i]);

                dfs(candidates, path, i + 1, target-candidates[i]);

                path.removeLast();
            }
        }

    }
}
