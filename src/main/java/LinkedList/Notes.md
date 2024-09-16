LinkedList 需要注意的事情

# reverse Linked-list

需要三个 node，一个作为 pre，另一个作为 cur， 最后一个作为 cur 之后的 node 叫做 temp，我们不断的 将 cur 和 temp 进行替换， 并不断的将 temp 替换到 pre 的下一个 位置上去

```text

step 1.
pre->cur->temp->node2->afterwards

step 2.
pre->cur->node2->afterwards, temp->node2->afterwards

step 3.
pre->cur->node2->afterwards, temp->cur->node2->afterwards

step 4.
pre->temp->cur-node2->afterwards
```


```java
class Example{
    public void reverse(ListNode cur, ListNode pre){
        // step 1
        ListNode temp = cur.next;
        // step 2
        cur.next = temp.next;
        // step 3
        temp.next = pre.next;
        // step 4
        pre.next = temp;
    }
}

```

# fast slow pointer

如果我们需要定位 从倒数第 nth 位置上的 node ，那么 我们需要 使用 fast slow pointer，在初始的状态下我们将 fast 和 slow pointer 的差距地精一位 为 n 然后我们将 fast pointer 移动 两步，将 slow pointer 移动 一步

最终 slow pointer 所处的位置就是 我们需要定位的位置, 比如我们期望获得 倒数第 2 个位置上的 node，也就是下面的 node 5 的位置


```text
n=2

1->2->3->4->5->6
|     | 
s     f

at the end:
1->2->3->4->5->6->null
            |      | 
            s      f
            
add a pre:
dummy->1->2->3->4->5->6->null
                |  |      | 
                p  s      f
```

也就是当 f 等于 null 的时候，s 就刚好处在我们希望的倒数第二个 node 的位置上，我们还可以加一个 pre pointer 初始化为 dummy，作为 slow pointer 之前的 的 node 的位置，因为有的时候我们不止需要定位这个 slow pointer 的位置 我们还需要这个 s pointer 之前的node 的位置


# LRUCache

LinkedList 最经典的一道题

LRUCache 需要实现 两个 目标 function

1. get(key)
也就是获取 key 所对应的 node，并并将这个 node 放置到 cache to the head of the linked list

2. put(key, value)
put the key into the cache, if the cache is full (the size exceed the limit of the cache), then evict the node before tail


首先我们需要知道 LRUCache 所需要的数据结构是 cache

cache 的数据结构就是 DoubleLinked List + HashMap

## 首先为什么需要使用 DoubleLinkedList？
首先我们需要使用 linked list，这是因为我们希望储存每一个 element 和 每一个 element 之间的先后顺序

使用 double LinkedList 的原因是可以简化 delete node 和 add node 的操作，因为如果我们没有 prev 以及 next pointer 这两个 data field 的话，那么我们就需要遍历这个 linked list, 使用 double linkedlist 可以将这两个操作 的时间复杂度 简化为 O(1), 这也是 由于 LRUCache 需要频繁的 调用 delete 以及 add function

使用 hashmap 的目的是 我们希望 快速的 获得 所对应的 node，否则我们就需要 遍历这个 linked list来获取我们希望得到的 node


在写这道题的时候需要注意的细节有

1. 首先我们的 addNode，deleteNode，或者 moveNodeToFront 的 input 变量是 ListNode，而不是简单的 key 和 value，所以在我们添加一个 new node 到 cache 的时候 我们需要首先创建这个 node
2. 其次在我们调用 deleteNode 以及 addNode 的时候要特别注意 对 cache/hashmap 的修改，可以将 这个操作也写到 method 当中

