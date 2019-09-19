package arithmetic.sort;

/**
 * @auther zhangyu(dpzain) 选择排序
 * @date 2019/9/18 21:31
 */
public class SelectSort {
    public static void main(String[] args) {
        //模拟数据
        int[] array = {52,63,14,59,68,35,8,67,45,99};
        System.out.println("原数组：");
        for (int i : array) {
            System.out.print(i+" ");
        }
        System.out.println();
        selectSort(array);
        System.out.println("排序后：");
        for (int i : array) {
            System.out.print(i+" ");
        }
    }

    public static void selectSort(int[] arr){
        for(int i = 0; i < arr.length-1; i++){
            int min = i;
            for(int j = i+1; j <arr.length-1;j++){
                if(arr[j]<arr[min]){
                    min = j;
                }
            }
            if(min!=i){
                swap(arr, i, min);
            }
        }
    }
    //完成数组两元素间交换
    public static void swap(int[] arr,int a,int b){
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
