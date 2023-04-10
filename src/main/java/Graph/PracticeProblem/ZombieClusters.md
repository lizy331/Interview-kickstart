```text
Zombie Clusters
There are zombies in Seattle. Some of them know each other directly. Others might know each other transitively, through others. For example, if zombies A<->B and B<->C know each other directly, then A and C know each other indirectly and all three belong to one cluster.

Knowing which zombies know each other directly, find the number of the zombie clusters.

Input is a square matrix where each cell, zombies[A][B], indicates whether zombie A knows zombie B directly
```

Example one:

```text
{
"zombies": [
"1100",
"1110",
"0110",
"0001"
]
}
Output:

2
```

Example Two:

```text

{
"zombies": [
"10000",
"01000",
"00100",
"00010",
"00001"
]
}
Output:

5
```


```java
class Solution{

    static Integer zombie_cluster(ArrayList<String> zombies) {
        // Write your code here.
        int n = zombies.size();
        HashMap<Integer,ArrayList<Integer>> map = new HashMap<>();

        for(int i = 0;i<n;i++){
            map.put(i,new ArrayList<Integer>());
        }

        int[] visited = new int[n];
        String temp = "";
        for(int i = 0;i<n;i++){
            temp = zombies.get(i);
            for(int j = 0;j<n;j++){
                if(j-i==0){
                    continue;
                }
                if(temp.charAt(j)-'1'==0){
                    map.get(i).add(j);
                }
            }

        }
        // System.out.println(map);

        int res = 0;
        for(int i = 0;i<n;i++){
            if(visited[i]!=1){
                dfs(i,map,visited);
                res++;
            }
        }
        return res;
    }

    static void dfs(int source, HashMap<Integer,ArrayList<Integer>> map, int[] visited ){

        visited[source] = 1;
        for(Integer des : map.get(source)){
            if(visited[des]!=1){
                dfs(des,map,visited);
            }
        }
    }



}
```