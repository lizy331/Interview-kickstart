package BinarySearch;

import java.util.Arrays;

public class BinarySearchWithDup {

    public static int binarySearch(int[] nums, int target){
        int res = -1;
        Arrays.sort(nums);

        int mid = 0;
        int l = 0;
        int r = nums.length-1;

        while(l<r){
            mid = l+(r-l)/2;
            if(nums[mid]==target){
                while(mid>1 && nums[mid-1] == target){
                    mid--;
                }
                return mid;
            }else if(nums[mid]<target){
                l = mid+1;
            }else{
                r = mid;

            }
        }

        return l;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1,2,3,4,5};
        int tar = 5;

        System.out.println(binarySearch(input,tar));
    }


//    -1,3,4,4,4,4,4,6,6,9,12,14

}
