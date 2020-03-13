# Other

[TOC]

## BitOperation

### [15. 二进制中 1 的个数](https://cyc2018.github.io/CS-Notes/#/notes/15.%20%E4%BA%8C%E8%BF%9B%E5%88%B6%E4%B8%AD%201%20%E7%9A%84%E4%B8%AA%E6%95%B0)

-   **n&(n-1):该位运算去除 n 的位级表示中最低的那一位。**

```java
/*
n       : 10110100
n-1     : 10110011
n&(n-1) : 10110000
*/
public int NumberOf1(int n) {
	int cnt=0;
	while (n != 0) {
		n = n & (n - 1);
		cnt++;
	}
	return cnt;
}
```

### [56. 数组中只出现一次的数字](https://cyc2018.github.io/CS-Notes/#/notes/56.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E5%8F%AA%E5%87%BA%E7%8E%B0%E4%B8%80%E6%AC%A1%E7%9A%84%E6%95%B0%E5%AD%97?id=_56-%e6%95%b0%e7%bb%84%e4%b8%ad%e5%8f%aa%e5%87%ba%e7%8e%b0%e4%b8%80%e6%ac%a1%e7%9a%84%e6%95%b0%e5%ad%97)

-   **两个相同数字异或=0，一个数和0异或还是它本身**
-   **两个不同的数异或的结果，最右侧的1那一位，表示，这两个数的二进制第一个不相同位**
-   a&=-a，负数的二进制是补码（原码+1），相反数作&运算，得到最右侧的1即，只有一个1，其它位全为0

    ```java
    22：10110
    -22：11111111111111111111111111101010
    22&(-22):10
    ```

-   **提取出来的这个不相同位** ，意味着，不同的两个数a和b，其中一个在这个位上为1，而另一个为0
-   **由此可以将a和b分隔开**

```java
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
```

### [64. 求 1+2+3+...+n](https://cyc2018.github.io/CS-Notes/#/notes/64.%20%E6%B1%82%201+2+3+...+n)

```java
public int Sum_Solution(int n) {
	int sum = n;
	//短路计算
	boolean Nagetive = (n>0) && ((sum += Sum_Solution(n - 1))>0);
	return sum;
}
```

### [65. 不用加减乘除做加法](https://cyc2018.github.io/CS-Notes/#/notes/65.%20%E4%B8%8D%E7%94%A8%E5%8A%A0%E5%87%8F%E4%B9%98%E9%99%A4%E5%81%9A%E5%8A%A0%E6%B3%95)

```java
//65. 不用加减乘除做加法
//a ^ b 表示没有考虑进位的情况下两数的和，(a & b) << 1 就是进位。
public int Add(int a,int b) {
	//递归会终止的原因是 (a & b) << 1 最右边会多一个 0，那么继续递归，进位最右边的 0 会慢慢增多，最后进位会变为 0，递归终止
	return b == 0 ? a : Add(a ^ b, (a & b) << 1);
}
```

## ArrayOperation

### [3. 数组中重复的数字](https://cyc2018.github.io/CS-Notes/#/notes/3.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E9%87%8D%E5%A4%8D%E7%9A%84%E6%95%B0%E5%AD%97)

-   链接解法是归位法

```java
//用下标负数标记是否来过
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
```

### [4. 二维数组中的查找](https://cyc2018.github.io/CS-Notes/#/notes/4.%20%E4%BA%8C%E7%BB%B4%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E6%9F%A5%E6%89%BE)

```java
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
```

### [21. 调整数组顺序使奇数位于偶数前面](https://cyc2018.github.io/CS-Notes/#/notes/21.%20%E8%B0%83%E6%95%B4%E6%95%B0%E7%BB%84%E9%A1%BA%E5%BA%8F%E4%BD%BF%E5%A5%87%E6%95%B0%E4%BD%8D%E4%BA%8E%E5%81%B6%E6%95%B0%E5%89%8D%E9%9D%A2)

```java
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
```

### [29. 顺时针打印矩阵](https://cyc2018.github.io/CS-Notes/#/notes/29.%20%E9%A1%BA%E6%97%B6%E9%92%88%E6%89%93%E5%8D%B0%E7%9F%A9%E9%98%B5)

```java
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
```

