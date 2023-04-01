这道题让我们判断 一个 graph 是否可以分成两个阵营，也就是说处在一个阵营的 vertex 之间没有相互的 edge/connection

leetcode 785


```java
class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] visited = new int[n];
        int[] parent = new int[n];
        int[] level = new int[n];

        for(int i = 0;i<n;i++){
            if(visited[i]!=1){
                if(bfs(i,graph,visited,parent,level)){
                    return false;
                };
            }
        }
        return true;
    }

    public boolean bfs(int source, int[][] graph,int[] visited, int[] parent, int[] level){

        Deque<Integer> dq = new ArrayDeque();
        dq.offer(source);
        visited[source] = 1;
        while(!dq.isEmpty()){
            Integer s = dq.poll();
            for(int des : graph[s]){
                if(visited[des]!=1){
                    dq.offer(des);
                    visited[des] = 1;
                    parent[des] = s;
                    level[des] = level[s] + 1;
                }
                else{
                    if(parent[des] != des && parent[des] != s && level[des] == level[s]){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
```

这道题核心需要思考到判断一个 graph 是否是 bipartite，就是看处在同一个 level 的 两个 vertex 有没有 connect（如果我们使用的 bfs 做法）

如果处在同一个 level 的两个 vertex 之间有 edge，那么必然存在一个三角形，出现三角形是一定无法形成 bipartite的

具体到 bfs 的做法上来说，我们需要判断 是否存在 同一个 level 上的 cross edge，

时间复杂度 O(m+n)

空间复杂度 O(n)

