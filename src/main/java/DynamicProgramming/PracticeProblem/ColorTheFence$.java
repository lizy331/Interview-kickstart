package DynamicProgramming.PracticeProblem;

public class ColorTheFence$ {
    /**
     * Time: O(n)
     * Space: 0(1)
     **/
    static Integer number_of_ways(Integer n, Integer k) {
        int mod = 1000000007;
        // edges cases
        if(n == 1) {
            return k;
        } else if(n == 2) {
            return (k * k) % mod;
        }
        // initization and base cases
        long table[] = new long[n + 1];
        table[0] = 0;
        table[1] = k;
        table[2] = (long) k * k; // 两个 fences 可以填图相同的颜色
        // recurrence traversal
        for(int i = 3; i <= n; i++) {
            // 为什么 (k - 1) * ((table[(i - 1)] + table[(i - 2) ]？
            table[i] = ((k - 1) * ((table[(i - 1)] + table[(i - 2) ]) % mod)) % mod;
        }
        return (int)(table[n] % mod);
    }


// _ _ _

// dp[i] 代表 从 [0-i] posts 的刷法sum

// dp[i] = previous two are same + previous are not same

// dp[i] = (k-1)*(dp[i-1] + dp[i-2])

  /*
    比如 n = 3
        k = 2

    初始化 dp
        dp = [_ _ _]
        dp = [2 2*2 _]  , 第一个位置上有 k 种刷法，第二个位置上有 k*k 种刷法，因为两个相邻的 位置可以有相同颜色

        dp[2] = previous two are same + previous are not same

            1.paint the last with previous are same (we need to make sure the 0 index and 1 index color are not same)
            the number of ways for painting index i and i-1 index are having same color is dp[i-1], and the number of ways for painting the i-2 fence is k-1, because we make sure the i-2 color is differ than i and i-1

            thus the number of ways for painting the index i and i-1 as same color is = dp[i-1]*(k-1),


            2.paint the last with previous are not same = assuming the last index i paint a color, then i-1 must have k-1 available color to choose
            because, the index i and i-1 must have different color, so the number of ways to paint is depend on the dp[i-2]
            from dp[i-2] you will have k-1 color to paint the i-1, and the index i are assume to be a color that is already paint

            thus paint the last with previous are not same = dp[i-2]*(k-1)

            (2-1)*(dp[0]+dp[1]) = 6

            paint the last with previous are same: 122,211
            not same : 121,212,112,221


    */
}
