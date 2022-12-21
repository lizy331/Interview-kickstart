这道题是 1-n 的数字 选出 k 个的组合

###### Example One

{
"n": 5,
"k": 2
}

Output:

[
[1, 2],
[1, 3],
[1, 4],
[1, 5],
[2, 3],
[2, 4],
[2, 5],
[3, 4],
[3, 5],
[4, 5]
]

###### Example Two

{
"n": 6,
"k": 6
}

Output:

[
[1, 2, 3, 4, 5, 6]
]


    static ArrayList<ArrayList<Integer>> res;
    static ArrayList<ArrayList<Integer>> find_combinations(Integer n, Integer k) {
        // Write your code here.
        res = new ArrayList<>();
        
        helper(n,k,1,new ArrayList<Integer>());
        return res;
    }
    
    static void helper(Integer n, Integer k, int start, ArrayList<Integer> out){
        if(out.size()-k==0){
            res.add(new ArrayList<>(out));
            return;
        }
        
        for( int i = start ; i<=n;i++){
            out.add(i);
            helper(n,k,i+1,out);
            out.remove(out.size()-1);
        }
        return;
        
        
    }


时间复杂度 O(n*2^n)

空间复杂度 O(n)