package Tree.BST;

import Tree.TreeNode;

public class KthSmallestElementInBST {


    private int counter;
    private int tar;
    private int res;
    public int kthSmallest(TreeNode root, int k) {
        // the kth smllest value is the inorder travsesal of the BST
        // we travse the tree, add the value to list, and return k-1 index value (due to k is 1 indexed)
        res = 0;
        counter = 1;
        tar = k;
        dfs(root);

        return res;


    }

    public void dfs(TreeNode node){
        if(node == null){
            return;
        }

        dfs(node.left);
        if(counter == tar){
            res = node.val;
        }
        counter++;
        dfs(node.right);

    }
}

/*

这道题主要的 关键点在于 follow up

Follow up: If the BST is modified often (i.e., we can do insert and delete operations) and you need to find the kth smallest frequently, how would you optimize?

也就是说 如果，这个 BST 经常进行 insert 以及 delete 的操作，那么我们将如何对这个 kth smallest 代码进行 优化

答案是：
修改 TreeNode 的数据结构，添加一个 data field 叫做 sizeOfSubTree，表示当前 node 下面的 sub node 的数量

然后 我们在 root node 的时候就可以 判断 kth smallest 是在 左sub tree 还是 右 sub tree，并且不断的 循环 call，（非常类似于 Binary Search，可以把 root node 想像成 binary search 当中的 mid）

这样我们就可以将 这个 查找 kth smallest 的 操作优化称为

O(logn) time


 */
