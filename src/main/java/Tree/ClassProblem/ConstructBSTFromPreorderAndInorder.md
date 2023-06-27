 这道题 让我们还原一个 BST，给了我们 preorder 和 inorder array，
 
首先我们先想清楚如何使用这两个 信息 preorder 和 inorder

preorder 的第一个元素就是 root，因为 preorder 的顺序是 中左右
有了 root 我们可以在 inorder 中寻找这个元素，找到以后 这个 元素的左边就是 左枝，这个元素的右边就是 右枝

比如

preorder = [3,9,20,15,7]

那么 3 就是我们想建立的树的 root

inorder = [9,3,15,20,7]

3 子 inorder 的位置找到之后，3 的左边 也就是 9，所以这个 tree 的左枝只有一个元素 那就是 9

这个tree 的右边的元素 就是 15,20,7


想清楚这点以后我们可以通过将问题 缩小话，即使用 divide and conquer 的思想

**将构建整个树问题缩小为 构建 一个 sub tree 的问题**

我们需要将 preorder 和 inorder 这两个 array 传递给下一个 call 这是肯定的

并且我们还需要传递 创建 sub tree 的 index，也就是我们需要从 preorder 的哪些元素 和 inorder 的哪些元素 构建 subtree

```java
    class Solution {
        Map<Integer,Integer> map;
        public TreeNode buildTree(int[] preorder, int[] inorder) {
        map = new HashMap<>();
        for(int i = 0;i<inorder.length;i++){
        map.put(inorder[i],i);
        }
        return helper(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
    }

    public TreeNode helper(int[] preorder, int pos, int poe, 
                            int[] inorder, int ios, int ioe){

        // cycle ends
        if(pos>poe){return null;}

        // leaf node
        if(pos==poe){return new TreeNode(preorder[poe]);}

        // idx is the index of the root in the inorder arr, 
        // pos of preorder is the root
        int idx = map.get(preorder[pos]);

        // count how many left sub nodes
        int count = idx - ios;

        TreeNode root = new TreeNode(preorder[pos]);

        root.left = helper(preorder,pos+1,pos+count, inorder, idx-count,idx-1);
        root.right = helper(preorder,pos+count+1,poe,inorder,idx+1,ioe);

        return root;
    }
}
```



注意在 51 行 我们在 inorder array 中寻找 root 的 index 的位置的时候，我们可以使用 一个 hashmap 提起记录下来 inorder 中所有元素的 index 