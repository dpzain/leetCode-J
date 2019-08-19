package leetCode.dynamicProgram;

/**
 * @Author dpzain
 * @Description
 * @Date Created in 20:57 2019/7/24
 */
public class LongestPalindromeStringV2 {
    public static String longestPalindrome(String arrStr) {
        int size = arrStr.length();
        if (size <= 1)
            return arrStr;
        String[] maxArr = new String[0];
        int longLength = 0;
        boolean[][] dp = new boolean[size][size];
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] =String.valueOf(arrStr.charAt(i));
        }
        for (int r = 1; r < size; r++) {
            for (int l = 0; l < r; l++) {
//                System.out.println(l+"-"+r);
                if (arr[l].equals(arr[r]) && (r - l <= 2 || dp[l + 1][r - 1])) {
                    dp[l][r] = true;
//                    System.out.println(l+"----"+r);
                    if (r - l + 1 > longLength) {
                        String[] curent_longArr = new String[r - l + 1];
                        int i = 0;
                        for (int k = l; k <= r; k++) {
//                            System.out.print(arr[k]);
                            curent_longArr[i] = arr[k];
                            i++;
                        }
//                    System.out.println();
//                    System.out.println("-----");
                        maxArr = curent_longArr;
                        longLength = maxArr.length;
                    }
                }
            }
        }
        StringBuilder builder  = new StringBuilder();
        for (String s1 : maxArr) {
            builder.append(s1);
        }
        return builder.toString();
    }

    public static void main(String[] args) {
//        String[] arr = {"a", "b", "c", "b", "a", "b", "d", "e"};
       String strings = longestPalindrome("abcbabde");
        System.out.println(strings);
    }
}
