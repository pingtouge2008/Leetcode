package com.ptg.algorithm.backtrace;

import java.util.ArrayList;
import java.util.List;

//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
//
//
//
// 示例:
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
//
//
// 说明:
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
// Related Topics 字符串 回溯算法
public class Leetcode_17_letter_combinations_of_a_phone_number {


    private final List<String> result = new ArrayList<>();

    private final String[] letterMap = {
            " ",    //0
            "",     //1
            "abc",  //2
            "def",  //3
            "ghi",  //4
            "jkl",  //5
            "mno",  //6
            "pqrs", //7
            "tuv",  //8
            "wxyz"  //9
    };

    char[] digitsArray;

    public List<String> letterCombinations(String digits) {
        if ("".equals(digits) || digits == null) return result;

        int len = digits.length();
        digitsArray = digits.toCharArray();

        dfs(0, len, "");

        return result;
    }

    private void dfs(int index, int len, String str) {
        if (index == len) {
            result.add(str);
            return;
        }

        char digit = digitsArray[index];
        String letters = letterMap[digit - '0'];

        for (int i = 0; i < letters.length(); i++) {
            /*
            str = str + letters.charAt(i);
            dfs(index + 1, len, str);
            str = str.substring(0, str.length()-1);
            */
            // 由于String的特性, 可以不用回溯
            dfs(index + 1, len, str + letters.charAt(i));
        }
    }

}
