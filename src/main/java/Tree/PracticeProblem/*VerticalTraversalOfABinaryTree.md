###### Vertical Order Traversal Of A Binary Tree

这道题给了一个 BST，让我们按照一定顺序 返回所有的 node value

这个顺序就是从左往右，也就是说最靠左的 node，排在前，越靠右的 nodes 排在后面

![image](https://github.com/lizy331/Interview-kickstart/blob/main/src/img/verticalordertraversalofBST.png)

很容易想到应该记录 每一个 node 的 position，当一个 node 向左走则 position 减 1，往右走 position 加 1

问题是我们如何记录这个 position，并不断的将上一个 parent 的 node 传递给 child node？

而且有一些 node 会具有重合的 position，此时题目规定 parent node 排在前面，也就是 从上到下

**我们可以尝试 BFS，遍历每一层 level 然后将 node value 以及 position 从左到右记录在一个 array 当中**

那么我们有几个问题需要想清楚

1. 如何将 position 传递给 child node
2. array 如何存放 对应位置 position 的 node value，（position1 作为 index，node value 作为元素值），比如 position 出现负数


首先第一个问题，在平常的 BFS 中我们 将 BinaryTreeNode 直接存放到 queue 中，也就是说我们无法储存 position 的信息，那么 我们可以自己创建一个 class，来同时储存 BinaryTreeNode 和 position，这样我们在 queue 中存放的 就是 我们新建的 class

第二个问题，题目中说了 node 数量会在 0 - 40000 之内，那么我们可以创建一个 400002，或者比 40000长度大的 ArrayList array，这样我们将 root node 的 position 初始化为 中间值，就可以解决 负数 index 的问题

```java
    class Solution {
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

        class Node {
            BinaryTreeNode root;
            Integer position;

            Node(BinaryTreeNode node, Integer pos) {
                this.root = node;
                this.position = pos;
            }
        }

        static Deque<Node> dq;

        static ArrayList<ArrayList<Integer>> vertical_order_traversal(BinaryTreeNode root) {
            // Write your code here.
            dq = new ArrayDeque();
            ArrayList<Integer>[] arr = new ArrayList[40002];
            ArrayList<ArrayList<Integer>> res = new ArrayList<>();
            if (root == null) {
                return res;
            }
            dq.offer(new Node(root, 20001));

            while (!dq.isEmpty()) {
                int n = dq.size();
                for (int i = 0; i < n; i++) {
                    Node node = dq.poll();
                    if (arr[node.position] == null) {
                        arr[node.position] = new ArrayList<Integer>();
                    }

                    arr[node.position].add(node.root.value);

                    if (node.root.left != null) {
                        dq.offer(new Node(node.root.left, node.position - 1));
                    }

                    if (node.root.right != null) {
                        dq.offer(new Node(node.root.right, node.position + 1));
                    }
                }
            }

            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == null) continue;
                res.add(arr[i]);
            }

            return res;
        }
    }
```

时间复杂度 O(n) BFS

