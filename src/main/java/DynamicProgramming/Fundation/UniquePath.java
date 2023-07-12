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
}
