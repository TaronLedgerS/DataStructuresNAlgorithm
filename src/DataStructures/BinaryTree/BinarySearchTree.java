package DataStructures.BinaryTree;

import java.util.*;

public class BinarySearchTree{
    public static void main(String[] args) {
        //随机生成n个节点的树
        BinarySearchTree tree = new BinarySearchTree();
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            //去重
            int temp = (int) (Math.random() * 100);
            while (set.contains(temp)){
                temp = (int) (Math.random() * 100);
            }
            set.add(temp);
            //插入
            if (null == tree.treeRoot)
                tree.treeRoot = new TreeNode(temp);
            else
                tree.insert(tree.treeRoot,temp);
        }
        //序列化结果-先序
        System.out.println("----序列化与反序列化结果-先序-----");
        System.out.println(tree.serializeByPreorder(tree.treeRoot));
        //反序列化结果-先序
        tree.treeRoot = tree.deserializeByPreorder(tree.serializeByPreorder(tree.treeRoot));
        tree.index = -1;//重置下标
        System.out.println(tree.serializeByPreorder(tree.treeRoot));
        //序列化结果-层序
        System.out.println("----序列化与反序列化结果-层序-----");
        System.out.println(tree.serializeByLevel(tree.treeRoot));
        tree.treeRoot = tree.deserializeByLevel(tree.serializeByLevel(tree.treeRoot));
        System.out.println(tree.serializeByLevel(tree.treeRoot));
        //最小
        System.out.println("----最小-----");
        System.out.println(tree.findMin(tree.treeRoot).val);
        //最大
        System.out.println("----最大-----");
        System.out.println(tree.findMax(tree.treeRoot).val);
        //删除根节点
        System.out.println("----删除根节点后序列化结果-----");
        tree.treeRoot = tree.deleteNode(tree.treeRoot, tree.treeRoot.val);
        System.out.println(tree.serializeByPreorder(tree.treeRoot));
        //返回第k大
        System.out.println("----返回第三大节点-----");
        System.out.println(tree.KthNode(tree.treeRoot, 3).val);
        tree.index = -1;//重置下标
        //返回先序、中序遍历结果
        int[] preO = tree.getPreorder();
        int[] inO = tree.getInorder();
        System.out.println("----前序与中序结果-----");
        System.out.println(Arrays.toString(preO));
        System.out.println(Arrays.toString(inO));
        //先序中序重建二叉树
        System.out.println("----重建二叉树与后序结果-----");
        tree.treeRoot = tree.reConstructBinaryTree(preO, inO);
        int[] postO = tree.getPostorder();
        System.out.println(Arrays.toString(postO));
        //树的子结构判断
        System.out.println("----判断树的子结构-----" + tree.HasSubtree(tree.treeRoot, tree.treeRoot.left));
        //二叉树镜像
        System.out.println("----二叉树层次序列化-----");
        tree.Mirror(tree.treeRoot);
        System.out.println(tree.serializeByLevel(tree.treeRoot));
        tree.Mirror(tree.treeRoot);//还原
        //打印二叉树
        System.out.println("----从上往下打印二叉树-----");
        System.out.println(tree.PrintFromTopToBottom(tree.treeRoot));
        System.out.println("----从上往下逐行打印二叉树-----");
        ArrayList<ArrayList<Integer>> A = tree.Print(tree.treeRoot);
        System.out.println(A);
        Collections.sort(A,(o1,o2)->(o2.size()-o1.size()));
        System.out.println(A);
        System.out.println("----从上往下Z字打印二叉树-----");
        System.out.println(tree.PrintLikeZ(tree.treeRoot));
        //判断后序遍历是否符合BST特征
        System.out.println("----判断是否为BST后序遍历---- :" + BinarySearchTree.VerifySquenceOfBST(postO));
        System.out.println("----判断是否为BST后序遍历---- :" + BinarySearchTree.VerifySquenceOfBST(preO));
        //二叉树中结点值的和为输入整数的所有路径
        System.out.println("----和为8的路径-----");
        System.out.println(tree.FindPath(tree.treeRoot,6));
        //二叉树的深度
        System.out.println("----二叉树的深度----- : "+tree.TreeDepth(tree.treeRoot));
        //判断是否为二叉树
        System.out.println("----二叉树的是否平衡----- : "+tree.IsBalanced_Solution(tree.treeRoot));

    }

    //树根节点
    public TreeNode treeRoot;

    //剑指37-序列化-先序
    public String serializeByPreorder(TreeNode root) {
        StringBuilder s = new StringBuilder();
        if (null == root) {
            s.append("#!");
            return s.toString();
        }
        s.append(root.val);
        s.append("!");
        s.append(serializeByPreorder(root.left));
        s.append(serializeByPreorder(root.right));
        return s.toString();
    }

    //剑指37-反序列化-先序
    public int index = -1;
    TreeNode deserializeByPreorder(String str) {
        index++;
        String[] strs = str.split("!");
        if ("#".equals(strs[index])) return null;
        TreeNode Node = new TreeNode(Integer.parseInt(strs[index]));
        Node.left = deserializeByPreorder(str);
        Node.right = deserializeByPreorder(str);
        return Node;
    }

    //leetcode297-序列化-层序-BFS
    public String serializeByLevel(TreeNode root) {
        if (null == root) return "";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (null==node) {
                sb.append("null,");
                continue;
            }
            sb.append(node.val);
            sb.append(",");
            queue.offer(node.left);
            queue.offer(node.right);
        }
        String result = sb.toString();
        return result.substring(0, result.length() - 1);
    }

    //leetcode297-反序列化-层序-BFS
    public TreeNode deserializeByLevel(String data) {
        if ("".equals(data)) return null;
        String[] strs = data.split(",");
        if (strs.length==0||"null".equals(strs[0])) return null;
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(strs[index]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            index++;
            if (!"null".equals(strs[index])) {
                node.left = new TreeNode(Integer.parseInt(strs[index]));
                queue.offer(node.left);
            }
            index++;
            if (!"null".equals(strs[index])) {
                node.right = new TreeNode(Integer.parseInt(strs[index]));
                queue.offer(node.right);
            }
        }
        return root;
    }

    //剑指57-返回第k大，中序遍历结果有序原则
    private TreeNode KNode;
    public TreeNode KthNode(TreeNode root, int k) {
        inorderForK(root, k);
        return KNode;
    }
    private void inorderForK(TreeNode root, int k) {
        if (null==root||index>k-1) return;
        inorderForK(root.left, k);
        index++;
        if (index==k-1)
            KNode = root;
        inorderForK(root.right, k);
    }

    //剑指7. 重建二叉树
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return reBuildTree(pre, 0, in, 0, in.length - 1);
    }
    private TreeNode reBuildTree(int[] pre, int preI, int[] in, int startIn, int endIn) {
        if (preI>=pre.length||startIn>endIn) return null;
        TreeNode node = new TreeNode(pre[preI]);
        int midIn;
        for (midIn = startIn; midIn <= endIn; midIn++) {
            if (in[midIn]==pre[preI]) break;
        }
        node.left = reBuildTree(pre, preI + 1, in, startIn, midIn - 1);
        node.right = reBuildTree(pre, preI + midIn-startIn+1, in, midIn + 1, endIn);
        return node;
    }

    //剑指26. 树的子结构 root2为root1的子结构？不是子树（完全一样）不要混淆
    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (null==root2 || null==root1) return false;
        return isSubstruct(root1,root2)||HasSubtree(root1.left,root2)||HasSubtree(root1.right,root2);
    }
    //只用于判断root1与root2结构是否匹配
    private boolean isSubstruct(TreeNode root1, TreeNode root2) {
        if (null == root2)  return true;//子结构匹配结束
        if (null == root1) return false;//匹配未结束，大树没了
        if (root1.val==root2.val){ //当前节点匹配成功，继续匹配
            return isSubstruct(root1.left, root2.left) && isSubstruct(root1.right, root2.right);
        } //匹配不成功，失败
        return false;
    }

    //剑指27. 二叉树的镜像
    public void Mirror(TreeNode root) {
        if (null==root) return;
        //交换
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        //递归左右子树
        Mirror(root.left);
        Mirror(root.right);
    }

    //剑指32.1 从上往下打印二叉树
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (null!=root) queue.offer(root);
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            while (cnt > 0) {
                cnt--;
                TreeNode node = queue.poll();
                ans.add(node.val);
                if (null!=node.left) queue.add(node.left);
                if (null!=node.right) queue.add(node.right);
            }
        }
        return ans;
    }
    //剑指32.2 把二叉树打印成多行
    public ArrayList<ArrayList<Integer>> Print(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (null!=root) queue.offer(root);
        while (!queue.isEmpty()) {
            ArrayList<Integer> line = new ArrayList<>();
            int cnt = queue.size();
            while (cnt > 0) {
                cnt--;
                TreeNode node = queue.poll();
                line.add(node.val);
                if (null!=node.left) queue.add(node.left);
                if (null!=node.right) queue.add(node.right);
            }
            ans.add(line);
        }
        return ans;
    }
    //剑指32.3 按之字形顺序打印二叉树
    public ArrayList<ArrayList<Integer> > PrintLikeZ(TreeNode root) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;//true则从左到右
        while (!queue.isEmpty()) {
            int cnt = queue.size();
            ArrayList<Integer> line = new ArrayList<>();
            while (cnt > 0) {
                cnt--;
                TreeNode node = queue.poll();
                if (null == node) continue;
                line.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
            if (line.size() != 0) {
                if (!flag) Collections.reverse(line);
                ans.add(line);
                flag = !flag;
            }
        }
        return ans;
    }

    //剑指33.判断序列是不是某二叉树的后序遍历结果
    public static boolean VerifySquenceOfBST(int [] sequence) {
        if (null==sequence||sequence.length==0) return false;
        return VerifyPostorder(sequence, 0, sequence.length - 1);
    }
    private static boolean VerifyPostorder(int[] seq, int startI, int endI) {
        if (endI-startI<=1) return true;
        int rootVal = seq[endI];
        int midI = startI;
        while (seq[midI]<rootVal&&midI<endI)//找出左子树序列[startI,mindI)
            midI++;
        for (int i = midI;i<=endI;i++) //检查右子树
            if (seq[i]<rootVal) return false;
        return VerifyPostorder(seq, startI, midI - 1) && VerifyPostorder(seq, midI, endI-1);
    }

    //剑指34.二叉树中结点值的和为输入整数的所有路径(根到叶子)：DFs
    private ArrayList<ArrayList<Integer>> ret;
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ret = new ArrayList<>();
        FindPathDFS(root, target, new ArrayList<>());
        ret.sort((o1, o2) -> (o2.size() - o1.size()));
        return ret;
    }
    private void FindPathDFS(TreeNode root, int target, ArrayList<Integer> path) {
        if (null==root) return;
        path.add(root.val);
        target = target - root.val;
        if (target==0&&null==root.left&&null==root.right)
            ret.add(new ArrayList<>(path));//注意是以此路径新建进去
        else {
            FindPathDFS(root.left, target, path);
            FindPathDFS(root.right,target,path);
        }
        path.remove(path.size() - 1);//回溯退出时
    }

    //剑指36.BST转有序链表
    private TreeNode pre = null;
    private TreeNode head = null;
    public TreeNode Convert(TreeNode root) {
        inOrder(root);
        return head;
    }
    private void inOrder(TreeNode node) {
        if (node == null)
            return;
        inOrder(node.left);
        node.left = pre;
        if (pre != null)
            pre.right = node;
        pre = node;
        if (head == null)
            head = node;
        inOrder(node.right);
    }

    //剑指55.1 二叉树的深度
    public int TreeDepth(TreeNode root) {
        if (null==root) return 0;
        return Math.max(TreeDepth(root.left),TreeDepth(root.right))+1;
    }
    //剑指55.2 判段是否为二叉树平衡二叉树
    private boolean isBalance;
    public boolean IsBalanced_Solution(TreeNode root) {
        isBalance = true;
        CheckDepth(root);
        return isBalance;
    }
    private int CheckDepth(TreeNode root) {
        if (null == root || !isBalance){
            return 0;
        }
        int left = CheckDepth(root.left);
        int right = CheckDepth(root.right);
        if (Math.abs(left-right)>1) isBalance = false;
        return 1 + Math.max(left, right);
    }

    //递归插入节点
    public void insert(TreeNode root,int x) {
        if (x<root.val)
            if (null==root.left)
                root.left = new TreeNode(x);
            else
                insert(root.left, x);

        if (x>root.val)
            if (null ==root.right)
                root.right = new TreeNode(x);
            else
                insert(root.right, x);
    }

    //递归找最小
    public TreeNode findMin(TreeNode root){
        if (null==root.left) return root;
        return root.left;
    }

    //循环找最大
    public TreeNode findMax(TreeNode root) {
        if (null==root) return null;
        while (null!=root.right)
            root = root.right;
        return root;
    }

    //递归删除指定元素
    public TreeNode deleteNode(TreeNode root, int x) {
        TreeNode tmpNode;
        if (x<root.val)
            root.left = deleteNode(root.left, x);
        else
            if (x>root.val)
                root.right = deleteNode(root.right, x);
            else //当前节点为需要删除的节点
                if (null!=root.left&&null!=root.right){//存在左右子节点
                    tmpNode = findMax(root.left);  //找左子树的最大节点（一定没有右节点）
                    root.val = tmpNode.val;         //替代当前节点的值
                    root.left = deleteNode(root.left, root.val);//删除左子树的最大节点
                }else { //只存在一个节点或者0个节点
                    tmpNode = root;
                    if (null==tmpNode.left)//左节点为空
                        root = tmpNode.right;//用右节点替换
                    else
                        root = tmpNode.left;
                }
        return root;
    }

    //先序遍历-返回数组
    private LinkedList<Integer> tmpList;
    public int[] getPreorder() {
        tmpList = new LinkedList<>();
        preorder(treeRoot);
        int[] ans = new int[tmpList.size()];
        int cnt = 0;
        for (Integer i : tmpList)
            ans[cnt++] = i;
        return ans;
    }
    private void preorder(TreeNode root) {
        if (null==root) return;
        tmpList.add(root.val);
        preorder(root.left);
        preorder(root.right);
    }

    //中序遍历-返回数组
    public int[] getInorder() {
        tmpList = new LinkedList<>();
        inorder(treeRoot);
        int[] ans = new int[tmpList.size()];
        int cnt = 0;
        for (Integer i : tmpList)
            ans[cnt++] = i;
        return ans;
    }
    private void inorder(TreeNode root) {
        if (null==root) return;
        inorder(root.left);
        tmpList.add(root.val);
        inorder(root.right);
    }

    //后序遍历-返回数组
    public int[] getPostorder() {
        tmpList = new LinkedList<>();
        postorder(treeRoot);
        int[] ans = new int[tmpList.size()];
        int cnt = 0;
        for (Integer i : tmpList)
            ans[cnt++] = i;
        return ans;
    }
    private void postorder(TreeNode root) {
        if (null==root) return;
        postorder(root.left);
        postorder(root.right);
        tmpList.add(root.val);
    }
}

class TreeNode{
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int v){
        val = v;
    }
}