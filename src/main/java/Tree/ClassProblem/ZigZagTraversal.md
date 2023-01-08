这道题给了我们一个 BST，让我们通过 S 形走位 来访问每一个 node，也就是先从左到右，在从右到左，在从左到右

实际上这道题还是在考察 Birth first search，我们需要使用一个 boolean 来记录 是否应该颠倒顺序



    class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
      List<List<Integer>> res = new ArrayList<>();
      if(root == null){
        return res;
      }
      LinkedList<TreeNode> dq = new LinkedList();
      dq.add(root);
      boolean flip = false;
        while(!dq.isEmpty()){
          int n = dq.size();
          LinkedList<Integer> out = new LinkedList<>();
          for(int i = 0;i<n;i++){
              TreeNode node = dq.poll();
              if(flip){
                out.addFirst(node.val);
              }else{
                out.add(node.val);
              }

              if(node.left != null){
                dq.add(node.left);
              }

              if(node.right!=null){
                dq.add(node.right);
              }
          }
          flip = !flip;
          res.add(out);
        }

        return res;
    }
}


使用 linked list 的性能要比使用 deque array 好

**Deque<TreeNode> dq = new LinkedList();**

is better than 

**Deque<TreeNode> dq = new ArrayDeque();**

if our goal is just adding elements into the data structure