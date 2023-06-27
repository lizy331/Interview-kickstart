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

这道题 需要我们倒着遍历 array，使用三个指针，一个是用于 数组 1的最后一个数字，一个用于数组2最后一个数字，最后一个用于 数组1 的最后一个位置
nums1 中存在 空位 0，并且长度是 m+n，前 m 个是真实的数字
