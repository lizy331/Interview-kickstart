这道题只让我们使用 preorder 来建立 一个 BST，注意是 binary search tree，那么我们势必要用到 BST 中的 大小关系属性

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
    static int idx=0;
    static ArrayList<Integer> list;
    static BinaryTreeNode helper(ArrayList<Integer> preorder,int lower,int upper,int length){
        if(idx==length)
            return null;
        int val = preorder.get(idx);
        if(val<lower || val>upper){
            return null;
        }

        idx++;
        BinaryTreeNode node = new BinaryTreeNode(val);
        node.left = helper(preorder,lower,val,length);
        node.right= helper(preorder,val,upper,length);
        return node;
    }
    static BinaryTreeNode build_binary_search_tree(ArrayList<Integer> preorder) {
        list= new ArrayList<>(preorder);
        return helper(preorder,Integer.MIN_VALUE,Integer.MAX_VALUE,preorder.size());
    }
}
```

这道题的思路是，按照顺序遍历 preorder list 中的元素，也就是在全局使用一个 index 来记录访问 preorder list 的顺序，

preorder 中的第一个元素一定是 root，第二个元素可能是 root 的左枝，判断的依据是左枝的大小是否在负无穷 到 root value 之间（也就是只要比 root 小说明这个 node 就是 root 的左枝）

第三个元素依然有可能是 root 的左枝（root 的child 的左枝）判断的依据依然是是否 小于 root 的 child

注意这种形式，helper function 返回的是 node，在 helper function 中先创建了 root 然后分别去创建左右枝

注意 idx 添加的顺序，必须后于 判断 preorder 中遍历到的 val 是否在 lower 和 upper 之间，如果是在其之间说明当前这个 node 确实要被创建

否则说明val 不属于当前这一枝，（违反了 binary search tree 的大小属性）