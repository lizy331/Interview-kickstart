又是一道 directed graph 问题, leetcode 202 course schedule

这道题告诉我们 有一些课程需要先完成其他课程才可以注册，问我们是否可以 顺利的完成所有课程

实际上这道题就是让我们寻找 一个 graph 中是否存在 cycle

而且 这是一个 directed graph，很明显，因为 如果 是 undirected

graph，那么我们一定完成不了， [1,2]  [2,1] 如果同时存在，那么 他们互为 铺垫课程，那么我们无法选择这两个课
 
**所以这道题就是 寻找 directed graph have cycle or not**

**使用 DFS 来寻找 cycle**

我们使用两个 array 一个叫做 arrival array，另一个叫做 departure array

分别记录我们第一次访问一个 vertex 的时间，和我们结束从这个 vertex 为起点向其他相邻的vertex 搜寻的，并返回到这个 vertex（从这个 vertex 撤出，remove from call stack） 时间节点

对于 directed 寻找 cycle， 当我们 遇见一个 vertex 之后 标记这个 vertex 的 arrival time，并向其指向的 vertex继续前进，如果我们发现 其指向的 vertex 的 arrival 题么 已经被标记（visited），并且 departure time 还没有被标记

那么就说明这个 edge 是一个 cycle

![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/Screenshot%202023-04-02%20at%206.55.20%20PM.png)

图中橙色的 edge 就是一个 back edge ，只要我们发现了 back edge，并且这个 back edge 指向的 vertex 还没有被设定 departure time，那么就说明存在一个 cycle


```text
Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
```

```java
class Solution {
    public int timer;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
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
        for(int i = 0;i<numCourses;i++){
            if(arri[i]==-1){
                if(dfs(numCourses,map,i,arri,depa)){
                    return false;
                }
            }
        }
        return true;

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

        return false;
    }
}


```