### [★39. 数组中出现次数超过一半的数字-多数投票算法](https://cyc2018.github.io/CS-Notes/#/notes/39.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E5%87%BA%E7%8E%B0%E6%AC%A1%E6%95%B0%E8%B6%85%E8%BF%87%E4%B8%80%E5%8D%8A%E7%9A%84%E6%95%B0%E5%AD%97?id=%e8%a7%a3%e9%a2%98%e6%80%9d%e8%b7%af)

```java
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
```

### [57.1 和为 S 的两个数字](https://cyc2018.github.io/CS-Notes/#/notes/57.1%20%E5%92%8C%E4%B8%BA%20S%20%E7%9A%84%E4%B8%A4%E4%B8%AA%E6%95%B0%E5%AD%97)

```java
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
```

### [57.2 和为 S 的连续正数序列](https://cyc2018.github.io/CS-Notes/#/notes/57.2%20%E5%92%8C%E4%B8%BA%20S%20%E7%9A%84%E8%BF%9E%E7%BB%AD%E6%AD%A3%E6%95%B0%E5%BA%8F%E5%88%97)

```java
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
```

### [61. 扑克牌顺子](https://cyc2018.github.io/CS-Notes/#/notes/61.%20%E6%89%91%E5%85%8B%E7%89%8C%E9%A1%BA%E5%AD%90)

```java
public boolean isContinuous(int [] numbers) {
	if (numbers==null||numbers.length<5) return false;
	Arrays.sort(numbers);
	int cnt=0;
	for (int n :numbers)
		if (n==0)
			cnt++;
	//用癞子补全不连续的部分
	for (int i = cnt;i<numbers.length-1;i++)   {
		if (numbers[i+1]==numbers[i])
			return false;
		cnt -= numbers[i + 1] - numbers[i] - 1;
	}
	return cnt >= 0;
}
```

## DivideNconquer

### [★51. 数组中的逆序对-归并排序](https://cyc2018.github.io/CS-Notes/#/notes/51.%20%E6%95%B0%E7%BB%84%E4%B8%AD%E7%9A%84%E9%80%86%E5%BA%8F%E5%AF%B9)

