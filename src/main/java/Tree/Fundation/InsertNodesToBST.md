Insert Nodes to BST

这道题给我们一个数组让我们创建一个 BST，这道题的 helper function 返回了一个 BinaryTreeNode，这道题值得复习


    static BinaryTreeNode build_a_bst(ArrayList<Integer> values) {
        // Write your code here.
        BinaryTreeNode bst= null;
        for (Integer value:values) {
            bst = insertNode(bst, value);
        }
        return bst;
    }

    static BinaryTreeNode insertNode(BinaryTreeNode bst, Integer value) {
        if(bst == null) {
            bst = new BinaryTreeNode (value);
            return bst;
        }
        if(value < bst.value) {
            bst.left = insertNode(bst.left, value);
        } else {
            bst.right = insertNode(bst.right, value);
        }

        return bst;
    }


