package leetCode.dynamicProgram;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/7/25 22:12
 */

public class RegularMatchV2 {

    Result[][] memo;

    /**
     * 动态规划  自顶向下
     *
     * @param text
     * @param pattern
     * @return
     */
    private boolean isMatch(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);

    }

    private boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {
            return memo[i][j] == Result.TRUE;
        }

        boolean ans;

        if (j == pattern.length()) {
            ans = i == text.length();
        } else {
            boolean first = (i < text.length() && (text.charAt(i) == pattern.charAt(j) || pattern.charAt(j) == '.'));
            if(j+1 < pattern.length() && pattern.charAt(j+1)=='*'){
                ans = (dp(i,j+2,text,pattern) || (first && dp(i+1,j,text,pattern)));
            }else{
                ans = first && dp(i+1,j+1,text,pattern);
            }
        }
        memo[i][j] = ans?Result.TRUE : Result.FALSE;
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(new RegularMatchV2().isMatch("aaaaa","a.*"));
    }
}

enum Result {
    TRUE, FALSE
}


