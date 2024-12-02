package InterviewByCompany.Amazon.Nov2024;

/**
 We need to build "Most frequently bought together" recommendations
 You are given data about Orders where each Order has: timestamp, customer id and list of products purchased and total price.

 You should be able to return the n most frequently purchased products for any given product, P.
 P and n will be inputs to your solution along with the list of Orders.



 example:
 order: timestamp, customer id, lis of products purchased, total price
 01.      jan 1.      123         [hat, keychain],    25
 02.      jan 1.      123         [keychain, water],    25
 03.      jan 1.      123         [keychain, hat],    25
 04.      jan 1.      123         [keychain, water, phone],    25
 05.      jan 1.      123         [water, phone],    25
 05.      jan 1.      123         [water, phone],    25

 MostFreqBoughtTogether(water, topBountTogether=3) -> phone, keychain

 */

import java.util.*;

public class MostFrequentBoughtTogether {

    class Order{
        Integer orderId;
        String timestamp;
        Integer customerId;
        List<String> purchased;
        Integer totalPrice;

        public Order(Integer ord, String tim, Integer cus, Integer tot ){
            this.orderId = ord;
            this.timestamp = tim;
            this.customerId = cus;
            this.totalPrice = tot;
            this.purchased = new ArrayList<>();
        }
    }

    public static List<String>  MostFreqBoughtTogether(List<Order> orders, String product, int n){
        // maxheap
        Map<String,Integer> freqMap = new HashMap<>();
        Queue<String> maxheap = new PriorityQueue<String>((a,b) -> Integer.compare(freqMap.get(b),freqMap.get(a)));

        // step 1: get freq
        for(Order order : orders){
            List<String> productPurchased = order.purchased;
            int isRelatedCount = 0;
            for(String productName : productPurchased){
                if(productName.equals(product)){
                    isRelatedCount += 1;
                }
            }

            if(isRelatedCount > 0){
                for(String productName : productPurchased){
                    if(productName.equals(product)){
                        continue;
                    }
                    if(!freqMap.containsKey(productName)){
                        freqMap.put(productName,isRelatedCount);
                        // maxheap.offer(productName);
                    }else{
                        freqMap.put(productName,freqMap.get(productName)+isRelatedCount);
                    }
                }
            }
        }

        // step2: put in head and get max
        for(String key : freqMap.keySet()){
            maxheap.offer(key);
        }

        // step 3: get results
        List<String> result = new ArrayList<>();
        int k = 0;
        while(!maxheap.isEmpty() && k<n){
            result.add(maxheap.poll());
            k++;
        }

        return result;

    }




    public static void main(String[] args) {
        System.out.println("Hello LeetCoder");


        MostFrequentBoughtTogether main = new MostFrequentBoughtTogether();
        MostFrequentBoughtTogether.Order order1 = main.new Order(1,"jan 1", 123, 25);
        order1.purchased.add("hat");
        order1.purchased.add("keychain");

        MostFrequentBoughtTogether.Order order2 = main.new Order(2,"jan 1", 123, 25);
        order2.purchased.add("keychain");
        order2.purchased.add("water");
        order2.purchased.add("water");
        order2.purchased.add("water");

        MostFrequentBoughtTogether.Order order3 = main.new Order(3,"jan 1", 123, 25);
        order3.purchased.add("keychain");
        order3.purchased.add("hat");

        MostFrequentBoughtTogether.Order order4 = main.new Order(4,"jan 1", 123, 25);
        order4.purchased.add("keychain");
        order4.purchased.add("water");
        order4.purchased.add("phone");

        MostFrequentBoughtTogether.Order order5 = main.new Order(5,"jan 1", 123, 25);
        order5.purchased.add("water");
        order5.purchased.add("phone");

        MostFrequentBoughtTogether.Order order6 = main.new Order(6,"jan 1", 123, 25);
        order6.purchased.add("water");
        order6.purchased.add("phone");

        List<MostFrequentBoughtTogether.Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        orders.add(order4);
        orders.add(order5);
        orders.add(order6);


        System.out.println(MostFreqBoughtTogether(orders,"water",2)); // keychain, phone



    }

//  Example:
//  order: timestamp, customer id, lis of products purchased, total price
//  01.      jan 1.      123         [hat, keychain],                  25
//  02.      jan 1.      123         [keychain, keychain, water, water, water],  25
//  03.      jan 1.      123         [keychain, hat],                  25
//  04.      jan 1.      123         [keychain, water, phone],         25
//  05.      jan 1.      123         [water, phone],                   25
//  06.      jan 1.      123         [water, phone],                   25

}


// 1. heap 的创建 map 需要先于 queue 创建
// 2. 如果 map 当中为空，并且 heap 使用了 map作为 comparator，此时queue offer 会导致 null pointer


















