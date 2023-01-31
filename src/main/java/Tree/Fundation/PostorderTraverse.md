后序遍历 

    static ArrayList<Integer> res;
    static ArrayList<Integer> postorder(BinaryTreeNode root) {
        // Write your code here.
        res = new ArrayList<>();
        dfs(root);
        return res;
    }
    
    static void dfs(BinaryTreeNode root){
        if(root==null){
            return;
        }
        
        dfs(root.left);
        dfs(root.right);
        res.add(root.value);
        
    }


2023.1.30 postorder traversal

```java
class Solution {
    
    class BinaryTreeNode {
        Integer value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    static ArrayList<Integer> res;

    static ArrayList<Integer> postorder_traversal(BinaryTreeNode root) {
        // Write your code here.
        res = new ArrayList<Integer>();
        helper(root);
        return res;
    }

    static void helper(BinaryTreeNode root) {
        if (root.left == null && root.right == null) {
            res.add(root.value);
            return;
        }

        if (root.left != null) {
            helper(root.left);
        }

        if (root.right != null) {
            helper(root.right);
        }

        res.add(root.value);

        return;


    }
}
```