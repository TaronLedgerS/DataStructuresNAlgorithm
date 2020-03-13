package CommomAlgorithm.Sorting;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class MinKNumber {

    // 40. 最小的K个数：快速排序的partition()方法：
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] nums, int k) {
       ArrayList<Integer> ret = new ArrayList<>();
       if (k>nums.length || k<=0)
           return ret;
       /* 会改变数组，使得前 k 个数都是最小的 k 个数 */
       binarySearchK(nums, k);
       for (int i = 0;i<k;i++)
           ret.add(nums[i]);
       return ret;

   }
    public void binarySearchK(int[] nums, int k) {
        int l = 0, h = nums.length - 1;
        while (l < h) {
            int m = partition(nums, l, h);
            if (m==k)
                break;
            if (m<k)//不够
                l = m + 1;
            else//多了
                h = m - 1;
        }
    }
    private int partition(int[] nums, int l, int h) {
        int p = nums[l];
        int i = l, j = h;
        while (i < j) {
            while (i<j&&nums[j]>=p) j--;
            if (i < j) {
                nums[i] = nums[j];
                i++;
            }
            while (i<j&&nums[i]<=p) i++;
            if (i<j){
                nums[j] = nums[i];
                j--;
            }
        }
        nums[i] = p;
        return i;
    }

    // 40. 最小的K个数：用大顶堆维护最小的k
    public ArrayList<Integer> GetLeastNumbersUseHeap(int [] nums, int k) {
        if (k>nums.length || k<=0)
            return new ArrayList<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
        for (int num:nums){
            maxHeap.add(num);
            if (maxHeap.size()>k)//当堆中超过k个元素，则去掉最大的那个
                maxHeap.poll();
        }
        return new ArrayList<>(maxHeap);
    }

}
