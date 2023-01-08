这道题 依旧是让我们寻找 一个 BST path 其 sum 等于 target sum 但是让我们返回所有的符合的 path

    class Solution {
    List<List<Integer>> res;
    int sum;

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        res = new ArrayList<>();
        if(root == null){
            return res;
        }
        sum = targetSum;
        dfs(root,0,new ArrayList<>());
        return res;
    }

    public void dfs(TreeNode root, int s, ArrayList<Integer> out){
        if(root == null )return;
        if(root.left==null && root.right==null){
            if(s-sum+root.val==0){
                out.add(root.val);
                res.add(new ArrayList<>(out));
                out.remove(out.size()-1);}
            return;
        }

        s += root.val;
        out.add(root.val);

        dfs(root.left,s,out);
        dfs(root.right,s,out);

        out.remove(out.size()-1);


    }
    }