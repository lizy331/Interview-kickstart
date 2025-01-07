package CodingGuru.CountingPattern;
import java.util.*;
public class SubarrayWithKDifferentInteger {

    public int subarraysWithKDistinct(int[] nums, int k) {
        // ToDo: Write Your Code Here.
        Map<Integer,Integer> windowMap = new HashMap<>();
        int result = 0;
        int windowCount = 0;
        int left = 0;
        for(int right = 0;right < nums.length;right++){
            int num = nums[right];

            if(windowMap.containsKey(num)){
                windowMap.put(num,windowMap.get(num)+1);
            }else{
                k--;
                windowMap.put(num,1);
            }


            while(k < 0){
                int leftValue = nums[left];
                windowMap.put(leftValue,windowMap.get(leftValue)-1);
                if(windowMap.get(leftValue)==0){
                    k++;
                    windowMap.remove(leftValue);
                }
                left++;
                windowCount = 0;
            }

            if(k == 0){
                while(windowMap.get(nums[left])>1){ // 注意这里是 大于 1 而不是 大于 0 因为 如果我们减小到 0 那么 这个 subarray 就不满足要求了
                    windowMap.put(nums[left],windowMap.get(nums[left])-1);
                    left++;
                    windowCount++;
                }

                result += (windowCount+1); // + 1 是因为起始的 left, right sub array 也需要被算进去
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}

/**
 input:
 int[] nums, unsorted, have duplicates
 int k,


 output:
 int, return the number of sub array contains k different integer,

 testcase:
 [4, 2, 4, 2, 5] k:2

 [4,2], [4,2,4], [4,2,4,2], [2,4], [2,4,2], [4,2], [2,5]
 output: 7


 solution:

 windowMap:
 left:0
 right:0
 windowCount: 0
 result: 0

 step1: initialize above parameters
 step2: for loop using right pointer
    for each run
    if nums[right] exist in map, add frequency
    if not, add nums[right] to map, and k--

    if, k > 0, continue;

    while k < 0, move left pointer until nums[left] in map is == 0, k++, (windowCount = 0, reset the windowCount = 0)

    if( k == 0), start collecting the result

    move left pointer, keep reduce the freq of nums[left] in map and windowCount++, until nums[left] in map == 0,
    add windowCount + 1 to result


 */
