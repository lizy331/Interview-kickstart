寻找最大的岛屿面积

```text

Example One
{
"grid": [
[1, 1, 0],
[1, 1, 0],
[0, 0, 1]
]
}
Output:

4
```

```java
class Solution{

    static int res;
    static int temp;
    static int row;
    static int col;
    static Integer max_island_size(ArrayList<ArrayList<Integer>> grid) {
        // Write your code here.

        res = 0;
        temp = 0;
        if(grid.size()==0 || grid.get(0).size()==0){
            return res;
        }

        row = grid.size();
        col = grid.get(0).size();

        for(int i = 0;i<row;i++){
            for(int j = 0;j<col;j++){
                if(grid.get(i).get(j)==1){
                    dfs(grid,i,j);
                    res = Math.max(res,temp);
                    temp = 0;
                }
            }
        }
        return res;
    }

    static void dfs(ArrayList<ArrayList<Integer>> grid, int x, int y){

        if(x>=row||y>=col||x<0||y<0||grid.get(x).get(y)==0){
            return;
        }

        grid.get(x).set(y,0);
        temp++;
        dfs(grid,x+1,y);
        dfs(grid,x-1,y);
        dfs(grid,x,y+1);
        dfs(grid,x,y-1);

        return;

    }

}
```