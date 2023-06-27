package PracticalExperiments.StreamAPI;

import java.util.stream.IntStream;

public class ReverseString {

    public static String reverseString(String str){
        char[] chars = str.toCharArray();

        int l = 0;int r = str.length()-1;

        while(l<r){
            char temp = chars[l];
            chars[l] = chars[r];
            chars[r] = temp;
            l++;r--;
        }

        return new String(chars);
    }


    public static void main(String[] args) {
        String str = "helloworld!";

        System.out.println(reverseString(str));


    }
}
