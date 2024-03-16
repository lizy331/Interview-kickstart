package InterviewByCompany.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayAsNumAdd {

    public static int[] arrAddOne(int[] arr){

        // transform the arr into number
        int num = 0;
        for(int i = 0;i<arr.length;i++){
            num  = num*10 + arr[i];
        }

        // number add one
        num+=1;

        // construct a new arr
        List<Integer> arrList = new ArrayList<>();
//        while(num>0){
//            arrList.add(num%10);
//            num/=10;
//        }

        for(int i = num;i>0;i/=10){
            arrList.add(i%10);
        }

        // reverse the arraylist
        Collections.reverse(arrList);

        // return arr
        int[] res = new int[arrList.size()];
        for(int i = 0;i<arrList.size();i++){
            res[i] = arrList.get(i);
        }

        return res;
    }

    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,3}; // [1,2,4]
        int[] arr2 = new int[]{1,2,9}; // [1,3,0]
        int[] arr3 = new int[]{9};     // [1,0]
        int[] arr4 = new int[]{9,9,9}; // [1,0,0,0]

        int[] arr5 = new int[0];


        System.out.println(Arrays.toString(arrAddOne(arr1)));
        System.out.println(Arrays.toString(arrAddOne(arr2)));
        System.out.println(Arrays.toString(arrAddOne(arr3)));
        System.out.println(Arrays.toString(arrAddOne(arr4)));
        System.out.println(Arrays.toString(arrAddOne(arr5)));
    }
}
