package com.ptg.algorithm.math.pattern;
//在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
//
// 注意:
//n 是正数且在32位整数范围内 ( n < 231)。
//
// 示例 1:
//
// 输入:
//3
//
//输出:
//3
//
//
// 示例 2:
//
// 输入:
//11
//
//输出:
//0
//
//说明:
//第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
//
// Related Topics 数学


public class Leetcode_400_nth_digit {
    public int findNthDigit(int n) {
        long len = 1, base = 1;
        long m = n;
        while (m > 9 * base * len) {
            m -= 9 * base * len;
            len++;
            base *= 10;
        }
        int curNum = (int) (base + (m - 1) / len);
        return ((curNum + "").charAt((int) ((m - 1) % len)) - '0');
    }
}
