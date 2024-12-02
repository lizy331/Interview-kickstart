package InterviewByCompany.DoorDash;

import InterviewByCompany.DoorDash.TreeNode;


import com.sun.source.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeMaximumPathSumReturnPath {


    static class Point {
        int sum;
        List<Integer> path;

        Point(int sum, List<Integer> path) {
            this.sum = sum;
            this.path = path;
        }
    }


    int maxSum;
    List<Integer> maxPath;


    public List<Integer> maxPathSum(TreeNode root) {
        dfs(root);
        return maxPath;
    }


    private Point dfs(TreeNode node) {
        if (node == null) {
            // Base case: return sum 0 and empty path
            return new Point(0, new ArrayList<>());
        }


        // Recursively find the maximum sum and path for left and right subtrees
        Point left = dfs(node.left);
        Point right = dfs(node.right);


        int leftSum = left.sum;
        List<Integer> leftPath = left.path;


        int rightSum = right.sum;
        List<Integer> rightPath = right.path;


        // Calculate the maximum path sum that includes the current node
        int currentSum = node.val + Math.max(0, leftSum) + Math.max(0, rightSum);


        // Update the global maximum sum and path if the current sum is greater
        if (currentSum > maxSum) {
            maxSum = currentSum;


            // Build the path: leftPath + current node + rightPath
            List<Integer> currentPath = new ArrayList<>();


            // Add left path if contributing to the current sum
            if (leftSum > 0) {
                currentPath.addAll(leftPath);
            }


            // Add the current node's value
            currentPath.add(node.val);


            // Add right path if contributing to the current sum
            if (rightSum > 0) {
                currentPath.addAll(rightPath);
            }


            maxPath = currentPath;
        }


        // Determine the maximum sum that can be extended to the parent node
        int extendSum = node.val + Math.max(Math.max(leftSum, rightSum), 0);


        // Determine which path to extend: left or right
        List<Integer> extendPath = new ArrayList<>();
        if (leftSum > rightSum && leftSum > 0) {
            extendPath.addAll(leftPath);
        } else if (rightSum > leftSum && rightSum > 0) {
            extendPath.addAll(rightPath);
        }
        extendPath.add(node.val);


        return new Point(extendSum, extendPath);
    }


    public static void main(String[] args) {
        /**
         * test case 1
         *              -10
         *             /   \
         *            9     20
         *                 /  \
         *              15    -7
         */


        BinaryTreeMaximumPathSumReturnPath solution = new BinaryTreeMaximumPathSumReturnPath();


        TreeNode leftChild = new TreeNode(9);
        TreeNode rightChild = new TreeNode(20, new TreeNode(15), new TreeNode(-7));
        TreeNode root = new TreeNode(-10, leftChild, rightChild);


        List<Integer> res = solution.maxPathSum(root);
        System.out.println("Path: " + res);

        /**
         * test case 2
         *                -10
         *              /     \
         *            9       20
         *          /  \     /  \
         *        22   -9  15    -7
         */




        TreeNode leftChild2 = new TreeNode(9, new TreeNode(22), new TreeNode(-9));
        TreeNode rightChild2 = new TreeNode(20, new TreeNode(15), new TreeNode(-7));
        TreeNode root2 = new TreeNode(-10, leftChild2, rightChild2);


        List<Integer> res2 = solution.maxPathSum(root2);
        System.out.println("Path: " + res2);
    }
}

/*
test case 2
        9
      /  \
    7     15
  /  \   /  \
 1    8 10  -19

output: [8,7,10,15,9] the result list is inorder traversed
 */
