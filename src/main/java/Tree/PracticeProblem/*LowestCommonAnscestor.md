Lowest Common Ancestor

这道题让我们寻找两个 node 的最近的公共 ancestor，

![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/lowestCommonAnscestor.png)

这道题应该使用 bottom up approach

这是因为，对于每一个 node 我们需要知道 左子树中 是否含有 其中一个 node，右子树是否含有其中一个 node，如果满足那么 当前 这个 node 就是 lowest common ancestor

还有一种情况是当前 node 就是 其中一个 我们想要寻找的 node，并且 其左子树或者右子树中一个也含有 一个 node是我们想要寻找的

我们如何保证找到的 node 是 lowest 的呢？

我们使用 bottom up approach 保证了 common ancestor 是 the lowest node 

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
    
    static Integer lca(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        // Write your code here.
        BinaryTreeNode node = helper( root, a, b);
        if( node != null ) {
            return node.value;
        }
        return 0;
    }


    static BinaryTreeNode helper(BinaryTreeNode head, BinaryTreeNode p, BinaryTreeNode q) {

        if( head == null ) {
            return null;
        }
        // what do we know about head at this point
        // Its not null
        // it can be p or q or neither
        if( head.value == p.value || head.value == q.value) {
            return head;
        }

        // if p is found and if q lies further below in the same side of the tree as p, then no need to look for q since 
        // p will always be the ancestor

        // head is not null AND head is not p or q

        BinaryTreeNode leftA = helper( head.left, p, q);
        BinaryTreeNode rightA = helper( head.right, p, q);

        // p and q or relative ancestor found in both sides of the tree so root has to be ansector
        if( leftA != null && rightA != null ) {
            return head;
        }

        if( leftA != null ) {
            return leftA;
        }
        return rightA;

    }
}
```

注意这道题的 写法，第 50 行，是 bottom up 中第一个遇见的 if statement，也就是用来判断 一个 node 是否是 我们需要找的 node 的代码

当我们在 50 行找到一个 node 之后立即返回
```text
if( head.value == p.value || head.value == q.value) {
            return head;
        }
```


这个代码巧妙地解决了一个 不太好直接看出来的 问题，也就是 我们需要寻找的 两个 node 就是 父子关系，

也就是说一个 node 是 另一个 node 的 ancestor，那么这个代码会直接被 50 行返回，这是因为题目确定了一定会有作为 input 的两个 node，如果我们发现一个 node 就是我们想找的，并且在其他地方都没有找到的话，说明另一个 node 一定是 这个 node 的 child

这样的话我们就没有必要继续在往下走了

比如 8 和 3， 3 是 8 的 ancestor，那么我们首先遇见的 会是 8，也就是在 59 行 不断向左走的情况下，在 50 行判断 了 8 就是 我们想要找的 其中一个 node，那么此时 就直接返回了，就回到了 8 的 parent node 的 59 行，parent node 的 60行一定是返回 null 的

**所以 如果两个 node 就是 ancestor 关系，那么 一旦我们找到 ancestor 循环就结束了**

10/30/2024
这代码我们需要修改一下，我们需要将 判断 当前的 node 是否是 p 或者 q 的逻辑放在 dfs 循环之后，这是因为我们采用的是 bottom up的方式，也就是说对于一个 node
我们先遍历这个 node 的左右子树，然后再 对当前这个 node 进行处理，


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
    
    static Integer lca(BinaryTreeNode root, BinaryTreeNode a, BinaryTreeNode b) {
        // Write your code here.
        BinaryTreeNode node = helper( root, a, b);
        if( node != null ) {
            return node.value;
        }
        return 0;
    }


    static BinaryTreeNode helper(BinaryTreeNode head, BinaryTreeNode p, BinaryTreeNode q) {

        if( head == null ) {
            return null;
        }
        

        BinaryTreeNode leftA = helper( head.left, p, q);
        BinaryTreeNode rightA = helper( head.right, p, q);

        // 我们需要将这个逻辑放在 遍历完成左右子树之后再进行，因为我们采取的是 bottom up 策略
        if( head.value == p.value || head.value == q.value) {
            return head;
        }

        // p and q or relative ancestor found in both sides of the tree so root has to be ansector
        if( leftA != null && rightA != null ) {
            return head;
        }

        if( leftA != null ) {
            return leftA;
        }
        return rightA;

    }
}
```
