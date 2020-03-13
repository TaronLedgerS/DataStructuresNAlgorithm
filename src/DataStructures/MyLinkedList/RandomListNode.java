package DataStructures.MyLinkedList;

//剑指35. 复杂链表的复制
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }

    public RandomListNode Clone(RandomListNode pHead){
        if(null == pHead) return null;
        //第一步，在每个节点的后面插入复制的节点。
        RandomListNode tmpNode = pHead;
        while (null != tmpNode) {
            RandomListNode clone = new RandomListNode(tmpNode.label);
            clone.next = tmpNode.next;
            tmpNode.next = clone;
            tmpNode = clone.next;
        }
        //第二步，对复制出来节点的 random 链接进行赋值。
        tmpNode = pHead;
        while (null != tmpNode) {
            RandomListNode clone = tmpNode.next;
            if (tmpNode.random!=null)
                clone.random = tmpNode.random.next;
            tmpNode = clone.next;
        }
        //第三步，拆分。
        RandomListNode newHead = new RandomListNode(-1);
        tmpNode = pHead;
        newHead.next = pHead.next;
        while (null != tmpNode) {
            RandomListNode clone = tmpNode.next;
            tmpNode.next = clone.next;//断掉原链表节点到克隆节点的箭头，指向下一个原节点
            tmpNode = clone.next;//下一组原节点与克隆节点
            if (null!=tmpNode) clone.next = tmpNode.next;//断掉克隆节点箭头，指向下一个克隆节点
        }
        return newHead.next;
    }
}
