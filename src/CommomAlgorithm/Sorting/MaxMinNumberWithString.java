package CommomAlgorithm.Sorting;

import java.util.Arrays;


public class MaxMinNumberWithString {
    public static void main(String[] args) {
        int[] A = new int[]{45, 7, 9};
        System.out.println(new MaxMinNumberWithString().MaxNumber(A));
        Arrays.sort(A);
        System.out.println(Arrays.toString(A));
        System.out.println(new MaxMinNumberWithString().PrintMinNumber(A));
    }

    //输入一个非负的整形数组，然后输出使用输入数组里的元素组合成的最大数字,输入[45，9，7]，输出字符串“9745”
    public String MaxNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0;i<nums.length;i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (s1, s2) -> {
            String c1 = s1 + s2;
            String c2 = s2 + s1;
            return c2.compareTo(c1);
        });
        StringBuilder sb = new StringBuilder();
        for (String str:strs)
            sb.append(str);

        return sb.toString();
    }

    //剑指45. 把数组排成最小的数
    public String PrintMinNumber(int [] numbers) {
        String[] numStr = new String[numbers.length];
        for (int i = 0;i<numbers.length;i++)
            numStr[i] = String.valueOf(numbers[i]);
        Arrays.sort(numStr,(o1,o2)->{
            String s1 = o1 + o2;
            String s2 = o2 + o1;
            return s1.compareTo(s2);
        });

        StringBuilder sb = new StringBuilder();
        for (String n:numStr)
            sb.append(n);
        return sb.toString();
    }

}
