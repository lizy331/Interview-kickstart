Binary Search 结合 rotated array，这类问题，一般是 让我们寻找 target 在一个 rotated array 的位置

我们需要特别注意的就是 每一次我们得到的 mid 是否处于 rotated part of the array

rotated 之后的 array 有一个特点就是，这个 array 有两截数字是递增的，中间被一个 pivot 给隔开

* 首先我们确定一下 mid 是否是我们要找的 target

* 如果 nums[l]<= nums[mid]

说明 mid index 的数字大于 l index 数字，mid 有可能处在两截中的任意一截当中，我们无法判断


* 如果 nums[r] < nums[mid]
**说明 mid index 所处的数字 小于 r index 数字，mid 和 r 一定 处在不同的 两截 array 当中，那么这个时候 pivot 一定处在 mid 和 r 这两个 index 的中间**
此时我们就可以根据题目的要求来相应的移动 指针

如果需要让我们 search target，那么我们需要首先判断这个 target 是 在这两截 array 当中的哪一截，

如果
target >= nums[l] && target < nums[mid] 那么说明 target 处在 左边的 一截 array 当中，此时我们应该更新右指针 r = mid - 1;
否则更新左指针 l = mid + 1;

mid 不一定是 pivot，但是我们可以确定的是 mid 并不是我们需要找的 target（因为我们之前判断过了）

