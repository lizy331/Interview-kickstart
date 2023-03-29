这道题 让我们计算一个 tree中 **两个 任意 node 之间的最大距离**，注意是任意两个 node，并不一定是 root 的左枝的一个 node 到 右枝的一个 node

只要距离够长，这两个node 可以在同一枝

注意这道题是如何计算 最长的路径的

我们使用 lazy manager 的思维， 从 tree 的 leaf node 开始思考，**leaf 的左枝为 null 此时 我们应该返回 0**，因为 leaf node 的manager 问 leaf node 你的左枝 有多少个 node？

这属于 bottom up approach

我们在 traverse 左枝和右枝之后才进行 res = Math.max ... 等一系列操作，所以这属于 post order 操作

    class Solution {
        int res;
        public int diameterOfBinaryTree(TreeNode root) {
        if(root==null)return 0;
        res = 0;
        helper(root);
        return res;
    }
    
        public int helper(TreeNode root){
            if(root == null){
                return 0;
            }
            int L = helper(root.left);
            int R = helper(root.right);
            res = Math.max(res,L+R);
            return 1+Math.max(L,R);
        }
    }

注意在这里 我们到达 null 的时候返回的是 0，我们实际记录 node 的数量是在 第二个 return 处

这里我们得到的 res 是两个 node 之间 最多的 node 的数量，我们想要的 path 其实是 node 的数量 减 1 

time: O(n)

space: O(n)

空间复杂度是 O(n) 这是因为在 call stack 中我们会 call 每一个 node，node.left   node.right ......这会造成在 stack 中存在 所有的 node