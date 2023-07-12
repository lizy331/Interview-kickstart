package HackerRank.TEKFullStack;

import java.util.Collections;
import java.util.List;

// decide if you can use binary search or not,
// if the question have the requirement of arr[a] < arr[b] < arr[c], where a<b<c,
// then it is a sign of using binary search
public class Triplets {
    static long triplets(long t, List<Integer> d) {
        Collections.sort(d);
        int n = d.size();
        long count = 0;
        for(int i = 0;i<n;i++){
            for(int j = i+1;j<n;j++){
                int k = j+1;
                long target = t - d.get(i) - d.get(j);
                int start = k, end = n;
                while(start<end){
                    int mid = start + (end-start)/2;
                    if(d.get(mid)<= target){
                        start = mid + 1;
                    }else{
                        end = mid;
                    }
                }
                count+= start - k;
            }
        }
        return count;
    }
}
