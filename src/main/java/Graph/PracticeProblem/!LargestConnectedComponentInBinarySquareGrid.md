这道题给了我们一个 二维矩阵其中包含 1 和 0， 问我们如果我们将其中所有的 0 中的一个变为 1 可以组成的 最大面积的 component 是多少？

也就是说我们希望能找到一个 0， 这个 0 周围 存在两个（或者多个） 不同的 connected component 从而 使得这些 component 连接起来 组成面积最大的 component

Example one:

```text
Example One
{
"grid": [
[1, 0],
[0, 0]
]
}
Output:

2
```

Example two:

```text
Example Two
{
"grid": [
[1, 1],
[1, 1]
],
}
Output:

4
```

```java
class Solution{
    static class pair
    {
        int x;
        int y;

        public pair(int r, int c)
        {
            this.x = r;
            this.y = c;
        }

        public String toString()
        {
            return  "(" + x + "," + y + ")";
        }
    }


    static Integer largest_connected_component(ArrayList<ArrayList<Integer>> grid) {
        int rowSize = grid.size();
        int componentId = 2;
        int maxSize = 0;
        HashMap<Integer, Integer> compSizeMap = new HashMap<>();

        for(int i=0; i<grid.size(); i++) {
            for(int j=0;j<grid.size(); j++) {
                if(grid.get(i).get(j) == 1) {
                    int size = bfs(new pair(i, j), grid, componentId);
                    compSizeMap.put(componentId, size);
                    componentId++;
                    maxSize = Math.max(size, maxSize);
                }
            }
        }

        for(int i=0; i<grid.size(); i++) {
            for(int j=0; j<grid.size(); j++) {
                if(grid.get(i).get(j) == 0) {
                    int size = findMaxNeighborSize(new pair(i, j), grid, compSizeMap);
                    maxSize = Math.max(size, maxSize);
                }
            }
        }
        return maxSize;
    }

    static int findMaxNeighborSize(pair p,
                                   ArrayList<ArrayList<Integer>> grid,
                                   HashMap<Integer, Integer> compSizeMap)
    {
        int[][] dist = new int[][] {{-1,0}, {1,0}, {0,1}, {0,-1}};
        List<pair> neighbors = new ArrayList<>();
        int size = 1;
        HashSet<Integer> visited = new HashSet<>();

        for(int i=0; i<dist.length; i++) {
            int neighx = dist[i][0] + p.x;
            int neighy = dist[i][1] + p.y;

            if(neighx >= 0 && neighx < grid.size() &&
                    neighy >= 0 && neighy < grid.size() &&
                    grid.get(neighx).get(neighy) != 0) {
                int compId = grid.get(neighx).get(neighy);
                if(!visited.contains(compId)) {
                    visited.add(compId);
                    int compSize = compSizeMap.get(compId);
                    size = size + compSize;
                }
            }
        }
        return size;
    }

    static int bfs(pair p,ArrayList<ArrayList<Integer>> grid,int componentId){
        Queue<pair> q = new LinkedList<>();
        q.add(p);
        grid.get(p.x).set(p.y, componentId);

        int len = grid.size();
        int count = 0;

        while(!q.isEmpty()) {
            pair el = q.poll();
            count++;
            for(pair nei : getNeighbors(el, len)) {
                if(grid.get(nei.x).get(nei.y) == 1) {
                    grid.get(nei.x).set(nei.y, componentId);
                    q.add(nei);
                }
            }
        }

        return count;
    }


    static List<pair> getNeighbors(pair p, int len)
    {
        int[][] dist = new int[][] {{-1,0}, {1, 0}, {0, -1}, {0, 1}};
        List<pair> neighbors = new ArrayList<>();
        for(int i=0; i<dist.length; i++) {
            int neighx = p.x + dist[i][0];
            int neighy = p.y + dist[i][1];
            if(neighx < len  && neighx>=0 && neighy>=0 && neighy < len) {
                neighbors.add(new pair(neighx, neighy));
            }
        }
        return neighbors;
    }
    
}
```

这道题的关键在于 我们需要得到一个 关于 componentId 和 component size 的hashmap

我们将同一个 component的数字全部变为其 componentID，然后 grid 遍历所有的 0，寻找这个 0 周围的 component，然后不断的组成新的component

最后查看这个 新的 component 是否是最大的

