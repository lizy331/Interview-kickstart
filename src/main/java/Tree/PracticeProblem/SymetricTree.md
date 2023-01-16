这道题让我们判断一个 tree 是否是对称的

比如

{

"root": [1,

5, 5,

7, null, null, 7]

}

                1
            5   |   5
        7 null  | null   7
                |
                |

这就是一个对称树，也就是说一个对称树需要满足两点

1. 树左右两边形状相同，

2. 对称位置上的 node 的 value 相等


我自己的想法是使用 level order traversal，使用两个 queue，一个用来记录左边的nodes

一个用来记录右边的 nodes，在进入 for loop 之前技术一下两个 queue 的size是否相等，如果不相等说明两边的形状不相等直接返回 false

进入 for loop 之后注意 添加 node 的的顺序，对于对称线左边的node 添加顺序是从左到右的

对于 对称线右边的 nodes 添加顺序是 从右到左


    static Boolean check_if_symmetric(BinaryTreeNode root) {
        // Write your code here.
        if(root == null || (root.left==null && root.right==null)){
            return true;
        }
        Deque<BinaryTreeNode> left = new ArrayDeque();
        Deque<BinaryTreeNode> right = new ArrayDeque();
        left.add(root);
        right.add(root);
        while(!left.isEmpty() && !right.isEmpty()){
            int l = left.size();
            int r = right.size();
            if(l!=r){return false;}
            for(int i = 0;i<r;i++){
                BinaryTreeNode leftNode = left.poll();
                BinaryTreeNode rightNode = right.poll();
                
                if(leftNode.value-rightNode.value != 0){return false;}
                
                if(leftNode.left!=null && rightNode.right!=null){
                    left.add(leftNode.left);
                    right.add(rightNode.right);
                }else if(leftNode.left==null && rightNode.right==null){
                    continue;
                }else{
                    return false;
                }
                
                if(leftNode.right!=null && rightNode.left!=null){
                    left.add(leftNode.right);
                    right.add(rightNode.left);
                }else if(leftNode.right==null && rightNode.left==null){
                    continue;
                }else{
                    return false;
                }
            }
        }
        return true;
    }


另一种想法是 使用 DFS，配合使用 recursive，想象 lazy manager 思维，从 root 开始，将左右两边的nodes 传递给下面，让他们去判断这两个nodes 是否相等，并且返回这两个 leftnode 的左枝和 rightnode 的右枝是否相等

left node 的右枝 和 right node 的左枝是否相等

在 leaf node 层面（也就是最底层）我们得到指令判断给定的两个 node 是否相等，首先判断这两个 node 是否都是 null，也就是 recursive 的 end ，即 leaf node 的下面一层 null

如果他们都是 null 则返回 true，如果 不是 说明至少有一个 node 不是 null，那么这种情况下直接返回 null

如果 这两个 node 都不是 null 那么 判断一下 value
是否相等，如果不相等则也返回 false；

    static Boolean check_if_symmetric(BinaryTreeNode root) {
    if (root == null) return true;
    return isSymmetric(root.left, root.right);
    }

    static boolean isSymmetric(BinaryTreeNode node1, BinaryTreeNode node2) {
        if (node1 == null && node2 == null) {
            return true;
        }
        // if (node1 == null || node2 == null) {
        //     return false;
        // }
        
        if (node1 == null || node2 == null || !node1.value.equals(node2.value)) {
            return false;
        }

        return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    }

