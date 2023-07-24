package workbook;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static int[] twoSum(int[] nums ,int k){

        Map<Integer,Integer> map = new HashMap<>();

        // store the key is k-nums[i], value is the nums[i]
        for (int i = 0;i<nums.length;i++){
            if(map.containsKey(nums[i])){
                return new int[]{nums[i],map.get(nums[i])};
            }
            map.put(k-nums[i],nums[i]);
        }

        return new int[]{Integer.MAX_VALUE,Integer.MAX_VALUE};
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1,2,3,4};
        int tar = 4;

        System.out.println(Arrays.toString(twoSum(arr,tar))); // 1,3

        arr = new int[]{1,2,3,4,9,-1};
        tar = 0;
        System.out.println(Arrays.toString(twoSum(arr,tar))); // 1,-1

        arr = new int[]{1,2,3};
        tar = 7;
        System.out.println(Arrays.toString(twoSum(arr,tar))); // max,max
    }
}
