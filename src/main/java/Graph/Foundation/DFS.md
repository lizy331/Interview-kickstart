Graph DFS input 和 BFS 一样，

DFS 其实可以使用 和 BFS 一样的写法，只需要把 queue 换成 stack

```java
class Solution{
    static private ArrayList<Integer> res;
    static ArrayList<Integer> dfs_traversal(Integer n, ArrayList<ArrayList<Integer>> edges) {
        // Write your code here.
        res = new ArrayList<Integer>();
        int[] visited = new int[n];
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        for(int i=0;i<n;i++){
            map.put(i,new ArrayList<Integer>());
        }

        for(ArrayList<Integer> edge : edges){
            map.get(edge.get(0)).add(edge.get(1));
            map.get(edge.get(1)).add(edge.get(0));
        }

        for(int i=0;i<n;i++){
            if(visited[i]!=1){
                visited[i] = 1;
                dfs(i,map,visited);
            }
        }
        return res;
    }

    static void dfs(Integer source, Map<Integer, ArrayList<Integer>> map,int[] visited){
        res.add(source);
        if(map.get(source).size()==0){
            return;
        }

        for(Integer des : map.get(source)){
            if(visited[des]!=1){
                visited[des] = 1;
                dfs(des,map,visited);

            }

        }
        return;
    }
}
```

注意 时间复杂度是 O(m+n), m = number of edges, m 来自于 sum of degree of all vertexes（因为对于每一个 vertex 我们都进行了 它的 degree 个数的操作，即 运行dfs function），实际上是 2m （sum of degree of all vertex = 2 * number of edges）

n 来自于 初始化 adjacency list 和 对于 每一个 vertex 的遍历