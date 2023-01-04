前序遍历

    static ArrayList<Integer> res;
    static ArrayList<Integer> preorder(BinaryTreeNode root) {
        // Write your code here.
        res = new ArrayList<>();
        dfs(root);
        
        return res;
    }
    
    static void dfs(BinaryTreeNode root){
        if(root == null){return;}
        
        res.add(root.value);
        dfs(root.left);
        dfs(root.right);
    }