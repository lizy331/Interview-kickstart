package JavaInterviewQuestions.StreamAPI;

import java.util.OptionalInt;
import java.util.stream.IntStream;

public class NonRepeatableChar {
    public static void main(String[] args) {
        String str = "hhhhhhbh";

        OptionalInt k = IntStream.range(1,str.length()).filter(i->str.charAt(i)-str.charAt(i-1)!=0).findFirst();

        System.out.println(str.charAt(k.getAsInt()));
    }
}
