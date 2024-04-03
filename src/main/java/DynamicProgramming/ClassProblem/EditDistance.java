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
                int rep = dp[i-1][j-1] + (word1.charAt(i-1) - word2.charAt(j-1) ==0 ? 0 : 1);

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

// 动态转移方程：
/*
    我们需要分成三类来比较，replacement 以及 add subtract
    add/subtraction: dp[i][j] = Math.min(dp[i-1][j] + 1,dp[i][j-1] + 1);

    注意为什么 dp[i-1][j] + 1 是delegation，这是因为，假如说我们希望将 wd1 = "abc" 转化为 wd2 = "ab", 那么我们需要计算的是 dp[3][2]
            那么我们需要观察的就是 dp[3-1][2] = dp[2][2] 也就是 sequation: wd1 = "ab" 转化成为 wd2 = "ab",
            通过代码之前的循环 dp 数组应该得到的是 dp[2][2] = 0，因为他们完全相等
            我们怎么知道 dp[2][2] = 0, 是因为下面这行代码实现的
            // the cost of replacement
            int rep = dp[i-1][j-1] + (word1.charAt(i-1) - word2.charAt(j-1) ==0 ? 0 : 1);

    那么问题又来了，你怎么知道 i 一定会大于 j 呢？ 也就是说 只有 wd1 = "abc" 转化为 wd2 = "ab" 这种情况，才会出现 delete
            比如说 dp[2][3] 即 wd1 = "ab" 转化为 wd2 = "abc", 你考虑的是对的，此时的 delete 是没有意义的，dp[2-1][3] wd1="a"-> wd2="abc"
            但是代码还是会计算，但是很显然 insert operation 的 cost 要小于 delete operation


     the cost of replacement
             int rep = dp[i-1][j-1] + (word1.charAt(i-1) - word2.charAt(j-1) ==0 ? 0 : 1);

       假如 i 和 j 是相等的 也就是 dp[3][3], wd1 = "abc" -> wd2 = "abd"，那么我们只需要从 dp 数组中获取 dp[2][2] 也就是 ab -> ab 的cost
       然后加上 确定最后一个 字母是否相等 "a"=="b"?，如果不相等则需要 replacement 就 +1，如果相等 就 + 0
*/

// 最后返回 dp[m][n]， 其意思就是，我们将一个 长度为 m 的字符串（horse）转换成长度为 n 的字符串（ros）所需要的 cost

//       r   o   s
//    0  1   2   3
// h  1  1   2
// o  2
// r  3
// s  4
// e  5          *
