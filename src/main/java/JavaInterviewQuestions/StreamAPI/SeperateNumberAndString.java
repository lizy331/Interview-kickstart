package JavaInterviewQuestions.StreamAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SeperateNumberAndString {

    public static List<String> pickNumber(List<String> input){
        List<String> res = new ArrayList<>();

        res = input.stream().filter(a -> isNumber(a)).collect(Collectors.toList());

        return res;
    }

    public static boolean isNumber(String str){
        for(char c : str.toCharArray()){
            if (Character.isDigit(c)){continue;}
            else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {

        List<String> input = new ArrayList<>(Arrays.asList("12345","123jia","123","09","*&*"));

        System.out.println(pickNumber(input));
    }
}
