package JavaInterviewQuestions.StreamAPI;

import java.util.Arrays;
import java.util.function.ToIntFunction;

public class FindThreeLargest {

    public static void main(String[] args) {
        int[] arr = new int[]{5,9,12,89,7,23,40}; // 1st 89, 2nd 40, 3rd 23
        System.out.println(Arrays.toString(findTopThree(arr)));
    }

    private static int[] findTopThree(int[] arr) {


        // loop the arr, for each number first compare with 0 index of res,
        // if smaller, then 1 index, if smaller, then 2 index
        // if smaller continue
        // 上面的方法不可行，这是因为
        // 那如果我们直接遇见了 第三大的数字，此时 第三大的数字排在第一，下一个循环就是最大的数字，那么此时 第三大的数字就被冲掉了


        // 我们可以使用 Stream API

        // 第一种写法
        // int[] res = Arrays.stream(arr).boxed().sorted((a,b)->b-a).limit(3).mapToInt(Integer::intValue).toArray();

        // 第二种写法
        // Integer::intValue 实际上是一种 function
        // int[] res = Arrays.stream(arr).boxed().sorted((a,b)->b-a).limit(3).mapToInt(integer -> integer.intValue()).toArray();

        // 第三种写法
        // integer -> integer.intValue(), Integer::intValue 实际上都是 function, 虽然他们都是以 functional interface 的形式出现的
        ToIntFunction<Integer> intValueExtractorLambda = integer -> integer.intValue();
        int[] res = Arrays.stream(arr).boxed().sorted((a,b)->b-a).limit(3).mapToInt(intValueExtractorLambda).toArray();
        return res;


    }
}
