这道题让我们使用 BSF 搜索来遍历 BST


```java
class Solution{
    static ArrayList<ArrayList<Integer>> level_order_traversal(BinaryTreeNode root) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root==null){return res;}
        Deque<BinaryTreeNode> q = new ArrayDeque();
        q.add(root);
        while(!q.isEmpty()){
            ArrayList<Integer> out = new ArrayList<>();
            int n = q.size();
            for(int i = 0;i<n;i++){
                BinaryTreeNode node = q.poll();
                out.add(node.value);
                if(node.left!=null){q.add(node.left);}
                if(node.right!=null){q.add(node.right);}
            }
            res.add(out);
        }
        return res;
    }
}
```
    


注意几点

1. 使用 Deque 的时候，是 new ArrayDeque
   
2. 13 行我们需要提前对 q 的 size 进行计算，而不能直接写到 for loop 中，如果我们写到了 for loop 中，那么每一次 loop 都会重新进行计算