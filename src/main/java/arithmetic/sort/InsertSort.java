package arithmetic.sort;

import java.util.Arrays;

/**
 * 插入排序
 * @auther zhangyu(dpzain)
 * @date 2019/9/18 22:31
 */
public class InsertSort {

    public static void sort(int[] arr) {
        if (arr.length >= 2) {
            for (int i = 1; i < arr.length; i++) {
                //挖出一个要用来插入的值,同时位置上留下一个可以存新的值的坑
                int x = arr[i];
                int j = i - 1;
                //在前面有一个或连续多个值比x大的时候,一直循环往前面找,将x插入到这串值前面
                while (j >= 0 && arr[j] > x) {
                    //当arr[j]比x大的时候,将j向后移一位,正好填到坑中
                    arr[j + 1] = arr[j];
                    j--;
                }
                //将x插入到最前面
                arr[j + 1] = x;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr ={16, 7, 3, 20, 17, 8, 1, 3, 3, 28, 92, 6};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