-   [数组中的逆序对-知乎](https://zhuanlan.zhihu.com/p/39811184)

```java
//51. 数组中的逆序对-归并排序
private  long cnt = 0;
private int[] tmp;
public int InversePairs(int [] nums) {
	tmp = new int[nums.length];
	mergeSort(nums, 0, nums.length - 1);
	return (int) (cnt%1000000007);

}
private void mergeSort(int[] nums, int l, int h) {
	if (h-l<1) return;
	int m = l + (h - l) / 2;
	mergeSort(nums, l, m);
	mergeSort(nums, m + 1, h);
	merge(nums, l, m, h);
}
private void merge(int[] nums, int l, int m, int h) {
	int i = l, j = m + 1, k = l;
	while (i <= m && j <= h) {
		if (nums[i]>nums[j]){
			tmp[k] = nums[j++];
			cnt += m - i + 1; //num[i]后面的都比num[j]大，都可以构成逆序对
		}else
			tmp[k] = nums[i++];
		k++;
	}
	while (i<=m)
		tmp[k++] = nums[i++];
	while (j<=h)
		tmp[k++] = nums[j++];
	for (k = l;k<=h;k++)
		nums[k] = tmp[k];
}
```

### [66. 构建乘积数组](https://cyc2018.github.io/CS-Notes/#/notes/66.%20%E6%9E%84%E5%BB%BA%E4%B9%98%E7%A7%AF%E6%95%B0%E7%BB%84)

```java
public int[] multiply(int[] A) {
	int n = A.length;
	int[] B = new int[n];
	for (int i = 0, product = 1; i < n; product *= A[i], i++)       /* 从左往右累乘 */
		B[i] = product;
	for (int i = n - 1, product = 1; i >= 0; product *= A[i], i--)  /* 从右往左累乘 */
		B[i] *= product;
	return B;
}
```

## 二分查找

### [11. 旋转数组的最小数字](https://cyc2018.github.io/CS-Notes/#/notes/11.%20%E6%97%8B%E8%BD%AC%E6%95%B0%E7%BB%84%E7%9A%84%E6%9C%80%E5%B0%8F%E6%95%B0%E5%AD%97)

```java
//11. 旋转数组的最小数字
public int minNumberInRotateArray(int [] nums) {
	if (nums.length == 0)  return 0;
	int l = 0, h = nums.length - 1;
	while (l < h) {
		int m = l + (h - l) / 2;
		if (nums[m]<=nums[h])
			h = m;
		else
			l = m + 1;
	}
	return nums[l];
}
```

### [53. 数字在排序数组中出现的次数](https://cyc2018.github.io/CS-Notes/#/notes/53.%20%E6%95%B0%E5%AD%97%E5%9C%A8%E6%8E%92%E5%BA%8F%E6%95%B0%E7%BB%84%E4%B8%AD%E5%87%BA%E7%8E%B0%E7%9A%84%E6%AC%A1%E6%95%B0)

```java
public int GetNumberOfK(int [] nums , int k) {
	   if (null==nums||nums.length==0) return 0;
	   int cnt = 0;
	   int l = 0, h = nums.length - 1;
	   while (l<h){
		   int m = l + (h - l) / 2;
		   if (nums[m]==k){
			   l = m;
			   break;
		   }
		   if(nums[m]<k)
			   l = m+1;
		   else
			   h = m;
	   }
	   //向前
	   if (nums[l]==k) cnt++;
	   else return 0;
	   int i = l-1;
	   while (i>=0&&nums[i]==k){
		   cnt++;
		   i--;
	   }
	   i= l+1;
	   while (i<nums.length && nums[i]==k){
		   cnt++;
		   i++;
	   }
	   return cnt;
   }
```

## MathRelated

### [16. 数值的整数次方](https://cyc2018.github.io/CS-Notes/#/notes/16.%20%E6%95%B0%E5%80%BC%E7%9A%84%E6%95%B4%E6%95%B0%E6%AC%A1%E6%96%B9)

```java
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
```

### [43. 从 1 到 n 整数中 1 出现的次数](https://cyc2018.github.io/CS-Notes/#/notes/43.%20%E4%BB%8E%201%20%E5%88%B0%20n%20%E6%95%B4%E6%95%B0%E4%B8%AD%201%20%E5%87%BA%E7%8E%B0%E7%9A%84%E6%AC%A1%E6%95%B0?id=_43-%e4%bb%8e-1-%e5%88%b0-n-%e6%95%b4%e6%95%b0%e4%b8%ad-1-%e5%87%ba%e7%8e%b0%e7%9a%84%e6%ac%a1%e6%95%b0)

-   [Leetcode : 233. Number of Digit One](https://leetcode.com/problems/number-of-digit-one/discuss/64381/4%2B-lines-O(log-n)-C%2B%2BJavaPython)

时间复杂度 O(logN)：思路是分别计算个位、十位、百位........上出现 1 的个数。以  n =216为例：

-   个位上： 1 ，11，21，31，.....211。个位上共出现（216/10）+ 1个 1。。因为除法取整，210~216间个位上的1取不到，所以我们加8进位。你可能说为什么不加9，n=211怎么办，这里把最后取到的个位数为1的单独考虑，先往下看。
-   十位上：10~19，110~119，210~216.   十位上可看成 求（216/10）=21 个位上的1的个数，然后乘10。这里再次把最后取到的十位数为1的单独拿出来，即210~216要单独考虑 ，个数为（216%10）+1 .这里加8就避免了判断的过程。
-   后面以此类推。

```java
public int NumberOf1Between1AndN_Solution(int n) {
	int cnt = 0;
	for (int m = 1; m <= n; m *= 10) {
		int a = n / m, b = n % m;
		cnt += (a + 8) / 10 * m + (a % 10 == 1 ? b + 1 : 0);
	}
	return cnt;
}
```

### [49. 丑数](https://cyc2018.github.io/CS-Notes/#/notes/49.%20%E4%B8%91%E6%95%B0)

-   穷举

```java
public int GetUglyNumber_Solution(int N) {
	if (N<=6) return N;
	////指向小于newUgly且最大的乘以2\3\5后可能成为下一个丑数的丑数
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
```

### [62. 圆圈中最后剩下的数](https://cyc2018.github.io/CS-Notes/#/notes/62.%20%E5%9C%86%E5%9C%88%E4%B8%AD%E6%9C%80%E5%90%8E%E5%89%A9%E4%B8%8B%E7%9A%84%E6%95%B0)

[约瑟夫环问题的数学解](https://www.jianshu.com/p/6ee5c7b21333)：幸存者的位置pn = (pn-1 + k) % n

```java
//62. 圆圈中最后剩下的数
public int LastRemaining_Solution(int n, int m) {
	if (n <= 0 || m <= 0) {
		return -1;
	}
	if (n==1) return 0;
	return (LastRemaining_Solution(n - 1, m) + m) % n;
}
//递推写法
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
```
