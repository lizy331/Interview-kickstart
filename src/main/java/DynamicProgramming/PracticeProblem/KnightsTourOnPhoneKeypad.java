package DynamicProgramming.PracticeProblem;

/*
Given a phone keypad as shown below:

1 2 3
4 5 6
7 8 9
– 0 –

How many different phone numbers of given length can be formed starting from the given digit? The constraint is that the movement from one digit to the next is similar to the movement of the Knight in chess.

For example, if we are at 1, then the next digit can be either 6 or 8; if we are at 6 then the next digit can be 1, 7 or 0.

Repetition of digits is allowed, e.g. 1616161616 is a valid number.
There is no need to list all possible numbers, just find how many they are.
Find a polynomial-time solution, based on Dynamic Programming.


Example One
{
"start_digit": 1,
"phone_number_length": 2
}
Output:

2
Two possible numbers of length 2: 16, 18.

 */

public class KnightsTourOnPhoneKeypad {

    static Long count_phone_numbers_of_given_length(Integer start_digit, Integer length) {
        // Write your code here.



        // build dp table, n = length+1, m = 10
        long[][] dp = new long[10][length+1];

        // init the value for length 1, which is row 1
        for(int i = 0;i<10;i++){
            dp[i][1] = 1;
        }


        // for loop
        for(int j = 2;j<=length;j++){
            dp[0][j] = dp[4][j-1] + dp[6][j-1];
            dp[1][j] = dp[8][j-1] + dp[6][j-1];
            dp[2][j] = dp[7][j-1] + dp[9][j-1];
            dp[3][j] = dp[4][j-1] + dp[8][j-1];
            dp[4][j] = dp[3][j-1] + dp[9][j-1] + dp[0][j-1];
            dp[5][j] = 0;
            dp[6][j] = dp[0][j-1] + dp[1][j-1] + dp[7][j-1];
            dp[7][j] = dp[2][j-1] + dp[6][j-1];
            dp[8][j] = dp[1][j-1] + dp[3][j-1];
            dp[9][j] = dp[2][j-1] + dp[4][j-1];
        }



        // loop the row starting from n = 1
        return dp[start_digit][length];

    }

/*
        f(d,i): # of phone numbers with length i, and starting with digit of d
        dp[i][j] start with digit i  what # of phone numbers with length j

        我们需要知道对于每一个数字，这个数字可以到达哪些其他数字

        0: 4 6
        1: 8 6
        2: 7 9
        3: 4 8
        4: 3 9 0
        5: _
        6: 0 1 7
        7: 2 6
        8: 1 3
        9: 2 4

        那么 dp[i][j] = dp[0][j-1] + dp[1][j-1] + dp[2][j-1] + ....
*/
}
