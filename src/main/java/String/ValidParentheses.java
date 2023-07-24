package String;

import org.apache.el.stream.Stream;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class ValidParentheses {

    // ({()}) ture
    // {[]()} false
    // {(}) false
    // ){}{} false
    // "" true

    public static boolean isValid(String str){

        // using a stack/deque to keep push the parentheses into
        Deque<Character> dq = new ArrayDeque<>();


        // loop the str, for each char
        // if it is left bracket, push
        char[] chars = str.toCharArray();


        for(int i = 0;i<str.length();i++){
            if(chars[i]-'('==0 || chars[i]-'{'==0 || chars[i]-'['==0){
                dq.push(chars[i]);

            }else if(dq.size()>0){
                if (chars[i]==')' && dq.peek()=='('){
                    dq.pop();
                }else if(chars[i]=='}' && dq.peek()=='{'){
                    dq.pop();
                }else if (chars[i]==']' && dq.peek()=='['){
                    dq.pop();
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
        // else right bucket, pop


        // check if deque is empty, empty = true, else false
        return dq.size()==0 ? true : false;
    }


    public static void main(String[] args) {
        System.out.println(isValid("({()})"));
    }
}
