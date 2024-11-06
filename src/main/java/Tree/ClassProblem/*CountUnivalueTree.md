这道题 让我们 计数所有的 tree，这些 tree 满足 其左右子树的所有 value 都相等和 parent 的 value 相等


所以 一个 uni tree 需要满足两个条件

1. 这个tree 的左右子树中的所有 value 都相等

2. 这个 tree node 的 value 和其左右子树的 value 也相等

在 34 行 和 38 行我们就在 检查上述的两个条件
```java
    class Solution {
        int res;
        public int countUnivalSubtrees(TreeNode root) {
        res = 0;
        if(root==null)return res;
        isUniTree(root);
        return res;
    }

    public boolean isUniTree(TreeNode node){
        // leaf node always uni
        if(node.left == null && node.right == null){
            res++;
            return true;
        }

        // initialize as true, since if a node's left is null, then it would be a uni
        boolean left = true;
        boolean right = true;

        // check left
        if(node.left != null){
            left = isUniTree(node.left) && (node.val - node.left.val==0);
        }
        // check right
        if(node.right != null){
            right = isUniTree(node.right) && (node.val - node.right.val==0);
        }

        // if left and right are both uni, and their value are both equal to curr node
        if(left && right){res+=1;}
        return (left&&right);

        }
    }
    
```