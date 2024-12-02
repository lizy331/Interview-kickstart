package InterviewByCompany.DoorDash;

import java.util.*;

/**

 https://www.1point3acres.com/bbs/thread-1096206-1-1.html
 给你三个list
 分别是 difficulty，profit和skills
 difficulty 和 profits 是对应了商品的难度和利润，skills是对应每一个厨师的skill
 只有skill 大于 difficulty，厨师才能做菜，才能获得profits
 请问能获得的最大profit 是多少

 假设每个厨师最多做一个菜 厨师之间的菜不可重复
 把菜按照profit从大到小排序，然后我们开始loop through sorted 菜，对于当前这个菜，我们在厨师里面找到skill尽可能小的但是却大于该菜difficulty的人 这个找厨师可以借用TreeMap<Skill, CountOf厨师>
 感觉像是LC 826？
 无论厨师可不可以重复做菜，solution都差不多


 */

public class MostProfitAssigningWork {

    public int maxProfit(int[] difficulty, int[] profits, int[] cheif){

        // step 1 initialize jobs, and sort the job based on the difficulty
        // [2,17] [4,10] [6,15] [8,30] [10,50]
        if(cheif.length==0)return 0;

        int n = difficulty.length;
        int[][] jobs = new int[n][2];
        for(int i = 0;i<n;i++){
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profits[i];
        }
        Arrays.sort(jobs,(int[] a, int[] b) -> Integer.compare(a[0],b[0]));

        // step 2 sorted the cheif array
        Arrays.sort(cheif);


        // step 3 assign cheif job, until there are job left
        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b,a));
        int j = 0;
        int result = 0;
        for (int ability : cheif) {  // m
            while (j < n && jobs[j][0] <= ability) {
                maxHeap.offer(jobs[j][1]);
                j++;
            }

            if (!maxHeap.isEmpty()) {
                result += maxHeap.poll();
            }
        }

        /**
         jobs[diff,profit,done]

         4 6  15 20
         5 15  15 20
         j


         maxHeap: profit
         offer: condition: ability
         heap:
         worker 6: 15 = poll()
         worker 8: 5 = poll()
         worker 9: heap isEmpty -> continue;
         worker 20: 20


         */



        return result;

    }





    public static void main(String[] args) {


        MostProfitAssigningWork solution = new MostProfitAssigningWork();

        int[] difficulty1 = new int[]{10,8,6,2,4};
        int[] profits1 = new int[]{50,30,15,17,10};
        int[] cheif1 = new int[]{4,5,6,7};

        System.out.println(solution.maxProfit(difficulty1,profits1,cheif1)); // 17(2) + 10(4) + 15(6) = 42

        int[] difficulty2 = new int[]{4,6,15,20};
        int[] profits2 = new int[]{5,15,10,20};
        int[] cheif2 = new int[]{6,8,9,20};
        System.out.println(solution.maxProfit(difficulty2,profits2,cheif2)); // 25


    }
}