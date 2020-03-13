package DataStructures.BinaryTree;

//剑指.8 找出中序遍历顺序的下一个结点
public class FindNextNode {
    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if(null!=pNode.right) {
            TreeLinkNode node = pNode.right;
            while (null != node.left)
                node = node.left;
            return node;
        }else{//向上找第一个左链接指向的树包含该节点的祖先节点。
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left==pNode)
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }
}

class TreeLinkNode{
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null; // 指向父结点的指针
    TreeLinkNode(int val) {
        this.val = val;
    }
}