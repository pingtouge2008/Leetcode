package com.ptg.algorithm.backtrace;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Leetcode_39_combination_sum {
    // https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-tao-mo-ban-ji-ke-by-jeromememory/
    // https://leetcode-cn.com/problems/combination-sum/solution/fei-chang-xiang-xi-de-di-gui-hui-su-tao-lu-by-re-2/
    /*
        result = []
        def backtrack(路径, 选择列表):
                if 满足结束条件:
                result.add(路径)
                return

                for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表)
        撤销选择
    */

    List<List<Integer>> lists = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0 || target < 0) {
            return lists;
        }

        Arrays.sort(candidates);

        LinkedList<Integer> list = new LinkedList<>();
        process(0, candidates, target, list);
        return lists;
    }

    private void process(int start, int[] candidates, int target, LinkedList<Integer> list) {
        //递归的终止条件
        if (target < 0) {
            return;
        }
        if (target == 0) {
            lists.add(new ArrayList<>(list));
        } else {
            for (int i = start; i < candidates.length; i++) {
                // 在数组有序的前提下，剪枝
                if (target - candidates[i] < 0) {
                    break;
                }

                list.add(candidates[i]);
                //因为每个数字都可以使用无数次，所以递归还可以从当前元素开始
                //下面会设置start，每次递归的时候只在candidates中当前及之后的数字中选择。
                process(i, candidates, target - candidates[i], list);
                list.removeLast();
            }
        }

    }

}
