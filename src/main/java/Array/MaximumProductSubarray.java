package Array;

public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int ans = maxSoFar;

        for (int i = 1; i < nums.length; i++) {
            // when number is negative, swap maxSoFar and minSoFar
            if (nums[i] < 0) {
                int temp = minSoFar;
                minSoFar = maxSoFar;
                maxSoFar = temp;
            }

            // check which is higher -> curNum, maxSoFar * curNum, minSoFar * curNum
            // in case of positive numbers, maxSoFar * curNum will be max
            // in case of negative numbers, minSoFar * curNum will be max
            // in case of preceding negative numbers or 0, curNum will be max

            maxSoFar = Math.max(nums[i], maxSoFar * nums[i]);
            minSoFar = Math.min(nums[i], minSoFar * nums[i]);
            ans = Math.max(maxSoFar, ans);
        }
        return ans;
    }
}

/*
二进制的负数表示方法：
注意在 java int 二进制当中，int 是 32位的，也就是说不管再小的二进制实际上都是有 32 位这么长的

比如说 5 的 32位 二进制就是
00000000 00000000 00000000 00000101

那么二进制是如何表示负数的？
a.	5 的二进制表示：
    00000000 00000000 00000000 00000101
b.	按位取反：
	11111111 11111111 11111111 11111010
c.	加1：
	11111111 11111111 11111111 11111011

 */
