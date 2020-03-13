package CommomAlgorithm.Other;

import javax.swing.*;

public class BitOperation {
    /*
    n       : 10110100
    n-1     : 10110011
    n&(n-1) : 10110000
    */
    //剑指15. 二进制中 1 的个数
    public int NumberOf1(int n) {
        int cnt=0;
        while (n != 0) {
            n = n & (n - 1);
            cnt++;
        }
        return cnt;
    }

    //56. 数组中只出现一次的数字
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int diff = 0;
        for (int n:array)
            diff ^= n;
        diff = diff&(-diff);//提取出最右侧的不相同位
        for (int n : array) {
            if ((diff&n)==0)//根据不相同位分组，使得a，b分别落入不同的两组中
                num1[0] ^= n;
            else
                num2[0] ^= n;
        }
    }

    //64. 求 1+2+3+...+n
    public int Sum_Solution(int n) {
        int sum = n;
        //短路计算
        boolean Nagetive = (n>0) && ((sum += Sum_Solution(n - 1))>0);
        return sum;
    }

    //65. 不用加减乘除做加法
    //a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
    public int Add(int a,int b) {
        //递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止
        return b == 0 ? a : Add(a ^ b, (a & b) << 1);
    }


    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(22));
        System.out.println(Integer.toBinaryString(-22));
        System.out.println(Integer.toBinaryString(22&(-22)));
    }
}
