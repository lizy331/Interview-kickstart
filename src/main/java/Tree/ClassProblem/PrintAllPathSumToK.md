这道题实际上是 print all path from root to leaf 的变化

这道题多了一个 target sum

```java
class Solution{
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
    
    static ArrayList<ArrayList<Integer>> res;
    static ArrayList<ArrayList<Integer>> all_paths_sum_k(BinaryTreeNode root, Integer k) {
        // Write your code here.
        res = new ArrayList<>();
        return res;
    }

    static void helper(BinaryTreeNode root,ArrayList<Integer> out, Integer k){

        if(k<0){
            return;
        }

        if(root==null){
            if(k==0){
                res.add(new ArrayList<>(out));
            }
            return;
        }

        out.add(root.value);
        helper(root.left,out,k-root.left.value);
        out.remove(out.size()-1);
        helper(root.right,out,k-root.right.value);
        out.remove(out.size()-1);

        return;
    }
}
```