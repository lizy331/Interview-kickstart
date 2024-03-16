package DynamicProgramming.PracticeProblem;

import java.util.ArrayList;

public class Robbery {

    static Integer maximum_stolen_value(ArrayList<Integer> values) {
        if(values.size()==1){
            return values.get(0);
        }
        // f(n) = max(f(n-2) + values(n), f(n-1))
        // f(0) = 0
        // f(1) = values(0)

        // 维护一个一位数组 dp，其中 dp[i] 表示 [0, i] 区间可以抢夺的最大值
        // 对当前i来说，有抢和不抢两种互斥的选择，不抢即为 dp[i-1]（等价于去掉 nums[i] 只抢 [0, i-1] 区间最大值）
        // 抢即为 dp[i-2] + nums[i]（等价于去掉 nums[i-1]）

        int[] dp = new int[values.size()];
        dp[0] = values.get(0);
        dp[1] = Math.max(values.get(0),values.get(1));

        for(int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i - 2] + values.get(i), dp[i - 1]);
        }

        return dp[values.size()-1];
    }
}
