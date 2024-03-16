package Sort.RelatedProblem;

import java.util.Arrays;
import java.util.Collections;

public class WeightBags {

    public static void main(String[] args) {

        // input is integers
//        int[] weights = new int[]{1,4,6,9,4,5};
        int[] weights = new int[]{9,9,6,9,4,1};

        // each bag can contain 10 pounds, at most 2 items
        int numBags = weightBags(weights);


        System.out.println(numBags); // 4

    }

    private static int weightBags(int[] weights) {
        int res = 0;

        // sort the weights DESC
        Arrays.sort(weights);
        int i = 0;
        int j = weights.length-1;
        while(i<j){
            int temp = weights[i];
            weights[i] = weights[j];
            weights[j] = temp;
            i++;j--;
        }

        // two pointers, put the heavy first, then try to put the lightest
        // if you can only put heavy, then continue
        int p = weights.length-1;
        int k = 0;
        while(k<=p){
            if(weights[k]<=10){
                k++;
                if (weights[p]+weights[k]<=10){
                    p--;
                }
                res++;

            }
        }


        return res;
    }
}
