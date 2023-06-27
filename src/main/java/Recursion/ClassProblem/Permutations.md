这道题给了我们一个 Integer 数组，让我们返回这个 数组的所有排列，每个数字只能使用一遍

Example

{
"arr": [1, 2, 3]
}

Output:

[
[1, 2, 3],
[1, 3, 2],
[2, 1, 3],
[2, 3, 1],
[3, 2, 1],
[3, 1, 2]
]

解题的思路是 swap， 注意如果我们使用组合的方式，也就是从空 array 开始一个一个的添加数字的话，数字会不够用，
因为组合类型的问题总是使用比较多的 数字来组合成一个较小的 array

**而排序问题，是有多少数字排多长的数组，所以我们应该进行 swap**

```java
class Solution{
    static ArrayList<ArrayList<Integer>> res;
    static ArrayList<ArrayList<Integer>> get_permutations(ArrayList<Integer> arr) {
        // Write your code here.
        res = new ArrayList<>();
        dfs(0,arr);

        return res;
    }

    static void dfs(int start, ArrayList<Integer> arr){
        if(start-arr.size()+1==0){
            res.add(new ArrayList<>(arr));
            return;
        }

        for(int i = start;i<arr.size();i++){
            Collections.swap(arr,start,i);
            dfs(start+1,arr);
            Collections.swap(arr,start,i);
        }
    }
}
```


注意这里的 start 其实应该叫做 fixed index，也就是说我们先 fixed index 0，然后让其他位置上的数字都和 index 0 进行交换

**这里我们在调用 dfs call 的时候使用的是 start， 而不是 i，这是因为我们需要将 start 固定，让其他位置的 index i-n 和 start 进行交换，如果 我们使用 i+1 会让循环提前结束**

[1,2,3]->[2,1,3]->[3,1,2]

那么我们会得到  [1,2,3]  [2,1,3]   [3,2,1]

在这三个数组的基础上继续进行 fixed index 1， 也就是其他数字和 index 1 进行交换，那么我们又会得到

[1,2,3]->[1,2,3], [1,3,2]    注意 index = 1 和 i = 1 也会进行交换，也就是自己和自己交换，得到的还是原来的数组

[2,1,3]->[2,1,3], [2,3,1]

[3,2,1]->[3,2,1], [3,1,2]

最后 fixed index = 2，到达了数组的最后一个位置，实际上不会再进行交换了，因为再往后就没有数字了，所有的交换都是自己和自己交换


[1,2,3]->[1,2,3]->[1,2,3]

[1,2,3]->[1,3,2]->[1,3,2]

[2,1,3]->[2,1,3]->[2,1,3]

[2,1,3]->[2,3,1]->[2,3,1]

[3,2,1]->[3,2,1]->[3,2,1]

[3,2,1]->[3,1,2]->[3,1,2]

**时间复杂度： O(n*n!)**

从第一个root node 开始， 我们有 n 个子 node，这是因为我们有 n 种选择，也就是 index = 0 时， index 0 可以交换， index 1 可以交换， index 2也可以交换 一共 n 种选择

到了fixed index 等于 1 时，我们少了一种选择，所以子 node 会变成 n-1 个
以此类推，最后别忘了 对于每一个最后得到的数组 都要进行 copy 的操作，时间复杂度 为 n


**空间复杂度： O(n)**

由于我们调用了 helper function 并且输入了 array，所以使用的 空间就是原来的数组长度





