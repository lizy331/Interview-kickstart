package BinarySearch;

public class SearchInRotatedArray {

    public int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0; int r = n - 1;

        // #### 大坑 1 这里 我们需要 在 l == r 的时候 依然检查一遍,这是因为 如果 target 在 边界处的时候 如果我们 不设置 l == r 的这个条件的话 那么就有可能遗漏
        // 这个原因是 我们移动指针的方式不同，在这道题中我们 是 将左右指针 赋值为 mid + 1, 或者是 mid - 1，所以这会导致我们遗漏掉 l==r 的条件
        while(l<=r){
            int mid = (r-l)/2 + l;
            if(nums[mid] == target){
                return mid;
            }

            //
            if(nums[l]<= nums[mid]){
                // 正常情况，也就是说 从 l 到 mid 是递增的
                // 需要检查一下他 tar 是否在左侧，如果在左，那么 r = mid - 1， 因为我们已经确定了 mid index 不是 target
                if(nums[l] <= target && target< nums[mid]){
                    r = mid - 1;
                }else{
                    l = mid + 1;
                }
            }else{
                // 说明 l index 的数字 比 mid 还要大，那么 说明 mid 所处的 位置 是 rotate 之后的 array 部分
                // [4,5,6,7,0,1,2], 也就是 0,1,2 的部分
                // 那么我们可以确认的事情是， mid 的右边是递增的

                // 首先确认一下 target 是否在 右边，因为我们已知 右边是 递增的
                if(target <= nums[r] && target > nums[mid]){
                    l = mid + 1;
                }else{
                    r = mid - 1;
                }
            }
        }

        return -1;
    }
}
