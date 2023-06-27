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

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(14,5,25,7,1,32,12));

        List<Integer> res = selection_sort(list);

        System.out.println(res);

    }


}


//keep puting the small number into the front
// we make sure each position we looping through is the minimial in the array

//
//[15,4,6,7,19,27]
//        ^
