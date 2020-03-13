package CommomAlgorithm.DynamicProgramming;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DPTest {
    public static void main(String[] args) {

    }
    //剑指19. 正则表达式匹配
    public boolean matchPattern(char[] str, char[] pattern){
        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i=1;i<=n;i++)
            if(pattern[i-1] =='*')
                dp[0][i] = dp[0][i - 2];

        for (int i = 1;i<=m;i++)
            for (int j = 1;j<=n;j++)
                if (str[i-1]==pattern[j-1]||pattern[j-1]=='.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pattern[j-1]=='*')
                    if (pattern[j-2]==str[i-1]||pattern[j-2]=='.'){
                        dp[i][j] |= dp[i][j - 1];// a* counts as single a
                        dp[i][j] |= dp[i-1][j];// a* counts as multiple a
                        dp[i][j] |= dp[i][j - 2];// a* counts as empty
                    }else
                        dp[i][j] = dp[i][j - 2];   // a* only counts as empty
        return dp[m][n];
    }

    //10.1 斐波那契数列
    private int[] f = new int[40];
    public int Fibonacci(int n) {
        f[0] = 0;
        f[1] = 1;
        for (int i = 2; i <= n; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f[n];
    }

    //10.2 矩形覆盖，递推公式依然是dp[n]=dp[n-1]+dp[n-2]
    public int RectCover(int n) {
        if (n<=2) return n;
        int a = 1, b = 2;
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = a + b;
            a = b;
            b = ans;
        }
        return ans;
    }

    //10.3 跳台阶递推公式依然是dp[n]=dp[n-1]+dp[n-2]
    public int JumpFloor(int n) {
        if (n<=2) return n;
        int a = 1, b = 2;
        int ans = 0;
        for (int i = 3; i <= n; i++) {
            ans = a + b;
            a = b;
            b = ans;
        }
        return ans;
    }

    //10.4 变态跳台阶dp[n]=dp[n-1]+...+dp[1]
    public int JumpFloorII(int n) {
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {//一次跳步骤
                dp[i] += dp[i - j];
            }
        }
        return dp[n];
    }

    //剑指14. 剪绳子
    public int cutRope(int n) {
        int[] dp = new int[n + 1];
        dp[1]=1;
        for (int i = 2; i<=n;i++)
            for (int j = 1;j<i;j++)
                dp[i] = Math.max(dp[i], Math.max(j*(i-j),dp[i-j]*j));
        return dp[n];
    }

    //剑指42. 连续子数组的最大和
    public int FindGreatestSumOfSubArray(int[] nums) {
        if (nums==null||nums.length==0) return 0;
        int[] dp = new int[nums.length];//由于每次只用前一个，所以可以不开空间，用sum替代即可
        dp[0] = nums[0];
        int ans = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);//sum = Math.max(nums[i], sum + nums[i]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    //剑指60
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        final int face = 6;
        final int pointNum = face * n;
        long[][] dp = new long[n + 1][pointNum + 1];

        for (int i = 1; i <= face; i++)
            dp[1][i] = 1;

        for (int i = 2; i <= n; i++)
            for (int j = i; j <= pointNum; j++)     /* 使用 i 个骰子最小点数为 i */
                for (int k = 1; k <= face && k <= j; k++)
                    dp[i][j] += dp[i - 1][j - k];

        final double totalNum = Math.pow(6, n);
        List<Map.Entry<Integer, Double>> ret = new ArrayList<>();
        for (int i = n; i <= pointNum; i++)
            ret.add(new AbstractMap.SimpleEntry<>(i, dp[n][i] / totalNum));

        return ret;
    }
    //47. 礼物的最大价值
    public int getMost(int[][] values) {
        if (values == null || values.length == 0 || values[0].length == 0)
            return 0;
        int n = values[0].length;
        int[] dp = new int[n];
        for (int[] value : values) {
            dp[0] += value[0];
            for (int i = 1; i < n; i++)
                dp[i] = Math.max(dp[i], dp[i - 1]) + value[i];
        }
        return dp[n - 1];
    }

}
