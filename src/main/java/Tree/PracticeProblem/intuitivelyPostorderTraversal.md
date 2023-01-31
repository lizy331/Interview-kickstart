###### **Intuitively Postorder Traversal**

这道题 让我们实现后序遍历 ，**但是不能使用 recursion**

思路是 使用一个 stack，由于 stack 是 FIFO，我们先初始化 stack，并将 root 压入 stack 中，

然后使用一个 while loop，直到 stack 为空停止，
我们不断的把 stack 栈顶元素取出来，然后先判断这个 node 的左枝是否为空，如果左枝非空则压入栈中 并且将 node 的左枝设定为 null（**这样做的目的是 当我们再次遇见这个 node 的时候不会又陷入 if statement 来判断这个 node 的左枝不为空而将这个 node 再次压入 栈中**）

然后重复上面的操作 继续 process root 左枝，直到我们抵达 leaf node 此时，node 左枝为空，
则 使用 else if 判断右枝是否为空（应该也为空 因为 leaf node），那么此时我们达到了 最左边的 leaf node（因为我们一直先 判断 node 的左枝）

也就是说我们到达的 后序遍历 左右中 的左，所以我们将当前的 node 的 value 放入 res 中，并将 当前 node 从 stack 中 pop 掉，此时 栈顶元素就是 parent node，我们不知道 这个 node 是 parent node 的左枝还是 右枝，但是 无论是左枝还是 右枝，parent node 都不会再次因为我们刚刚 被 pop 掉的 node 而进入 stack 中去（因为我们 在 判断 左枝不是空的时候 将左枝 设置为了 null，如果右枝不为空 也将 右枝 设置为了 null）

```java
class Solution{
    class BinaryTreeNode {
        Integer value;
        BinaryTreeNode left;
        BinaryTreeNode right;

        BinaryTreeNode(Integer value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
    
    static ArrayList<Integer> res;
    static ArrayList<Integer> postorder_traversal(BinaryTreeNode root) {
        // Write your code here.
        res = new ArrayList<Integer>();
        Deque<BinaryTreeNode> dq = new ArrayDeque();
        dq.push(root);
        while(!dq.isEmpty()){
            BinaryTreeNode node = dq.peek();
            if(node.left!=null){
                dq.push(node.left);
                node.left = null;
            }else if(node.right!=null){
                dq.push(node.right);
                node.right=null;
            }else{
                res.add(node.value);
                dq.poll();
            }
        }
        return res;
    }

}
```

注意 39 行 和 42 行的细节







