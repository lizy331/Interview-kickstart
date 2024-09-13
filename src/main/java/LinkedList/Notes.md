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



