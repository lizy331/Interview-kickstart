后序遍历 

    static ArrayList<Integer> res;
    static ArrayList<Integer> postorder(BinaryTreeNode root) {
        // Write your code here.
        res = new ArrayList<>();
        dfs(root);
        return res;
    }
    
    static void dfs(BinaryTreeNode root){
        if(root==null){
            return;
        }
        
        dfs(root.left);
        dfs(root.right);
        res.add(root.value);
        
    }
