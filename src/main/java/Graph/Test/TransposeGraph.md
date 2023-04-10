这道题给了我们一个 GraphNod，这个 GraphNode 有两个 feature，一个是 node value，另一个 是 这个 node 所指向的其他 node arraylist

让我们对这个 graph node 进行转制，也就是原来 将所有的 direct graph edge
全部转换方向，

实际上这道题是在考察 返回数值的 dfs call 的写法，之前我们写的 dfs 都是 使用 void 的方式

这道题的难点是需要我们返回的还是这个 GraphNode，转换 edge 的方向其实很简单，但是我们需要想一种方法来将转换好的edge的 GraphNode 连接成一个 GraphNode 作为结果返回

我们的想法是 遍历 这个 GraphNode，同时将所有的 node 重新建立一遍 并保存在 hashmap 中（key = node.value, value = new graph node）

这个 hashmap 就是我们用来生成 transpose 的 hashmap，最后返回的就是题目输入的 node value 在这个 map 中对应的 graph node，因为我们在不断的更新这个 hashmap中的每一个 node

outer loop 就是 dfs(node)，没得说，在写 dfs 的时候我们首先写 base case，也就是当前遍历到的 node 在我们的 map 中出现了，那说明我们需要使用这个 graph node，（输入的 graph 是一个 cycle）

example one 

```text

input:
 
{
"edges": [
[1, 2],
[2, 3],
[3, 1]
]
}

output:

[
[2, 1],
[3, 2],
[1, 3]
]
```
 
```java
class Solution{

    /*
    For your reference:
    class GraphNode {
        Integer value;
        ArrayList<GraphNode> neighbors;

        GraphNode(Integer value) {
            this.value = value;
            this.neighbors = new ArrayList(3);
        }
    }
    */
    static Map<Integer, GraphNode> map;
    static GraphNode create_transpose(GraphNode node) {
        // Write your code here.
        map = new HashMap<>();
        return dfs(node);
    }

    static GraphNode dfs(GraphNode node){

        if(!map.containsKey(node.value)){
            map.put(node.value,new GraphNode(node.value));
        }else{
            return map.get(node.value);
        }

        for(GraphNode nei : node.neighbors){
            // call method to use the same reference in the map
            GraphNode newNei = dfs(nei);
            GraphNode newSur = dfs(node);
            newNei.neighbors.add(newSur);
        }
        return map.get(node.value);
    }

}
```

想象 1->2, 2->3, 3->1, 这个 graph 在我们遍历到 3 这个 node 的时候，它的 neighbor 1 已经出现在了 map 中，

所以 此时 neiNei 就会是 dfs(1)，也就是我们在 map 中新建的 graph node 1，注意 这里我们使用method 来 call map 中的 graph node

所以这个 **newNei 1 就是 map 中 1 对应的新建的 node，如果我们对 newNei 进行修改 map 中的 node 也会改变，因为他们具有相同的 reference**，我们在做的就是从 graph cycle 的末端开始，不断的将被指向的 neighbor node 对应的 neighbor feature中添加 指向这个 node 的 source node（也就是将 edge 调转方向）

最后我们将map 中第一个添加的 node 返回，此时我们已经通过 map 将这个 node 旗下的所有node edge 调转了方向
