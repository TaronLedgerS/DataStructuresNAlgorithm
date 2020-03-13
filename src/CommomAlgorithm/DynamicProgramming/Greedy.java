package CommomAlgorithm.DynamicProgramming;

public class Greedy {
    //剑指14. 剪绳子
    public int cutRope(int n) {
        if (n < 2)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        int timesOf3 = n / 3;
        if (n - timesOf3 * 3 == 1)
            timesOf3--;
        int timesOf2 = (n - timesOf3 * 3) / 2;
        return (int) (Math.pow(3, timesOf3)) * (int) (Math.pow(2, timesOf2));
    }

    //63. 股票的最大利润,Leetcode：121. Best Time to Buy and Sell Stock
    public int maxProfit(int[] prices) {
        if (null==prices||prices.length==0) return 0;
        int maxProfit = 0;
        int minBuy = prices[0];
        for (int i = 1; i < prices.length; i++) {
            maxProfit = Math.max(maxProfit, prices[i] - minBuy);
            minBuy = Math.min(minBuy, prices[i]);
        }
        return maxProfit;
    }


}
