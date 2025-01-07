package CodingGuru.MonotonicQueuePattern;

import java.util.*;
/**

 Problem Statement
 Given an integer array nums and an integer limit, return the maximum length of a continuous subarray such that the absolute difference between any two elements in this subarray is less than or equal to limit. The subarray must be non-empty.

 Examples
 Example 1:

 Input: nums = [1, 3, 6, 7, 9, 10], limit = 3
 Output: 3
 Explanation: Consider the [6, 7, 9] or [7, 9, 10] array. The absolute difference between any two elements in these subarrays is at most 3.

 这道题的意思是让我们寻找 最长的 sub array 其中这个 sub array 当中 的 所有element 之间的 difference 小于或等于 limit

 使用 sliding window 以及 两个 deque/stack 来做

 *********
 重要的知识关于 deque，deque 可以被当作 stack 来使用，主要是通过 pollFirst() 相当于 pop, addFirst() 相当于 push
 同样的 deque 也可以被当作 queue 来使用，主要是通过 pollLast() 相当于 poll(), offerLast() 相当于 offer

 peekFirst() 返回的是顶层元素，相当于 stack 的栈顶元素
 peekLast() 返回的是底层元素 ，相当于 queue 的 peek
 *********
 *




 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {

    // Method to find the length of the longest subarray with an absolute difference less than or equal to limit
    public int longestSubarray(int[] nums, int limit) {
        // ToDo: Write Your Code Here.
        // step1: two deque, minDeque, maxDeque (add the first element into max/min deque), left = 0, maxLength = 0;
        Deque<Integer> maxDeque = new LinkedList<>();
        Deque<Integer> minDeque = new LinkedList<>();
        int left = 0;
        int maxLength = 0;

        // step2: loop array, update max/min deque, check max - min <= limit
        for(int i = 0;i<nums.length;i++){
            while(!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[i]){
                maxDeque.pollLast();
            }
            maxDeque.addLast(i); // 当前的 i 有可能并不是 最大的，但是我们依然需要将 i 添加到 deque 底部，因为 i 是连续的

            while(!minDeque.isEmpty() && nums[minDeque.peekLast()]  >= nums[i]){
                // add to front
                minDeque.pollLast();
            }
            minDeque.addLast(i);

            if(nums[maxDeque.peekFirst()]- nums[minDeque.peekFirst()] > limit){
                left++;

                while(maxDeque.peekFirst() < left){
                    maxDeque.pollFirst();
                }
                while(minDeque.peekFirst() < left){
                    minDeque.pollFirst();
                }
            }

            maxLength = Math.max(i - left + 1, maxLength);


        }




        return maxLength;
    }

}
