这道题让我们访问 一个 BST 的 right side view 的 nodes

我们可以使用 DFS 也可以使用 BFS

    class Solution {
    List<Integer> rightside = new ArrayList();
    
        public void helper(TreeNode node, int level) {
            if (level == rightside.size()) 
                rightside.add(node.val);
            
            if (node.right != null) 
                helper(node.right, level + 1);  
            if (node.left != null) 
                helper(node.left, level + 1);
        }    
        
        public List<Integer> rightSideView(TreeNode root) {
            if (root == null) return rightside;
            
            helper(root, 0);
            return rightside;
        }
    }


注意 如果我们在题目中读到了 bottom up，或者 top down 之类的词，那意味着我们必须使用 recursive 的算法来实现