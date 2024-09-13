N-Queens leetcode 51

这道题给了一个 nxn 的棋盘 和需要摆放 queen 的数量 也是 n，问我们如何摆放 queens 才使得所有的 queen 之间无法进行厮杀

```text
input n = 4

output
[

 [".Q..",
  "...Q",
  "Q...",
  "..Q."],
  
 ["..Q.",
  "Q...",
  "...Q",
  ".Q.."]
  
  ]
```

题目要求我们返回 摆放好的摆法

queen 在棋盘当中的走法是，可以走直线以及斜线，


edge case:
    1 -> 1
    0 -> empty

sol:
    we can try all the possible solution 
    for each line starting from line 0, filling each column with queen from index 0, and then validate each step, 
    if it is validated then proceed to the next line, if it reaches the last line, then it is a valid result
    if it is not a valid result, then go back to the previous step

```java


public class Solution {
    List<List<String>> res;
    int size;

    public List<List<String>> solveNQueen(int n) {
        res = new ArrayList<>();
        size = n;

        // make a function call to start filling from line 0
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<n;i++){
            sb.append('.');
        }
        
        List<String> out = new ArrayList<>();
        for(int i = 0;i<n;i++){
            out.add(new String(sb));
        }
        helper(0, out);


        return res;
    }

    public void helper(int i, List<String> out) {
        // stopping condition, where the i is exceeded the last line, where i == size
        if (i == size) {


            res.add( new ArrayList<>(out));
            return;
        }
        
        // starting from index 0 for each column try to replace with Queen
        char[] chars = out.get(i).toCharArray(); // current line
        for(int j = 0;j<n;j++){
            chars[j] = 'Q';
            if(isvalid(out,i,j)){
                // if it is valid then move to the next line
                helper(i+1);
                
            }
            chars[j] = '.';
        }
    }
    
    public boolean isvalid(List<StringBuilder> out, int x, int y){
        
        // check the vertical line is valid or not
        for(int i = 0;i<size;i++){
            if(out.get(i).charAt(y) == 'Q'){
                return false;
            }
        }
        
        // check horizontal
        for(int j = 0;j<size;j++){
            if(out.get(x).charAt(j) == 'Q'){
                return false;
            }
        }

        // check diagnal from upper left to lower right
        int k = Math.abs(x-y);
        int l = 0;
        int r = k;
        while(l<=size && r<=size){
            if(out.get(l).charAt(r)=='Q'){
                return false;
            }
            l++;
            r++;
        }
        
        // check diagnal from lower left to upper right
        int k = Math.abs(x-y);
        int l = size-1;
        int r = k;
        while(l<=0 && r<=size){
            if(out.get(l).charAr(r)=='Q'){
                return false;
            }
            l--;
            r++;
        }
        
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        List<List<String>> res = sol.solveNQueen(4);
        
        
        for(int i = 0;i<res.size();i++){
            System.out.println(res.get(i));
        }
        
    }
}
```