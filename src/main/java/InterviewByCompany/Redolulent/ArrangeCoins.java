package InterviewByCompany.Redolulent;

import java.util.Arrays;

public class ArrangeCoins {

    public static int[] arrangeCoins(int[] coins){

        // binary search
        // for each coin, we need to use BS,
        int[] res = new int[coins.length];

        for(int i = 0;i<coins.length;i++){
            res[i] = findStairs(coins[i]);
        }

        //
        return res;
    }

    public static int findStairs(int coin){
        // two pointer
        // we check from 0-coin
        // which number x satisfy (x+1)*x/2<=coin
        int res = 0;
        int i = 0;
        int j = coin;
        while(i<=j){
            int mid = i+(j-i)/2;
            long sum = (mid+1)*mid/2;
            if(sum<=coin){
                i = mid + 1;
            }else{
                j = mid-1;
            }
        }
        return j;
    }
    public static void main(String[] args) {
//        int[] coins = new int[]{9,6,3,1000000000,123456789}; // stairs : 3, 3, 2, 44721, 15716
        int[] coins = new int[]{100, 500, 1000, 5000, 10000};
//        Expected Output: [13, 31, 44, 98, 140]

        System.out.println(Arrays.toString(arrangeCoins(coins)));

    }
}
