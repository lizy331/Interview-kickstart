### Nov 14, 2024 TikTok

Course Schedule II

Follow up 如果 input 没有给 n 个 course 怎么做这道题？

首先回顾一下 我们如何做这道题的？

1. 首先创建 graph 通过 adjacency list 
2. 然后初始化 visited array，0 代表这个 vertex 还没有被访问， 1 代表 这个 vertex 正在被访问， 2 代表这个 vertex 已经被访问过了 
3. 之后 我们对所有的 vertex 作为起始点开始使用 dfs 遍历，并且在 遍历的过程中如果发现了 环那么直接返回 empty array，如果没有发现环那么 我们就将所有的 vertex 添加到 result list 当中去



首先我们为什么需要 这个 n？

我们需要这个 n，是因为我们需要通过这个 n 来创建一个 visited array，然后通过这个 array 来判断是否存在一个环
我们需要这个 n 作为 这个 visited array 的长度

那么我们可以通过 找到 vertex 当中的最大值 来找到我们所需要的 n

在我们创建 graph 的时候我们需要做出一些改变，之前我们可以通过 numVertex 直接将所有的vertex 的 adjacency list 初始化，但是当题目中没有 给 numVertex 的时候
我们就需要通过 所有的 edges 来将 adjacency list 给初始化出来

[CourseII.java](https://github.com/username/leetcode/blob/main/src/main/java/InterviewByCompany/TikTok/CourseII.java)




### Nov 20, 2024 Meta

