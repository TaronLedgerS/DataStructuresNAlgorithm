package CommomAlgorithm.Sorting;



import java.util.ArrayList;
import java.util.PriorityQueue;

public class PriorityQueueTest {
    public static void main(String[] args) {
        System.out.println(new PriorityQueueTest().maxInWindows(new int[]{2,3,4,2,6,2,5,1},3));
    }
    //剑指59. 滑动窗口的最大值
    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(((o1, o2) -> (o2 - o1)));
        ArrayList<Integer> ans = new ArrayList<>();
        if (size > num.length || size < 1)
            return ans;
        int i = 0;
        for (i = 0;i<size;i++)
            maxHeap.add(num[i]);
        ans.add(maxHeap.peek());
        for (i = size; i < num.length; i++) {
            maxHeap.add(num[i]);
            maxHeap.remove(num[i - size]);
            ans.add(maxHeap.peek());
        }
        return ans;
    }

    //剑指41.1 数据流中的中位数
    //大顶堆，存小的一半
    PriorityQueue<Integer> leftHeap = new PriorityQueue<>((o1, o2) -> (o2 - o1));
    //小顶堆，存大的一半
    PriorityQueue<Integer> rightHeap = new PriorityQueue<>();
    int size = 0;//两个堆的总数

    public void Insert(Integer num) {
        //保持两边数量平衡，总数为奇数时右边比左边多一个
        if (size % 2 == 0) {//插入第奇数个元素
            leftHeap.add(num);//先插如左边
            rightHeap.add(leftHeap.poll());//将左边最大放回给右边小顶堆
        } else {//插入第偶数个元素，应该插入到左边
            rightHeap.add(num);
            leftHeap.add(rightHeap.poll());
        }
        size++;
    }
    public Double GetMedian() {
        if (size % 2 == 0) {
            return (leftHeap.peek() + rightHeap.peek()) / 2.0;
        }else
            return (double) rightHeap.peek();
    }
}
