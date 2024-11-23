package InterviewByCompany.Meta;

public class FindMinInRotatedArray {

    public int findMin(int[] nums){
        int left = 0;
        int right = nums.length-1;
        while(left<right){
            int mid = (right-left)/2 + left;
            if(nums[mid] > nums[right]){
                // min is on the right, and min is not the mid, so left = mid + 1
                left = mid + 1;
            }else{
                // nums[mid] <= nums[right]
                // mid could be the min
                right = mid;
            }
        }
        return nums[left];
    }

    public static void main(String[] args){
        FindMinInRotatedArray findMinInRotatedArray = new FindMinInRotatedArray();
        int[] testcase1 = new int[]{4,5,6,7,0,1,2,3};
        System.out.println(findMinInRotatedArray.findMin(testcase1)); // 0
    }
}
