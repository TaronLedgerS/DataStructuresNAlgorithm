package CommomAlgorithm.Other;

public class MathRelated {
    //剑指16. 数值的整数次方
    public double Power(double base, int exponent) {
        if (exponent==0) return 1;
        if (exponent==1) return base;
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        double pow = Power(base * base, exponent / 2);
        if (exponent%2!=0)
            pow = pow * base;
        return isNegative ? 1 / pow : pow;
    }

    //剑指43. 从 1 到 n 整数中 1 出现的次数
    public int NumberOf1Between1AndN_Solution(int n) {
        int cnt = 0;
        for (int m = 1; m <= n; m *= 10) {
            int a = n / m, b = n % m;
            cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
        }
        return cnt;
    }

    //剑指49. 丑数：只包含质因子2、3和5的数，把 1 当做是第一个丑数
    public int GetUglyNumber_Solution(int N) {
        if (N<=6) return N;
        //指向小于newUgly且最大的乘以2\3\5后可能成为下一个丑数的丑数
        int i2 = 0, i3 = 0, i5 = 0;
        int[] dp = new int[N];
        dp[0] = 1;
        for (int i = 1; i < N; i++) {
            int next2 = dp[i2] * 2, next3 = dp[i3]*3, next5 = dp[i5] * 5;
            dp[i] =Math.min(next2,Math.min(next3,next5));
            if (dp[i]==next2) i2++;
            if (dp[i]==next3) i3++;
            if (dp[i]==next5) i5++;

        }
        return dp[N-1];
    }

    //62. 圆圈中最后剩下的数
    public int LastRemaining_Solution(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        if (n==1) return 0;
        return (LastRemaining_Solution(n - 1, m) + m) % n;
    }
    public int LastRemaining(int n, int m) {
        if (n <= 0 || m <= 0) {
            return -1;
        }
        int ans = 0;
        for (int i = 2; i <= n; i++) {
            ans = (ans + m) % i;
        }
        return ans;
    }

}
