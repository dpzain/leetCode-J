package leetCode.dynamicProgram;


/**
 * 正则表达式匹配问题
 * 给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * <p>
 * 所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
 * <p>
 * 说明:
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *
 * 来源：LeetCode
 * 链接：https://leetcode-cn.com/problems/regular-expression-matching
 *
 * @auther zhangyu(dpzain)
 * @date 2019/7/25 22:12
 */
public class RegularMatchV1 {

    /**
     * 回溯 暴力递归
     * @param text
     * @param pattern
     * @return
     */
    private static boolean isMatch(String text, String pattern) {
        if (pattern.isEmpty()) return text.isEmpty();
        boolean first_match = (!text.isEmpty() && (pattern.charAt(0) == text.charAt(0) || pattern.charAt(0) == '.'));
        if (pattern.length() >= 2 && pattern.charAt(1) == '*') {
            //如果此次递归 发现有字符和* 结合
            //1 要么匹配0个  2  要么匹配多个   ( 匹配多个 移动text 前提是第一个至少匹配 )
            return (isMatch(text, pattern.substring(2)) || (first_match && isMatch(text.substring(1), pattern)));
        } else {
            return first_match && isMatch(text.substring(1), pattern.substring(1));
        }

    }

    public static void main(String[] args) {
        System.out.println( isMatch("aaaaab", "a.*"));
    }
}
