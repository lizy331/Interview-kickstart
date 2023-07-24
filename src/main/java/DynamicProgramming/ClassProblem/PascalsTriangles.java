package DynamicProgramming.ClassProblem;

import java.util.ArrayList;

public class PascalsTriangles {

    static ArrayList<ArrayList<Integer>> find_pascal_triangle(Integer n) {
        // Write your code here.
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        ArrayList<Integer> temp = new ArrayList<>();

        temp.add(1);
        result.add(new ArrayList(temp));

        if(n == 1) return result;

        temp.add(1);
        result.add(new ArrayList(temp));

        if(n == 2) return result;

        for(int i=3; i<=n; i++){
            // temp is reused because the trangle is only getting wider and wider
            // cache stands for arr[i-1][j-1]
            // the transition function is arr[j] = arr[i-1][j-1] + arr[i-1][j]
            // which is equal to arr[j] = cache + temp.get(j)
            int cache = temp.get(0);
            for(int j=1; j<temp.size(); j++){
                int new_num = (cache + temp.get(j)) % 1000000007;
                cache = temp.get(j);
                temp.set(j, new_num);
            }
            temp.add(1);
            result.add(new ArrayList(temp));
        }

        return result;
    }
}
