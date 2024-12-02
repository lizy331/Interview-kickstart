package InterviewByCompany.DoorDash;

public class BinaryTreeMaximumPathSumLeafToLeaf {

    public int result;
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        dfs(root);

        return result == Integer.MIN_VALUE ? 0 : result;
    }

    public int dfs(TreeNode node){
        if(node == null )return 0;

        int leftMax = dfs(node.left);
        int rightMax = dfs(node.right);

        int pathSum = node.val + leftMax + rightMax;
        if(pathSum > result && node.left !=null && node.right!= null){
            result = pathSum;

        }


        if (node.left == null) {
            return rightMax + node.val; // 左子树为空，只能通过右子树返回
        } else if (node.right == null) {
            return leftMax + node.val; // 右子树为空，只能通过左子树返回
        } else {
            return Math.max(leftMax, rightMax) + node.val; // 左右子树都存在，选择较大的路径
        }

    }


    public static void main(String[] args){
        BinaryTreeMaximumPathSumLeafToLeaf solution = new BinaryTreeMaximumPathSumLeafToLeaf();

        TreeNode leftChild = new TreeNode(9);
        TreeNode rightChild = new TreeNode(20, new TreeNode(15), new TreeNode(-77));
        TreeNode testRoot = new TreeNode(-10,leftChild,rightChild);

//        int result = solution.maxPathSum(testRoot);
//        System.out.println(result);


        //      -10
        //     /   \
        //   9      20
        //         /  \
        //      15    -77

        //      output: 9 + -10 + 20 + 15 = 34

        // test case 2:

        /*
        test case 2
                9
              /  \
            7     15
          /  \   /  \
         -1    8 10  -19

        output: 8 + 7 + 9 + 15 + 10 = 49
        */


        TreeNode leftChild2 = new TreeNode(7,new TreeNode(1), new TreeNode(8));
        TreeNode rightChild2 = new TreeNode(15, new TreeNode(10), new TreeNode(-19));
        TreeNode testRoot2 = new TreeNode(9,leftChild2,rightChild2);

//        int myResult2 = solution.maxPathSum(testRoot2);
//        System.out.println(myResult2);

        /*
        test case 3
                9
              /  \
            7

        output: max path is 8 7 9  but it only contains one side of result, hence result = 0
        */
        TreeNode leftChild3 = new TreeNode(7);
//        TreeNode rightChild3 = new TreeNode(-15, new TreeNode(-10), new TreeNode(-19));
        TreeNode testRoot3 = new TreeNode(9,leftChild3,null);

        int myResult3 = solution.maxPathSum(testRoot3);
//        System.out.println(myResult3); // 0

        /*
        test case 4
                9
              /  \
            7     -15
          /  \   /  \
       -16    8 -10  -19

        output: max path is 8 7 9  but it only contains one side of result, hence result = -1
        */
        TreeNode leftChild4 = new TreeNode(7,new TreeNode(-16), new TreeNode(8));
        TreeNode rightChild4 = new TreeNode(-15, new TreeNode(-10), new TreeNode(-19));
        TreeNode testRoot4 = new TreeNode(9,leftChild4,rightChild4);

        int myResult4 = solution.maxPathSum(testRoot4);
        System.out.println(myResult4);

    }
}

/*
这道题 让我们返回的 是从 leaf 到 另一个 leaf 的 max path sum，原题 124 让我们返回的是 any to any path sum，

不同点在于如果我们希望返回的是 leaf to leaf 那么 我们就必须保证只有在当前 node 左右两枝都不为空的时候 才更新我们的 result

也就是 node.left !=null && node.right!= null

edge case 就是 如果 当 root 的一边为 null 的时候返回 0 这道题，那就判断一下 result 是否一直没有被更新


 */
