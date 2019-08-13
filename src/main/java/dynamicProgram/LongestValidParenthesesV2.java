package dynamicProgram;

import java.util.Stack;

/**
 * fixme Only Java
 */
public class LongestValidParenthesesV2 {

    /**
     * 对于遇到的每个 ‘(’\text{‘(’}‘(’ ，我们将它的下标放入栈中。
     * 对于遇到的每个 ‘)’\text{‘)’}‘)’ ，
     * 我们弹出栈顶的元素并将当前元素的下标与弹出元素下标作差，得出当前有效括号字符串的长度
     * <p>
     * 时间复杂度 O(N)
     * 空间复杂度O（N）
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int maxans = 0;
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()){
                   stack.push(i);
                }else {
                    maxans = Math.max(maxans,i-stack.peek());
                }
            }
        }

        return maxans;
    }

    public static void main(String[] args) {
        int maxLen = new LongestValidParenthesesV2().longestValidParentheses("()((()))");
        System.out.println(maxLen);
//        System.out.println(")()())".charAt(1)=='(');
    }
}
