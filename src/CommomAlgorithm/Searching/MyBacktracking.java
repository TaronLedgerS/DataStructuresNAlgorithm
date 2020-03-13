package CommomAlgorithm.Searching;

import java.util.ArrayList;
import java.util.Arrays;

public class MyBacktracking {
    public static void main(String[] args) {

    }
    //剑指12. 矩阵中的路径
    public boolean hasPath(char[] matrix, int rows, int cols, char[] str){
        //建矩阵
        char[][] matrixForPath = new char[rows][cols];
        for (int i = 0,index=0;i<rows;i++)
            for (int j = 0;j<cols;j++)
                matrixForPath[i][j] = matrix[index++];
        //开始回溯查找
        for (int i = 0;i<rows;i++)
            for (int j = 0;j<cols;j++)
                if (backtrackingForPath(matrixForPath,new boolean[rows][cols],str,0,i,j))
                    return true;
        return false;
    }
    //四个方向
    private final static int[][] next={{0,-1},{0,1},{-1,0},{1,0}};
    public boolean backtrackingForPath(char[][] matrix,boolean[][] visited, char[] str, int index, int r,int c ) {
        //回溯出口
        if (index==str.length) return true;

        if (r<0||c<0||r>=matrix.length||c>=matrix[0].length //出了边界
               ||matrix[r][c]!=str[index]//匹配不上
               ||visited[r][c]//重复进入
        )
            return false;
        //回溯置位
        visited[r][c] = true;
        for (int[] n:next)
            if (backtrackingForPath(matrix,visited,str,index+1,r+n[0],c+n[1]))
                return true;
        //回溯复位
        visited[r][c] = false;
        return false;
    }



    //剑指38. 字符串的排列
    private ArrayList<String> ans = new ArrayList<>();
    public ArrayList<String> Permutation(String str) {
        if (str.length()==0) return ans;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        backtrackingForString(chars,new boolean[chars.length],new StringBuilder());
        return ans;
    }
    private void backtrackingForString(char[] chars, boolean[] visited, StringBuilder sb) {
        //回溯出口
        if (sb.length() == chars.length) {
            ans.add(sb.toString());
            return;
        }
        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) continue;

            if (i != 0 && chars[i] == chars[i - 1] && !visited[i - 1]) /* 保证不重复 */
                continue;

            //回溯前置位
            visited[i] = true;
            sb.append(chars[i]);
            //回溯
            backtrackingForString(chars,visited,sb);
            //回溯后复位
            sb.deleteCharAt(sb.length() - 1);
            visited[i] = false;
        }
    }

    //17. 打印从 1 到最大的 n 位数
    public void print1ToMaxOfNDigits(int n) {
        if (n <= 0)
            return;
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }
    private void print1ToMaxOfNDigits(char[] number, int digit) {
        if (digit == number.length) {
            printNumber(number);
            return;
        }
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }
    private void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0')
            index++;
        while (index < number.length)
            System.out.print(number[index++]);
        System.out.println();
    }
}
