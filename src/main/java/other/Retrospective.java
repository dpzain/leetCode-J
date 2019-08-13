package other;

/**
 * @auther zhangyu(dpzain)  fixme Only Java
 * @date 2019/8/5 22:33
 * <strong>回溯法</strong>经典案例
 * <p>
 * 回溯算法是一种算法思想，是我们解决问题的策略；而递归是一种算法结构。递归就是函数调用本身，一般回溯法多用递归来实现。
 */
public class Retrospective {


    /**
     * 楼梯问题 当前次数
     */
    private static int s = 1;

    /**
     * 楼梯问题 存储次数对应的步数
     */
    private static int[] steps = new int[100];


    private static int num = 1; //  方案的总数
    private static final int MAX_QUEEN = 8;
    private static int[] position = new int[MAX_QUEEN];//position[row]=colum 第row行 colum列

    /**
     * 爬楼梯问题
     * 对于一个与 n 级台阶组成的楼梯，爬楼梯时一次可以上 1 级台阶或 2 级台阶。共有多少种不同的走法。
     *
     * @param n n阶 台阶
     */
    static void tryStep(int n) {
        for (int i = 1; i <= 2; i++) {
            if (n < i)
                break;
            steps[s++] = i; //一步 第s次 走了i级台阶
            n -= i;
            if (n == 0) {
                for (int j = 1; j < s; j++) {
                    System.out.println("第" + j + "步走了" + steps[j]
                            + "级台阶 ");
                }
                System.out.println();
            } else {
                tryStep(n);
            }
            n += i;
            s--;
//            steps[s--]=0;
        }
    }


    /**
     * 八皇后问题
     * 在国际象棋棋盘8 × 8上放置八个皇后，使得任意两个皇后之间不能在同一行，同一列，也不能位于同于对角线上。问共有多少种不同的方法，并且指出各种不同的放法
     * 不在同一列:position[i] != position[j];
     * 不在对角线上:|i − j| != |position[i] − position[j]|.
     *
     * @param row 放置从第row行开始放置
     */
    public static void eightQueen(int row) {
        // 如果摆完MAX_QUEEN行，则输出结果
        if (row == MAX_QUEEN) {
            print();
            return;
        }
        for (int column = 0; column < MAX_QUEEN; column++) {
            position[row] = column; // 放在第row行第column列
            // 如果满足条件，则进行下一行
            if (isValid(row))
                eightQueen(row + 1);
        }
    }

    /**
     * 判断位置是否满足条件
     *
     * @param row
     * @return
     */
    private static boolean isValid(int row) {
        for (int i = 0; i < row; i++) {
            /**
             * 如果前面放好的位置不在同一行、同一列、同一对角线
             * 则返回true
             * 否则返回false
             */
            if (position[i] == position[row] ||
                    Math.abs(position[i] - position[row]) ==
                            Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    private static void print() {
        System.out.println("第" + num++ + "种摆法：");
        for (int i = 0; i < MAX_QUEEN; i++) {
            for (int j = 0; j < MAX_QUEEN; j++) {
                if (position[i] == j)
                    System.out.print("+ ");
                else
                    System.out.print("0 ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void main(String[] args) {
// 1 、爬楼梯问题
// tryStep(5);
        //2、八皇后问题
        eightQueen(0);
    }

}
