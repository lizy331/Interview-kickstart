中序遍历

```java
class Solution{
    static ArrayList<Integer> res;
    static ArrayList<Integer> inorder(BinaryTreeNode root) {
        // Write your code here.
        res = new ArrayList<>();
        dfs(root);
        return res;
    }

    static void dfs(BinaryTreeNode root){
        if(root == null){

            return;}

        dfs(root.left);
        res.add(root.value);
        dfs(root.right);

    }
}
```