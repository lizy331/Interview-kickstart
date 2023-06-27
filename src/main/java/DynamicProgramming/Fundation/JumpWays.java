package DynamicProgramming.Fundation;

public class JumpWays {

    static Long jump_ways(Integer n) {
        // Write your code here.
        if(n==1){return 1L;}

        // remember that at stair 2 there two ways, 1+1 or 2
        if(n==2){return 2L;}

        long[] arr = new long[n+1];
        arr[1] = 1;
        arr[2] = 2;
        for(int i = 3;i<=n;i++){
            arr[i] = arr[i-1] + arr[i-2];
        }
        return arr[n];
    }
}
