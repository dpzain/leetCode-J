package leetCode.dynamicProgram;

import java.util.Arrays;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * <p>
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * <p>
 * https://leetcode-cn.com/problems/maximum-subarray/
 *
 * @auther zhangyu(dpzain)
 * @date 2019/9/1 15:21
 */
public class MaxSubArrayV1 {

    /**
     * 动态规划
     * @param arr
     * @return
     */
    private static int maxSubArrSum(int[] arr) {
        int[] dp = new int[arr.length];//dp[i] 表示arr前i个元素组成的子数组最大的和
        dp[0]=arr[0];
        int maxSum = arr[0];
        int start = 0,end = 0,temp=0;
        for (int i = 1; i < arr.length; i++) {
            if(dp[i-1]>0){
                dp[i]=dp[i-1]+arr[i]; //动态转移方程
            }else{
                dp[i]=arr[i];
                start=i;
            }
            if(dp[i]>maxSum){
                end=i;
                maxSum=dp[i];
            }
        }
        System.out.println(start+"--"+end);
        return maxSum;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArrSum(arr));
    }

}
