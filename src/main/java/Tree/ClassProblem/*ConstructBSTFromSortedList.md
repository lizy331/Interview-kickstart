这道题给了我们一个 sorted array，注意 input 其实不一定是 list，有可能是一个 linked list，linked list 不具备 indexing 的功能，那么我们可以将linked list 中的 value 储存下来放到一个 list 中，

也就是说我们可以对 input 进行修改，以达到方便实用的目的，**尤其是当我们遇见一些不好直接处理的 input**，

就比如这道题的 Linked list node，实际上这个 数据结构是 出题者自己创建的，那么我们思维就要灵活 把其中的 node 储存的 value 提取出来

好了回到正题，题目给了一个 sorted list，那么我们应该思考使用什么作为 BST 的 root？ 应该很容易想到使用 mid index 作为 root，因为已知 list 是 sorted

所以中间的值 适合作为 root，以减少 树长成奇怪的形状，比如 一条长枝


    /*
    For your reference:
    class LinkedListNode {
    Integer value;
    LinkedListNode next;

        LinkedListNode(Integer value) {
            this.value = value;
            this.next = null;
        }
    }

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
    */

    static BinaryTreeNode sorted_list_to_bst(LinkedListNode head) {
        // Write your code here.
        List<Integer> list = new ArrayList<>();
        while(head!=null){
            list.add(head.value);
            head = head.next;
        }
        
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
    
实际的做法类似于 binary search，不断的寻找中间值，然后将 boundary 传递给下一个 call， edge case 需要处理的是，当左指针大于右指针，这主要会出现在 leaf node 以下的 null， 因为这时 左指针超过了右指针，可以结合 binary search 来思考，

在binary search 中什么时候左指针会超过右指针？
 1. 我们所寻找的 数字不存在，循环还没有结束，所以左指针超过了 右指针