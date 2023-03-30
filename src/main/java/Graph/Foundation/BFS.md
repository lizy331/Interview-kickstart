Graph 的 BFS， 输入的 input 是 n （number of vertexs） 和 edges ArrayList<ArrayList<Integer>>

input:

```text
{
"n": 6,
"edges": [
[0, 1],
[0, 2],
[0, 4],
[2, 3]
]
}
```


```java

class Solution{
    static private ArrayList<Integer> res;
    static ArrayList<Integer> bfs_traversal(Integer n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        int[] visited = new int[n];
        res = new ArrayList<Integer>();
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0;i<n;i++){
            map.put(i,new ArrayList<Integer>());
        }

        for(ArrayList<Integer> edge : edges){
            map.get(edge.get(0)).add(edge.get(1));
            map.get(edge.get(1)).add(edge.get(0));
        }

        for(int i =0;i<n;i++){
            if(visited[i]!=1){
                dfs(i,edges,map,visited);
            }
        }
        return res;
    }

    static void dfs(Integer source, ArrayList<ArrayList<Integer>> edges,Map<Integer,ArrayList<Integer>> map, int[] visited){

        Deque<Integer> dq = new ArrayDeque();

        dq.offer(source);
        visited[source] = 1;
        res.add(source);

        while(!dq.isEmpty()){
            Integer s = dq.poll();
            for(Integer des : map.get(s) ){

                if(visited[des]!=1){
                    dq.offer(des);
                    visited[des] = 1;
                    res.add(des);
                }
            }
        }
    }
    
}
```