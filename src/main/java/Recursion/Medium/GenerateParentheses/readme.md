这道题让我们将所有的合规的括号形式返回，给了一个数字 n，括号组合的长度最大为 2n
![img.png](img.png)

对于括号类型寻找合规的形式我们可以使用一个 stack，正括号压入stack，反括号抵消，最后只要stack 没有剩余说明括号形式是合规的

也可以不使用 stack，而是记录有多少open 括号，以及有多少 close 括号，当open 小于 n（2n长度的一半） 说明我们还可以添加open 括号，
如果open，如果 close 小于 open，那么我们 close 的括号还不够，则可以添加 close