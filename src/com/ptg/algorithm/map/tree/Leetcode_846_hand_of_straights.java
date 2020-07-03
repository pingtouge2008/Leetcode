package com.ptg.algorithm.map.tree;


//爱丽丝有一手（hand）由整数数组给定的牌。
//
// 现在她想把牌重新排列成组，使得每个组的大小都是 W，且由 W 张连续的牌组成。
//
// 如果她可以完成分组就返回 true，否则返回 false。
//
//
//
//
//
//
// 示例 1：
//
// 输入：hand = [1,2,3,6,2,3,4,7,8], W = 3
//输出：true
//解释：爱丽丝的手牌可以被重新排列为 [1,2,3]，[2,3,4]，[6,7,8]。
//
// 示例 2：
//
// 输入：hand = [1,2,3,4,5], W = 4
//输出：false
//解释：爱丽丝的手牌无法被重新排列成几个大小为 4 的组。
//
//
//
// 提示：
//
//
// 1 <= hand.length <= 10000
// 0 <= hand[i] <= 10^9
// 1 <= W <= hand.length
//
//
//
//
// 注意：此题目与 1294 重复：https://leetcode-cn.com/problems/divide-array-in-sets-of-k-co
//nsecutive-numbers/
// Related Topics Ordered Map


import java.util.Arrays;
import java.util.TreeMap;

//leetcode submit region begin(Prohibit modification and deletion)
class Leetcode_846_hand_of_straights {

    public boolean isNStraightHand(int[] hand, int W) {
        int len = hand.length;
        if (len % W != 0) return false;
        Arrays.sort(hand);
        boolean[] used = new boolean[len];// 用来标志有没有访问过
        for (int i = 0; i < len; i++) {
            if (used[i]) continue;
            int cur = hand[i];
            used[i] = true;
            for (int j = i + 1; j < len; j++) {
                if (cur - hand[i] == W - 1) break;   // 如果已经组成了顺子，break
                if (used[j]) continue;   // j位置已经用过，跳过
                if (hand[j] > cur + 1) {    // 不是连续的，返回false
                    return false;
                } else if (hand[j] == cur + 1) {   // 是连续的，
                    used[j] = true;
                    cur++;
                }
            }
            if (cur - hand[i] != W - 1) return false;
        }
        return true;
    }


    public boolean isNStraightHand_2(int[] hand, int W) {
        if (hand.length == 0 || hand.length % W != 0 || hand == null) return false;

        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i : hand) {
            if (map.containsKey(i)) {
                map.put(i, map.get(i) + 1);
            } else {
                map.put(i, 1);
            }
        }

        while (map.size() > 0) {
            int start = map.firstKey();
            for (int i = 0; i < W; i++) {
                if (!map.containsKey(start)) {
                    return false;
                }
                int cnt = map.get(start);
                if (cnt == 1) map.remove(start);
                else map.put(start, cnt-1);
                start++;
            }
        }
        return true;

    }


}
//leetcode submit region end(Prohibit modification and deletion)

