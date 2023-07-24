package Sort.ClassProblem;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static int[] quickSort(int[] arr){


        helper(arr,0,arr.length-1);

        return arr;

    }

    private static void helper(int[] arr, int start, int end) {

        if(start >= end) return;

        // pick the pivot using Random or mid
        int pivotIndex = start + (end-start)/2;
        int pivot = arr[pivotIndex];

        // swap with the first element
        swap(arr,start,pivotIndex);

        // record an index s = start
        int s = start;

        // loop the arr, if the arr[i] is smaller than pivot, s++ and swap with arr[i]
        for(int i = start+1;i<=end;i++){
            if(arr[i]<pivot){
                s++;
                swap(arr,s,i);
            }
        }

        // swap back the s and pivot(index start)
        swap(arr,s,start);

        System.out.println(Arrays.toString(arr));

        // continue for left half
        helper(arr,start,s-1);
        helper(arr,s+1,end);

        // continue for right half

        return;
    }

    private static int[] swap(int[] arr, int l, int r) {

        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
        return arr;
    }

    public static void main(String[] args) {

        int[] arr = new int[]{9,8,3,4,5,1,7};

        System.out.println(Arrays.toString(quickSort(arr)));


    }

//    piviot = 4
//
//    [4,8,3,9,5,1,7]
//     s i
//
//    [4,8,3,9,5,1,7]
//     s   i
//
//    [4,8,3,9,5,1,7].   =>.    [4,3,8,9,5,1,7]
//       s i                       s i
//
//    [4,3,8,9,5,1,7].   =>.    [4,3,1,9,5,8,7]
//       s       i                   s     i
//
//    [4,3,1,9,5,8,7]
//         s       i
//
//    [1,3,4,9,5,8,7]
//         s       i
}
