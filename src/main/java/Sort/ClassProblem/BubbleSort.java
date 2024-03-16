package Sort.ClassProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 使用两个 for loop， outer loop 用来进行计次 n 次 循环，另一个 for loop 从后往前 只要遇见比自己大的就 swap
public class BubbleSort {
    static ArrayList<Integer> bubble_sort(ArrayList<Integer> arr) {
        // Write your code here.
        for(int k = 0;k<arr.size();k++){
//            System.out.println(k);
            for(int i = arr.size()-1;i>0;i--){
//                System.out.println(arr);
                if(arr.get(i)<arr.get(i-1)){
                    int temp = arr.get(i);
                    arr.set(i,arr.get(i-1));
                    arr.set(i-1,temp);
                }

            }
        }
        return arr;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(14,5,25,7,1,32,12));

        List<Integer> res = bubble_sort(list);

        System.out.println(res);

    }
}


// strating from the back of the array, and towards to the first position of array
