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


}
