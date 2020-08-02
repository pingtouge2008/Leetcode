package com.ptg.algorithm.backtrace;

import java.util.*;


//n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
//
//
//
// 上图为 8 皇后问题的一种解法。
//
// 给定一个整数 n，返回所有不同的 n 皇后问题的解决方案。
//
// 每一种解法包含一个明确的 n 皇后问题的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。
//
// 示例:
//
// 输入: 4
//输出: [
// [".Q..",  // 解法 1
//  "...Q",
//  "Q...",
//  "..Q."],
//
// ["..Q.",  // 解法 2
//  "Q...",
//  "...Q",
//  ".Q.."]
//]
//解释: 4 皇后问题存在两个不同的解法。
//
//
//
//
// 提示：
//
//
// 皇后，是国际象棋中的棋子，意味着国王的妻子。皇后只做一件事，那就是“吃子”。当她遇见可以吃的棋子时，就迅速冲上去吃掉棋子。当然，她横、竖、斜都可走一到七步
//，可进可退。（引用自 百度百科 - 皇后 ）
//
// Related Topics 回溯算法
public class Leetcode_51_n_queens {

    private final List<List<String>> result = new LinkedList<>();

    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return result;
        char[][] board = new char[n][n];
        for (char[] chars : board) {
            Arrays.fill(chars, '.');//初始化棋盘
        }
        backtrace(0, board, n);
        return result;
    }

    private void backtrace(int row, char[][] board, int n) {
        if (row == n) {
            result.add(convert(board));
            return;
        }
        for (int col = 0; col < n; col++) {
            if (!isValid(board, row, col)) continue; // 如果放在(row, col) 不合理, 尝试放在(row, col + 1)
            board[row][col] = 'Q';
            backtrace(row + 1, board, n);
            board[row][col] = '.';
        }
    }

    /**
     * 检查 第 row 行以上的 queen 会不会 攻击到 位于(row, col)的queen
     * 即: queen放在(row, col) 是否合理
     *
     * @param board
     * @param row
     * @param col
     * @return
     */
    private boolean isValid(char[][] board, int row, int col) {
        int size = board.length;
        for (char[] chars : board) { //同一列中不能有queen
            if (chars[col] == 'Q') return false;
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++) {// 检查(row, col)右上方的棋盘
            if (board[i][j] == 'Q') return false;
        }
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {// 检查(row, col)左上方的棋盘
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }

    private LinkedList<String> convert(char[][] board) {
        LinkedList<String> solution = new LinkedList<>();
        for (char[] chars : board) {
            solution.addLast(new String(chars));
        }
        return solution;
    }
}

// =======================================================================================

class Solution_2 {

    /**
     * 优化isValid的查询，通过3个set来分别记录列、主对角线、副对角线上Q的情况，减少迭代的查询
     * Key值：colIndex, [r-c], [r + c] 作为set的key
     */
    private final List<List<String>> res = new LinkedList<>();
    private final Set<Integer> colSet = new HashSet<>();
    private final Set<Integer> masterSet = new HashSet<>();
    private final Set<Integer> slaveSet = new HashSet<>();

    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for (char[] chars : board) Arrays.fill(chars, '.');
        backtrack(board, 0);
        return res;
    }

    /**
     * path: board in [0, row -1]
     * choices for a row : every cols
     * time to end: row == board.length
     *
     * @param board
     * @param row
     */
    private void backtrack(char[][] board, int row) {
        if (row == board.length) {
            res.add(charToString(board));
            return;
        }
        for (int col = 0; col < board[row].length; col++) {
            if (!isValid(board, row, col)) continue;
            updateRecords(board, row, col);
            backtrack(board, row + 1);
            updateRecords(board, row, col);
        }
    }

    private void updateRecords(char[][] board, int row, int col) {
        if (colSet.contains(col)) {
            board[row][col] = '.';
            colSet.remove(col);
            masterSet.remove(row - col);
            slaveSet.remove(row + col);
        } else {
            board[row][col] = 'Q';
            colSet.add(col);
            masterSet.add(row - col);
            slaveSet.add(row + col);
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        return !colSet.contains(col)
                && !masterSet.contains(row - col)
                && !slaveSet.contains(row + col);
    }
    private static List<String> charToString(char[][] array) {
        List<String> result = new LinkedList<>();
        for (char[] chars : array) {
            result.add(String.valueOf(chars));
        }
        return result;
    }


}

class Solution_3 {

    private List<List<String>> res;
    private int n;

    public List<List<String>> solveNQueens(int n) {
        this.n = n;
        res = new ArrayList<>();
        if (n == 0) {
            return res;
        }

        int col = 0;
        int master = 0;
        int slave = 0;
        Stack<Integer> stack = new Stack<>();

        backtrack(0, col, master, slave, stack);
        return res;
    }

    private void backtrack(int row, int col, int master, int slave, Stack<Integer> stack) {
        if (row == n) {
            List<String> board = convert2board(stack, n);
            res.add(board);
            return;
        }

        // 针对每一列，尝试是否可以放置
        for (int i = 0; i < n; i++) {
            if (((col >> i) & 1) == 0
                    && ((master >> (row + i)) & 1) == 0
                    && ((slave >> (row - i + n - 1)) & 1) == 0) {
                stack.add(i);
                col ^= (1 << i);
                master ^= (1 << (row + i));
                slave ^= (1 << (row - i + n - 1));

                backtrack(row + 1, col, master, slave, stack);

                slave ^= (1 << (row - i + n - 1));
                master ^= (1 << (row + i));
                col ^= (1 << i);
                stack.pop();
            }
        }
    }

    private List<String> convert2board(Stack<Integer> stack, int n) {
        List<String> board = new ArrayList<>();
        for (Integer num : stack) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < n; i++) {
                stringBuilder.append(".");
            }
            stringBuilder.replace(num, num + 1, "Q");
            board.add(stringBuilder.toString());
        }
        return board;
    }
}