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

    public static void main(String[] args) {
        System.out.println(ncr(15,9));
    }
}
