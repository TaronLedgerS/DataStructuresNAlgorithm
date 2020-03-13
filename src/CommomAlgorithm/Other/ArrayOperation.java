package CommomAlgorithm.Other;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayOperation {

    public static void main(String[] args) {
        new ArrayOperation().reOrderArray(new int[]{1,2,3,4,1});
    }

    //剑指3. 数组中重复的数字
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        if (null==numbers||numbers.length<=0)
            return false;
        int cnt_0 = 0;
        int cnt_0_index = 0;
        for (int n : numbers) {
            int num = Math.abs(n);
            if (num==0){
                cnt_0++;
                if (cnt_0>1) {
                    duplication[0] = num;
                    return true;
                }
            }
            if (numbers[num] == 0) {
                cnt_0_index++;
                if (cnt_0_index>1){
                    duplication[0] = num;
                    return true;
                }
            }
            if (numbers[num]<0){
                duplication[0] = num;
                return true;
            }
            numbers[num] = -numbers[num];
        }
        return false;
    }
    //剑指4. 二维数组中的查找
    public boolean Find(int target, int [][] array) {
        if (null==array||array.length==0||array[0].length==0) return false;
        int rows = array.length, cols = array[0].length;
        int r = 0, c = cols - 1;//右上角开始
        while (r < rows && c >= 0) {
            if (target==array[r][c])
                return true;
            if (target>array[r][c])
                r++;
            else
                c--;
        }
        return false;
    }

    //剑指21. 调整数组顺序使奇数位于偶数前面
    public void reOrderArray(int[] nums) {
        // 奇数个数
        int oddCnt = 0;
        for (int x : nums)
            if (x % 2 == 1)
                oddCnt++;
        int[] copy = nums.clone();
        int i = 0, j = oddCnt;
        for (int num : copy) {
            if (num % 2 == 1)
                nums[i++] = num;
            else
                nums[j++] = num;
        }
    }
    //剑指29. 顺时针打印矩阵
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> ret = new ArrayList<>();
        int up = 0, down = matrix.length - 1, left = 0, right = matrix[0].length - 1;
        while (up <= down && left <= right) {
            for (int i = left;i<=right;i++)
                ret.add(matrix[up][i]);
            for (int i = up+1;i<=down;i++)
                ret.add(matrix[i][right]);
            if (up!=down)
                for (int i = right-1;i>=left;i--)
                    ret.add(matrix[down][i]);
            if (left!=right)
                for (int i = down-1;i>up;i--)
                    ret.add(matrix[i][left]);
             up++;down--;left++;right--;
        }
        return ret;
    }

    //剑指39. 数组中出现次数超过一半的数字:多数投投票算法-先找出多数元素，再统计
    public int MoreThanHalfNum_Solution(int [] array) {
        int majority = array[0];
        int cnt = 1;//当前多数元素的统计数
        //找出多数元素
        for (int num : array) {
            if (num==majority) cnt++;
            else cnt--;
            if (cnt == 0) {
                majority = num;
                cnt++;
            }
        }
        cnt = 0;//验证是否超过一半
        for (int num : array) {
            if (num==majority)
                cnt++;
        }
        return cnt > array.length / 2 ? majority : 0;
    }

    //57.1 和为 S 的两个数字
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        ArrayList<Integer> ret = new ArrayList<>();
        if (array==null||array.length<=1) return ret;
        int left = 0, right = array.length - 1;
        while (left<right){
            int t = array[left] + array[right];
            if (t==sum){
                ret.add(array[left]);
                ret.add(array[right]);
                return ret;
            }
            if (t<sum)
                left++;
            else
                right--;
        }
        return ret;
    }

    //57.2 和为 S 的连续正数序列
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> ret = new ArrayList<>();
        int start = 1,end = 2;
        int curSum = 3;
        while (end<sum){
            if (curSum<sum){
                end++;
                curSum += end;
            }else if (curSum>sum){
                curSum -= start;
                start++;
            }else {
                ArrayList<Integer> oneRet = new ArrayList<>();
                for (int i = start;i<=end;i++)
                    oneRet.add(i);
                ret.add(oneRet);
                curSum -= start;
                start++;
                end++;
                curSum += end;
            }
        }
        return ret;
    }

    //61. 扑克牌顺子
    public boolean isContinuous(int [] numbers) {
        if (numbers==null||numbers.length<5) return false;
        Arrays.sort(numbers);
        int cnt=0;
        for (int n :numbers)
            if (n==0)
                cnt++;
        for (int i = cnt;i<numbers.length-1;i++)   {
            if (numbers[i+1]==numbers[i])
                return false;
            cnt -= numbers[i + 1] - numbers[i] - 1;
        }
        return cnt >= 0;
    }

    //66. 构建乘积数组
    public int[] multiply(int[] A) {
        int n = A.length;
        int[] B = new int[n];
        for (int i = 0, product = 1; i < n; product *= A[i], i++)       /* 从左往右累乘 */
            B[i] = product;
        for (int i = n - 1, product = 1; i >= 0; product *= A[i], i--)  /* 从右往左累乘 */
            B[i] *= product;
        return B;
    }

}
