package workbook;

import java.util.PriorityQueue;

public class SecondMax {

    public int secMax(int[] nums) throws IllegalArgumentException{

        if(nums.length<2){
            throw new IllegalArgumentException("input length should at least 2");
        }
        // use PriorityQueue, add all the number into queue
        // sort the number DESC
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->b-a);
        for(int num : nums){
            pq.offer(num);
        }

        pq.poll();

        return pq.peek();
    }
}
