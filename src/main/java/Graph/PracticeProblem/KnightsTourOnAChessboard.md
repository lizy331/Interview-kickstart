这道题给了一个 二维矩阵的长和宽，和一个 起点坐标，还有一个终点坐标，问我们从起点开始按照 knight 国际象棋的走法，最少几步可以到达终点

knight 的走法有点类似于 中国象棋的 马，但是不一样的在于国际象棋走的 不是 "日"字 而是一个长为 3 宽为 2 的矩形

他比 中国象棋的 马 走的更远一点


这道题的思路就是使用 bfs 来求极值，列出从每个点 可以抵达的位置，然后一层一层的 走，最后如果能抵达终点就返回步数

```text
Example
{
"rows": 5,
"cols": 5,
"start_row": 0,
"start_col": 0,
"end_row": 4,
"end_col": 1
}
Output:

3

3 moves to reach from (0, 0) to (4, 1):
(0, 0) → (1, 2) → (3, 3) → (4, 1).
```

```java
class Solution{

    static Integer res;
    static Integer temp;
    static int r;
    static int c;
    static Integer find_minimum_number_of_moves(Integer rows, Integer cols, Integer start_row, Integer start_col, Integer end_row, Integer end_col) {
        // Write your code here.
        res = -1;
        temp = 0;
        r = rows;
        c = cols;
        int[][] visited = new int[r][c];
        bfs(start_row,start_col,end_row,end_col,visited);
        return res;
    }

    static void bfs(Integer xs,Integer ys, Integer xe, Integer ye, int[][] visited){
        visited[xs][ys] = 1;
        Deque<int[]> dq = new ArrayDeque();
        dq.offer(new int[]{xs,ys});
        while(!dq.isEmpty()){
            int n = dq.size();
            for(int i = 0;i<n;i++){
                int[] source = dq.poll();
                if(source[0]-xe==0 && source[1]-ye==0){
                    res = temp;
                }
                for(int[] des : getDes(source)){
                    // System.out.println(des[0] + "," + des[1]);
                    if(visited[des[0]][des[1]]!=1){
                        visited[des[0]][des[1]]=1;
                        dq.offer(des);
                    }
                }
            }
            temp++;
        }
    }

    static ArrayList<int[]> getDes(int[] source){
        ArrayList<int[]> result = new ArrayList<>();
        int x = source[0];
        int y = source[1];

        if(x+1<r&& y+2<c){
            result.add(new int[]{x+1,y+2});
        }
        if(x+2<r&&y+1<c){
            result.add(new int[]{x+2,y+1});
        }

        if(x-2>=0&&y+1<c){
            result.add(new int[]{x-2,y+1});
        }

        if(x-1>=0&&y+2<c){
            result.add(new int[]{x-1,y+2});
        }

        if(x-2>=0&&y-1>=0){
            result.add(new int[]{x-2,y-1});
        }

        if(x-1>=0&&y-2>=0){
            result.add(new int[]{x-1,y-2});
        }

        if(x+1<r&&y-2>=0){
            result.add(new int[]{x+1,y-2});
        }

        if(x+2<r&& y-1>=0){
            result.add(new int[]{x+2,y-1});
        }


        return result;


    }

}
```

这道题注意 在写 BFS 的时候原本就有一个 for loop，这个 for loop 是用来遍历 queue 中当前这一层的所有元素

这道题在 BFS 中还有一个 for loop 这个 是用来遍历所有从当前点的可以抵达的坐标的 遍历，写的时候注意区分

还有在我们获取可以抵达的坐标的时候需要注意 减 是 >= 0, 如果坐标是 + （往右或者往下）那么判断条件是是否小于 <r 或 <c

时间复杂度是如何计算的？

我们知道 BFS 的时间复杂度是 O(n+m)  n 就是 所有的 vertex 也就是 r*c, m 就是 number of edges，这道题中 number of edges = r*c*8/2 （r*c*8是对于每一个 vertex 来说都可以和8个方向连接，除以2 是对于同一个 edge 会同时被两个 vertex share，即类似于 undirected graph

**所以时间复杂度 O(r*c)**

**空间复杂度 O(r*c)** 主要考虑在 queue 中最多可能有几个 vertex 同时存在
