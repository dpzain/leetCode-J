package arithmetic.bitOperation;

import java.util.Random;

/**
 * 1—1000这1000个数放在大小为1001的数组中，只有唯一的一个元素值重复，其他均只出现一次。
 * 每个数组元素只能访问一次，设计一个算法，将这个重复的元素找出来。不用辅助存储空间，
 * 能否设计一个算法实现？
 *
 * 按位^ 异或: a^a = 0，b^0 = b
 * 所以 1—1000中只有一个数是重复的，如果我们直接将这1001个数进行异或，就会将这个重复的数给消去
 * 所以 （1^2^3^...^K^...^K^...^999^1000）^（1^2^3^...^998^999^1000）=  K 即为重复的数字
 * @auther zhangyu(dpzain)
 * @date 2019/10/24 23:33
 */
public class FindReatNumber {

    public static void main(String[] args) {

        int N = 1001;
        int[] arr = new int[N];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }

        // 随机下标index：模拟重复元素所在的位置
        int index = new Random().nextInt(N);

        arr[arr.length - 1] = arr[index];

        // 将arr[index]置为1—1000中的一个随机数 ; 这样 重复的数字就为 随机的数，且位置不确定
        int a;
        arr[index] = a=new Random().nextInt(N - 1) + 1;
        System.out.println(a);


        int x = 0;

        for (int i = 0; i < N; i++) {
            x ^= arr[i];
        }

        for (int i = 1; i < N; i++) {
            x = x ^ i;
        }



        System.out.println(x);
    }
}
