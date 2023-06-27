package PracticalExperiments.StreamAPI;

import javax.swing.text.html.Option;
import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class DemoStreamApi {
    //在这段代码中，分组依据是一个固定的字符串 "a"，因此 Stream 中的所有元素都被分到同一个组中。这是因为 Collectors.groupingBy() 方法的工作原理是，将 Stream 中的每个元素应用一个分组函数，根据分组函数的返回值将元素分到相应的组中。在这个例子中，由于所有元素应用分组函数后返回的结果都是固定的字符串 "a"，因此所有元素都被分到同一个组中。
    //具体来说，这里使用了 lambda 表达式 a -> "a"，表示将 Stream 中的每个元素 a 都映射为字符串 "a"，即每个元素都被映射为相同的字符串。因此，Collectors.groupingBy() 方法将所有元素都分到了同一个组中，生成了一个 key 为 "a"，value 为包含所有元素的列表的 Map 对象。
    public static void testMethodGroupby() {
        List<String> letters = Arrays.asList("j", "a", "v", "a");
        Map<Object, List<String>> word = letters.stream().collect (Collectors.groupingBy(a->"a"));
        System.out.println(word);
    }

    public static void testMethodReduce() {
        List<String> letters = Arrays.asList("j", "a", "v", "a");
        String word = letters.stream().reduce("", (a, b)->a.concat(b));
        System.out.println(word);
    }


    public static void testMethodCollectJoin() {
        List<String> letters = Arrays.asList("j", "a", "v", "a");
        String word = letters.stream().collect(Collectors.joining());
        System.out.println(word);
    }

    public static void testMethodCollectGroupBy() {
        List<String> letters = Arrays.asList("j", "a", "v", "a");
        String word = letters.stream().collect(Collectors.groupingBy(a->a)).toString();
        System.out.println(word);
    }

    public static void testMethodMax() {
        List<Integer> nums = Arrays.asList(1, 2, 8, 19,6);
        Integer m = nums.stream().reduce(0,(last,n)->Math.max(last,n));
        System.out.println(m);
    }

    public static void testMap(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        Optional<Integer> res = numbers.stream()
                .filter(n -> n % 2 == 0)
                .map(n -> n * n)
                .findFirst();

        System.out.println(res);
    }

    public static void main(String[] args) {
//        testMethodGroupby();
//        testMethodReduce();
//        testMethodCollectJoin();
//        testMethodCollectGroupBy();
//        testMethodMax();
        testMap();
    }
}
