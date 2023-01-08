Dec 28, 2022 remember ur hard works!

Tree Foundations

1. 什么是 ADT？ 
   
   Abstract Data Type

   
2. Array 使用 contiguous data structure，也就是说数组中的元素都存放在一起
   
   Linked List 使用的是 linked data structure, 一个元素指向下一个元素


3. 为什么 hashtable 中的 hash function 都会使用 mode？
   
   因为 hashtable 的空间是有限的，当同一个位置上已经存储了一个 value，有新的value 再次被指派到这个位置时，就会发生 collision
   此时我们需要对hashtable 进行 expand，**同时我们不希望 hashtable 中被指派到的 index 超出 hashtable 的现有空间范围，比如 hashtable 使用 array 储存元素
   元素的长度为 16，那么我们需要对 hash function 取 mod 16，这样所有得到的 index都会小于 16**
   

4. hash function 时间复杂度 O(1)， 但是 极端情况下 时间复杂度 worst case 会是 O(n)，这发生在当所有的 key 都被指派到了同一个 index 上
   也就是说我们的 hash function 没有起到任何作用
   

5. 什么是 balanced tree，定义是什么？
   只要 tree 的长度 h = O(logn) 那么我们就说这个 tree 是 balanced tree
   
   
6. Map 和 HashMap 的区别？
   HashMap 一定是使用 hashtable 进行安装的
   Map 可能是使用 BST 进行安装的
   

7. BST 中 什么是 successor？ 
   就是说如果我们将 这个 BST 中所有元素按照从小到大的顺序排列，那么一个元素刚好排在另一个元素的后面，那么这个比较大的元素就是这个较小的元素的 successor
   

8. 我们如何寻找一个 BST 元素的 successor？
   寻找 successor 我们需要分为两种情况来考虑， 
   
   a. 这个 元素是 leaf node，也就是说这个元素没有 right subtree，那么我们需要寻找这个元素的 parent node 来寻找 successor
   
   b. 这个元素不是 leaf node，那么 successor 会存在于 right subtree 中
   
   b 的情况非常简单，只需要寻找right subtree 中最小的元素就是 successor
   
   a 的情况比较复杂， 首先我们需要从 root search 这个元素（也就是我们需要寻找 successor 的元素），并且我们需要找到 距离这个元素最近的 right turn 的事件，right turn 意味着搜寻的路径往右走了
   我们需要在中间记录并不断更新 ancestor，直到我们搜寻到 这个元素为止


9. 如何对 BST 进行 delete operation？
   delete 操作非常的复杂， 需要考虑的 edge case 有很多，因为 delete 操作会发生在tree 的中间，也就是说 我们希望删除的 node 可能有 一个或者 两个子 node，我们需要维持 BST 的属性，所以需要我们进行 re-balancing
   对于 delete 我们分为以下三种情况
   1. 希望删除的 node 是 leaf node
   2. 希望删除的 node 有一个 子 node 
   3. 希望删除的 node 有两个 子 node
   
   第一种情况最简单，我们直接将这个 leaf node 的 parent 指向 null 就可以

   第二种情况，也比较简单，我们可以直接将这个 node 的 parent 指向这个即将被删除的 node 的唯一一个 子 node 即可

   最复杂的是第三种情况，我们需要找到这个 node 的 successor，然后将他们的 value 交换，然后再删除 successor， 由于 successor 只存在一个 子 node（successor 没有左枝），所以就相当于上面的 case 2


10. 相比较于使用 一个 sorted array 来储存元素，BST 的优势在哪里？

      首先 insert 一个 元素到 array 中，我们需要对这个 array 进行 right shift，时间复杂度为 O(n)
      使用 BST 进行 insert 时 时间复杂度最差就是 O(logn)

      同样对于 delete 也是， 所以 BST 的优势在于 insert 和 delete，但是 array 具有跨域 index 的优势，如果我们知道我们想要的元素在第几个位置，使用 array 具有优势


11. BST 什么是 re-balancing？什么时候需要进行 re-balancing？
      
      re-balancing 指的是如何选择 root 使得 BST 不会出现 一条长枝的情况，
    
      比如我们在创建 tree 的时候，输入的 array 是 reverse order 的，那么我们创建的树就会是一条长枝


12. 一个 BST 可以用来安装 Priority Queue 的数据结构

      A balanced BST can be used to implement the Priority Queue ADT because the operations of insert and extract-min (or extract-max) can both be implemented in O(log n) time.  

13. 如果一个 tree 有多个 child 怎么办？

      我们可以使用一个 list 来储存所有的 child，而不用 pointer 来指向下一个 node

14. 如何对 BST 进行 BFS 搜索
      sudo code：
       
      queen q = new queen
   
      q.push(root)
   
      while(q is not Empty){
   
         Node n = q.poll()
         print(n.value)
         if(n.left != null){q.push(n.left)}
         if(n.right != null){q.push(n.right)}
      }

15. preorder, inorder, postorder

      preorder 遍历顺序，**中左右**
   
      inorder 遍历顺序 **左中右**
   
      postorder 遍历顺序 **左右中**

      注意如果一个 tree 是 BST的话，那么 中序遍历 inorder traversal 将会得到 按照从小到大顺序排列的 node

        preorder 的意思是中间的 node 会 pre 与左右子枝 即 **中左右**
        
        inorder 意思是中间的 node 会在 左枝 和 右枝的中间被访问 **左中右**

        postorder 意思是 中间的 node 会在 左枝和右枝 最后被访问 **左右中**


16. 如果我们知道 一个 tree 的 preorder，或者 inorder 或者 postorder 我们能否重建 这个tree？
    
    如果已知我们的 tree 是 BST那么 我们只需要知道 preorder或者 postorder 就可以重建整个 tree

    如果不知道这个tree 是否是 BST 仅仅知道 一个 preorder 或者 inorder 或者 postorder 是远远不够的，我们必须知道 inorder + preorder 或者 inorder + postorder
    这是因为 inorder 提供了左右枝有哪些 node， preorder 提供了 root node 的位置信息 
    
    但是 preorder + postorder 是不够重建一个 非 BST tree 的


17. 注意BFS 的时间复杂度和 DFS 的时间复杂度 是一样的，是 O(n) n 是 node 的数量，并不是 O(logn) logn 是 BST 的标准长度，但是并不是遍历所有 node 的时间复杂度 