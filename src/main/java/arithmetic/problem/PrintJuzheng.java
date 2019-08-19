package arithmetic.problem;

/**
 * @auther zhangyu(dpzain)
 * @date 2019/3/29 22:41
 * <p> 正整数矩阵
 * 1   2   3   4   5
 * 6   7   8   9   10
 * 11  12  13  14  15
 * 16  17  18  19  20
 * <p>
 * print 1 2 3 4 5 10 15 20 19 18 17 16 11 6 7 8 9 14 13  12  顺时针打印
 */
public class PrintJuzheng {

    public static void main(String[] args) {
        int[][] arr = new int[200][200];
        int n = 4;
        int m = 5;
        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(count + " ");
                arr[i][j] = count++;
            }
            System.out.println();
        }
        printJuzhen(arr,0,-2,-2);


        //长款相等的矩阵
//        int[][] num = new int[100][100];
//        int n = 5;
//        int count =1;
//
//        for(int i=0;i<n;i++){
//            for(int j =0;j<n;j++){
//                System.out.print(count + " ");
//                num[i][j]=count++;
//            }
//            System.out.println();
//        }
//        output(num,0,n-1);

    }

    //TODO  矩阵 长宽都不确定的情况下顺时针遍历
    private static void printJuzhen(int[][] arr,int start,int end,int h){
        if(start>end && end!=-2) return;
        int temp=-1;
        if(end==-2){//第一次遍历行
            for (int i = 0; i >-1; i++) {
                if(arr[start][i]==0)//没有值 为0
                    break;
                temp++;
                System.out.print(arr[start][i]+" ");
            }
            end=temp;//end 为下标 行数=1 从-1开始
        }else{
            for (int i = start; i <=end; i++) {
                System.out.print(arr[start][i]+" ");
            }
        }


        int temp2=0;
        if(h==-2){
            for (int i=start+1;i>0;i++){
                if(arr[i][end]==0)
                    break;
                temp2++;//从start+1行开始  下标从0开始
                System.out.print(arr[i][end]+" ");
            }
            h=temp2;
        }else{
            for(int i=start+1;i<=h;i++){
                System.out.print(arr[i][end]+" ");
            }
        }


        for (int i = end-1; i >=start ; i--) {
            System.out.print(arr[h][i]+" ");
        }

        for (int i = h-1; i >start ; i--) {
            System.out.print(arr[i][start]+" ");
        }

        printJuzhen(arr,start+1,end-1,h-1);
    }

    //长宽相等的情况
    public static void output(int[][] num,int start,int end){
        if(start>end || end<=0)return;
        for(int i=start;i<=end;i++){
            System.out.print(num[start][i]+" ");
        }
        for(int i=start+1;i<=end;i++){
            System.out.print(num[i][end]+" ");
        }

        for(int i=end-1;i>=start;i--){
            System.out.print(num[end][i]+"*");
        }
        for(int i=end-1;i>start;i--){
            System.out.print(num[i][start]+" ");
        }

        output(num,start+1,end-1);
    }
}
