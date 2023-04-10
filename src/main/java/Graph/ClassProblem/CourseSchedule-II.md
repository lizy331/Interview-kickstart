这道题和上一道 Course Schedule 类似，只不过这会让我们返回成功选修所有课程的顺序

这道题实际上在考察 directed graph 的 topology sorting

```text
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
```

```java
class Solution {
    public int timer;
    public List<Integer> toplog;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // build adj map
        Map<Integer, ArrayList<Integer>> map = new HashMap<>();
        for(int i = 0;i<numCourses;i++){
            map.put(i,new ArrayList<Integer>());
        }

        for(int[] edge : prerequisites){
            map.get(edge[0]).add(edge[1]);
        }
        //arrival arr/ visited 
        int[] arri = new int[numCourses];

        // departure arr
        int[] depa = new int[numCourses];

        Arrays.fill(arri,-1);
        Arrays.fill(depa,-1);



        // outer loop for each vertex
        // why we have to use outer loop to loop each vertex?
        // because we want to traverse all the connected components inside the graph
        timer = 0;
        toplog = new ArrayList<>();
        for(int i = 0;i<numCourses;i++){
            if(arri[i]==-1){
                if(dfs(numCourses,map,i,arri,depa)){
                    return new int[0];
                }
            }
        }
        // System.out.println(toplog);
        // Collections.reverse(toplog);
        int[] arr = toplog.stream().mapToInt(i -> i).toArray();
        return arr;

    }

    // dfs function
    public boolean dfs(int n,Map<Integer, ArrayList<Integer>> map, int source, int[] arri, int[] depa ){
        arri[source] = timer;
        timer++;
        for(Integer des : map.get(source)){
            if(arri[des] == -1){
                if(dfs(n,map,des,arri,depa)){
                    return true;
                }
            }
            else{
                if(depa[des]== -1){
                    return true;
                }
            }
        }
        depa[source] = timer;
        timer++;
        toplog.add(source);
    
        return false;
    }

}
```


我们只需要在上一道题的基础上将 所有的vertex 按照 **departure time** 从小到大排列起来 就是我们的答案

也就是最先 departure 的vertex 一定是 一个 没有指向任何下一个 vertex 的点，可以理解为 directed graph 的尽头

实际上 topology sorting 是 按照 departure time 排列之后的 reverse

但是结合这道题具体的情况 我们不用 reverse ，因为 directed graph 的尽头一定是最基础的课程 因为 [1,0] 代表 0 是 1 的 前置课程

所以直接 返回 按照 departure time 排列的vertex 即可

**时间复杂度 O(m+n)** 注意 这里是 directed graph，m 实际上是 所有 vertex 的 out degree 之和

所以 undirected graph 为 2*m，directed graph 为 m