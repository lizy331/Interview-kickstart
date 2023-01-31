**Merge Two BST**

这道题让我们将两个 BST merge，要求是 得到的 tree 必须是 balanced BST，
balance 的定义是左右两边 subtree 的高度差不大于 1

这道题 思路是 inorder 遍历两个 BST tree，得到 两个 value list

然后进行 merge two arraylist

最后 进行 construct BST from sortedlist

```java

class   Solution{
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
    
    static BinaryTreeNode res;
    static BinaryTreeNode merge_two_binary_search_trees(BinaryTreeNode root1, BinaryTreeNode root2) {
        // Write your code here.
        if(root1==null){
            return root2;
        }else if(root2 == null){
            return root1;
        }


        ArrayList<Integer> arr1 = new ArrayList<>();

        inorder(arr1,root1);
        ArrayList<Integer> arr2 = new ArrayList<>();

        inorder(arr2,root2);
        ArrayList<Integer> vals = mergeArray(arr1,arr2);

        return sortedListToBst(vals);
    }

    public static void inorder(ArrayList<Integer> out,BinaryTreeNode root){
        if(root == null){
            return;
        }

        if(root.left!=null){
            inorder(out,root.left);
        }
        out.add(root.value);
        if(root.right!=null){
            inorder(out,root.right);
        }
        return;
    }

    static ArrayList<Integer> mergeArray(ArrayList<Integer> first, ArrayList<Integer> second) {
        // Write your code here.
        if(first.size()>second.size()){
            return mergeArray(second,first);
        }

        int i = first.size()-1;
        int j = second.size()-1;

        for(int l = 0;l<first.size();l++){
            second.add(0);
        }
        int k = second.size()-1;

        while(i>=0 && j >=0){
            if(first.get(i)>second.get(j)){
                second.set(k,first.get(i));
                i--;
            }else{
                second.set(k,second.get(j));
                j--;
            }
            k--;
        }
        // System.out.println(second);
        while(k>=0 && i>=0){
            second.set(k,first.get(i));
            i--;
            k--;
        }

        while(k>=0 && j>=0){
            second.set(k,second.get(j));
            j--;
            k--;
        }

        return second;
    }

    static BinaryTreeNode sortedListToBst(ArrayList<Integer> list) {
        // Write your code here.

        return helper(list,0,list.size()-1);
    }

    static BinaryTreeNode helper(List<Integer> list, int s, int e){
        if(s>e){return null;}
        if(s==e){return new BinaryTreeNode(list.get(e));}

        int mid = s + (e-s)/2;

        BinaryTreeNode root = new BinaryTreeNode(list.get(mid));

        root.left = helper(list,s,mid-1);
        root.right = helper(list,mid+1,e);

        return root;

    }
}
```