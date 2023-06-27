package Sort.ClassProblem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InsertionSort {

    static ArrayList<Integer> insertion_sort(ArrayList<Integer> arr) {
        // Write your code here.
        for(int i = 1;i<arr.size();i++){
            int temp = arr.get(i);
            int j = i-1;
            System.out.println(arr);
            while(j>=0 && arr.get(j)>temp){
                arr.set(j+1,arr.get(j));
                j--;
            }
            arr.set(j+1,temp);
        }
        return arr;
    }

    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(14,5,25,7,1,32,12));

        List<Integer> res = insertion_sort(list);

        System.out.println(res);

    }


}


//[14, 5, 25, 7, 1, 32, 12]
