package CommomAlgorithm.Sorting;


import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] a =  new  int[]{49,38,65,97,76,13,27,49};
        quickSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static  void quickSort(int a[]) {
        partition(a, 0, a.length - 1);
    }

    private static void partition(int a[], int low, int high) {
        if (low>=high) return;
        //初始化基准值
        int i = low, j = high, p = a[low],temp;
        while (i < j) {
            //从右开始找，找到小于p则停止
            while (i<j&&a[j]>=p) j--;
            if (i<j){// 用比基准小的记录替换低位记录
                a[i]=a[j];
                i++;
            }
            //从右开始找，找到大于p则停止
            while (i<j&&a[i]<=p) i++;
            if (i<j) {// 用比基准大的记录替换高位记录
                a[j] = a[i];
                j--;
            }
        }
        //将基准树枝替换a[i]
        a[i] = p;
        //继续分区
        partition(a,low,i-1);
        partition(a,i+1,high);
    }

}
