package InterviewByCompany.Amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountingAnalogousArray {

    public static Integer solution(Integer lowerBound, Integer upperBound, List<Integer> diff){

        // if diff is empty return 0
        int n = diff.size();
        if(n==0){return 0;}

        int res = 0;

        // for loop lowerbound to upper for each a0
        // within outloop, cal a1...an-1, where n is the length of the aanlogous array and secret array
        // n should be equal to diff.size()+1
        // inner loop a(i) = a(i-1) - diff[i-1]
        // once a(i) is out of bound break this innerloop, and start next outloop
        for (int i = lowerBound; i <= upperBound; i++) {
            int temp = i;
            boolean valid = true; // check if any a is out of bound
            for (int j = 0; j < diff.size(); j++) {
                temp = temp - diff.get(j);
                if (temp < lowerBound || temp > upperBound) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                res++;
            }
        }



        return res;

    }

    /*
    low = 3
    upper = 10

    diff = [-2,-1,-2,5]
    a(i-1)-a(i) = diff[i]

    a0 = 3
    a(1-1) - a(1) = -2 -> a(1) = 3 + 2 = 5
    a(2-1) - a(2) = -1 -> a(2) = 5 + 1 = 6
    a(3-1) - a(3) = -2 -> a(3) = 6 + 2  = 8
    a(4-1) - a(4) = 5  -> a(4) = 8 - 5 = 3
    -> a = [3,5,6,8,3]

    none of the value is out of upperBound, hence this is a valid count

    next we start the a0 = 4,

    ...


    count all the valid analogs array
     */

    public static void main(String[] args) {
        // Test cases
        Integer upper1 = 10;
        Integer lower1 = 3;
        List<Integer> diff1 = Arrays.asList(-2, -1, -2, 5);
        System.out.println(solution(lower1, upper1, diff1)); // Expected: 3

        Integer upper2 = 5;
        Integer lower2 = 1;
        List<Integer> diff2 = Arrays.asList(1, -1, 1);
        System.out.println(solution(lower2, upper2, diff2)); // Expected: 0

        Integer upper3 = 10;
        Integer lower3 = 5;
        List<Integer> diff3 = Arrays.asList(0, 0, 0);
        System.out.println(solution(lower3, upper3, diff3)); // Expected: 6

        Integer upper4 = 7;
        Integer lower4 = 1;
        List<Integer> diff4 = Arrays.asList(1, 2, 3);
        System.out.println(solution(lower4, upper4, diff4)); // Expected: 0
    }
}
