import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

public class Solution {

    // s = {"water", "fire", "ring", "earth", "lord", "fire", "water"},
    public static Integer topKFreq(int[] arr){

        Map<Integer,Integer> map = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)->map.get(b)-map.get(a));
        for(int a: arr){
            map.put(a,map.getOrDefault(map.get(a),0)+1);
            pq.add(map.get(a));
        }
        return pq.peek();
    }

    public void deleteAlpha(String str){
        int res = 0;
        int temp = 0;
        char[] chars = str.toCharArray();
        for(int i = 0;i<chars.length;i++){
            if(Character.isAlphabetic(chars[i])){continue;}
            int unit = 0;
            temp = unit * 10 +(chars[i]-'0');

        }
        int unit = 1;
    }


    public static int[] moveZeroToLeft(int[] num){

        int s = num.length;
        for(int i = num.length-1;i>=0;i--){
            if (s!=num.length && num[s]==0 && num[i]!=0){
                swap(num,i,s);
                while(num[s]!=0 && s>=0){
                    s--;
                }
            }
            if (num[i]==0 && s==num.length){
                s = i;
            }
        }

        return num;

    }

    public static void swap(int[] num, int l, int r){
        int temp = num[l];
        num[l] = num[r];
        num[r] = temp;
    }

//    1,2,7,0,9,10,0,3
//          s



    public static void main(String[] args) {
//        int[] arr = new int[]{1,2,3,4,5,1,1,1,1,2,2,2,2,3,3,3,3,3,3,3,};
//        System.out.println(topKFreq(arr));
//        System.out.println(100 + 100 + "Simple");
//        System.out.println("simple" + 100 + 100);


        int[] arr = new int[]{1,2,7,0,9,10,0};

        System.out.println(Arrays.toString(moveZeroToLeft(arr)));

    }
}



