###### **Maximum Width Of A Binary Tree**

这道题给了我们一个 BST，让我们寻找 这个 BST 的最大宽度，宽度的定义是在同一个 level 上
最左边的 node 和 最右边的 node 的中间距离，

比如 某一个 level 上 为 "4,null,null,5" 那么 这个 tree 的 width 就是 4，也就是最左边的 node 到 最右边的 node 的位置数量

只要最右边存在 node，那么中间的 null 也算入其中


![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/MaxWidthOfBST.png)

由于宽度是 同一级来进行 测量的 ，那么 我们只需要进行层级遍历，然后对于每一层进行 width 的计算，最后选出最大的 width 就是我们的答案

这道题的重点在于如何储存 每一个 level 的 node 的 位置信息，也就是说我们希望知道在每一层中 最左边的 node 的 位置，和最右边的 node 的位置，将这两个 index 做差就能得到我们的 答案

1. 如果我们直接将 TreeNode class 储存在 queue 中，那么就无法储存位置信息，所以这里的做法是 创建一个 新的 class 来同时储存 TreeNode 和 其位置信息
   
2. 当我们添加 node 的 left 和 right 到 queue 当中的时候我们如何 传递 位置信息？首先 root 的位置信息 是 0 很好理解，root 下面的左右枝（ level 1 ）我们希望他们的 编号是 0 和 1，再往下（ level 2 ）我们希望 四个 node 的编号是 0，1，2，3

    这样的话 如果 某一个 node 缺失，那么只要最大的 index 和 最小的 index 就可以计算出 这个 level 的 width

    比如说 level 2，四个 node 中中间的 两个 node 缺失，即 "0，null，null，3"，那么 我们只要统计了 最大的 index 3 和最小的 index 0，二者做差再加 1（如果不加 1 计算的是中间的距离，加1则是node 的数量也就是我们想要的）

###### **那么 我们如何将 每一层的 编号都按照顺序赋值呢？** （实际上就是这道题的精华）

**我们在添加 node 的 left 和 right 的时候 对于 left position 为 当前 node 的 position*2，对于 right position 为 当前的 node 的position*2+1**

这样一来 从 root 位置 为 0

level 1：左枝 0*2= 0 ， 右枝 0*2 + 1 = 1

level 2：0*2=0，0*2+1=1，1*2=2，1*2+1=3

以此类推

**我们就将 每一层的 tree node index 按照从小到大的连续的 顺序给赋值了**
   
