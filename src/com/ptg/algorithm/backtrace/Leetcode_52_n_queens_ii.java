package com.ptg.algorithm.backtrace;

import java.util.HashSet;

//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
// 上图为 8 皇后问题的一种解法。
//
// 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
//
// 示例:
//
// 输入: 4
//输出: 2
//解释: 4 皇后问题存在如下两个不同的解法。
//[
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//
//
//
//
// 提示：
//
//
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一或七步
//，可进可退。（引用自 百度百科 - 皇后 ）
//
// Related Topics 回溯算法
public class Leetcode_52_n_queens_ii {

    private int totalSolution = 0;
    private HashSet<Integer> colSet = new HashSet<>();
    private HashSet<Integer> masterSet = new HashSet<>();
    private HashSet<Integer> slaveSet = new HashSet<>();

    public int totalNQueens(int n) {
        backtrace(0, n);
        return this.totalSolution;
    }

    private void backtrace(int row, int n) {
        if (row == n) {
            this.totalSolution++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (!isValid(row, col)) continue;
            colSet.add(col);
            masterSet.add(row - col);
            slaveSet.add(row + col);

            backtrace(row + 1, n);

            colSet.remove(col);
            masterSet.remove(row - col);
            slaveSet.remove(row + col);
        }
    }

    private boolean isValid(int row, int col) {
        return !colSet.contains(col) && !masterSet.contains(row - col) && !slaveSet.contains(row + col);
    }
}

