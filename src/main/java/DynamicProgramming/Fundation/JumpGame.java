package DynamicProgramming.Fundation;

/*
You are given an integer array nums. You are initially positioned at the array's first index, and each element in the array represents your maximum jump length at that position.

Return true if you can reach the last index, or false otherwise.



Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.
 */


public class JumpGame {

    public static boolean canJump(int[] arr){
        int n = arr.length;
        int lastGoodIndex = n-1;
        for (int i = n-2;i>=0;i--){
            int diss = lastGoodIndex - i;
            if(arr[i]>=diss){
                lastGoodIndex = i;
            }
        }
        return lastGoodIndex == 0;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{2,3,1,1,4};
        int[] arr2 = new int[]{3,2,1,0,4};
        int[] arr3 = new int[]{1,1,3};

        System.out.println(canJump(arr1));
        System.out.println(canJump(arr2));
        System.out.println(canJump(arr3));
    }
}
