package InterviewByCompany.Amazon;

public class PublicStringEncode {


    public static String solution(String s){

        int n = s.length();
        int i = 0;
        int j = 0;
        char[] arr = s.toCharArray();
        StringBuilder sb = new StringBuilder();

        while(j<n){
            while(j<n && arr[i]==arr[j]){
                j++;
            }
            int count = 0;
            count = j-i;
            sb.append(count);
            sb.append(arr[i]);
            i=j;
        }

        return sb.toString();

    }


    // aaabbc -> 3a2b1c
    // aabbcca -> 2a2b2c1a
    // abc -> 1a1b1c


    // output will construct a string with the correspongding char frency
    // input string is empty?
    // "" -> ""

    // edge case, input should only contain characters

    // sol
    // hashmap, count the frequency of each character, but what if the repeat char shows up?
    // or two pointer solutions

    /*
    i=0, j=0
    aabbcca

    StringBuilder sb = new StringBuilder();

    while(j<n ){
        while(arr[i]==arr[j]){
            j++;
        }
        count = j-i;
        sb.append(count);
        sb.append(arr[i]);
        i=j;
    }

    return sb.toString();

    out of loop which j is in next cluster

    count = j-i
    i=j

    start over...

     */

    public static void main(String[] args) {

        String test1 = "aaabbc";
        String test2 = "aabbcca";
        String test3 = "abc";
        String test4 = "";

        System.out.println(solution(test1));
        System.out.println(solution(test2));
        System.out.println(solution(test3));
        System.out.println(solution(test4));
    }

}
