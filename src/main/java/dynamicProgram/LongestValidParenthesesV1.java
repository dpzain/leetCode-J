package dynamicProgram;

import java.util.Stack;

/**
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * @auther zhangyu(dpzain)  fixme Only Java
 * @date 2019/8/7 22:45
 * <p>
 * Parentheses 圆括号 ()
 * braces 大括号   {}
 * brackets  方括号   []
 */
public class LongestValidParenthesesV1 {

    /**
     * 有效括号 定为偶数 穷举 所有偶数子串 判断
     * 时间复杂度 O(N^2)
     * 空间复杂度O（N）
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {

        int maxlen = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 2; j <= s.length(); j += 2) {
                if (isValid(s.substring(i, j))) {
                    maxlen = Math.max(maxlen, j - i);
                }
            }
        }
        return maxlen;

    }

    /**
     * 偶数子串是否有效
     *
     * @param subStr
     * @return
     */
    private boolean isValid(String subStr) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < subStr.length(); i++) {
            char c = subStr.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')' && !stack.isEmpty()) {
                stack.pop();
            } else {
                //)()) 包含有效子串也无效 因为这是穷举法  有效的()也会遍历到
                return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        int maxLen = new LongestValidParenthesesV1().longestValidParentheses("()((()");
        System.out.println(maxLen);
//        System.out.println(")()())".charAt(1)=='(');
    }
}
