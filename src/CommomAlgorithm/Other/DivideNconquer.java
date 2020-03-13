package CommomAlgorithm.Other;

import java.util.logging.Level;

public class DivideNconquer {

    public static void main(String[] args) {
        System.out.println(new DivideNconquer().GetNumberOfK(new int[]{1,2,3,3,3},3));
    }

    //剑指11. 旋转数组的最小数字
    public int minNumberInRotateArray(int [] nums) {
        if (nums.length == 0)  return 0;
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (nums[m]<=nums[h])
                h = m;
            else
                l = m + 1;
        }
        return nums[l];
    }

    //51. 数组中的逆序对-归并排序
    private  long cnt = 0;
    private int[] tmp;
    public int InversePairs(int [] nums) {
        tmp = new int[nums.length];
        mergeSort(nums, 0, nums.length - 1);
        return (int) (cnt%1000000007);

    }
    private void mergeSort(int[] nums, int l, int h) {
        if (h-l<1) return;
        int m = l + (h - l) / 2;
        mergeSort(nums, l, m);
        mergeSort(nums, m + 1, h);
        merge(nums, l, m, h);
    }
    private void merge(int[] nums, int l, int m, int h) {
        int i = l, j = m + 1, k = l;
        while (i <= m && j <= h) {
            if (nums[i]>nums[j]){
                tmp[k] = nums[j++];
                cnt += m - i + 1; //num[i]后面的都比num[j]大，都可以构成逆序对
            }else
                tmp[k] = nums[i++];
            k++;
        }
        while (i<=m)
            tmp[k++] = nums[i++];
        while (j<=h)
            tmp[k++] = nums[j++];
        for (k = l;k<=h;k++)
            nums[k] = tmp[k];
    }

    //剑指53. 数字在排序数组中出现的次数
    public int GetNumberOfK(int [] nums , int k) {
        if (null==nums||nums.length==0) return 0;
        int cnt = 0;
        int l = 0, h = nums.length - 1;
        while (l<h){
            int m = l + (h - l) / 2;
            if (nums[m]==k){
                l = m;
                break;
            }
            if(nums[m]<k)
                l = m+1;
            else
                h = m;
        }
        //向前
        if (nums[l]==k) cnt++;
        else return 0;
        int i = l-1;
        while (i>=0&&nums[i]==k){
            cnt++;
            i--;
        }
        i= l+1;
        while (i<nums.length && nums[i]==k){
            cnt++;
            i++;
        }
        return cnt;
    }
}
