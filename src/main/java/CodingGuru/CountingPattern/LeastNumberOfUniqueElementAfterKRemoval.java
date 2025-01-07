package CodingGuru.CountingPattern;

import java.util.*;

/**

 Given an array of integers arr and an integer k, return the least number of unique integers remaining after removing exactly k elements from the array.

 Input: arr = [5, 5, 4, 3, 2, 3, 2, 3, 3, 2], k = 6
 Expected Output: 1
 Justification: After removing 4, all 5, and three instances of 2, the updated array will be [3, 3, 3, 3]. It will have only 1 unique element.
 */
public class LeastNumberOfUniqueElementAfterKRemoval {

    public int getUniqueElements(int[] arr, int k){

        // step1: count the frequency of each integer in the array, and record the maxFreq
        Map<Integer,Integer> freqMap = new HashMap<>();
        int maxFreq = 0;
        for(int num : arr){
            freqMap.put(num,freqMap.getOrDefault(num,0)+1);
            if(freqMap.get(num)>maxFreq){
                maxFreq = freqMap.get(num);
            }
        }

        // step2: use a freq array to store the freq of each integer
        // for exp, index is freq, number with how many integer have this freq
        int[] freqArray = new int[maxFreq+1];
        for(int key : freqMap.keySet()){
            freqArray[freqMap.get(key)]++;
        }

        // step3: loop from beginning of the freq array,
        // if freqArray[i]*i <= k -> k can remove all elements that having freq as "i", removed element = freqArray[i]
        // freqArray[i]*i > k, -> k can remove partial of elements, removed elements = k/i
        // total elements -= removed elements
        // total elements = size of freqMap,
        int totalElements = freqMap.size();
        for(int i = 0;i<freqArray.length;i++){
            if(freqArray[i]*i <= k){
                totalElements -= freqArray[i];
                k -= freqArray[i]*i;
            }else{
                totalElements -= k/i;
                break;
            }
        }


        return totalElements;



    }

    public static void main(String[] args){
        LeastNumberOfUniqueElementAfterKRemoval solution = new LeastNumberOfUniqueElementAfterKRemoval();

        int[] testcase1 = new int[]{5, 5, 4, 3, 2, 3, 2, 3, 3, 2};
        int k1 = 6;
        System.out.println(solution.getUniqueElements(testcase1, k1)); // 1
    }
}
