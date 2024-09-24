package BinarySearch;

public class TwoDMatrixBinarySearch {

    public boolean searchMatrix(int[][] matrix, int target) {

        // input: mxn matrix, int target
        // out: boolean if the target exist in the metrix

        // edge case: one line matrix, m = 1, one colume matrix, n = 1
        // duplicate values might have

        // solution:
        // binary search
        // first locate the row
        // then locate the column

        // 直接将这个 2D matrix 想象成一个 很长的 chain
        // 然后直接使用 binary search 对整个 长 chain 进行 binary search
        // 需要注意的就是 如何将chain 的 index 和 2D 坐标之间进行转化

        int m = matrix.length;
        int n = matrix[0].length;
        int l = 0;
        int r = m*n;

        while(l<r){
            int mid = (r-l)/2 + l;
            // 比如说  mid  等于 6, ########注意这里的 6 是 0 indexed，这是因为 我们初始化 的 l pointer 是从 0 开始的
            // 那么 这个 mid 相当于是长 chain 的 第 6 个数字 （1 indexed based）也就是 11 （题目中第一个例子）
            // n 相当于 每一个 group 的长度，那么 mid/n 就是 可以定位这个 chain index 6 是在 哪一个 group 当中 ，或者说 是在哪一个 row 当中的？ 6/4 = 1, 也就是说 这个 数字 11 是处在 第一行当中的

            // 接下来 我们使用 6%n，也就是 说 我们 看看 6%n 还余下几个 index，这些 index 就是 对应着column 的坐标
            if(matrix[mid/n][mid%n] == target){
                return true;
            }else if(matrix[mid/n][mid%n] > target){
                r = mid;
            }else{
                l = mid+1;
            }
        }

        return false;
    }
}
