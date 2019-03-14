public class KthSmallest {
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 230. Kth Smallest Element in a BST
     * @Param root: 一棵平衡查找树BST
     * @Param K ：第K小的节点
     * @Return res ：返回第K小的节点的值
     */
    int res;
    int count;
    public int kthSmallest(TreeNode root, int k) {
        count = k;
        dfs(root);
        return res;
    }
    /**
     * 按中序遍历（DFS），找到最小值
     * @param root
     */
    private void dfs(TreeNode root){
        if(root==null)return;
        dfs(root.left);
        // Key Point
        count--;
        if(count==0){
            res = root.val;
        }
        dfs(root.right);
    }


}
