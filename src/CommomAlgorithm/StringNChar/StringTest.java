package CommomAlgorithm.StringNChar;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class StringTest {
    public static void main(String[] args) {
        System.out.println(new StringTest().replaceSpace(new StringBuffer("A B C")));

    }
    //剑指5. 替换空格
    public String replaceSpace(StringBuffer str) {
        //调用自带函数
        //return str.toString().replace(" ", "%20");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i)==' ')
                sb.append("%20");
            else
                sb.append(str.charAt(i));
        }
        return sb.toString();
    }

    //剑指20. 表示数值的字符串
    public boolean isNumeric(char[] str) {
        if (null==str||str.length ==0) return false;
        return new String(str).matches("[+-]?\\d*(\\.\\d+)?([eE][+-]?\\d+)?");
    }

    //剑指41.2 字符流中第一个不重复的字符
    private int[] cnts = new int[256];//打表统计
    private Queue<Character> queue = new LinkedList<>();//存储第一个只出现一次字符的队列
    public void Insert(char ch) {
        cnts[ch]++;
        queue.add(ch);
        while (!queue.isEmpty() && cnts[queue.peek()] > 1)//当新增字符时，清除队列非一次字符
            queue.poll();
    }
    public char FirstAppearingOnce() {
        return queue.isEmpty() ? '#' : queue.peek();
    }

    //剑指50. 第一个只出现一次的字符位置
    public int FirstNotRepeatingChar(String str) {
        int[] cnt = new int[256];
        for (char c:str.toCharArray())
            cnt[c]++;
        for (int i = 0;i<str.length();i++)
            if (cnt[str.charAt(i)]==1)
                return i;
        return -1;
    }

    //58.1 翻转单词顺序列 先旋转每个单词，再旋转整个字符串
    public String ReverseSentence(String str) {
        char[] chars = str.toCharArray();
        int i = 0;
        int j = 0;
        while (j <= chars.length) {//反转每个单词
            if ( j == chars.length||chars[j] == ' ' ) {
                reverse(chars, i, j - 1);
                i=j+1;
            }
            j++;
        }
        //反转整个字符串
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }
    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char c = chars[i];
            chars[i] = chars[j];
            chars[j] = c;
            i++;
            j--;
        }
    }

    //58.2 左旋转字符串 先单独反转两部分，在反转整个字符串
    public String LeftRotateString(String str,int n) {
        if (n >= str.length())
            return str;
        char[] chars = str.toCharArray();
        reverse(chars, 0, n - 1);
        reverse(chars, n, chars.length - 1);
        reverse(chars, 0, chars.length - 1);
        return new String(chars);
    }

    //67. 把字符串转换成整数
    public int StrToInt(String str) {
        if (null==str||str.length()==0) return 0;
        boolean isNegative=false;
        if (str.charAt(0)=='-')
            isNegative = true;
        long ans = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (i==0&&(c=='-'||c=='+'))
                continue;
            if (c>='0'&& c<='9')
                ans = ans*10+(c-'0');
            else
                return 0;
        }
        ans = isNegative ? -ans : ans;
        if (ans>Integer.MAX_VALUE||ans<Integer.MIN_VALUE)//溢出判断
            return 0;
        return (int) ans;
    }

    //48. 最长不含重复字符的子字符串
    public int longestSubStringWithoutDuplication(String str) {
        int curLen = 0;
        int maxLen = 0;
        int[] preIndexs = new int[26];
        Arrays.fill(preIndexs, -1);
        for (int curI = 0; curI < str.length(); curI++) {
            int c = str.charAt(curI) - 'a';
            int preI = preIndexs[c];
            if (preI == -1 || curI - preI > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = curI - preI;
            }
            preIndexs[c] = curI;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

    //43. 从 1 到 n 整数中 1 出现的次数
    public int NumberOf1Between1AndN_Solution(int n) {
        int count = 0;
        while(n>0){
            String str = String.valueOf(n);
            char [] chars = str.toCharArray();
            for(int i=0;i<chars.length;i++){
                if(chars[i]=='1')
                    count++;
            }
            n--;
        }
        return count;
    }

    //44. 数字序列中的某一位数字
    public int getDigitAtIndex(int index) {
        if (index < 0)
            return -1;
        int place = 1;  // 1 表示个位，2 表示 十位...
        while (true) {
            int amount = getAmountOfPlace(place);
            int totalAmount = amount * place;
            if (index < totalAmount)
                return getDigitAtIndex(index, place);
            index -= totalAmount;
            place++;
        }
    }
    /**
     * place 位数的数字组成的字符串长度
     * 10, 90, 900, ...
     */
    private int getAmountOfPlace(int place) {
        if (place == 1)
            return 10;
        return (int) Math.pow(10, place - 1) * 9;
    }
    /**
     * place 位数的起始数字
     * 0, 10, 100, ...
     */
    private int getBeginNumberOfPlace(int place) {
        if (place == 1)
            return 0;
        return (int) Math.pow(10, place - 1);
    }
    /**
     * 在 place 位数组成的字符串中，第 index 个数
     */
    private int getDigitAtIndex(int index, int place) {
        int beginNumber = getBeginNumberOfPlace(place);
        int shiftNumber = index / place;
        String number = (beginNumber + shiftNumber) + "";
        int count = index % place;
        return number.charAt(count) - '0';
    }

    //KMP
    int strStrKMP(String s, String p) {
        //KMP algorithms
        if(s.equals("")) return 0;
        if(p.equals("")) return 0;
        int sLen = s.length();
        int pLen = p.length();
        char[] S =s.toCharArray();
        char[] P = p.toCharArray();
        int[] next = makeNext(P);
        //开始KMP匹配
        int i = 0, j = 0;
        while(i<sLen&&j<pLen){
            //如果j=-1，或者当前字母匹配成功，那么比较下一个
            if (j==-1||S[i]==P[j]) {
                i++;
                j++;
            }else//②如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]
                //即，回退到最长公共前后缀的下一个字符去匹配
                j = next[j];

        }
        if(j==pLen)//P串匹配完了
            return i-j;//返回匹配成功的起始下标
        else
            return -1;
    }

    private int[] makeNext(char[] p){
        int len = p.length;
        int[] next = new int[len];//记录除去当前字符之前子串的最长公共前后缀
        next[0] = -1;
        int k = -1;
        int j = 0;
        while(j<len-1){
            //p[k]表示前缀，p[j]表示后缀
            if (k==-1||p[j]==p[k]) {//匹配上了
                next[j+1]=k+1;
                k++;
                j++;
            }else//匹配失败，回去找更小的公共前缀
                k=next[k];
        }
        return next;
    }
}
