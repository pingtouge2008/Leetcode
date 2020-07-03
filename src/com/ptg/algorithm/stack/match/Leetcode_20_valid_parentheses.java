package com.ptg.algorithm.stack.match;

import java.util.HashMap;
import java.util.Stack;

public class Leetcode_20_valid_parentheses {

    public boolean isValid(String s) {
        // if ("".equals(s)) return true;
        char[] c = s.toCharArray();
        char p = ' ';
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '(' || c[i] == '[' || c[i] == '{') {
                stack.push(c[i]);
            } else {
                try {
                    p = stack.pop();
                } catch (Exception e) {
                    return false;
                }
                if ( (c[i] == ')' && p == '(') || (c[i] == ']' && p == '[') || (c[i] == '}' && p == '{') ) {
                    continue;
                }
                return false;
            }
        }
        return stack.isEmpty();
    }

    public boolean isValid_2(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character,Character> map = new HashMap<>();
        map.put(')','(');map.put('}','{');map.put(']','[');
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == '}'||c == ')'||c == ']'){
                if(stack.isEmpty()||stack.pop()!=map.get(c)){
                    return false;
                }
            }else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
