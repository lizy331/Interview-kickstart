package DynamicProgramming.ClassProblem;

// leetcode 256
public class PaintHouses {
    public int minCost(int[][] costs) {

        // f(n,red) defines the minimum cost from 1 - n with last color
        // painted as red

        // we init a 2D dp dp[n][3], 3 for 3 color red,blue, green
        // the res is the smallest cost of the colors in dp[n-1]

        // init the dp[0][1] = cost[0][1] etc
        int n = costs.length;
        int[][] dp = new int[n][3];
        dp[0][0] = costs[0][0];
        dp[0][1] = costs[0][1];
        dp[0][2] = costs[0][2];

        for(int i = 1;i<n;i++){
            // dp[i][j] = dp[i-1][not j in 0,1,2]
            dp[i][0] = costs[i][0] + Math.min(dp[i-1][1],dp[i-1][2]);
            dp[i][1] = costs[i][1] + Math.min(dp[i-1][2],dp[i-1][0]);
            dp[i][2] = costs[i][2] + Math.min(dp[i-1][0],dp[i-1][1]);
            // System.out.println(Arrays.toString(dp[1]));
        }


        // return the Math.min(dp[n-1][0],dp[n-1][1],dp[n-1][2])
        return Math.min(dp[n-1][0],Math.min(dp[n-1][1],dp[n-1][2]));
    }

//    0     1
// R  17    18
// B  2     33
// G  17    7
}
