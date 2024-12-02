package InterviewByCompany.DoorDash;

public class BinaryTreeMaximumPathSum {
    public int result;
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        dfs(root);

        return result;
    }

    public int dfs(TreeNode node){
        if(node == null )return 0;

        int leftMax = dfs(node.left);
        int rightMax = dfs(node.right);

        // leftMax+rightMax+node.val, leftMax + node.val, rightMax + node.val
        int pathSum = node.val;
        if(leftMax > 0){
            pathSum += leftMax;
        }
        if(rightMax > 0){
            pathSum += rightMax;
        }

        result = Math.max(pathSum,result);

        if(leftMax < 0 && rightMax < 0){
            return node.val;
        }else{
            return node.val + Math.max(leftMax,rightMax);
        }

    }


    public static void main(String[] args){
        BinaryTreeMaximumPathSum solution = new BinaryTreeMaximumPathSum();

        TreeNode leftChild = new TreeNode(9);
        TreeNode rightChild = new TreeNode(20, new TreeNode(15), new TreeNode(-77));
        TreeNode testRoot = new TreeNode(-10,leftChild,rightChild);

        int result = solution.maxPathSum(testRoot);
        System.out.println(result);

//        System.out.println(result.val);
//        System.out.println(result.left.val);
//        inorder(result);

        //      -10
        //     /   \
        //   9      20
        //         /  \
        //      15    -77

        //      output: 35

        // test case 2:

        /*
        test case 2
                9
              /  \
            7     15
          /  \   /  \
         1    8 10  -19

        output: 8 + 7 + 9 + 15 + 10 = 49
        */


        TreeNode leftChild2 = new TreeNode(7,new TreeNode(1), new TreeNode(8));
        TreeNode rightChild2 = new TreeNode(15, new TreeNode(10), new TreeNode(-19));
        TreeNode testRoot2 = new TreeNode(9,leftChild2,rightChild2);

        int myResult2 = solution.maxPathSum(testRoot2);
        System.out.println(myResult2);

    }
}
