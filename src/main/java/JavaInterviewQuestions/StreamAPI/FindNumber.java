package JavaInterviewQuestions.StreamAPI;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class FindNumber {
        public static void main(String[] args) {
            List<Integer> numbers = IntStream.rangeClosed(1, 100)
                    .filter(n -> n % 3 == 0 || n % 7 == 0)
                    .boxed()
                    .collect(Collectors.toList());

            System.out.println(numbers);
        }
}
