02/21/2023

1.这个问题能否被转换为 graph 问题

2. 1763 年 东普鲁士 中修建了7座桥，市民们好奇有没有可能 能够访问 7 做不同的桥恰好一次，而不重复的访问任何一座桥超过一次以上

3. 数学家 欧拉，将 每个陆地 分成了不同的点，A B C D，每条路径使用两个点来表示 比如 A到 B 为 AB

4. 面试中无论如何都需要先讨论 暴力方法，这么做的目的是确保你了解问题

5. 什么是 multi graph，如果一个 vertex 具有 指向自己的 edge，说明这个 graph 就是 multi graph

6. 什么是 degree of vertex? 就是一个 vertex 有几条线和其连接，注意有的 vertex 可以指向自己，那么这条线算两个 degree， 注意这种degree 计算方式只限于 undirected graph

7. 对于一个 undirected graph，sum of all degree 等于什么，**等于 edge 的数量的两倍，因为每一条 edge 都被算了两遍！** 

8. 对于 directed graph 我们将 degree 分为两种，out-degree，也就是 从一个 vertex 指向其他 vertex 的数量，indegree，就是一个vertex 含有的从其他 vertex 指向来的edge 数量

9. directed graph 中 sum of in-degree = sum of out-degree

10. **什么是 Eulerian cycle，访问所有的 edge 而不重复访问任何一条 edge，最后返回到起始点**
    什么是 Eulerian path，和 Eulerian cycle 类似，但是最后回到起始点
    

11. hamilton cycle 类似于在纸上画一个 graph 连接所有的点，而不重复的访问任何一个 点
    ，并且最后回到起点

12. 什么是 connected graph，c从任意一个 vertex 可以抵达任何一个 vertex

13. 如果一个 graph 存在 eulerian cycle，那么每个 vertex 的 degree 一定是偶数，或者可以说如果 一个 graph 中有任何一个点的 degree 是奇数，那么这个 graph 都不可能含有 eulerian cycle

    这是因为 eulerian cycle 需要访问所有的 edge，如果vertex 存在一个 奇数个 edge，那么这个 edge 会造成无法唯一访问的情况

14. **总结一下 Eulerian 点可以重复，边只能访问一次
    hamilton 边可以重复，点只能访问一次**

15. **一个重要推论，如果一个 connected graph 所有的 vertex 都具有偶数个 degree，那么这个 graph 一定存在 Eulerian cycle
    反之，如果一个 graph 存在 Eulerian cycle 那么 这个 graph 的所有的 vertex 都一定具有 偶数个 degree**

16. 对于一个 graph，是否存在一个 Eulerian path 的问题，如果存在 Eulerian cycle 那么一定存在 Eulerian path，path 的意思是最终不一定回到起点

    一个推论是 如果一个 graph **存在 0 个 或者 2个 vertex**，这些 vertex 每一个都含有奇数个 degree，那么graph 就存在 Eulerian path

    实际上一个 undirected graph 必然存在 偶数个 vertex，这些vertex 每个都含有 奇数个 degree，因为 sum of a graph degree = 2* Edges, 这里 sum of degree 很明显是一个 偶数
    
    所以不会存在奇数个 vertex 具有奇数个 degree，这会造成 sum of degree = odd

    **但是存在 Eulerian path 的条件一定是 0 个 或 2 个 vertex 具有 odd number of degree**

    

17. 欧拉对于 Eulerian cycle 和 path 存在性的总结：

    **if a graph exist more than two vertex with odd number of degrees, then there is no Eulerian cycle or path

    if a graph exist exactly two vertex with odd number of degrees, then there is an Eulerian path

    if a graph exist no vertex with odd number of degrees, then there is an Eulerian cycle**

18. Adjacency list 的表达方式的缺点在于，如果edge 由多个种类组成，比如 点代表 城市，但是 edge 可以是 飞机航线，也可以是 铁路轨道，那么我们需要对 空中航线使用一个 adjacency list，对铁路使用另一个 adjacency list

19. 什么时候使用 adjacency matrix，什么时候使用 adjacency list，
    
    当 graph 的 edge 数量非常多的时候 接近 n^2, 那么我们称之为 dense graph，此时使用 adjacency matrix 比较合适
    当 graph 的 edge 数量远远小于 n^2 时，我们称之为 sparse graph，比如 facebook 的 graph，点代表 用户，edge 代表两个用户之间的联系，facebook 有许多用户，但是用户之间的联系 要远远小于 用户的数量，所以此时使用 adjacency list 比较合适

20. 什么是 adjacency map？
    
    当我们相比adjacency list 和 adjacency matrix 的时候，AM 具有的优势在于 寻找一个 vertex A 是否具有 到达 vertex B 的 edge 的寻找时间 为 O(1)
    而 adjacency list 需要1 O(n) 的时间来遍历 vertex A 对应的 list，从而确定 vertex A 到 B 的 edge 是存在的
    
    adjacency map 是在 adjacency list 的基础上改进的，使得我们希望寻找 edge A-B 也可以通过 O(1) 的时间

    ![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/adjacency-map.png)

    当我们希望寻找 vertex A 到 B edge 是否存在的时候，直接去 vertex A 的对应 map 中（注意 adjacency map 实际上是一个 map of map，or called nested map）使用 get 或者 contains function 判断或者寻找 对应的 A-B edge 是否存在

21. 我们如何判断一个 graph 是 connected？

    ![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/connected-graph.png)

    从一个 点开始 不断的 capture 其相邻的点 直到无法继续进行 capture 如果此时在 un captured 的圆圈中还有 点剩余说明 这个点就是 unconnected

22. 使用 capture， 和 un-captured 思想，统一我们所遇见的其他 类型的 graph 算法

    ![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/Screen%20Shot%202023-02-23%20at%204.55.08%20AM.png)

23. 什么是 graph 中 的 BFS 算法

    类似于声纳，从某一个点开始，向四面八方走一步，得到的所有点算作第二层，在第二层的基础上 将第二层的所有点再向四面八方拓展一步，以此类推

    类似于 tree 的 BFS 我们还是需要使用 queue，注意当我们从第一个点开始扩展的时候，越往后，各个点扩展的 overlap 的区域也越来越多

24.

25.