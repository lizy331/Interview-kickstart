package DynamicProgramming.Fundation;

import java.util.ArrayList;

public class MaximumPathSum {

    static Integer maximum_path_sum(ArrayList<ArrayList<Integer>> grid) {
        // Write your code here.

        int m = grid.size();
        int n = grid.get(0).size();

        // create an arr with m x n
        int[][] arr = new int[m][n];

        arr[0][0] = grid.get(0).get(0);

        // init the value for the enitre column 0, where arr[i][0] = arr[i-1][0] + grid[i][0]
        // and init the value for the entire row 0, ...

        for(int i = 1;i<m;i++){
            arr[i][0] = arr[i-1][0] + grid.get(i).get(0);
        }

        for(int j = 1;j<n;j++){
            arr[0][j] = arr[0][j-1] + grid.get(0).get(j);
        }

        // System.out.println("first row: " + Arrays.toString(arr[0]));
        // loop the 2D matrix
        // where arr[i][j] = Math.max(arr[i-1][j],arr[i][j-1])+grid[i][j];

        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                arr[i][j] = Math.max(arr[i-1][j],arr[i][j-1])+grid.get(i).get(j);
            }
        }
        return arr[m-1][n-1];
    }


// [4, 9, 17],
// [7, 0, 0],
// [9, 0, 0]


    // time O(mn)
    // space O(mn)
}
