package DynamicProgramming.Fundation;

public class UniquePath {

    static Integer unique_paths(Integer n, Integer m) {
        // Write your code here.
        if(n==1 || m== 1){
            return 1;
        }

        // create a n x m array
        int[][] arr = new int[n][m];

        // init [0][1] = 1, [1][0] = 1
        for(int i = 0;i<n;i++){
            arr[i][0] = 1;
        }

        for(int j = 0;j<m;j++){
            arr[0][j] = 1;
        }
        // arr[0][1] = 1;
        // arr[1][0] = 1;

        // arr[i][j] = arr[i-1][j] + arr[i][j-1]

        for(int i = 1;i<n;i++){
            for(int j = 1;j<m;j++){
                arr[i][j] = (arr[i-1][j] + arr[i][j-1])%1000000007;
            }
        }
        return arr[n-1][m-1];
    }


    // 0 1
    // 1 0
    // 0 0

    // time O(mn)
    // space O(mn)


    // 07/24/2023
    static Integer unique_paths2(Integer n, Integer m) {
        // Write your code here.
        if(n<2 || m<2){
            return 1;
        }
        int[][] dp = new int[n][m];

        for(int i = 0;i<m;i++){
            dp[0][i] = 1;
        }

        for(int i = 0;i<n;i++){
            dp[i][0] = 1;
        }

        for(int i =1;i<n;i++){
            for(int j = 1;j<m;j++){
//                By using %100000007, the function guarantees that the returned result will always be
//                within the range of a 32-bit signed integer (i.e., from -2,147,483,648 to 2,147,483,647)
                dp[i][j] = (dp[i-1][j] + dp[i][j-1])%1000000007;
            }
        }
        return dp[n-1][m-1];
    }


// dp[i][j] indicate the number of unique path to reach i,j

// to reach to i,j the last step either go down or go right, overall unique path is the sum of those two pathes
// dp[i][j] = dp[i-1][j] + dp[i][j-1];


// we need to init the 2D array

// ^ 0  1  2
// 0 1  1  1
// 1 1
// 2 1
// 3 1     *
}
