package InterviewByCompany.DoorDash;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class DasherDelivery {


    /**
     https://www.1point3acres.com/bbs/thread-1097995-1-1.html

     coding craft 是高频题算dasher工资。题本身不难，input是dasherId，output是工资数额。说是假装写一个endpoint，但是实际上就是自己从class定义开始写。然后会有一个sample data，是一个list，每一个item大致是{dasherId: 1, deliveryId: 1, timestamp: "2024-01-01 00:00:00", status: "ACCEPTED"} 这样的，然后每个delivery会有两条记录，一个是accept，一个是cancel或者delivered.

     一定要注意时间，因为写的时候还要和面试官讨论你的thought process，比如说对于invalid dasherId你会返回0或者-1这样的特殊值还是throw exception。如果throw exception的话你还需要写个exception class。所以真的很花时间。。。


     https://www.1point3acres.com/bbs/thread-1091855-1-1.html

     Part 1:
     You are in charge of implementing the dasher payment model. The
     first version of the payment model is naively based on how much time
     dasher spends on each order. Given the sequence of accepted/fulfilled
     order activities from a given dasher on a given day, calculate the
     dasher pay based on the following payment rules
     1. base pay rate: $0.3 per minute
     2. multi order pay rate: # ongoing deliveries * base pay rate
     (when dasher is delivering multiple orders at the same time）
     Assumptions:
     1. All order activities are from the same calendar day
     2. Order activity sequence is valid. All orders are fulfilled by end of day
     (no duplicates, no fulfillment without pickup, no pickup without fulfillment, etc).
     3. API call along with time increasing, API freq not fix, (push model), real-time stream
     4l assume no same order accept or fulfillment twice
     Example:
     # Input:
     time    DxID   status deliveryId
     # 06:15: Dx accepted order A
     # 06:15: Dx accepted order C
     # 06:18: Dx accepted order B
     # 06:36: Dx fulfilled order A
     # 06:45: Dx fulfilled order B
     # Output (getPay): final pay: $14.4
     ...
     getOrder()
     getPay

     A       A + B.   B
     1           2.   1
     [15,18]. [18,36] [36,45]
     -----  ------  -------
     6:15.18    36.      45
     # Explanation:
     PAY = #accpted orders * 0.3 * duration

     # 06:15 - 06:18 →> рау = 1 * 0.3 * 3 →> $0.9
     # 06:18 - 06:36 →> pay = 2 orders * 0.3 * 18 →> $10.8
     # 06:36 - 06:45 →> рау = 1 * 0.3 * 9 = $2.7

     use case1: all orders will be fulfilled, and all fulfilled will previous be accept
     use case2: more orders at same time but never order time decrese


     step1: parse input: time + status + order + DxId
     step2: cal payment:
     loop inputs, maintains accpeted count, current Rate, if accepted count++, calculate the current rate, and add to the result

     accept: 0
     lastcall:
     totalPay:
     curPay: 0.3 * accept * duration(curtime - lasttime)



     input:
     List<Dasher>

     class Dasher{
     int dasherId;
     int deliveryId;
     int timestamp;
     String status;
     }

     output:

     int result
     how many delivery is delivered for each dasher
     ‘


     testcase:
     {dasherId: 1, deliveryId: 1, timestamp: 175632, status: "delivered"}


     */
    // str = "06:15 DX accepted orderA" Order{time, status, order}



//     private double totalPay;
//     private int accept;
//     private LocalTime lastTime;
//     private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");


//     public void getOrder(String log){
//         String[] info = log.split(" "); //{time, dxId, status, order}

//         // String[] timeInfo = info[0].split(":");
//         // int hours = String.parseInt(timeInfo[0]);
//         // int min = String.parseInt(timeInfo[1]);
//         // int curTime = hours * 60 + min;
//         LocalTime curTime = LocalTime.parse(info[0], formatter);

//         if (lastTime == null) {
//             lastTime = curTime;
//             if (status.equals("accepted")) {
//                 accept++;
//                 processedOrders.add(orderId);
//             }
//             return;
//         }

//         Duration duration = Duration.between(lastTime, curTime);
//         long minutes = duration.toMinutes();
//         totalPay += accept * 0.3 * minutes;


//         if (status.equals("accepted")) {
//             if (!processedOrders.contains(orderId)) {
//                 accept++;
//                 processedOrders.add(orderId);
//             }
//         } else if (status.equals("fulfilled")) {
//             if (processedOrders.contains(orderId)) {
//                 accept--;
//             }
//         }

//         lastTime = curTime;
//     }

//     public double getPay(){
//         return totalPay;
//     }

// /**
//     # 06:15 Dx accepted orderA
//     # 06:18 Dx accepted orderB
//     # 06:36 Dx fulfilled orderA
//     # 06:45 Dx fulfilled orderB
//     # Output (getPay): final pay: $14.4
//  */

//     public static void main(String[] args) {
//         Main solution = new Main();

//         String testcase1 = "06:15 Dx accepted orderA";
//         String testcase2 = "06:18 Dx accepted orderB";
//         String testcase3 = "06:36 Dx fulfilled orderA";
//         String testcase4 = "06:45 Dx fulfilled orderB";

//         solution.getOrder(testcase1);
//         solution.getOrder(testcase2);
//         System.out.println(solution.getPay());
//         solution.getOrder(testcase3);
//         solution.getOrder(testcase4);
//         System.out.println(solution.getPay());

//     }

    private double totalPay = 0;
    private int accept = 0;
    private LocalTime lastTime = null;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
    private Set<String> processedOrders = new HashSet<>();

    public void getOrder(String log) {
        String[] info = log.split(" "); // {time, dxId, status, order}
        LocalTime curTime = LocalTime.parse(info[0], formatter);
        String status = info[2];
        String orderId = info[3];

        // 如果是第一个订单，初始化时间
        if (lastTime == null) {
            lastTime = curTime;
            if (status.equals("accepted")) {
                accept++;
                processedOrders.add(orderId);
            }
            return;
        }

        // 计算时间差并累加工资
        Duration duration = Duration.between(lastTime, curTime);
        long minutes = duration.toMinutes();
        totalPay += accept * 0.3 * minutes;

        // 更新状态
        if (status.equals("accepted")) {
            // 如果订单未被处理过，增加 accept
            if (!processedOrders.contains(orderId)) {
                accept++;
                processedOrders.add(orderId);
            }
        } else if (status.equals("fulfilled")) {
            // 如果订单已经被接受，则减少 accept
            if (processedOrders.contains(orderId)) {
                accept--;
            }
        }

        // 更新最后处理的时间
        lastTime = curTime;
    }

    public double getPay() {
        return totalPay;
    }

    public static void main(String[] args) {
        DasherDelivery solution = new DasherDelivery();

        String testcase1 = "06:15 Dx accepted orderA";
        String testcase2 = "06:18 Dx accepted orderB";
        String testcase3 = "06:36 Dx fulfilled orderA";
        String testcase4 = "06:45 Dx fulfilled orderB";

        solution.getOrder(testcase1);
        solution.getOrder(testcase2);
        System.out.println("Pay after first two orders: $" + solution.getPay());
        solution.getOrder(testcase3);
        solution.getOrder(testcase4);
        System.out.println("Final pay: $" + solution.getPay());
    }
}
