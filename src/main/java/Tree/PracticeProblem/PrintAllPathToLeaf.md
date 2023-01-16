这道题让我们 打印所有的 从 root 到 leaf node的path，并返回所有的 path

    static ArrayList<ArrayList<Integer>> res;
    static ArrayList<ArrayList<Integer>> all_paths_of_a_binary_tree(BinaryTreeNode root) {
        // Write your code here.
        res = new ArrayList<>();
        dfs(root,new ArrayList<Integer>());
        return res;
    }
    
    static void dfs(BinaryTreeNode root, ArrayList<Integer> out ){
        
        if(root==null){return;}
        
        out.add(root.value);
        
        if(root.left == null && root.right==null){
            res.add(new ArrayList<>(out));
            return;
        }
        
        if(root.left!=null){
            dfs(root.left,out);
            out.remove(out.size()-1);
        }
        if(root.right!=null){
            dfs(root.right,out);
            out.remove(out.size()-1);    
        }
        
        
        return;
        
    }

**错误的想法：**
注意和数组的 recursive 不一样的是 我们在 out 添加了一个node 总是要 back propagate，不像 数组的 recursive 只需要删除一次

**上述的想法是错误的，不管是数组还是 tree，只要添加就必须还原，只不过 tree 多了一个 判断左右枝是否存在的操作**
