package arithmetic.bitOperation;

import org.junit.Test;

/**
 * 常见位运算小技巧
 *        a: 判断一个数是否是奇数     a & 1 == 1;
 * 　　　　b: 一个数 对2 ^ n 取余。    a & (2^n - 1);
 * 　　　　c: 如何判断一个数是否是2^n   a & (a-1) == 0；
 * 　　　　d: 两个整数之间的交换；
 * 　　　　e: 用最有效率的方式求2 * 8的值 2 << 3;
 * @auther zhangyu(dpzain)
 * @date 2019/10/24 22:21
 */

public class BitOprationDemo {


    /**
     * 一个数 对2 ^ n 取余。    a & (2^n - 1)
     */
    @Test
    public void modBit(){
        int a=2019;
        System.out.println(a%64);
        System.out.println(a & (64-1));
    }


    /**
     * 如何判断一个数是否是2^n   a & (a-1) == 0
     */
    @Test
    public void judge2N(){
        int a = 1024;
        System.out.println((a & (a - 1)) == 0);
    }

    @Test
    public void swapInt(){
        // 方式一
        /*int a = 4;
        int b = 3;
        System.out.println("a=" + a + ", b=" + b);
        int temp = a;
        a = b;
        b = temp;
        System.out.println("a=" + a + ", b=" + b);*/

        // 方式二
        // 加法和减法互为逆运算。
        /*int a = 4;
        int b = 3;
        System.out.println("a=" + a + ", b=" + b);
        a = a + b; // a = 4 + 3, b = 3;
        b = a - b; // a = 4 + 3, b = 4 + 3 - 3 = 4;
        a = a - b; // a = 4 + 3 - 4 = 3, b = 4;
        System.out.println("a=" + a + ", b=" + b);*/

        // 方式三
        /*int a = 4;
        int b = 3;
        System.out.println("a=" + a + ", b=" + b);
        a = a ^ b; // a = 4 ^ 3, b = 3
        b = a ^ b; // a = 4 ^ 3, b = 4 ^ 3 ^ 3 = 4;
        a = a ^ b; // a = 4 ^ 3 ^ 4 = 3, b = 4;
        System.out.println("a=" + a + ", b=" + b);*/

        // 方式四
        int a = 4;
        int b = 3;
        System.out.println("a=" + a + ", b=" + b);
        a = a ^ b;
        b = b ^ a;
        a = a ^ b;
//        a = (a ^ b) ^ (b = a); //工作中千万别这样写, 太show了。写代码，简洁易懂。
        System.out.println("a=" + a + ", b=" + b);
    }

}
