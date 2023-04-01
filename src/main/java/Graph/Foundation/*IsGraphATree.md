这道题让我们判断一个 graph 是否是一个 tree，

我们需要明确什么是 tree，tree 实际上也是一个 graph，只是 tree 必须要满足:

**1. connected

2. no cycle**

所以这道题的目的其实就是判断 一个 graph 是否为 connected 并且 没有 cycle

input:

```text
{
"node_count": 4,
"edge_start": [0, 0, 0],
"edge_end": [1, 2, 3]
}
```

```java
class Solution{

    static Boolean is_it_a_tree(Integer node_count, ArrayList<Integer> edge_start, ArrayList<Integer> edge_end) {
        // Write your code here.
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        int[] visited = new int[node_count];
        int[] parent = new int[node_count];
        for(int i = 0;i<node_count;i++){
            map.put(i,new ArrayList<Integer>());
        }

        for(int i =0;i<edge_start.size();i++){
            map.get(edge_start.get(i)).add(edge_end.get(i));
            map.get(edge_end.get(i)).add(edge_start.get(i));
        }


        int component = 0;
        for(int i = 0;i<node_count;i++){
            if(visited[i]!=1){

                component++;
                if(component>1){
                    return false;
                }
                if(dfs(i,map,visited,parent)){
                    return false;
                }
            }
        }
        return true;
    }

    static boolean dfs(Integer source, Map<Integer,ArrayList<Integer>> map, int[] visited, int[] parent){
        visited[source] = 1;

        for(Integer des : map.get(source)){

            if(visited[des] !=1){
                parent[des] = source;
                if(dfs(des,map,visited,parent)){
                    return true;
                }
            }else{
                if(parent[source]!=des|| parent[source]-source==0){
                    return true;
                }
            }
        }
        return false;
    }

}
```

建立 adjacency list 的步骤和之前一样，不同之处在于我们需要使用一个 parent array，

**对于 dfs，只要我们发现了 一个 back edge 我们就判断这个 graph 有cycle** 但是我们如何发现 back edge 呢？注意我们使用 dfs 每次抵达一个新的 vertex 的时候都会去 adjacency list中寻找 这个 vertex 相邻的/指向的 vertex，

由于题目中给定的是 undirected graph，那么 这个list 中会包含当前 vertex 的 parent，我们必须要排除 这个 parent，因为 它不是我们要找的 back edge，其他情况下 如果当前 vertex 的 neighbor在 visited 中出现过，那么他就是 back edge，

这是这道题的比较重要的一点，在上面的代码中 source 就是我们的 当前vertex，des 就是 当前 vertex 的 neighbor