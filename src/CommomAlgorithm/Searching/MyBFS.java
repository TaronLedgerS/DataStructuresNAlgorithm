package CommomAlgorithm.Searching;

import java.util.LinkedList;
import java.util.Queue;

public class MyBFS {
    public static void main(String[] args) {
        System.out.println(new MyBFS().movingCount(5,10,10));
    }
    //剑指13. 机器人的运动范围
    public int movingCount(int threshold, int rows, int cols){
        final int[][] next = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};
        if (threshold<0) return 0;
        boolean[][] visited = new boolean[rows][cols];
        int cnt= 0;
        Queue<Integer> queueRows = new LinkedList<>();
        Queue<Integer> queueCols = new LinkedList<>();
        queueCols.offer(0);
        queueRows.offer(0);
        visited[0][0] = true;
        while (!queueRows.isEmpty()) {
            int c = queueCols.poll();
            int r = queueRows.poll();
            cnt++;
            for (int[] n : next) {
                int nextr = r + n[0];
                int nextc = c + n[1];
                if (nextr<0||nextr>=rows||nextc<0||nextc>=cols)
                    continue;
                if (visited[nextr][nextc])
                    continue;
                if (digitSum(nextr)+digitSum(nextc)>threshold)
                    continue;
                //复合条件加入待搜索队列
                queueCols.offer(nextc);
                queueRows.offer(nextr);
                visited[nextr][nextc] = true;
            }
        }
        return cnt;
    }
    private int digitSum(int a) {
        int sum = 0;
        while (a>0){
            sum += a % 10;
            a = a / 10;
        }
        return sum;
    }

}
