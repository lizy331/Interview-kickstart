package InterviewByCompany.TikTok;

import java.util.*;

// for each time the drone can only take at max 3 weight of goods
public class MinOperationCarrayAllGoods {

    public int getMinOperations(double[] arr){
        Arrays.sort(arr);

        double curWeight = 0;
        int result = 1;
        for(int i = 0;i<arr.length;i++){
            if(arr[i] + curWeight > 3){
                curWeight = arr[i];
                result++;
            }else{
                curWeight += arr[i];
            }
        }

        return result;

    }

    public static void main(String[] args){
        MinOperationCarrayAllGoods minOperationCarrayAllGoods = new MinOperationCarrayAllGoods();

        double[] testcase1 = new double[]{1.01,1.01,1.01,1.01};
        double[] testcase2 = new double[]{1.01,1.01,1.99,2};

        System.out.println(minOperationCarrayAllGoods.getMinOperations(testcase1)); // result = 2
        System.out.println(minOperationCarrayAllGoods.getMinOperations(testcase2)); // result = 3
    }
}
