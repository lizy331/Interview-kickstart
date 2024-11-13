package InterviewByCompany.Amazon.Aug2024;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/*
Amazon's database doesn’t support very large numbers, so numbers are stored as a string of binary characters, '0' and '1'. Accidentally, a '!' was entered at some positions and it is unknown whether they should be '0' or '1'.
The string of incorrect data is made up of the characters '0', '1' and '!' where '!' is the character that got entered incorrectly. '!' can be replaced with either '0' or '1'. Due to some internal faults, some errors are generated every time '0' and '1' occur together as '01' or '10' in any subsequence of the string. It is observed that the number of errors a subsequence '01' generates is x, while a subsequence '10' generates y errors.
Determine the minimum total errors generated. Since the answer can be very large, return it modulo 109+7.
Note: A subsequence of a string is obtained by omitting zero or more characters from the original string without changing their order.
Hint: It can be proved that (a + b) % c = ((a% c) + (b % c)) % c where a, b, and c are integers and % represents the modulo operation.
Function Description
Complete the function getMinErrors in the editor.
getMinErrors has the following parameter(s):
String errorString: a string of characters '0', '1', and '!'
int x: the number of errors generated for every occurrence of subsequence 01
int y: the number of errors generated for every occurrence of subsequence 10
Returns
int: the minimum number of errors possible, modulo 109+7
Example 1:
Input:  errorString = "101!1", x = 2, y = 3
Output: 9
Explanation:

If the '!' at index 3 is replaced with '0', the string is "10101". The number of times the subsequence 01 occurs is 3 at indices (1, 2), (1, 4), and (3, 4). The number of times the subsequence 10 occurs is also 3, indices (0, 1), (0, 3) and (2, 3). The number of errors is 3 * x + 3 * y = 6 + 9 = 15.

If the '!' is replaced with '1', the string is "10111". The subsequence 01 occurs 3 times and 10 occurs 1 time. The number of errors is 3 * x + y = 9.

The minimum number of errors is min(9, 15) modulo (109 + 7) = 9.
 */

public class MinimumErros {
    class Node{
        public String str;
        public int[] candi;

        public Node(String s, int[] c){
            this.str = s;
            this.candi = c;
        }
    }


    private int getMinErrors(String errorString, int x, int y) {
        // write your code here
        // 01 - > x, 10 -> y
        // subsequence

        // 10111 -> 3 + 2 + 2 + 2 = 9

        // 10101 -> 3 + 3 + 3 + 2 + 2 + 2 = 15

        // locate the !


        // [0] : how many 0 before first !
        // [1] : how many 1 before first !
        // [2] : error before first !
        // [3] : order of the !
        final int MOD = 1_000_000_007;
        Map<Integer,Integer> map = new HashMap<>();
        int level = 0;

        Queue<Node> queue = new LinkedList<>();
        int ones = 0;
        int zeros = 0;
        int errors = 0;
        int result = Integer.MAX_VALUE;
        for(int i = 0;i<errorString.length();i++){
            char c = errorString.charAt(i);
            if(c == '0'){
                errors += ones * y;
                zeros++;
            }else if(c == '1'){
                errors += zeros * x;
                ones++;
            }else{
                break;
            }
        }

        Node root = new Node(errorString, new int[]{0,0,0});

        queue.offer(root);

        int p = 0;
        while(p < errorString.length()){
            int size = queue.size();
            for(int i = 0;i<size;i++){
                Node node = queue.poll();
                String str = node.str;
                int[] arr = node.candi;
                int curZero = arr[0];
                int curOne = arr[1];
                int curError = arr[2];
                System.out.println(str);
                System.out.println(curZero);
                System.out.println(curOne);
                System.out.println(curError);
                System.out.println(" ");

                int index = str.indexOf('!');

                if(p == errorString.length()-1){
                    // all ! is replaced
                    result = Math.min(result,curError);
                    continue;
                }

                char[] tempArray = str.toCharArray();

                // changing to 0
                char c = errorString.charAt(p);
                if(c == '!'){
                    tempArray[index] = '0';
                    String newStrZero = new String(tempArray);
                    int errorsFlipZero = (curError + curOne * y)% MOD;
                    queue.offer(new Node(newStrZero,new int[]{curZero+1,curOne,errorsFlipZero}));

                    // changing to 1
                    tempArray[index] = '1';
                    String newStrOne = new String(tempArray);
                    int errorsFlipOne = (curError + curZero * x)%MOD;
                    queue.offer(new Node(newStrOne,new int[]{curZero,curOne+1,errorsFlipOne}));
                }else if(c == '0'){
                    tempArray[index] = '0';
                    String newStrZero = new String(tempArray);
                    int errorsFlipZero = (curError + curOne * y)% MOD;
                    queue.offer(new Node(newStrZero,new int[]{curZero+1,curOne,errorsFlipZero}));
                }else{
                    tempArray[index] = '1';
                    String newStrOne = new String(tempArray);
                    int errorsFlipOne = (curError + curZero * x)%MOD;
                    queue.offer(new Node(newStrOne,new int[]{curZero,curOne+1,errorsFlipOne}));
                }


            }
        }

        // case changing to 0
        // for each i searching for x and y pattern, and calculate

        // case changing to 1

        // compare, and return min

        return result;


    }

    public static void main(String[] args){
        MinimumErros minimumErros = new MinimumErros();
        String errorString = "101!1";
        int x = 2;
        int y = 3;

        System.out.println(minimumErros.getMinErrors(errorString,x,y));

    }

}
