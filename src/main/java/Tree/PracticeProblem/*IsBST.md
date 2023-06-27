#### Is tree BST

这道题 让我们判断一个 tree 是否是 BST，注意这道题还是有坑的，没想象的那么简单

首先我们的想法可能就是遍历这个 tree 然后判断一下 左枝是否小于 parent 右枝是否大于 parent

但是我们还需要判断当前的 node 的右枝是否小于 当前其祖父

比如 一个 tree

[100,

95,300,

85,200,null,null]

那么这个 tree 就不是BST 这是因为 95 的右枝 比 root value 还要大，**所以我们不能只看 child 和 parent 的关系，我们还需要把 root value 一直传递下去 来确保root 左枝的所有 node value 都比 root value 小，root 的右枝的 value 都比 root value 要大**

同时 还有一点在我们使用 if statement 的时候注意如果 **if 里面判断的是 boolean，那么要使用 == 而不是 =**

###### 错误的写法:

if(flag = false)

###### 正确的写法:

if(!flag)  或者 if(flag == false)


上面错误的写法也可以进行编译 可以运行，但是会将 boolean 赋值


```java
class Solution{
    static Boolean flag;
    static Boolean is_bst(BinaryTreeNode root) {
        // Write your code here.
        flag = true;
        helper(root,null,null);
        // System.out.println(flag);
        return flag;
    }

    static void helper(BinaryTreeNode root, Integer low, Integer high){

        if(!flag){
            return;
        }

        if(root==null){
            return;
        }

        if(low != null && low > root.value){
            flag = false;
        }

        if(high != null && high < root.value){
            flag = false;
        }

        helper(root.left,low,root.value);
        helper(root.right,root.value,high);

        return;
    }
}
```


在 第 61 行 和 62 行 我们可以看到 

对于一个 node 左枝以下的最大值是当前 node value， 最小值不确定，如果这个 node 是 parent node 的右枝的话，那么最小值就是 parent node value，如果这个 node 是 parent node 的左枝，那么 最小值是传递下来的 也就是 low

如果 root 一直往左走，那么 low 会一直是 null

如果 root 一直往右走 那么 high 会一直是 null

只有当 发生拐点的时候， 之前一直向左走的前提下，low 会被更新为 当前的 node value，（因为现在向右走了）high 会是上层传下来的



