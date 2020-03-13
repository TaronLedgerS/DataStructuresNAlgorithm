package DataStructures.StackNQueue;

import java.util.Stack;

public class MyStackTest {
    public static void main(String[] args) {
        MyStackTest test = new MyStackTest();
        test.queuePush(1);
        test.queuePush(2);
        test.queuePush(3);
        System.out.println(test.queuePop());
        test.queuePush(4);
        test.queuePush(5);
        System.out.println(test.queuePop());
    }

    //剑指9. 用两个栈实现队列 一个用来入队一个用来出队
    Stack<Integer> stack1 = new Stack<Integer>();//入
    Stack<Integer> stack2 = new Stack<Integer>();//出
    public void queuePush(int node) {
        stack1.push(node);
    }
    public int queuePop() {
        if (stack2.isEmpty())
            while (!stack1.isEmpty())
                stack2.push(stack1.pop());
        return stack2.pop();
    }

    //剑指30. 包含 min 函数O（1）的栈
    Stack<Integer> dataStack = new Stack<Integer>();
    Stack<Integer> minStack = new Stack<Integer>();//同步记录当前栈中对应最小值
    public void push(int node) {
        dataStack.push(node);
        if (minStack.isEmpty())
            minStack.push(node);
        else
            if (minStack.peek()>node)
                minStack.push(node);
            else
                minStack.push(minStack.peek());
    }
    public void pop() {
        dataStack.pop();
        minStack.pop();
    }
    public int top() {
        return dataStack.peek();
    }
    public int min() {
        return minStack.peek();
    }

    //剑指31. 栈的压入、弹出序列
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int j = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty()&&stack.peek() == popA[j]) {
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }
}
