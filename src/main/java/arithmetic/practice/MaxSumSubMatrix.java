package arithmetic.practice;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/4/11 15:04
 * <p>
 * 给定一个矩阵 matrix，其中矩阵中的元素可以包含正数、负数、和0，返回子矩阵的最大累加和。例如，矩阵 matrix 为：
 * 0 -2 -7 0
 * 9 2 -6 2
 * -4 1 -4 1
 * -1 8 0 -2
 * 拥有最大和的子矩阵为：
 * 9 2
 * -4 1
 * -1 8
 * 其和为15
 * <p>
 * (动态规划  矩阵)
 */
public class MaxSumSubMatrix {

    public static void main(String[] args) {
//        int matrix[][] = {{-1,-1,-1},{-1,2,2},{-81,-7,-1}};
//        maxSumTrix(matrix);

        int[] arr = new int[]{1, 2, -3, 4, -2, 5, -3, -1, 7, 4, -6};
        findArrMaxSum(arr);
    }

    private static void maxSumTrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0)
            return;
        int max = 0;
        int col = matrix[0].length, row = matrix.length;
        for (int i = 0; i < row; i++) {
            int arr[] = new int[col];
            for (int j = i; j < row; j++) {

            }
        }
    }

    //一维数组 最大和的子数组(三重循环)
    private static void findArrMaxSum(int arr[]) {
        int sum, max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j <= i; j++) {
                sum = 0;
                for (int k = j; k <= i; k++) {
                    sum += arr[k];
                    System.out.print("arr[" + k + "] ");
                }
                System.out.println("end....");
                System.out.println();
                if (sum > max) max = sum;
            }
        }
        System.out.println(max);
    }

//    //一维数组 最大和的子数组(两重循环)
//    private static void findArrMaxSum2(int arr[]) {
//        int sum, max = 0;
//        for (int i = 0; i < arr.length; i++) {
//            sum = 0;
//            for (int j = i; j <= arr.length; j++) {
//                sum += arr[j];
//                if (sum > max) max = sum;
//            }
//        }
//        System.out.println(max);
//    }

    //一维数组 最大和的子数组(带记忆的递归)
    private static void findArrMaxSumRecursive(int arr[]) {
        int record[] = new int[arr.length];//recode[1]  ~recode[i] 前i个元素的和
        int max = 0;
        int sum = 0;
        record[0] = 0;
        for (int i = 1; i < arr.length; i++) {
            record[i] = record[i - 1] + arr[i];
        }
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 0; j < i; j++) {
                sum = record[i] - record[j];
                if (sum > max) max = sum;
            }
        }
        System.out.println(max);
    }

}
