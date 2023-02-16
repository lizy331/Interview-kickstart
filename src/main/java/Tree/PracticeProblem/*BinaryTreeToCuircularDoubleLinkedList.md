###### **Binary Tree To Curricular Double Linked List**

这道题 给了我们一个 Tree，让我们将这个 tree 转化成一个 double linked list

![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/BinaryTreeToDCLL.png)


仔细观察这张图，我们会发现最终我们希望得到一个 inorder 的顺序，并且 从小的 value
 的 node 的 right 总是大的 node，

value 大的 node 的 left 总是上一个 比他小的 node

最后我们还需要将 最小的 node 和最大的 node 进行连接