package JavaInterviewQuestions.MultiThreading;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MultiThreadExample {

    public static void main(String[] args) {
        // 创建一个 ExecutorService
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        // 创建一个 Callable 任务列表
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            final int num = i;
            Callable<Integer> callable = () -> {
                // 模拟耗时任务
                Thread.sleep(1000);
                return num;
            };
            callableList.add(callable);
        }

        // 提交 Callable 任务列表并获得 Future 对象列表
        List<Future<Integer>> futureList = null;
        try {
            futureList = executorService.invokeAll(callableList);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 处理 Future 对象列表中的结果
        List<Integer> resultList = new ArrayList<>();
        for (Future<Integer> future : futureList) {
            try {
                int result = future.get();
                resultList.add(result);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        // 输出结果
        System.out.println(resultList);

        // 关闭 ExecutorService
        executorService.shutdown();
    }
}
