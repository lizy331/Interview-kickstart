这道题是经典的 岛屿问题，给了一个 2d array 或者 arraylist，让我们判断图中有多少岛屿？

注意这道题考虑对角线上的陆地为连接着的

input:

```text
{
"matrix": [
[1, 1, 0, 0, 0],
[0, 1, 0, 0, 1],
[1, 0, 0, 1, 1],
[0, 0, 0, 0, 0],
[1, 0, 1, 0, 1]
]
}
```

output:

```text
number of islands = 5
```

我们使用 BFS 和 DFS 两种做法：

BFS:

BFS 的写法比较麻烦，主要在于我们需要一个 getNabor function 来手动的返回一个坐标点周围九宫格的坐标，即 nabors

如果我们使用 DFS 则可以让其 recursive 只要坐标值超出了 grid 的范围或者 当前遍历到的 cell 是 0/water，就返回

```java
class Solution{


    static Integer count_islands(ArrayList<ArrayList<Integer>> matrix) {
        // Write your code here.
        Integer res = 0;
        if(matrix.size()==0){
            return res;
        }

        for(int i = 0;i<matrix.size();i++){
            for(int j = 0;j<matrix.get(0).size();j++){
                if(matrix.get(i).get(j)!=0){
                    bfs(new int[]{i,j},matrix);
                    res++;
                }
            }
        }
        return res;
    }

    static void bfs(int[] pos, ArrayList<ArrayList<Integer>> matrix){
        matrix.get(pos[0]).set(pos[1],0);
        Deque<int[]> dq = new ArrayDeque();
        dq.offer(pos);
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            for(int[] des : getNabor(cur,matrix)){
                if(matrix.get(des[0]).get(des[1])!=0){
                    dq.offer(des);
                    matrix.get(des[0]).set(des[1],0);
                }
            }
        }
        return;
    }

    static ArrayList<int[]> getNabor(int[] cur, ArrayList<ArrayList<Integer>> matrix){
        ArrayList<int[]> nabor = new ArrayList<>();
        int row = matrix.size();
        int col = matrix.get(0).size();
        if(cur[0]>0){
            nabor.add(new int[]{cur[0]-1,cur[1]});
        }
        if(cur[1]>0){
            nabor.add(new int[]{cur[0],cur[1]-1});
        }
        if(cur[0]<row-1){
            nabor.add(new int[]{cur[0]+1,cur[1]});
        }
        if(cur[1]<col-1){
            nabor.add(new int[]{cur[0],cur[1]+1});
        }
        if(cur[0]>0 && cur[1]>0){
            nabor.add(new int[]{cur[0]-1,cur[1]-1});
        }
        if(cur[1]>0 && cur[0]<row-1){
            nabor.add(new int[]{cur[0]+1,cur[1]-1});
        }
        if(cur[0]>0 && cur[1]<col-1){
            nabor.add(new int[]{cur[0]-1,cur[1]+1});
        }
        if(cur[0]<row-1 && cur[1]<col-1){
            nabor.add(new int[]{cur[0]+1,cur[1]+1});
        }

        return nabor;
    }

}
```


DFS :

```java
class Solution{

    static int row;
    static int col;
    static Integer count_islands(ArrayList<ArrayList<Integer>> matrix) {
        // Write your code here.
        Integer res = 0;
        row = matrix.size();
        col = matrix.get(0).size();
        if(matrix.size()==0){
            return res;
        }

        for(int i = 0;i<matrix.size();i++){
            for(int j = 0;j<matrix.get(0).size();j++){
                if(matrix.get(i).get(j)!=0){
                    dfs(i,j,matrix);
                    res++;
                }
            }
        }
        return res;
    }

    static void dfs(int x, int y, ArrayList<ArrayList<Integer>> matrix){
        if(x>=row || y>=col || x<0 || y<0 || matrix.get(x).get(y)==0){
            return;
        }
        matrix.get(x).set(y,0);
        dfs(x+1,y,matrix);
        dfs(x-1,y,matrix);
        dfs(x,y+1,matrix);
        dfs(x,y-1,matrix);
        dfs(x-1,y-1,matrix);
        dfs(x-1,y+1,matrix);
        dfs(x+1,y-1,matrix);
        dfs(x+1,y+1,matrix);
        return;
    }



}
```


无论是 BFS 还是 DFS 时间复杂度 都是 O(m+n)

空间复杂度 O(n)