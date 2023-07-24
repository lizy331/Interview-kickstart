package DynamicProgramming.Fundation;

public class Fibonacci {

    static Integer find_fibonacci(Integer n) {
        // Write your code here.
        if(n<=1){
            return n;
        }
        int[] arr = new int[3];
        arr[0] = 0;
        arr[1] = 1;

        for(int i = 2;i<=n;i++){
            arr[i%3] = arr[(i-1)%3] + arr[(i-2)%3];
        }

        return arr[n%3];
    }



    static Integer fib(Integer n) {
        // Write your code here.
        if(n<2){return n;}

        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;

        for(int i = 2;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }


        return dp[n];
    }


    // recursive
    static Integer fibRecur(Integer n) {
        // Write your code here.
        if(n<=1){return n;}


        return fibRecur(n-1) + fibRecur(n-2);
    }



//    n = 5
//    0,1,1,2,3,5

    public static void main(String[] args) {
        System.out.println(fibRecur(12));
    }



}
