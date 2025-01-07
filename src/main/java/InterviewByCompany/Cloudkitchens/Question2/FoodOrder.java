package InterviewByCompany.Cloudkitchens.Question2;

import java.util.*;
public class FoodOrder {

    /**
     // You are given a static log file containing billions of entries. Each entry contains a timestamp and the name of a food order. The entries in the log file appear in order of increasing timestamp. Design a method getCommon(k) to determine the k most common food orders found in the log file.

     // Hamburger          1595268625
     // Salad              1595268626
     // HotDog             1595268627
     // Hamburger          1595268628
     // ...


     第三轮 Coding，Parse log，就是一个log file，里面一堆entry表示一些order的信息，让实现一个方法返回最受欢迎的topK orders。中规中矩，没啥特别的。



     follow up:
     - time window: 算法论 top k largest in moving window of size m in an array.
     - order size:
     - TBD

     log file
     name, time
     input:


     n - log size
     m - order name
     k - top k

     getCommon(k):
     data structure:
     Map<String, Integer> map
     key: name
     value: freq
     step1: loop log to store in map -> n
     step2: loop map to put in max-heap -> mlogm
     step3: pop heap k times -> klogm


     follow up1: top k largest in time range
     getCommon(k, int start, int end):
     data structure:
     Map<String, Integer> map
     key: name
     value: freq

     step1: loop log and store time range [start, end] order in map
     step2: loop map to put in max-heap -> mlogm
     step3: pop heap k times -> klogm

     follow up2: top k largest in time range, function be called during read log but no finish yet.
     - call getCommon live time
     - billions of entries
     class FoodOrderAnalyzer{
     Map<Integer, Map<String, Integer>> map. // TBD
     FoodOrderAnalyzer() {}
     List<String> getCommon(int k, int start, int end) {}
     }

     List<Object> stroed
     bs(starttime,stored) -> start object index
     bs(endtime, stored) -> end object index

     T: n + logn + logn


     step1: store when read
     step2: binary search to find start and end time
     step3: getCommon(k, start, end) with max-heap approach

     */

    class FoodOrderAnalyzer {
        // 全局频率状态
        private Map<String, Integer> globalFreqMap = new HashMap<>();
        // 每种食物的频率时间线
        private Map<String, TreeMap<Integer, Integer>> freqTimeline = new HashMap<>();

        // TC: O(1)
        public void addLog(String name, int timestamp) {
            // 更新全局频率状态
            globalFreqMap.put(name, globalFreqMap.getOrDefault(name, 0) + 1);

            // 更新该食物的频率时间线
            freqTimeline.putIfAbsent(name, new TreeMap<>());
            TreeMap<Integer, Integer> timeline = freqTimeline.get(name);

            int cumulativeFreq = timeline.isEmpty() ? 0 : timeline.lastEntry().getValue();
            timeline.put(timestamp, cumulativeFreq + 1); // logh -> h stands for number of name
        }

        //
        public List<String> getCommon(int k, int start, int end) {
            // 统计时间段内的频率
            Map<String, Integer> freqMap = new HashMap<>();
            for (String food : freqTimeline.keySet()) {
                TreeMap<Integer, Integer> timeline = freqTimeline.get(food);

                int startFreq = timeline.floorEntry(start) != null ? timeline.floorEntry(start).getValue() : 0;
                int endFreq = timeline.floorEntry(end) != null ? timeline.floorEntry(end).getValue() : 0;

                freqMap.put(food, endFreq - startFreq);
            }

            // 构建最大堆
            Queue<String> maxHeap = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));

            for (String key : freqMap.keySet()) {
                maxHeap.offer(key);
            }

            // 获取Top K
            List<String> result = new ArrayList<>();
            while (!maxHeap.isEmpty() && k > 0) {
                result.add(maxHeap.poll());
                k--;
            }

            return result;
        }
    }






    public static void main(String[] args) {
        FoodOrder main = new FoodOrder();
        FoodOrderAnalyzer analyzer = main.new FoodOrderAnalyzer();

        // 模拟日志添加
        analyzer.addLog("Hamburger", 1595268625);
        analyzer.addLog("Salad", 1595268626);
        analyzer.addLog("HotDog", 1595268627);
        analyzer.addLog("Hamburger", 1595268628);
        analyzer.addLog("Pizza", 1595268629);
        analyzer.addLog("Hamburger", 1595268630);

        // 查询
        System.out.println("Top 2 foods (1595268625 to 1595268630): " + analyzer.getCommon(2, 1595268625, 1595268630));

    }






}
