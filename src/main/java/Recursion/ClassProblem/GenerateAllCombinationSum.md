###### **Generate All Combinations With Sum Equal To Target**

这道题 就是 combination sum，给我们一个 数组 和一个数字 target，问我们数组中那些数字的组合的sum 等于 target
，并且数组中存在 重复的数字，每一个 combination 都需要是独一无二的

###### Example One

{
"arr": [1, 2, 3],
"target": 3
}

Output:

[
[3],
[1, 2]
]

###### Example Two

{
"arr": [1, 1, 1, 1],
"target": 2
}

Output:

[
[1, 1]
]


    static ArrayList<ArrayList<Integer>> res;
    static Integer tar;
    static ArrayList<ArrayList<Integer>> generate_all_combinations(ArrayList<Integer> arr, Integer target) {
        // Write your code here.
        res = new ArrayList<>();
        tar = target;
        ArrayList<Integer> out = new ArrayList<>();
        Collections.sort(arr);
        
        helper(arr,target,out,0);
        
        return res;
    }
    
    static void helper(ArrayList<Integer> arr, Integer t, ArrayList<Integer> out,int start){
        if(t==0){
            res.add(new ArrayList<>(out));
            return;
        }
        
        if(t<0){
            return;
        }
        
        for(int i = start ;i<arr.size();i++){
            out.add(arr.get(i));
            helper(arr,t-arr.get(i),out,i+1);
            out.remove(out.size()-1);
            
            while(i<arr.size()-1 && arr.get(i)-arr.get(i+1)==0){
                i++;
            }
        }
    }


由于这道题 带有重复的数字，所以我们必须进行跳针的操作 也就是 63 行， **同时 既然我们需要进行 跳针操作 ，我们必须保证 array 是 sorted**


时间复杂度 O(n*2^n)

n 指的是 unique characters

空间复杂度 O(n)