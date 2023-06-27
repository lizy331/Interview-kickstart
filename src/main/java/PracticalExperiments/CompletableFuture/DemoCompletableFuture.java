package PracticalExperiments.CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class DemoCompletableFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> productNameFuture = CompletableFuture.supplyAsync(() -> {
            // 异步查询商品名称
            // ...
            return "iPhone 12";
        });

        CompletableFuture<Double> priceFuture = CompletableFuture.supplyAsync(() -> {
            // 异步查询商品价格
            // ...
            return 9999.00;
        });

        // 注意 thenCombine 中lambda function 参数顺序
        CompletableFuture<String> resultFuture = productNameFuture.thenCombine(priceFuture, (productName, price) -> {
            // 合并结果
            return String.format("Product: %s, Price: %.2f", productName, price);
        });

        System.out.println("result: " + resultFuture.get());
    }
}
