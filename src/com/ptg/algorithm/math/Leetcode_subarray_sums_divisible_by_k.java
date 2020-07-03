package com.ptg.algorithm.math;

import java.util.HashMap;
import java.util.Map;

public class Leetcode_subarray_sums_divisible_by_k {
    //https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/solution/tong-su-yi-dong-de-jie-fa-li-yong-tong-yu-de-xing-/
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> map = new HashMap<>();
        // record.put(0, 1);
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            sum += A[i];
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int mod = (sum % K + K) % K;
            if (map.containsKey(mod)) {
                int modCount = map.get(mod);
                modCount++;
                map.put(mod, modCount);
            } else {
                map.put(mod, 1);
            }
        }

        int ans = 0;

        for (Integer mod : map.keySet()) {
            if (mod == 0) {
                int mod_0_cnt = map.get(mod);
                ans += mod_0_cnt;
                ans += (mod_0_cnt * (mod_0_cnt - 1)) / 2;
                continue;
            }
            if (map.get(mod) == 1 || map.get(mod) == null) {
                continue;
            }
            int mod_other_cnt = map.get(mod);
            ans += (mod_other_cnt * (mod_other_cnt - 1)) / 2;
        }

        return ans;

    }
}
