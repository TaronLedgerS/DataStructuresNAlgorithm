package DataStructures.MyLinkedList;

import sun.reflect.generics.tree.Tree;

import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MyLinkedList {
    public static void main(String[] args) {
        MyLinkedList myLinkedList = new MyLinkedList();
        ListNode head = myLinkedList.arrayToLinkedList(new int[]{3, 3, 6, 8, 8, 1,3,5,9,9,9});
        System.out.println("-----从头到尾输出链表："+ myLinkedList.linkedListToArray(head));
        System.out.println("-----从尾到头输出链表：" + myLinkedList.printListFromTailToHead(head));
        System.out.println("-----链表倒数第2个节点：" + myLinkedList.FindKthToTail(head, 2).val);
        head = myLinkedList.deleteDuplication(head);
        System.out.println("-----删除链表重复元素："+myLinkedList.linkedListToArray(head));
        head = myLinkedList.ReverseList(head);
        System.out.println("-----反转链表：" + myLinkedList.linkedListToArray(head));
        ListNode list1 = myLinkedList.arrayToLinkedList(new int[]{1,3,6});
        ListNode list2 = myLinkedList.arrayToLinkedList(new int[]{2,4,5,7,8});
        System.out.println("-----list1："+ myLinkedList.linkedListToArray(list1));
        System.out.println("-----list2："+ myLinkedList.linkedListToArray(list2));
        ListNode mergeList = myLinkedList.Merge(list1, list2);
        System.out.println("-----mergeList："+ myLinkedList.linkedListToArray(mergeList));
    }

    //剑指6.从尾到头打印链表-用栈
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> ans = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        while (null != listNode) {
            stack.push(listNode.val);
            listNode = listNode.next;
        }
        while (!stack.isEmpty())
            ans.add(stack.pop());
        return ans;
    }

    //剑指18.删除链表中重复的结点:头部、中间、结尾
    public ListNode deleteDuplication(ListNode Head) {
        if (null==Head || null==Head.next) return Head;
        ListNode newhead = new ListNode(0);
        newhead.next = Head;
        ListNode pre = newhead;
        while (null!=Head&&null!=Head.next){
            if (Head.val == Head.next.val) {//发现重复部分
                while (null!=Head.next&&Head.val == Head.next.val)
                    Head = Head.next;
                //删除重复部分
                pre.next = Head.next;
                Head = Head.next;
            } else {
                Head = Head.next;
                pre = pre.next;
            }
        }
        return newhead.next;
    }

    //剑指22. 链表中倒数第 K 个结点两个指针p1，p2,先让p2移动k-1次，然后一同移动至p2到最后
    public ListNode FindKthToTail(ListNode head, int k) {
        if (null==head||k==0) return null;
        ListNode p1 = head, p2 = head;
        int cnt = 0;
        while (cnt < k-1) {
            cnt++;
            p2 = p2.next;
            if (null==p2) return null;
        }
        while (null != p2.next) {
            p1 = p1.next;
            p2 = p2.next;
        }
        return p1;
    }

    //剑指23. 链表中环的入口结点
    public ListNode EntryNodeOfLoop(ListNode pHead){
        ListNode slow = pHead, fast = pHead;
        if (pHead == null || pHead.next == null)
            return null;
        do {
            slow = slow.next;
            fast = fast.next.next;
        } while (slow != fast);
        //到达相遇点后，相遇点到入口的距离=开始点到入口的距离
        fast = pHead;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }

    //剑指24. 反转链表-头插法
    public ListNode ReverseList(ListNode head) {
        ListNode newhead = new ListNode(-1);
        while (head != null) {
            ListNode tmp = head.next;
            head.next = newhead.next;
            newhead.next = head;
            head = tmp;
        }
        return newhead.next;
    }

    //剑指25.合并两个单调递增链表
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (null==list1) return list2;
        if (null==list2) return list1;
        ListNode newHead = new ListNode(-1);
        ListNode tmpNode = newHead;
        while (null != list1 && null != list2) {
            if (list1.val<list2.val) {
                tmpNode.next = list1;
                list1 = list1.next;
            }
            else {
                tmpNode.next = list2;
                list2 = list2.next;
            }
            tmpNode = tmpNode.next;
        }
        if (null != list1) {
            tmpNode.next = list1;
        }else
            tmpNode.next = list2;
        return newHead.next;
    }

    //剑指52. 两个链表的第一个公共结点，
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode l1 = pHead1, l2 = pHead2;
        while (l1 != l2) {//短的先到尾巴，开始从长头的开始走；长的后到尾巴，从短头开始走，
            l1 = (null == l1) ? pHead2 : l1.next;
            l2 = (null == l2) ? pHead1 : l2.next;
        }
        return l1;
    }

    //数组转换成链表
    public ListNode arrayToLinkedList(int[] A) {
        if (null == A || A.length==0) return null;
        ListNode head = new ListNode(A[0]);
        ListNode node = head;
        for (int i = 1; i < A.length; i++) {
            node.next = new ListNode(A[i]);
            node = node.next;
        }
        return head;
    }

    //链表转数组
    public ArrayList<Integer> linkedListToArray(ListNode head) {
        if (null==head) return null;
        ArrayList<Integer> a = new ArrayList<>();
        while (head != null) {
            a.add(head.val);
            head = head.next;
        }
        return a;
    }
}
class ListNode{
    int val;
    ListNode next;
    ListNode(int val) {
        this.val = val;
    }
}
