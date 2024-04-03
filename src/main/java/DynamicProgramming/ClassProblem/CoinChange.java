package DynamicProgramming.ClassProblem;

import java.util.ArrayList;
import java.util.Arrays;

public class CoinChange {

    static Integer minimum_coins(ArrayList<Integer> coins, Integer value) {
        // Write your code here.

        // f(n) = min number of coins to make up n

        // f(n) = min(f(n-1)+1,f(n-3)+1,f(n-5)+1)
        // make sure the coin <= n, because we will look up dp array dp[n-coin], otherwise index will be a negative number


        int[] dp = new int[value+1];

        // we fill up the dp as value+1, because we will use Math.min, and there should be not
        // amount of coin which is larger than the value
        // we could also fill with MAX_VALUE
        Arrays.fill(dp, value + 1);
        dp[0] = 0;

        for(int i = 1;i<=value;i++){
            for(int coin : coins){
                if(coin<=i){
                    // which means this coin can be added
                    // dp[i] means the min number of coin to made up i
                    // we need to compare with the dp[(i-c)]+1
                    dp[i] = Math.min(dp[i],dp[i-coin]+1);
                }
            }
        }


        return dp[value]>value ? -1 : dp[value];
    }
}
