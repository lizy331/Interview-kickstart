# Interview Kickstart Nov, 2022

Hope you find yourself during the journey!


# 面试 coding 步骤以及技巧

对于一道题目而言我们需要思考的流程以及步骤有哪些

## 1. Input output

想清楚 Input 是什么data structure list？array？String？

Output 是什么 data structure，list？array？String？
    

## 2. Edge Cases 以及 test case的思考

接下来看看 input/output 的example，想一想有哪些 edge cases

**这一部分如果我们是在一个 coding platform的时候可以直接将 test case 准备好，也就是写到 main method 当中**

思考方向应该是 比如**不同的 input 变换是否会产生新的 output**

### Input 是否可能为 null？

### input 是 String

是否存在 input 为empty String 的情况
大小写是否重要
String 当中是否只含有字母，或者数字，或者特殊符号
字母顺序调换之后是否会产生不同的 output

### input 是 Array

如果 Array 是一个 空集？
Array 是否是 sorted
Array 是否是可以修改的？

### 如果input 位比较复杂，比如两个String，或者一个String和一个 array


## 3. 思考潜在的 data structure 或者 algorithm来解决这个 问题

核心思想就是 **我们可以模拟电脑的运算来进行一次计算，看看我们的想法是否能够将 input leads to output**

比如是否需要使用 double for loop

比如使用 hashmap来解决 key/value pair 的问题（最常见的就是 index/value）

比如使用 Queue 用来解决 first in first out 问题


## 4. 思考以及添加注释来分解我们解决这道问题的步骤

第一步 initializing data structure (hashmap, arraylist, etc)

第二步 for loop, 并且在注释中明确的写出来这个 for loop 的目的

第三步 处理 result （这一部分比较灵活随题意）



## 5. 完成 code implementation 之后需要回顾一下, 作为一个检查错误和总结的环节

data 是否 initialize 正确

是否有一些 syntax error, typo

主要的逻辑是否正确

核心思想是以一个第三人称 code reviewer 的视角来重新审视这个代码，而不是依然重复的以 code implementer 的视角来观察




