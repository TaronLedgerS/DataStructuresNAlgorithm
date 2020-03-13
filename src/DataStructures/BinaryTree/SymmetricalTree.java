package DataStructures.BinaryTree;
//剑指28.对称二叉树
public class SymmetricalTree {
    boolean isSymmetrical(TreeNode pRoot){
        if (null==pRoot) return true;
        return isSymmetrical(pRoot.left, pRoot.right);
    }
    boolean isSymmetrical(TreeNode r1,TreeNode r2){
        if (null == r1 && null == r2)  return true;
        if (null == r1 || null == r2) return false;
        if (r1.val != r2.val ) return false;
        return isSymmetrical(r1.left, r2.right) && isSymmetrical(r1.right, r2.left);
    }

    public int index = -1;
    public TreeNode deserialize(String str) {
        if (null==str||str.length()==0) return null;
        String[] strs = str.split("!");
        index++;
        if ("#".equals(strs[index])) return null;
        TreeNode node = new TreeNode(Integer.parseInt(strs[index]));
        node.left = deserialize(str);
        node.right = deserialize(str);
        return node;
    }
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new SymmetricalTree().deserialize("1!2!#!3!#!#!#");
        root.right = new SymmetricalTree().deserialize("1!#!2!3!#!#!#!");
        System.out.println(new SymmetricalTree().isSymmetrical(root));
    }
}
