package DataStructures.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class BSTGeneric {
    public static void main(String[] args) {
        int[] randoms = new int[]{67, 7, 30, 73, 10, 0, 78, 81, 10, 74 };
        BSTNode<Integer> roots = new BSTNode<>();
        for (int number: randoms)
            roots.add(number);
        System.out.println(roots.inOrder());
    }
}

//https://blog.csdn.net/afei__/article/details/83037761
class BSTNode<E extends Comparable<E>> {//!!!比较
    public BSTNode<E> leftNode;
    public BSTNode<E> rightNode;
    public E value;

    public void add(E v) {
        if(null==value) value = v;
        else{
            if ((v.compareTo(value)) <= 0) {//比较
                if (null == leftNode)
                    leftNode = new BSTNode<>();
                leftNode.add(v);
            }else{
                if (null == rightNode)
                    rightNode = new BSTNode<>();
                rightNode.add(v);
            }
        }
    }

    public List<E> inOrder() {
        List<E> values = new ArrayList<>();
        if (null!=leftNode)
            values.addAll(leftNode.inOrder());
        values.add(value);
        if (null!=rightNode)
            values.addAll(rightNode.inOrder());
        return values;
    }
}
