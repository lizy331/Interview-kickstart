这道题让我们寻找一个 graph 中具有几个 connected component，实际上就是在让我们使用 DFS/BFS search，运行 DFS 的次数就是 connected component的个数

input:

```text
{
"n": 5,
"edges": [[0 ,1], [1, 2], [0, 2], [3, 4]]
}
```

```java
class Solution{


    static Integer number_of_connected_components(Integer n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        Integer res = 0;

        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        int[] visited = new int[n];

        for(int i = 0;i<n;i++){
            map.put(i,new ArrayList<Integer>());
        }

        for(ArrayList<Integer> edge : edges){
            map.get(edge.get(0)).add(edge.get(1));
            map.get(edge.get(1)).add(edge.get(0));
        }

        for(int i = 0;i<n;i++){
            if(visited[i]!=1){
                dfs(i,map,visited);
                res++;
            }
        }
        return res;
    }

    static void dfs(Integer source, Map<Integer,ArrayList<Integer>> map, int[] visited){
        visited[source] = 1;
        if(map.get(source).size()==0){
            return;
        }

        for(Integer des : map.get(source)){
            if(visited[des] !=1){
                dfs(des,map,visited);
            }
        }
        return;
    }

}
```

时间复杂度 O(m+n)

空间复杂度 O(n)