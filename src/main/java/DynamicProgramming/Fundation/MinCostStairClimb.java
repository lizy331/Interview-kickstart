package DynamicProgramming.Fundation;

import java.util.ArrayList;

public class MinCostStairClimb {

    static Integer min_cost_climbing_stairs(ArrayList<Integer> cost) {
        // Write your code here.
        int n = cost.size();
        // create a 1D arr, with length n+2, two extra 0 are the ground and top
        int[] arr = new int[n+2];

        // init the 1 index as cost.get(i)
        arr[0] = 0;
        arr[1] = cost.get(0);

        // add on the 0 at the end
        cost.add(0);

        // loop starts from index 2
        // arr[i] = Math.min(arr[i-1],arr[i-2]) + cost.get(i);
        for(int i = 2;i<=n+1;i++){
            arr[i] = Math.min(arr[i-1],arr[i-2]) + cost.get(i-1);
        }
        return arr[n+1];
    }
}
