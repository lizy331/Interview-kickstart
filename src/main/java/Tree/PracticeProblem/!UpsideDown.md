这道题给了我们一个 特殊的形状的树，这个树只有左枝没有右枝，然后这道题让我们将这个 树整个颠倒

Example:

input:

[1

2,3]

output:

[2

3,1]

也就是说原来的 tree 最左下角的 node 作为 root，这个 node 右边的 sibling 作为 root 的左枝， 这个 node 的 parent 作为 root 的右枝

以此类推

input:

[1

2,3

^

I

4 -->, 5,null,null]      

4将会作为 root，其左枝会是 5，右枝 会是 2

output:

[4

5,2

null,null,3,1]


    static BinaryTreeNode newRoot = null;
    static BinaryTreeNode flip_upside_down(BinaryTreeNode root) {
        // Write your code here.
        if(root != null)
            helper(root);
        return newRoot;
    }
    
    static void helper(BinaryTreeNode node){
        if(node.left == null && node.right == null) {
            if(newRoot == null)
                newRoot = node;
            return;
        }
        
        // this recursive will end at leaf node, so node.left is the leaf node
        helper(node.left);
        // node.left is the root, and the node.right should be the root's left
        node.left.left = node.right;
        node.left.right = node;
        
        // we need to clean up the current parent node, which is the "node"
        // because this node will be the new leaf, so it's left and right child should be null 
        // (in the middle "node" left child and right might be updated, but at the end it will have left and right as null)
        node.right = null;
        node.left = null;
    }


这道题的几个想法非常值得参考，首先一点就是 **如何建立一个 tree 作为 result，然后利用 recursive 构建这个 tree 的左右子树**

解决方法是建立一个 global tree，当我们到达最左下角的时候 initialize tree，也就是 第 54 行 和 55 行

第 60 行不断的 进行 调用直到 到达 leaf node，到达 leaf node 就会被返回，也就是在这个时候 我们 initialize tree as same reference as the most left leaf node 

我们不断的修改这个 most left leaf node，也就是在修改我们的 result


当我们第一次到达 62 行的时候，此时 node.left 是 leaf node，也就是说 helper(node.left) 第一次 return 了

那么 我们需要做的就是 将 node.left 当作新的 root，这个 new root 的左枝就会是，当前 node 的右枝，（62 行）

这个 root 的右枝就会是 root 本身 （63行）

第 68 和 69 行，我们需要将 当前的node 的左右枝替换成 left 和 right，这是因为 这个 node 最终会变成 新的 tree 的 leaf （可能在recursive 调用的 中途 这个 node的 左右枝 又会被 62 63 行更新，但是最后一次 的 node 就会变成 leaf node）
