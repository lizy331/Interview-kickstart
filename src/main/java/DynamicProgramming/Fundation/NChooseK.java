package DynamicProgramming.Fundation;

public class NChooseK {

    static Integer ncr(Integer n, Integer r) {
        // Write your code here.

        if (r > n)
            return 0;
        if (r==0 || r==n)
            return 1;

        // set up the 2D array with row = n+1, col = r+1
        int[][] arr = new int[n+1][r+1];

        // init C(n,0) = (n,0) = 1, C(n,n) = (col,col) = 1
        for(int i = 0;i<=n;i++){
            arr[i][0]=1;
        }

        for(int j = 0;j<=r;j++){
            arr[j][j] = 1;
        }
        // loop through each node (upper diagnal is ignored)
        // arr[i][j] = arr[i-1][j] + arr[i-1][j-1]

        for(int i = 2;i<=n;i++){
            for(int j = 1;j<=Math.min(r,i);j++){
                arr[i][j] = (arr[i-1][j] + arr[i-1][j-1])% 1000000007;
            }
        }
        return arr[n][r];
    }


    // another ncr Apr 1, 2024
    static Integer ncr2(Integer n, Integer k){

        if (k > n)
            return 0;
        if (k==0 || k==n)
            return 1;

        // init the arr, we need to add one more colume and one more row,
        // because we want to include the 0,0
        int[][] arr = new int[n+1][k+1];

        // init the first colume as 0, because C(i,0) = 1, i choose 0 is 1
        for(int i = 0;i<=n;i++){
            arr[i][0] = 1;
        }

        // inti the C(j,j) = 1, j choose j is 1
        for(int j = 0;j<=k;j++){
            arr[j][j] = 1;
        }

        //for loop
        for(int i = 2;i<=n;i++){
            //######## 注意这里是 j<=Math.min,
            // 因为我们建立的 数组并不一定是正方形的，如果 k 小于 n 的时候那么会出现
            // 在 i 比较小的时候，i<k, 数组是正方形的
            // 在 i > k之后，列循环就只能最多抵达 k 了
            for(int j = 1;j<=Math.min(i,k);j++){
                arr[i][j] = (arr[i-1][j] + arr[i-1][j-1])% 1000000007;
            }
        }

        return arr[n][k];

    }

    public static void main(String[] args) {

        System.out.println(ncr(15,9));
        System.out.println(ncr(14,3));

        System.out.println(ncr2(15,9));
        System.out.println(ncr2(14,3));
    }
}
