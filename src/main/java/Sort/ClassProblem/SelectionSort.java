package Sort.ClassProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SelectionSort {

    static ArrayList<Integer> selection_sort(ArrayList<Integer> arr) {
        // Write your code here.
        for(int i = 0;i<arr.size();i++){
            int s = i;
            for(int j = i+1;j<arr.size();j++){
                if(arr.get(j)<arr.get(s)){
                    s = j;
                }
            }
            int temp = arr.get(i);
            arr.set(i,arr.get(s));
            arr.set(s,temp);
        }
        return arr;
    }

    public static void selectionSort(int[] arr){

        // loop each int i, start from 0
        // loop each int j, start from i
        // find the smallest among int j , and swap with the index of i
        for(int i = 0;i<arr.length;i++){
            int index = i;
            for(int j = i;j<arr.length;j++){
                // find the min
                if(arr[j]<arr[index]){
                    index = j;
                }

            }
            // swap with index i
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }

    }

    public static void main(String[] args) {
//        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(14,5,25,7,1,32,12));
//
//        List<Integer> res = selection_sort(list);
//
//        System.out.println(res);


        int[] arr = new int[]{14,5,25,7,1,32,12};
        selectionSort(arr);
        System.out.println(Arrays.toString(arr));

    }


}


//keep puting the small number into the front
// we make sure each position we looping through is the minimal in the array

//
//[15,4,6,7,19,27]
//        ^
