package InterviewByCompany.Apple;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class NonSortedArrayUnion {

    public static int[] arrUnion(int[] arr1, int[] arr2){

        // create an arraylist
        List<Integer> res = new ArrayList<>();

        // sort arr1 and sort arr2
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        // two pointers traverse the arr1 and arr2
        // put the smaller one into the list, move the smaller pointer
        // if they are equal, put the arr1's into list, move both pointers
        int p1 = 0;
        int p2 = 0;
        while(p1< arr1.length && p2<arr2.length){
            if(arr1[p1]==arr2[p2]){
                res.add(arr1[p1]);
                p1++;
                p2++;
            }else if(arr1[p1]<arr2[p2]){
                res.add(arr1[p1]);
                p1++;
            }else{
                res.add(arr2[p2]);
                p2++;
            }
        }

        // if p1 still not exceed, keep adding
        while(p1<arr1.length){
            res.add(arr1[p1]);
            p1++;
        }

        // if p2 not exceed
        while(p2<arr2.length){
            res.add(arr2[p2]);
            p2++;
        }


        // return the arr
        int[] arrRes = new int[res.size()];
        for(int i = 0;i<res.size();i++){
            arrRes[i] = res.get(i);
        }

        return arrRes;
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,5};
        int[] arr2 = new int[]{5,7};

        System.out.println(Arrays.toString(arrUnion(arr1,arr2))); // [1,2,5,7]

        int[] arr3 = new int[]{9,8,1};
        int[] arr4 = new int[]{6,12};

        System.out.println(Arrays.toString(arrUnion(arr3,arr4))); // [1,6,8,9,12]
    }
}
