package DynamicProgramming.ClassProblem;

//leetcode 72
public class EditDistance {
    // f(m,n): return the min cost to align the "m" len word with "n" len word
    // the top manager will review these three problems
    // f(m-1,n-1): mismatch/match, need replacement
    // deletion : f(m,n-1) the last manager can delete one char of m
    // insertion: f(m-1,n) the last manager can add one char to m
    public int minDistance(String word1, String word2) {

        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m+1][n+1];
        for(int i = 0;i<=n;i++){
            dp[0][i] = i;
        }

        for(int i = 0;i<=m;i++){
            dp[i][0] = i;
        }

        for(int i = 1;i<=m;i++){
            for(int j = 1;j<=n;j++){

                // the min cost should be the min of addition, subtraction, and replacement

                // take the min of addition or subtraction
                dp[i][j] = Math.min(dp[i-1][j]+1,dp[i][j-1]+1);

                // the cost of replacement
                int rep = dp[i-1][j-1] + (word1.charAt(i-1) - word2.charAt(j-1) ==0 ? 0 : 1) ;

                // take the min of replacement or add/subtract, the min
                dp[i][j] = Math.min(dp[i][j],rep);
            }
        }
        return dp[m][n];
    }


// T: O(mn)
// S: O(mn)
}

// dp is a two denominational array:

// where dp[i][j] indicate the min cost of align the index from 0-i of word1 with index from 0-j of word2

// init the dp array，第一列和第一行，应该怎么初始化？
// dp[0][1] 意思是我们将一个 空的字符串align 一个 长度为 1 的字符串，那么实际上就是 进行 insertion，
// min cost 为 1

// dp[0][2] 进行 insertion insert 两个 char，所以 min cost 为 2，以此类推

// 追后返回 dp[m][n]

//       r   o   s
//    0  1   2   3
// h  1
// o  2
// r  3
// s  4
// e  5          *
