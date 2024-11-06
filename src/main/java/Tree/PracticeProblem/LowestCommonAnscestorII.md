### 1644. Lowest Common Ancestor of a Binary Tree II

这道题 和 LCA 非常类似，不同点在于，这道题 当中 p 或者 q 可能不存在 在 tree 当中

那么我们可以使用 两个 全局变量来记录是否找到了 p 或者 q 如果没有找到，那么在返回结果的时候直接返回 null

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private TreeNode left;
    private TreeNode right;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode node = dfs(root,p.val,q.val);
        if(node!=null){
            return (left==null || right==null) ? null : node;
        }
        return null;
    }

    public TreeNode dfs(TreeNode node, int p, int q){
        if(node == null){
            return node;
        }


        TreeNode leftNode = dfs(node.left,p,q);
        TreeNode rightNode = dfs(node.right,p,q);

        if(node.val == p || node.val == q){
            // find the target node
            if(node.val == p){
                left = node;
            }else if(node.val == q){
                right = node;
            }
            return node;
        }

        if(leftNode != null && rightNode != null){
            return node;
        }

        if(leftNode == null && rightNode == null){
            return null;
        }

        if(leftNode == null){
            return rightNode;
        }

        return leftNode;
    }
}
```

注意 我们下面的逻辑 是需要放在 dfs 左子树和右子树之后的，为什么？

因为假设我们当前遍历到的 就是 p 或者 q，如果这段逻辑放在 dfs 左右子树之前，那么 这段逻辑会检测到当前 node 就是 p 或者 q 当中的一个那么就直接返回了，就不会在进行 左右子树的遍历了
但是如果另一个 p 或者 q 是存在于这个 node 的左右子树下面的话，那么我们就没有机会将其 capture 了

```text
if(node.val == p || node.val == q){
            // find the target node
            if(node.val == p){
                left = node;
            }else if(node.val == q){
                right = node;
            }
            return node;
        }
```