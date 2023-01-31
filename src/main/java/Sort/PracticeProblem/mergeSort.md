**Merge Sort**

```java
class Solution{
    static ArrayList<Integer> merge_one_into_another(ArrayList<Integer> first, ArrayList<Integer> second) {
        // Write your code here.

        int i = first.size()-1;
        int j = (int)second.size()/2-1;
        int k = second.size()-1;

        while(i>=0 && j >=0){
            if(first.get(i)>second.get(j)){
                second.set(k,first.get(i));
                i--;
            }else{
                second.set(k,second.get(j));
                j--;
            }
            k--;
        }

        while(k>=0 && i>=0){
            second.set(k,first.get(i));
            i--;
            k--;
        }

        while(k>=0 && j>=0){
            second.set(k,second.get(j));
            j--;
            k--;
        }

        return second;
    }
}
```