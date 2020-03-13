package CommomAlgorithm.Sorting;

import java.util.Arrays;

public class MergeSort {
    public static void main(String[] args) {
        int[] a =  new  int[]{49,38,65,97,76,13,27,49};
        MergeSort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    public static void MergeSort(int[] nums,int low,int high)  {
        int mid = low + (high - low) / 2;
        if (low < high) {
            MergeSort(nums, low, mid);
            MergeSort(nums, mid + 1, high);
            merge(nums, low, mid, high);
        }
    }
    private static void merge(int[] nums,int low,int mid,int high) {
        int[] tmp = new int[high - low + 1];
        int i = low;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= high) {
            if (nums[i]<nums[j])
                tmp[k] = nums[i++];
            else
                tmp[k] = nums[j++];
            k++;
        }
        while (i<=mid)
            tmp[k++] = nums[i++];
        while (j<=high)
            tmp[k++] = nums[j++];
        for (i = 0;i<tmp.length;i++)
            nums[i+low] = tmp[i];
    }
}
