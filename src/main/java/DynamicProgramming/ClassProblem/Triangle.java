package DynamicProgramming.ClassProblem;

import java.util.List;

// leetcode 120
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        // init a arr as 2D array with same dimensions with triangle
        int m = triangle.size();
        if(m==1){
            return triangle.get(0).get(0);
        }

        // use the last row as the column number !!!
        int n = triangle.get(triangle.size()-1).size();
        int[][] arr = new int[m][n];

        // the base case is the first arr number
        arr[0][0] = triangle.get(0).get(0);

        // init the first column, since the first column is only filled by the number above it
        for(int i = 1;i<m;i++){
            arr[i][0] = arr[i-1][0] + triangle.get(i).get(0);
        }

        // init the right side of the triangle
        for(int i = 1;i<m;i++){
            arr[i][i] = arr[i-1][i-1] + triangle.get(i).get(i);
        }

        // loop the rest number, add the smaller number on top or on the "top & one left"
        for(int i = 1;i<arr.length;i++){
            for(int j = 1;j<i;j++){
                arr[i][j] = triangle.get(i).get(j) + Math.min(arr[i-1][j],arr[i-1][j-1]);
            }
        }

        // we pick the min in the last row of arr
        int res = Integer.MAX_VALUE;
        for(int i = 0;i<n;i++){
            res = Math.min(res,arr[m-1][i]);
        }

        return res;
    }


}


// 2 0 0 0
// 3 4 0 0
// 6 5 7 0
// 4 1 8 3

// arr:

// 2 0 0 0
// 5 6 0 0
// 11 10 13 0
// 15 11 18 16
