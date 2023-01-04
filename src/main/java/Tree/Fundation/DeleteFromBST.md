这道题让我们从 BST 中删除一个 node，不用考虑 re-balancing 的事情

delete 操作非常的复杂， 需要考虑的 edge case 有很多，因为 delete 操作会发生在tree 的中间，也就是说 我们希望删除的 node 可能有 一个或者 两个子 node，我们需要维持 BST 的属性，所以需要我们进行 re-balancing
对于 delete 我们分为以下三种情况
1. 希望删除的 node 是 leaf node
2. 希望删除的 node 有一个 子 node
3. 希望删除的 node 有两个 子 node

第一种情况最简单，我们直接将这个 leaf node 的 parent 指向 null 就可以

第二种情况，也比较简单，我们可以直接将这个 node 的 parent 指向这个即将被删除的 node 的唯一一个 子 node 即可

最复杂的是第三种情况，我们需要找到这个 node 的 successor，然后将他们的 value 交换，然后再删除 successor， 由于 successor 只存在一个 子 node（successor 没有左枝），所以就相当于上面的 case 2

###### Example one

{

"root":

[4,

2, 6,

1, 3, 5, 7],

"values_to_be_deleted": [5, 6]

}

output:

[4,

2, 7,

1, 3]


    static BinaryTreeNode delete_from_bst(BinaryTreeNode root, ArrayList<Integer> values_to_be_deleted) {
        for ( int i =0;i< values_to_be_deleted.size();i++){
            Integer num = values_to_be_deleted.get(i);
             root = delete_helper(root,num);
        }
        return root;
    }
    
        // Write your code here.
      static BinaryTreeNode delete_helper(BinaryTreeNode root, Integer value){
        BinaryTreeNode prev = null;
        BinaryTreeNode curr = null;
        BinaryTreeNode child = null;
        BinaryTreeNode succ = null;
        curr = root;
        while(curr != null){
            if ( value.equals(curr.value) ){
                break;
            }else if ( value < curr.value ){
                prev = curr;
                curr = curr.left ;
            }else{
                prev = curr;
                curr = curr.right;
            }
        }
        if ( curr == null ){
            return root; 
        }
        // case 1 : Leaf Node
        if( curr.left == null && curr.right == null ){
            if(prev == null){
                return null;
            }
            
            if(curr == prev.left){
                prev.left = null;
            }else{
                prev.right = null;
            }
            return root;
        }
        
    // Case 2 : Node has one child
         if(curr.left == null && curr.right  != null){
            child = curr.right;
         }
        
        if( curr.left != null && curr.right == null ){
            child = curr.left;
        }
        if(child != null){
            if(prev == null){
                root = child;
                return root;
            }
            if(prev.left == curr){
                prev.left = child;
            }else{
                prev.right = child;
            }
            return root;
        }
        
        // Case 3 : Node whith two child node
        if(curr.left != null && curr.right != null){
            succ = curr.right;
            prev = curr;
            while(succ.left != null){
                prev = succ;
                succ = succ.left;
                
            }    
            curr.value = succ.value;
                if(succ == prev.left){
                    prev.left = succ.right;
                }else{
                    prev.right = succ.right;
                }
            return root;
        }
        return root;
        
    }
    
    static BinaryTreeNode successor(BinaryTreeNode root, BinaryTreeNode node){
        BinaryTreeNode curr = null;
        BinaryTreeNode ancestor = null;
        if(root == null){
            return null;
        }
        if(node.right != null){
            curr = node.right;
            while( curr.left != null){
                curr = curr.left;
            }
            return curr;
        }
        curr = root;
        while(curr.value != node.value){
            if(node.value < curr.value){
                ancestor = curr;
                curr = curr.left;
            }else{
                curr = curr.right;
            }
        }
        return ancestor;
    }