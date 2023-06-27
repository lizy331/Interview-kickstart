这道题 让我们寻找一个 BST 中是否存在一个 从 root 到 leaf 的路径，使得 这条路径上的 和等于 targetsum

```java

    class Solution {
        boolean flag;
        int sum;
        public boolean hasPathSum(TreeNode root, int targetSum) {
            if(root == null){
            return flag;
            }
            sum = targetSum;
            dfs(root,0);
            return flag;
        }
        
        public void dfs(TreeNode root, int s){
            if(flag){return;}
            if(root == null )return;
            if(root.left==null && root.right==null){
                if(s-sum+root.val==0){flag = true;}
                return;
            }
    
            s += root.val;
    
            dfs(root.left,s);
            dfs(root.right,s);
    
    
        }
    }
  ```


注意 leaf 的定义，leaf 的定义是 这个 node 即没有左枝也没有 右枝，root 本身可不可以算作 leaf，只要root 没有左枝也没有右枝那么就可以算作 leaf

所以 root - root 不算一条路径，因为 root 可能不是 leaf