## 235. Lowest Common Ancestor of a Binary Search Tree

又是 lowest common 系列，只不过这道题 是一个 BST

，那么 我们需要思考的核心就是 如何利用 BST 的性质来判断一个node 是 p 和 q 的 anscestor 或者说是 lowest Anscestor

答案就是如果一个 node val 处在 p 和 q 两个 node 的 value 之间，那么这个 node 一定是这两个 p 和 q 的最小公共祖先，为什么？

从根节点的角度看，root 将会是 p 和 q 之间的分割点，因为 p 和 q 不可能同时位于 root 的同一侧（否则 root 不会介于它们之间）。因此，root 就是它们的 LCA。

