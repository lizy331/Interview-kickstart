## Array:

### Leetcode 215: Kth Largest Element in an Array

考点 QuickSelect， PQ

注意在 partition 的时候 使用 4个 pointer start, a, b, end
然后再进行 recursive call 的时候注意对比 k 和 对应 阶段数组的长度

这道题顺便考差了 sort color，也就是在 使用 quickselect 的时候 我们其实就是在 进行 color sort，也就是将 数组分成三个部分 即 SSSSPPPPPLLLL

smaller then pivot, pivot, and larger than pivot

注意在我们进行 partition 的时候 ，使用 三个 pointer
pointer 用来进行对数组的遍历
sIndex 用来 swap 比 pivot 小的数字
lIndex 用来 swap 比 pivot 大的数字

使用 while loop (pointer <= lIndex) 也就是说 pointer 一旦遇见了 lIndex 那么partion 就完成了，这是因为 lIndex 之后的数字都是大于povit的

```text
while(t<=b){
         if(nums[t] < pivot){
             swap(nums,t,a);
             t++;
             a++;
         }else if(nums[t]>pivot){
             swap(nums,t,b);
             b--;
             // dont move the t, because we have not check the nums[t] value
         }else{
             // t is the pivot
             t++;
         }
     }
```

注意我们为什么不使用两遍 for loop？ 因为之后我们需要使用 sIndex 以及 lIndex 来继续进行下一轮的 quickselect, 

我们为什么使用这个 sIndex 以及 lIndex，因为假设我们的 数组当中有非常多的的 duplicated 数字，使用 sIndex 以及 lIndex 可以快速精准度定位 下一轮的 quickSelect 应该使用的 范围

### 15. 3Sum

考点：two pointer, 

这道题给了一个数组，要求我们找到 三个 元素 nums[i] + nums[j] + nums[k] = 0
```text
Example 1:

Input: nums = [-1,0,1,2,-1,-4]
Output: [[-1,-1,2],[-1,0,1]]
Explanation: 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
Example 2:

Input: nums = [0,1,1]
Output: []
Explanation: The only possible triplet does not sum up to 0.
Example 3:

Input: nums = [0,0,0]
Output: [[0,0,0]]
Explanation: The only possible triplet sums up to 0.
```

非常经典但是非常容易出错，有许多坑需要在面试的时候讲清楚，这道题我觉得核心要素就是你如何避免添加重复的答案, 就比如 [-1,-1,2] 和 [-1,2,-1] 实际上是一个答案

首先我们一定要 sort 这个 array，为什么？ 因为这是避免重复的第一步，因为我们可以从 最左侧开始选一个 index i 然后在 i 之后 进行 twoSum 找到其余的另外两个 index，这点为什么也是在去重，因为index i 之前的 数字已经被遍历过了，所以我们只关心 index i 之后的部分
，**然后非常关键的一步就是 在我们选择 index i 的时候 ，如果 nums[i] == nums[i+1] 那么我们需要跳过当前的 i，因为可以想像当 我们下一次 选择 index i+1 的时候一定会遇见一个重复的 triplet**

对于每一个 index i 我们进行 twoSum，来寻找 其余的 两个 index j 和 k，注意这里的 twoSum 并不是使用 binary search，而是 使用一个 sum 来判断 左指针或者右指针移动，注意左指针和右指针是 一步一步移动的
，当我们找到一个 answer 的时候 即 nums[index] + nums[j] + nums[k] == 0 的时候，此时我们将 左右指针向中间移动，但是 移动之后我们还需要做去重的工作

```java
class Solution {
    public List<List<Integer>> res;
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        res = new ArrayList<>();

        for(int i = 0;i<nums.length;i++){
            if(i == 0 || nums[i] != nums[i-1]){ // 我们需要跳过 重复的答案
                twoSum(nums,i);
            }
        }

        return res;
    }

    public void twoSum(int[] nums, int index){

        int left = index+1; // 不包括 index，因为index 就是 triplet 其中的一个答案
        int right = nums.length-1;
        

        while(left<right){
            int sum = nums[index] + nums[left] + nums[right];
            if(sum == 0){
                List<Integer> temp = new ArrayList<>();
                temp.add(nums[index]);
                temp.add(nums[left]);
                temp.add(nums[right]);
                res.add(temp);
                right--;
                left++;
                while(right>left && nums[right+1]==nums[right]){
                    right--;
                }
                while(right>left && nums[left-1]==nums[left]){
                    left++;
                }
            }else if(sum > 0){
                // dont move left pointer, because left pointer could be the min
                right--;
            }else{
                left++;
            }
        }

        return;
    }
}
```


### 16. 3Sum Closest

考点: two pointer

这道题是 3sum 的变种，现在告诉我们，array 中可能不存在 target，让我们寻找 3sum 得到的最接近 target 的结果，还是和 3sum 一样的做法，在 two sum 当中我们需要在每次移动两个指针的时候判断一下 当前的sum 和 target 的距离，并使用一个 global result 来记录距离 target 最近的 result

我第一次的做的时候出现的错误，
1. 既然是距离，那么你就需要使用 Math.abs() 来做，
2. 在初始化 result 的时候，应该将 result 初始化为 nums[i], nums[j], nums[k]
3. for loop 的时候，i 应该在 i<nums.length-2 时停止

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        //sort the array
        Arrays.sort(nums);

        // for every i, find the j and k that closest to target, if equal to tagrte then return
        int result = nums[0] + nums[1] + nums[2];
        for(int i = 0;i<nums.length-2;i++){
            int temp = twoSum(nums,i,target);
            if(temp == target){
                return temp;
            }else{
                result = Math.abs(result-target) >= Math.abs(temp-target) ? temp : result;
            }
        }

        return result;

    }

    public int twoSum(int[] nums, int i, int target){
        int j = i+1;
        int k = nums.length-1;
        int sum = 0;
        int result = nums[i] + nums[j] + nums[k];
        while(j<k){
            sum = nums[i] + nums[j] + nums[k];
            if(sum == target){
                return sum;
            }else if(sum < target){
                result = Math.abs(result - target) >= Math.abs(sum - target) ? sum : result;
                j++;
            }else{
                result = Math.abs(result - target) > Math.abs(sum - target) ? sum : result;
                k--;
            }
        }

        return result;
    }
}
```

时间复杂度 O(nlogn)
空间复杂度 O(1)


### 18. 4Sum

考点: two pointer

```text
Example 1:

Input: nums = [1,0,-1,0,-2,2], target = 0
Output: [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
Example 2:

Input: nums = [2,2,2,2,2], target = 8
Output: [[2,2,2,2]]
```
4Sum 同样是按照 3 sum 的方法来做，先选一个 a，然后开始循环 b，选一个 b，开始循环 c 和 d，特别需要注意中间的去重

这道题给我的启发是，我们不需要特别的写一个单独的function，来做 twoSum，我们可以直接将所有的嵌套写在一个function 当中，我们在写 3sum 的时候也应该这么做，因为快

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        
        for (int a = 0; a < nums.length - 3; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) continue;  // 去重
            
            for (int b = a + 1; b < nums.length - 2; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) continue;  // 去重
                
                int c = b + 1;
                int d = nums.length - 1;
                
                while (c < d) {
                    int sum = nums[a] + nums[b] + nums[c] + nums[d];
                    
                    if (sum == target) {
                        result.add(Arrays.asList(nums[a], nums[b], nums[c], nums[d]));
                        
                        while (c < d && nums[c] == nums[c + 1]) c++;  // 去重
                        while (c < d && nums[d] == nums[d - 1]) d--;  // 去重
                        
                        c++;
                        d--;
                    } else if (sum < target) {
                        c++;
                    } else {
                        d--;
                    }
                }
            }
        }
        
        return result;
    }
}

```

时间复杂度 O(N^3)
空间 O(n); 用于保存结果

### 259. 3Sum Smaller

考点: two pointer

和之前的 3sum 非常类似，不同点在于 现在 我们需要统计的是 3sum 小于 target 的数量，并且我们是允许 duplicate 的

```java
class Solution {
    public int threeSumSmaller(int[] nums, int target) {
        if(nums.length<3){return 0;}

        Arrays.sort(nums);
        int result = 0;
        for(int i = 0;i<nums.length-1;i++){
            int j = i+1;
            int k = nums.length-1;
            while(j<k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum < target){
                    result += k-j; // 注意计数的方式 i j       k, 如果 目前的 i j k 满足小于 target的条件，那么 i j k-1 也满足，i j k-2 也满足 。。。，所以共有 k-j 个答案，
                    j++;
                }else{
                    k--;
                }
            }
        }

        return result;
    }
}
```

时间 O(nlogn)
空间 O(1)

注意 k-j+1 是 index j 到 k 所有元素的个数
k-j 则是去除了一个元素，在这道题中，我们计数结果的时候，不能把 j 算进去，因为 k 不能移动到 j 的位置上

### 30. Substring with Concatenation of All Words

考点: Two pointer

这道题给了一个 words array 其中包含 string，然后给了我们一个 string s, 让我们寻找哪些 startIndex 是一个恰好（exactly）包含所有的 在 words 当中的 string


```text
Example 1:

Input: s = "barfoothefoobarman", words = ["foo","bar"]

Output: [0,9]

Explanation:

The substring starting at 0 is "barfoo". It is the concatenation of ["bar","foo"] which is a permutation of words.
The substring starting at 9 is "foobar". It is the concatenation of ["foo","bar"] which is a permutation of words.

Example 2:

Input: s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]

Output: []

Explanation:

There is no concatenated substring.

Example 3:

Input: s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]

Output: [6,9,12]

Explanation:

The substring starting at 6 is "foobarthe". It is the concatenation of ["foo","bar","the"].
The substring starting at 9 is "barthefoo". It is the concatenation of ["bar","the","foo"].
The substring starting at 12 is "thefoobar". It is the concatenation of ["the","foo","bar"].
```

sliding window

```java
import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return result;

        int wordLength = words[0].length();
        int wordCount = words.length;
        int windowSize = wordLength * wordCount;

        Map<String, Integer> wordFreq = new HashMap<>();
        for (String word : words) {
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        // 这里的 start 是为了调整 sliding window 的起始位置，sliding window 起始点可以是 0->wordLength-1
        // 后面的 sliding 部分会交给下面的 for 循环来处理
        for (int start = 0; start < wordLength; start++) {
            Map<String, Integer> windowMap = new HashMap<>();
            int left = start, count = 0;

            for (int right = start; right <= s.length() - wordLength; right += wordLength) { // 需要学习这种 移动指针的方式，尤其是我们需要按照一个递增或递减的的 fixed 数量来遍历
                String sub = s.substring(right, right + wordLength);

                if (wordFreq.containsKey(sub)) {
                    windowMap.put(sub, windowMap.getOrDefault(sub, 0) + 1);
                    count++;

                    while (windowMap.get(sub) > wordFreq.get(sub)) {
                        String leftWord = s.substring(left, left + wordLength);
                        windowMap.put(leftWord, windowMap.get(leftWord) - 1);
                        left += wordLength;
                        // 这个 while 循环的意义是在于，目前我们将 sub 添加进来之后发现, windowMap 中的 sub 超过了 wordFreq 当中这个 word 的 frequency
                        // 也就是说 我们现在又添加了一次 sub，导致 sub 出现的次数太多了，那么我们需要通过挪动左指针 来将这个 sub 的 次数减小到合法
                        // left 指针在移动的过程中，在某个时间点会指向 sub，此时 windowMap 就会将 sub 的 freq 减小 1，最终让我们的 sub 合法
                        // 此时的这个 left 就是我们下一轮 sliding window 的起点
                        count--;
                    }

                    if (count == wordCount) {
                        result.add(left);
                    }
                } else {
                    windowMap.clear();
                    count = 0;
                    left = right + wordLength;
                }
            }
        }
        return result;
    }
}

```
### leetcode 238: Product of Array Except Self

考点 数组
follow up: 不使用额外数组

注意这道题目的 product 是不包含数组 nums[i] **（也就是不包含其自身的product）**

两遍遍历
可以使用一个 结果 数组 先计算 leftProduct
然后使用一个 suffix，从后往前遍历，用 suffix 来作为 rightProduct

### leetcode 53. Maximum Subarray

考点 数组 Divid and Conquer
follow up: 如何使用 divide and conquer 的方法

分治法，divide the array into three halfs,

Left -> Mid
crossArray
mid -> right

```text
L L L L L L mid R R R R R R
    i               j 
    
假设我们 i 到 j 就是 crossArray 
如何找到 i 和 j?  可以从mid 向两边遍历寻找最大sum
```

### 169. Majority Element

考点: counting, Array

这道题 给了一个数组 让我们计数其中最多次出现的 element

我们可以使用一个 count 和一个 result，然后遍历数组，当这个 count 变成 0 的 时候我们就将result 赋值为 当前数字，如果当前数字就是 result 那么就将 count++
如果不是就 count--，这样出现的最多次的 element 一定会是 result

```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        int res = 0;
        for(int i = 0;i<nums.length;i++){
            if(count==0){res = nums[i]; count++;}
            else{
                if(nums[i] == res){
                    count++;
                }else{
                    count--;
                }

            }
        }
        return res;
    }
}

// res = 2
// [2,2,1,1,1,2,2]
//      ^
```

### 153. Find Minimum in Rotated Sorted Array

考点 rotated array, binary search

找最小值，如果 nums[r] < nums[mid] 说明 右侧一定被旋转过了，那么 l = mid + 1
否则 r = mid
最后返回 l


### 167. Two Sum II - Input Array Is Sorted

考点 two pointer

这道题的考点在于，对于一个 数组中寻找两数之和等于一个 target 的情况，你如何保证每一个元素只被使用了一次，
因为你使用 二分法找 num1 的 另一个 partner num2 的时候 是不应该返回 这个 num1 本身的，所以这种情况下存在binary search
 返回的还是 num1 的 index（如果 num1 + num1 = target）

所以这道题 使用 双指针从两头开始找，但是在找的过程中，可以使用 一个 二分法的思想 让 双指针走得更快一些


<<<<<<< HEAD
### 665. Non-decreasing Array

考点: sort, Array

这道题 要求我们在 只能替换一个数字的情况下，判断这个 array 是否是 non decreasing 的

```text
Example 1:

Input: nums = [4,2,3]
Output: true
Explanation: You could modify the first 4 to 1 to get a non-decreasing array.
Example 2:

Input: nums = [4,2,1]
Output: false
Explanation: You cannot get a non-decreasing array by modifying at most one element.
```

这道题的核心在于 当我们遇见第一次 nums[i-1] >= nums[i] 的时候如何使用我们的 替换权利 来将 nums[i-1] 或者是 nums[i] 替换
比如说 5 6 4 9 12 当我们遍历到 4 的时候，我们需要考虑 是将 4 变成 6 还是 将 6 变成 4，显然我们需要将 4 变成 6，否则数组将不再递增

所以我们在 判断的时候 还需要考虑 nums[i-2] 这个 数字

```text
if nums[i-2] > nums[i]          //说明 nums[i] 太小了
    nums[i] = nums[i-1]
else if nums[i-2] <= nums[i]    // 注意这里为什么是 <=,  如果 nums[i-2] 和 nums[i] 相等了，那么我们应该替换的是 nums[i-1]，因为此时 nums[i-1] 是 大于 nums[i] 的
    nums[i-1] = nums[i] 
```

还有 我们使用了 i-2 这样的 index，所以需要小心数组越界的情况

```java
class Solution {
    public boolean checkPossibility(int[] nums) {
        

        // 3 6 4 3
        int count = 0;
        for(int i = 1;i<nums.length;i++){
            if(nums[i] < nums[i-1]){
                count++;
                if(count > 1){
                    return false;
                }

                if(i<2 || nums[i-2] <= nums[i]){ // 为什么这里是 <= ?
                    nums[i-1] = nums[i];
                    
                }else{
                    // nums[i] is too small
                    nums[i] = nums[i-1];
                }
            }
        }

        return true;
    }
}
```
        
### 80. Remove Duplicates from Sorted Array II

考点: two pointers

这道题 让我们将 一个 array 当中的所有的 duplicate 都去除，但是有一个附加条件就是 每一个 duplicated1 可以最多重复出现 两次 （实际上没有让我们去除，而是让我们返回 前 k 个 array 的元素）

注意这道题，我们使用双指针，是用一个 wirte pointer 用来不停的 写入数字，用一个 read pointer 来读取数字，我们让这个 wirte pointer 的位置始终处于一个 两个 duplicate 之外的第三个位置
比如说
```text
1 1 1 2 2 2
    w r
```

我们让 write pointer 和 read pointer 交换的条件就是 当 nums[write-2] != nums[read]

```text
initial:
1 2 2 3 3 3 
    w 
    r

nums[w-2] != nums[r], swap, and w++


1 2 2 3 3 3
      w
      r
```

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=2){
            return nums.length;
        }

        int write = 2;
        for(int read = 2;read<nums.length;read++){
            if(nums[write-2] != nums[read]){
                nums[write] = nums[read];
                write++;
            }
        }

        return write;

    }
}
```

### 283. Move Zeroes

考点: Two pointers

这道题 让我们将所有的 0 移动到 数组的右侧（并且 需要 维持之前的数字的顺序），那么反过来来思考就是将所有的非 0 数字移动到左侧

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int write = 0;
        
        for(int read = 0; read<nums.length;read++){
            if(nums[read] != 0){
                int temp = nums[write];
                nums[write] = nums[read];
                nums[read] = temp;
                write++;
            }
        }

        return;
    }
}
```


### 142. Linked List Cycle II

考点: two pointer， LinkedList

链表的双指针，我们都知道可以使用 快慢针来确定 这个 linkedList 当中是否存在 cycle，但是这道题 让我们返回的是环的 入口

![img_142.png](img_142.png)

```text
Example 1:

Input: head = [3,2,0,-4], pos = 1
Output: tail connects to node index 1
Explanation: There is a cycle in the linked list, where tail connects to the second node.

Example 2:
Input: head = [1,2], pos = 0
Output: tail connects to node index 0
Explanation: There is a cycle in the linked list, where tail connects to the first node.

Example 3:
Input: head = [1], pos = -1
Output: no cycle
Explanation: There is no cycle in the linked list.
```

```text
假设两个指针相遇时，快指针走过了m步进入环的入口然后转了k1圈（每圈n步）又多p步；同理，慢指针走过了m步进入环的入口然后转了k2圈又多p步。由于两者是两倍关系，所以 m+k1*n+p = 2*(m+k2*n+p)。

化简之后得到 m = (k1-2k2)*n-p，变换一下 p+m = (k1-2k2)*n

因为慢指针目前已经比整数圈多走了p步，结合这个数学式子，这说明如果慢指针再走m步的话，又会凑成整数圈，即到了环的入口。怎么确定m呢？只要另开一个指针从head开始与慢指针一齐走，它们相遇的地方就是环的入口。
```
问题： 
为什么 快指针进入圈多走了 p 步，慢指针进入圈也多走了 p 步？ **因为快指针和慢指针最后相遇了**

为什么 使用一个新的 pointer 从 head 开始，就一定会和 慢指针在 入口相遇？

```text
为什么新指针和慢指针会在入口相遇

从上面的公式我们可以得知：

当快慢指针相遇时，慢指针已经在环内比整数圈多走了 p 步。
因此，如果慢指针再走 m 步，就会到达环的入口。
为了找到这个环的入口点，我们可以：

保持慢指针在原地不动。
从链表头（head）开始一个新的指针，让它和慢指针同时移动，每次都走一步。

由于新的指针 head 和慢指针分别离环的入口有相同的步数 m 所以它们一定会在环的入口处相遇。
```

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        boolean flag = false;
        while(fast!=null && fast.next != null){
            fast = fast.next.next;
            slow= slow.next;
            if(fast == slow){
                flag = true;
                break;
            }
        }

        if(flag == false){
            return null;
        }

        fast = head;
        while(slow!=fast){
            fast = fast.next;
            slow = slow.next;
        }

        return slow;
    }
}
```

### 713. Subarray Product Less Than K

考点: two pointer

```text
Example 1:

Input: nums = [10,5,2,6], k = 100
Output: 8
Explanation: The 8 subarrays that have product less than 100 are:
[10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
Example 2:

Input: nums = [1,2,3], k = 0
Output: 0
```

这道题 让我们寻找有多少个 subarray 满足其中所有元素的 product 小于 k，使用滑动窗口, 如果 subarray 的 product 小于 k 则 expand，如果 subarray 的 product 大于或者等于了 k 则 移动左指针
在计数的时候我们需要向 result 添加 result += i-j+1, 为什么? 因为比如 [5,2,6] 那么满足条件的就是 [5,2,6], [2,6], [6],  为什么不包括 [5,2] 以及 [2]? 因为[5,2] 会在之前一轮的 i-j+1 被统计
**这里的 i-j+1 包含的只是以新添加的最后一个元素 6 为基础 组成的 subarray**, 

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1)return 0;

        int j = 0;

        int windowProduct = 1;
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            windowProduct *= nums[i];
            while(windowProduct>=k){
                windowProduct /= nums[j];
                j++;
            }
            result += i-j+1;
        }
        return result;
    }
}
```

### 923. 3Sum With Multiplicity

考点: two pointer

```text
Example 1:

Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
Output: 20
Explanation: 
Enumerating by the values (arr[i], arr[j], arr[k]):
(1, 2, 5) occurs 8 times;
(1, 3, 4) occurs 8 times;
(2, 2, 4) occurs 2 times;
(2, 3, 3) occurs 2 times.
```

这道题依然是 3sum，不同的是现在我们的 i j k 位置上的三个元素是可以相同的, 比如说 [1,1,2] 也是一个合法的 三元组合了

那么这道题我们依然还是按照 3sum 的做法来做，不同点在于，我们需要处理 duplicated 的情况



## _区间类问题_
56, 

### 56. Merge Intervals

考点 two pointer

设置一个 low 和 high，如果新遇见的 interval 的 lower bound 小于 之前设置的 high，那么就可以merge，否则就直接加到result list 当中
**注意 在我们循环结束之后，还需要手动的 将 low 和 high 再加一遍**

### 253. Meeting Rooms II

考点: heap,

题目描述: 给定一组会议时间区间 intervals，我们需要计算至少需要多少个会议室，才能安排所有会议。

```text
Example 1:

Input: intervals = [[0,30],[5,10],[15,20]]
Output: 2
Example 2:

Input: intervals = [[7,10],[2,4]]
Output: 1
```

我们首先将 intervals 根据 start time 排序，为什么要排序 排序的目的是 为了让所有的 会议按照 start time 来遍历，我们知道下一个 会议的 start time 一定是大于当前会议的start time 的所以我们只需要比较下一个会议的 start time 是否大于当前会议的 end time，如果大于那么说明我们不需要多余的会议室，这两个 meeting 并不相交

然后使用一个 min heap，**这个 min heap 当中放的是每一个会议的 end time**，每一个 在 heap 当中的 endTime 都代表着其占用了一个 meeting room，就如刚才所说的 如果 下一个 会议不需要使用一个新的 meeting room 那么 就可以将 heap 的 peek pop 掉

#### ChatGPT:

最小堆：使用一个最小堆（min heap）来追踪当前会议室的最早结束时间。堆顶的元素即为最早结束的会议的结束时间。

如果新会议的开始时间 >= 堆顶的结束时间，这意味着可以复用这个会议室，因此弹出堆顶。
将当前会议的结束时间加入堆中，表示占用一个会议室。

```java
class Solution {
    public int minMeetingRooms(int[][] intervals) {
        // min heap
        // top of heap is the interval with smallest start time
        // if the next meeting interval[1] <= heap.peek[1], then add the new meeting
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.offer(intervals[0][1]);

        for(int i = 1;i<intervals.length;i++){
            if(intervals[i][0] >= heap.peek()){
                heap.poll();
            }
            heap.offer(intervals[i][1]);
        }

        // finally count the size of the qeuue

        return heap.size();
    }
}
```

面试官可能询问的问题

```text
1. 为什么需要按开始时间排序？
回答思路：按开始时间排序是为了确保我们能够按顺序处理会议，使得每次检查新的会议时，都能通过前一个会议的结束时间来判断是否可以复用会议室。如果不排序，就无法判断接下来的会议是否与当前会议室安排冲突。

2. 为什么选择最小堆？有其他数据结构可以替代吗？
回答思路：最小堆适合管理动态变化的结束时间。堆顶始终是最早结束的会议，因此在处理下一个会议时，可以直接判断是否可以复用这个会议室。虽然可以用数组存储所有会议的结束时间，但每次查找最小结束时间需要 O(n) 时间复杂度，而堆实现只需 O(log n)，更高效。

```

### 218. The Skyline Problem

考点: 扫描线，区间

这道题给了一个 2纬 数组，其中包含了所有建筑的 左 右 高，让我们将所有的关键点记录下来，关键点的定义是，每一个skyline 的最左边的点

这道题可以使用 扫描线来做，我们首先构建一个 map key 是 x 坐标 也就是 左 和 右点的横坐标，value 是 [height,flag] flag 的意思是用来判断这个 height 是一个building的 start 还是 end
如果 是 start flag = 1，如果是 end flag = -1，注意我们使用的应该是 tree map，因为我们到时候希望从左到右来遍历所有的 横坐标的点

接着我们需要建立一个 maxHeap，这个 maxHeap的作用是 用来维持当前 遍历到的 x 坐标上 最高的 height，因为在我们画天际线的时候如果有多个 building 重合 我们希望将最高的点画出来
然后遍历treemap，对于每一个 x 坐标，如果当前的 height flag 是 1 （也就是start of build）那么就加入到 queue 当中如果是 -1 那么就将当前的 height 从 queue 当中移除（height 一定之前被加入过）

然后此时 maxHeap 的 peek 就是我们应该加入到 result 当中的 height，但是还有一点，我们需要保证这个 x 点上的 height 和上一个 x 点的 height 是不一样的，如果和上一个 height 一样，那么说明skyline的关键点已经被画出来了， 可以一直画下去

还有一个坑就是 我们在初始化 maxHeap 的时候需要加入一个 0，作为 base line，也就是说 我们保持 maxHeap 不为空

```java
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        Map<Integer,List<int[]>> map = new TreeMap<>(); // treemap to store the: x location -> [height,start/end]

        List<List<Integer>> result = new ArrayList<>();

        for(int[] building : buildings){
            int left = building[0];
            int right = building[1];
            int height = building[2];

            map.putIfAbsent(left,new ArrayList<>());
            map.putIfAbsent(right,new ArrayList<>());

            map.get(left).add(new int[]{height,1});
            map.get(right).add(new int[]{height,-1});
        }

        Queue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a)); // maxHeap to keep track of cur x location's max height (and the heights are not end)
        int prevH = 0;
        maxHeap.offer(0);
        for(Map.Entry<Integer,List<int[]>> entry : map.entrySet()){
            int x = entry.getKey();
            List<int[]> infos = entry.getValue();
            for(int[] info : infos){
                int height = info[0];
                int flag = info[1];
                if(flag == 1){
                    maxHeap.offer(height);
                }else{
                    maxHeap.remove(height);
                }
            }

            int h = 0;
            if(!maxHeap.isEmpty()){
                h = maxHeap.peek();
                if(maxHeap.peek()!= prevH){
                    result.add(new ArrayList<>(Arrays.asList(x,h)));
                }
                prevH = h;
            }else{
                result.add(new ArrayList<>(Arrays.asList(x,h)));
            }
        }

        return result;


    }
}
```

### 128. Longest Consecutive Sequence

考点 set

注意的点，使用一个 set 检查 num-1 是否存在，如果不存在 ，说明 num 有可能是 一个连续递增 sequence 的头

注意我们在遍历的时候不要遍历数组，直接遍历 set，因为 数组当中可能存在许多 duplicate

### 31. Next Permutation

考点 array

找到下一个 比当前数组更大的 permutation
```text
find the first element start decreasing, which is 4
X X 4 7 5 3
a

then find the element just larger than 4, which is 5, 
X X 4 7 5 3
b

swap i and j
X X 5 7 4 3
a   b

then sort the part after a
```

### 75. Sort Colors

考点: array

经典的 sort color 问题

思路打开，我们可以多做几遍 loops，记住 linear time 并不是要求你 one pass，如果是 one pass 那么才是只能使用一次 for loop

### 42. Trapping Rain Water

考点 array，单调栈

解法1
使用两个数组 leftMaxHeight 以及 rightMaxHeight，leftMaxHeight 代表着从 index 0 到 index i 上出现的最高的 height，**注意这个 LeftMaxheight 或者 rightMaxHeight 是包含其自身的 height[i]的**

这是因为我们在最终计算 area 的时候使用的公式是 int area = Math.min(leftMaxHeight[i],rightMaxHeight[i]) - height[i]，也就是左右两边出现的最高的 height当中比较小的哪一个 并减去底座 也就是 height[i]

假如我们处在一个 最高的 index i 的位置上 那么这个位置上是无法收集雨水的，那么左右两边最高的就是这个 height[i]，减去这个 底座（也就是其本身）得到的 area 是 0

### 560. Subarray Sum Equals K

考点 prefixSum

这道题 让我们寻找有多少个 subarray，条件是这个 subarray 的 sum 刚好等于 k

我们可以使用 prefixSum 的思想，**并使用一个 hashmap 来记录某一个 prefixSum 出现的次数**，**每次我们计算出一个 sum，我们寻找一下 hashmap 当中是否存在 sum-k 的 prefixsum**
如果存在那么说明我们可以通过 subtract 当前 subarray 当中 sum-k的部分 来获得一个 subarray 其 sum 刚好等于 k

#### Think again:
也就是说 我们需要判断是否存在一个先前的前缀和 prefixSum[j]，满足 prefixSum[j] = prefixSum - k。如果存在，那么从 j + 1 到当前的位置 i 的子数组和即为 k。

```text
nums      [1,-1,2,3,4,5,6], k = 5
prefixSum [1,0 ,2,5,9,14,20]
```


我们发现 prefixMap 中存在 两个 prefixSum - k,
第一个是 prefixSum = 5，5-5=0，也就是说 subarray [1,-1,2,3] 是一个正确答案
第二个是 prefixSum = 14, 14-5 = 9，也就是说 subarray[1,-1,2,3,4,5] 去除 sum 等于 9 的 部分 也就是 [1,-1,2,3,4] 得到的 subarray [5] 也是一个答案

只要 map 中存在 prefixSum - k 的 prefixSum array，我们总是可以找到对应的 去除掉这部分（prefixSum-k 这部分）的 subarray 作为答案


### 739. Daily Temperatures

考点 monotonic stack

这道题，让我们寻找一个 temperature 数组当中，第 i 个 temperature 之后最近的比这个 temperature[i] 高的 temperature 的天数

我们维持一个单调递减的 monotonic stack, 每当有新的 比栈顶元素高的 temperature 出现的时候，就 pop 栈顶元素并记录天数


## Stack

### 636. Exclusive Time of Functions

考点: Stack, 边界判断

这道题 让我们模拟计算机的 function call，也就是 计算每一个 stack call 的运行时间，题目比较复杂但是题本身并不难，也就是一个 start function 一定会被一个 end function 给结束，然后让我们记录这两者之间的 时间
，难点在于，如何处理边界时间, 需要使用一个 globalTime 来记录 上一个 function call 的时间

```java
class Solution {
    public int[] exclusiveTime(int n, List<String> logs) {
        
        int[] result = new int[n];

        // [0] function id, [1] is the timestamp, pop/push based on end/start
        Deque<int[]> stack = new LinkedList<>();

        int globalTime = 0;
        for(String log : logs){
            String[] info = log.split(":");
            int id = Integer.parseInt(info[0]);
            int time = Integer.parseInt(info[2]);
            // boolean start = info[1].equals("start") ? true : false;
            if(info[1].equals("start")){
                if(!stack.isEmpty()){
                    result[stack.peek()[0]] += time - globalTime;
                }
                stack.push(new int[]{id,time});
                globalTime = time;
            }else{
                // 题目保证了 start 和 end 是相互对应的
                int[] startCall = stack.poll();
                result[startCall[0]] += time - globalTime + 1;
                globalTime = time+1;
            }
        }


        return result;
    }
}
```

### 2334. Subarray With Elements Greater Than Varying Threshold

考点: 单调栈

这道题 让我们寻找一个 subarray 其中每一个元素 都满足 大于 threadhold/k, k 是 subarray 的size

1. **当我们需要判断 thread/k < num[I] 的时候 我们可以反方向思考 thread < num[i]*k**


youtube 视频
https://www.youtube.com/watch?v=RLXKMvqNhdw


2. 维护一个 单调栈的目的是用来 返回 previous smaller 以及 next smaller，这样 我们就可以方便的将 nums[i] 衍生到 previous smaller 以及 next smaller，也就是组成一个 rectangular，这个 rectangular最高点就是 这个 nums[i]， **因为在这个 nums[i] 组成的 subarray 当中 这个 nums[i] 是最小的（因为抛去了 previous smaller 和 next smaller）**


这个 rectangular 的面积就是 nums[i] * (next smaller index - previous smaller index)


将题目转化成为 找到 一个 rectangular，让这个 rec 的面积 大于 threadhold

问题：如果我们遇见了一个 和 stack peek 相等的元素 该怎么办，是否pop 掉 stack peek？
答案是，不用pop，为什么？

```java
class Solution {
    public int validSubarraySize(int[] nums, int threshold) {
        // nums[i] > thread/k

        // nums[i] * k > thread
        

        // [1,3,7,9,6,6,1]

        // stack 4     5
        //       1
        //       0

        // pop 4

        List<Integer> list = new ArrayList<>();
        list.add(0);
        for(int num : nums){
            list.add(num);
        }
        list.add(0);

        Stack<Integer> stack = new Stack(); // stack store the index of element

        for(int i = 0;i<list.size();i++){
            // 什么时候才开始计算 面积
            // 为什么这里使用 while loop
            while(!stack.isEmpty() && list.get(i) < list.get(stack.peek())){
                // found right boundary
                int h = list.get(stack.pop());
                int width = i - stack.peek() -1;
                int area = h * width;
                if(area > threshold){
                    return width;
                }
            }
            stack.push(i);

        }

        return -1;
        
    }
}
```

问题：答案当中输出的 [3,4,3] 是在什么情况下出现的？



## String 字符串

### 49. Group Anagrams

考点: hashmap

注意在当hashmap 中没有储存过 key 的时候，我们添加这个 key 和 ArrayList 的pair，**并且要将当前的 string 加入到这个 arraylist 当中**

### 394. Decode String

考点：String Stack

这道题给了一个数字 以及方括号 以及 字母的组合，让我们将这些 string 还原成其原来的 string

```text
Example 1:

Input: s = "3[a]2[bc]"
Output: "aaabcbc"
Example 2:

Input: s = "3[a2[c]]"
Output: "accaccacc"
Example 3:

Input: s = "2[abc]3[cd]ef"
Output: "abcabccdcdcdef"
```

实现步骤
遍历字符串：对字符串中的每个字符执行相应操作。


1. 遇到数字：使用 while 循环提取完整数字并计算倍数，将倍数存入 numStack。
2. 遇到 [：将当前倍数 num 和当前构建的 cur（部分字符串）分别推入 numStack 和 strStack。然后重置 num 和 cur，为处理新括号内容做好准备。
3. 遇到 ]：从 numStack 弹出倍数 times，从 strStack 弹出外层字符串 content。将 cur 重复 times 次并添加到 content，再将合成的字符串赋值给 cur。
4. 遇到字母：直接添加到当前字符串 cur 中。

注意细节：在提取数字时，while 循环将 i 移动到非数字位置。为了避免跳过字符，需要在 while 循环后执行 i--，确保 for 循环能正确处理接下来的字符。

总结
strStack 用于存储每层括号外部的字符串，cur 用于当前括号内容的构建。通过这种方式，每当遇到 ] 时，可以将 cur 与上层字符串合并，从而实现递归解码。

```java
class Solution {
    public String decodeString(String s) {
        
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>(); // 我们应该将整个 cur String push 到 stack 当中

        int num = 0;
        StringBuilder cur = new StringBuilder();
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isDigit(c)){
                while(i < s.length() && Character.isDigit(s.charAt(i))){
                    num = num*10 + s.charAt(i)-'0';
                    i++;
                }
                i--;
            }else if(c == '['){
                numStack.push(num);
                strStack.push(cur);
                num = 0;
                cur = new StringBuilder(); // 这一步会影响已经存到 stack 当中的结果么，这一步开启了一个 新的 reference
            }else if(c == ']'){
                int times = numStack.pop();
                StringBuilder content = strStack.pop();
                for(int j = 0;j<times;j++){
                    content.append(cur);
                }
                cur = content;
            }
            else{
                cur.append(c);
            }
        }

        return cur.toString();
    }
}
```

### 5. Longest Palindromic Substring

考点: two pointer

主要的核心在于 处理回文长度的起点 是奇数还是偶数的情况 

aba 是 从一个 character 'b' 开始向两边衍生的，

abba 是从两个 character 'bb' 开始向两遍衍生的，
这些细节是需要通过 观察以及经验 总结出来的

### 680. Valid Palindrome II

考点: string

这道题要求我们在最多只替换一个 character 的前提下是否能将给定的 string 变成一个回文，那么我们可以使用双指针，左指针从 0 开始，右指针从 数组末尾开始，一旦遇见两个指针的 character 不想等，那么我们就开始比对 i,j-1 以及 i+1,j 这两个区间内的 回文是否 valid
也就是说 只要出现了一次两个指针不相等，那么我们就开始判断 去除左指针或者右指针的 部分是否 是 valid 回文




### 3. Longest Substring Without Repeating Characters

考点: hashmap, sliding window

这道题，需要注意我们维持一个 sliding window，每一次我们遍历到一个新的 character，检查一下这个 char 是否出现在 hashmap 当中

如果出现了，那么我们需要判断这个repeat char 是出现在 当前维持的 sliding window (left -> right) 之间还是在 left 之前

如果 这个 repeat char 出现在 left 之前那么 当前的 sliding window 就是 valid，如果 出现在 当前的 left 之后，那么我们需要更新当前的 left pointer 然后计算 当前 sliding window
 的 length

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character,Integer> map = new HashMap<>();
        int i = 0;
        int result = 0;
        for(int j = 0;j<s.length();j++){
            char c = s.charAt(j);
            if(map.containsKey(c)){
                // found duplicated, update window with duplicated
                i = Math.max(i,map.get(c)+1);
                map.put(c,j);
            }else{
                map.put(c,j);
            }

            result = Math.max(result,j-i+1);
        }
        return result;
    }
}
```

### 424. Longest Repeating Character Replacement

考点: sliding window

这道题，让我们通过 最多k 次转换来找到一个 最长的 repeating subarray，和上面的 leetcode 3 有点相似

```text
Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
There may exists other ways to achieve this answer too.
```

这道题 比较自然的会想到使用 sliding window比 需要想的点在于在每一个 sliding window 当中让哪些 character 使用 这个 replacement的机会？ 我们会希望保留那些freq 最多的 character
那么当我们使用 双指针的 sliding window来 寻找合适的 subarray 的时候，我们就需要维持一个 window 之内所有的 character 的 freq，我们可以使用 一个 int[26] 来计数每个字母出现的次数
在每一次遍历到新的 字母的时候我们将这个字母的 freq 加一，然后计算一下当前 window 当中最大的 freq 是多少，**然后计算需要替换的 字母的数量就是 right-left+1 - findMax(windowFreq)**
如果需要替换的 字母数量大于 k，则需要移动左指针，来缩减我们的 window 直到满足需要替换的字母数量小于 k 为止

```java
class Solution {
    public int characterReplacement(String s, int k) {
        
        int[] charFreq = new int[26];
        int left = 0;
        int right = 0;
        int n = s.length();
        int result = 0;
        while(right<n){
            char c = s.charAt(right);
            charFreq[c-'A']++;
            while(left < right && (right - left + 1) - findMax(charFreq) > k){
                // we need more replacement than k to make all char same
                // need to move the left pointer to shrink the window
                charFreq[s.charAt(left)-'A']--;
                left++;
            }
            result = Math.max(right - left+1,result);
            right++;
        }

        return result;
    }

    public int findMax(int[] array){
        int result = 0;
        for(int num : array){
            if(num>result){
                result = num;
            }
        }
        return result;
    }
}
```

Time O(n), findMax method is O(26), right pointer traverse O(n), left pointer shrinking O(n)
Space O(26)


### 20. Valid Parentheses

考点: 括号类字符, Stack

注意当栈为空的时候以及 当前遍历的 char 是 左括号时 stack.push


### 76. Minimum Window Substring

考点: sliding window

这道题最主要的是使用两个 hashmap 来 记录 两个 string 的 frequency

一个 是 target word 的 character frequency, freqT
另一个是用来记录 sliding window 当中的 character frequency

我们需要使用一个 formed 数字用来记录当前 window 当中有多少 character 满足了 target word frequency，如果 formed = freqT.size() 那么说明当前 windwo 满足了包括所有 target word 当中的 character 以及 frequency 的条件

此时我们就开始 shrinking 我们的 window
，因为题目要求我们找到最小的 window，在shrink 的同时也需要对window 的 frequency map 进行修改


### 1249. Minimum Remove to Make Valid Parentheses

考点: 括号类 

使用 stack 的时候 需要表达清楚 stack 当中储存的是什么，这档题当中 stack 储存 左括号的的 index


### 165. Compare Version Numbers

考点: 正则表达，从 String 当中提取数字

这道题让我们对两个 string version 进行比较，version 的内容只包含 数字 和 "."

```text
Example 1:

Input: version1 = "1.2", version2 = "1.10"

Output: -1

Explanation:

version1's second revision is "2" and version2's second revision is "10": 2 < 10, so version1 < version2.

Example 2:

Input: version1 = "1.01", version2 = "1.001"

Output: 0

Explanation:

Ignoring leading zeroes, both "01" and "001" represent the same integer "1".

Example 3:

Input: version1 = "1.0", version2 = "1.0.0.0"

Output: 0

Explanation:

version1 has less revisions, which means every missing revision are treated as "0".
```

这道题 最关键的 想法是需要对 version string 进行 dot 也就是"." 进行 split，并且在 Java 当中 **String.split() 这个 function 输入的是一个正则表达式，而 "." 是正则表达的一个特殊符号，所以我们必须将其写成 "\\." 也就是使用两个 back slash，这是这个题目的一个坑**

在我们 split 了 string 之后，那么就按照split之后的 String 数组进行遍历就可以了，在每一个 index 位置上对 String 进行 parse int，然后进行比较

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");

        // System.out.println(Arrays.toString(s1));

        int maxLength = Math.max(s1.length,s2.length);

        for(int i = 0;i<maxLength;i++){
            int v1 = i<s1.length ? Integer.parseInt(s1[i]) : 0;
            int v2 = i<s2.length ? Integer.parseInt(s2[i]) : 0;
            if(v1 > v2){
                return 1;
            }else if (v1<v2){
                return -1;
            }

        }

        return 0;

    }
}
```

### 6. Zigzag Conversion

考点: String Math

这道题给了一个 字符串让我们以 zigzag 的方式来遍历这个 字符串 然后按照每一行来输出 结果

```text
Example 1:

Input: s = "PAYPALISHIRING", numRows = 3
Output: "PAHNAPLSIIGYIR"
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
Example 3:

Input: s = "A", numRows = 1
Output: "A"
```

比较简单的想法是使用 hashmap, numRow -> StringBuilder, 然后按照 zigzag 的顺序向每一行添加 character，最后将所有行合并
```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        
        // hashmap , numRow -> StringBuilder
        Map<Integer,StringBuilder> map = new HashMap<>();
        for(int i = 1;i<=numRows;i++){
            map.put(i,new StringBuilder());
        }

        int index = 0;
        int curRow = 1;
        boolean down = true;
        while(index < s.length()){
            char c = s.charAt(index);
            map.get(curRow).append(c);
            if(down == true && curRow==numRows){
                curRow--;
                down = false;
            }else if(down == false && curRow == 1){
                curRow++;
                down = true;
            }else if(down == true && curRow < numRows){
                curRow++;
            }else if(down == false && curRow > 1){
                curRow--;
            }
            index++;
        }

        StringBuilder result = new StringBuilder();
        for(int i = 1; i<=numRows;i++){
            result.append(map.get(i));
        }

        return result.toString();
    }
}
```

但是这道题 最优解是通过 数学的 方式计算出来 第一行的 zigzag 字母有哪些，第二行到倒数第二行有哪些，最后一行有哪些，观察发现 zigzag 的一个周期 是下面这样的

```text
P     ...
A   L 
Y A   
P    
```
也就是说 下一个第一行的 元素的 index 将会是 curIndex + numRows + (numRows - 2), 解释一下是因为numRows 从上往下，numRows - 2 是 中间的肚子的数量，从地下往上遍历的 元素的数量
那么也就是说 一个周期的 元素的个数是 M =  numRows + (numRows - 2), 那么会有多少个这样的周期？ 一共会有 s.length()/M, 但是注意 如果 s 的 length 恰好是 M 的倍数的时候，我们会得到正确结果，但是 如果 s length 不是 M 的倍数的时候，最后一个周期将会被 skip 掉，**因为计算机计算int 的时候默认是向下取整的**
但是在这道题当中我们不能向下取整，因为即使最后一个周期的数量不完整，我们也需要将其计数下来，就比如说 example 当中 s length = 14, M = 6, 14/6 = 2 但是实际上 这个 zigzag 是经历了三个周期的

```text
P     I    N
A   L S  I G
Y A   H R
P     I

第一个周期 PAYPAL
第二个周期 ISHIRI
第三个周期 NG
```

**所以我们应该将 a/b 进行向上取整的运算，既 (a-1)/b + 1** 所以我们计算的 N = (s.length()-1)/M + 1, 计算出了 M 和 N 之后我们需要对每一行按照周期将 character 加入到 result 当中

第一行 index += M
第二行(中间肚子有两个元素), 第二行是第１和M-1个元素，第三行是第２和Ｍ-2个元素也就是 M*i + curRow, M * i + M - curRow，注意这里的 i 是第几个周期 i = 0; i<N ;i ++
最后一行 M*i + curRow-1

```java
class Solution {
    public String convert(String s, int numRows) {
        // 如果只有一行，直接返回原字符串
        if (numRows == 1) return s;

        int len = s.length();
        int M = numRows * 2 - 2; // 一个周期的元素数目
        int N = (len - 1) / M + 1; // 计算周期数

        StringBuilder result = new StringBuilder();

        // 处理第一行
        for (int i = 0; i < N; i++) {
            int index = M * i;
            if (index < len) {
                result.append(s.charAt(index));
            }
        }

        // 处理中间行（第1行到第numRows-2行）
        for (int row = 1; row < numRows - 1; row++) {
            for (int i = 0; i < N; i++) {
                int index1 = M * i + row;
                int index2 = M * i + M - row;

                if (index1 < len) {
                    result.append(s.charAt(index1));
                }
                if (index2 < len) {
                    result.append(s.charAt(index2));
                }
            }
        }

        // 处理最后一行
        for (int i = 0; i < N; i++) {
            int index = M * i + numRows - 1;
            if (index < len) {
                result.append(s.charAt(index));
            }
        }

        return result.toString();
    }
}

```

## Binary Search

### 528. Random Pick with Weight

考点: Binary Search

使用 prefixSum，生成一个 prefixSum 数值范围内的 random number，然后使用 binary search
 在 prefixSum 当中寻找插入点

### 33. Search in Rotated Sorted Array

考点: Binary Search

这道题 告诉我们给定的 input array 是已经被sorted 并且是更具一个 点被 rotated 了，让我们在这个 array 当中寻找 target，

```text
Example 1:

Input: nums = [4,5,6,7,0,1,2], target = 0
Output: 4
Example 2:

Input: nums = [4,5,6,7,0,1,2], target = 3
Output: -1
Example 3:

Input: nums = [1], target = 0
Output: -1
```

我们的做法是 首先找到 这个 pivot，通过的方式是，寻找最小的数字的 index，当我们找到这个 minIndex 之后再根据 target 和 nums[minIndex] 之间的 大小关系进行 regular binary search
注意在寻找 最小 数字的 index 时候 的边界条件的 判断 nums[mid] > nums[right] 这说明了 从 mid 到 右侧一定存在 被 rotated 的部分，因为 正常来说 right 肯定是要大于 mid的，而且 mid 肯定不是 minIndex，因为 mid > right怎么说也可能是 right

```java
class Solution {
    public int search(int[] nums, int target) {
        
        // find the pivot
        int left = 0;
        int right = nums.length-1;

        // find the min value index
        while(left<right){
            int mid = (right-left)/2 + left;
            if(nums[mid] > nums[right]){
                // min is on right
                left = mid + 1;
            }else{
                right = mid;
            }
        }

        int minIndex = left;
        left = 0;
        right = nums.length-1;

        // based on the which side of the target reside of the pivot, use the regular binary search
        if(target >= nums[minIndex] && target <= nums[right]){ // 注意这里必须要保证两个条件都满足，因为如果只有一个条件 target >= nums[minIndex] 满足，还不能说明 target 就一定在 右侧
            return binarySearch(nums,minIndex, right,target);
        }else{
            return binarySearch(nums,left,minIndex-1,target);
        }

    }

    public int binarySearch(int[] nums, int left, int right, int target){
        while(left<right){
            int mid = left + (right-left)/2;
            if (nums[mid] == target){
                return mid;
            }else if(nums[mid] > target){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        return nums[left] == target ? left : -1;
    }
}
```
### 162. Find Peak Element

考点: Binary Search

对比 nums[mid] 和 nums[mid+1] 的值，**如果 mid 大于 mid+1 的值 说明 peak 一定存在 mid 之前（有可能就是 mid），如果 mid小于 mid + 1，那么说明 peak 一定在 mid 之后**

```text
            *
          @
            *
#                     #
```
假设 @ 是我们的 mid，

那么对比一下 mid + 1，如果 mid + 1 大于 mid，（上边的星）那么 peak一定在 mid 之后，移动左指针 left = mid + 1

如果 mid + 1 小于 mid（下边的星）那么peak 一定在 mid 之前，有可能就是 mid, 所以我们应该移动右指针 right = mid，（**不是 mid - 1，因为 mid 也有可能就是答案**）

### 300. Longest Increasing Subsequence

考点: Binary Search

这道题 让我们在一个array 当中找出最长的递增 sequence，注意 sequence 的意思是所有的 元素并不一定是 连续的，但是只要他们是递增的就可以，也就是 LIS（longest increasing subsequence）

```text
Example 1:

Input: nums = [10,9,2,5,3,7,101,18]
Output: 4
Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
Example 2:

Input: nums = [0,1,0,3,2,3]
Output: 4
Example 3:

Input: nums = [7,7,7,7,7,7,7]
Output: 1
```

解决方案是使用一个 和nums 相同长度的 array，我们不断更新这个 array让其 size 是 LIS 的 size，每一次我们遇见新的元素 比如 num，我们都在这个 array 当中寻找最小的大于这个 num 的元素，并且将其更新为 num
比如说
```text
我们维持了一个 array 1 2 9 10 是一个 increase subsequence, 当我们遇见 4 的时候，会将这个 array 更新成 1 2 4 10，此时 LIS 的 size 依然没有变，还是 4，但是我们优化了这个 array，

array 1 2 4 10 下一次我们遇见了 5，此时我们又将 这个 array 更新成了 1 2 4 5， size 依然没有变花，但是 array 更加优化了，

1 2 4 5 是一个更优解，因为 如果下次 有一个 6 元素出现的 时候，这个 subsequence 就可以将这个 6 insert 进去
```

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        // [2,3,7,101,109] 109

        int[] arr = new int[nums.length];
        int size = 0;

        for(int i = 0;i<nums.length;i++){
            int index = binarySearch(arr,0,size,nums[i]);

            if(index >= size){
                arr[size] = nums[i];
                size++;
            }

            arr[index] = nums[i]; //这一步的作用是什么，如果 我们的 arr 是 [2,3,7,15] 此时遇见了一个数字 4, 那么我们 会将 array 更成 [2,4,7,15] 这么做的好处是什么？
        }

        return size;

        
    }

    public int binarySearch(int[] arr, int left, int right, int target){
        while(left<= right){
            int mid = left + (right-left)/2;
            if(arr[mid] == target){
                return mid;
            }else if(arr[mid] > target){
                right = mid -1;
            }else{
                left = mid + 1;
            }
        }

        return left;
    }
}
```

注意我们在写 这个 binary search 的时候使用的条件是 left <= right,使用 left <= right 作为循环条件的原因是为了确保当 left == right 时依然会进行最后一次判断。如果 nums[left] 依然不是目标 target，则 left 最终会指向数组中第一个大于 target 的位置，从而满足查找最小的大于 target 的元素的需求。
这种方式适用于我们知道 target 可能不存在于 array 当中的情况，**此时 输出的 left 就是 离 target 最近并且 大于 target 的元素的 index**


### 1004. Max Consecutive Ones III

考点: two pointer, sliding window

给了一个数组和一个 k ，让我们寻找数组中最长的连续的 1（数组只包含 0 或者 1），并且规定我们可以最多 flip k 个 0 成为 1

那么就思考 sliding window
，一旦 right pointer 为 0，并且 flipped 的数量超过或等于了 k，也就是说 对于此时这个 nums[right] = 0 我们不能再使用 flip 将其变成 1了，

那么只能通过 移动左指针来找到一个 0，并且将左指针跨过这个 0，这样才可以找补回来当前 right pointer 遇见的 0，

**下面的代码是我自己写的，比较好理解**

```java
class Solution {
    public int longestOnes(int[] nums, int k) {
        

        // 1,1,1,0,0,0,1,1,1,1,0
                //    l
                //      r
        // sliding window, maintain a number called fliped
        
        int left = 0;
        int right = 0;
        int result = 0;
        int fliped = 0;

        while(right<nums.length){
            if(nums[right] == 1){
                result = Math.max(right-left+1,result);
                // right++;
            }else{
                if(fliped>=k){
                    // move the left pointer
                    while(left<right && nums[left]==1){
                        left++;
                    }
                    // fliped--;
                    left++;
                    // right++;
                }else{
                    // still can flip
                    result = Math.max(right-left+1,result);
                    fliped++;
                    // right++;
                }
            }
            right++;
        }

        return result;
    }
}
```


### 670. Maximum Swap

考点: array

```text
Example 1:

Input: num = 2736
Output: 7236
Explanation: Swap the number 2 and the number 7.
Example 2:

Input: num = 9973
Output: 9973
Explanation: No swap.
```

这道题 让我们对一个数字进行一次 swap，使得这个 数字的数值最大，正常的想法就是找到哪一个位置我们需要进行 swap，然后从右往左进行遍历找到一个最大的数字，然后和这个数字进行 swap

如果有多个最大数字 9，那么我们交换的时候显然是希望将 越往数组后边的 数字越好，因为这样交换出来的数字就越大











## LinkedList

### 23. Merge k Sorted Lists

考点: Linkedlist heap

这道题让我们对一个 listNode array 进行合并，并且告诉我们每一个 linkedlist 都是 ASC sorted

我们可以使用 一个 heap 用来储存 每一个 linkedlist 的 node, 然后每一次将 heap 当中的最小的 node 提出来 construct 新的 node
，我们需要使用 Pair， heap 当中储存的 是 Pair<Integer,ListNode>，也就是 lists 的 index 以及 ListNode

在我们构建 priorityQueue 的时候 需要注意使用正确的 lambda function

```text
// 1 4 5
// 1 3 4
// 2 6
// heap, put the index and listNode value
```

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if(lists.length==0){return null;}
        
        // 1 4 5
        // 1 3 4
        // 2 6
        // heap, put the index and listNode value

        PriorityQueue<Pair<Integer,ListNode>> heap = new PriorityQueue<>((Pair<Integer,ListNode> a, Pair<Integer,ListNode> b) -> Integer.compare(a.getValue().val,b.getValue().val));


        // put all the listnode head value into heap
        for(int i = 0;i<lists.length;i++){
            // 需要检查 每一个 lists 的头节点是否为空
            if(lists[i]!=null){
                heap.add(new Pair<>(i,lists[i]));
            }
        }

        // while heap is not empty, keep rotate the lisinode, untill it reach null
        ListNode dummy = new ListNode(-1);
        ListNode pointer = dummy;

        while(!heap.isEmpty()){
            Pair<Integer,ListNode> pair = heap.poll();
            int index = pair.getKey();
            ListNode node = pair.getValue();

            pointer.next = node;
            pointer = pointer.next;
            
            if(node.next != null){
                heap.offer(new Pair<>(index,node.next));
            } 
        }

        return dummy.next;

    }
}
```


### 24. Swap Nodes in Pairs

考点: LinkedList

这道题 让我们将一个 linkedlist 的每相邻的两个 node 调换位置，

```text
Example 1:

Input: head = [1,2,3,4]

Output: [2,1,4,3]

Example 2:

Input: head = []

Output: []

Example 3:

Input: head = [1]

Output: [1]

Example 4:

Input: head = [1,2,3]

Output: [2,1,3]
```

就平常的做法，使用 三个 pointer, prev -> cur -> nex

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode pre = dummy;
        while(head!=null && head.next!=null){
            // changing:  pre -> cur -> nex -> ...
            // to become: pre -> nex -> cur -> ...
            ListNode cur = head;
            ListNode nex = head.next;
            pre.next = nex;

            
            cur.next = nex.next;
            nex.next = cur;
            pre = cur;
            head = cur.next;
        }
        return dummy.next;
    }
}
```

注意这里的 trick，我们移动的是 head，并且在 while loop 当中每次新建根据 head 来新建 cur 和 nex




## Tree

### 314. Binary Tree Vertical Order Traversal

考点: BFS 

这道题让我们将处在同一个水平线上的 treenode value 统一的收集起来，注意在返回的时候，我们需要按照从左到右的顺序返回这些在一个水平线上的 values

使用一个 treeMap（treemap 用来对 水平线坐标排序） 以及queue，queue中储存的 是 TreeNode 和 location 的 pair

![img.png](img.png)


### 236. Lowest Common Ancestor of a Binary Tree

考点: DFS 


### 199. Binary Tree Right Side View

考点: DFS BFS

这道题返回 一个 binary tree 的 右边的 视角，并且从上到下的返回 node value

BFS 很直接，在每一层的最后一个 位置添加 node value 到 result list 当中即可

DFS 比较麻烦，我第一次做的时候，觉得只要让 node 一直往右子树走就可以，但是这种想法是错的，因为如果在某一层上 node 的右子树为 null，那么此时 right side view 看到的其实是 左子树的 node
所以我们对于每一个 node 还是得 左右子树都遍历，但是在添加的时候使用一些技巧

```text
         1
      2     3
   4    5 6    7
   
```
对于上面这个 树，right side view 是 1 3 7，我们使用 DFS 的时候 可以使用一个 depth，我们可以发现 树的深度就是最终 result list 的 size

所以我们可以对比一下 如果 depth = 0 的时候 result 也等于 0，那么 此时我们就将 当前的 node value 加入到 result list 当中，

为什么？因为我们是从上到下的 （这种方法是 top down approach）所以当前遇见的 node 一定是这个 depth 第一次遇见的 node，如果之前已经有 node 被添加在了 result list 当中
那么 result list 的 size 一定会 大于 depth

```java
class Solution {
  public List<Integer> result;
  public List<Integer> rightSideView(TreeNode root) {
   result = new ArrayList<>();
   if(root == null){return result;}
 
   dfs(root,0);
 
   return result;
  }
 
  public void dfs(TreeNode node, int depth){
   if(node == null){return;}
 
   if(depth==result.size()){
    result.add(node.val);
   }
 
   dfs(node.right,depth+1);
   dfs(node.left,depth+1);
 
   return;
  }
}
```

### 1644. Lowest Common Ancestor of a Binary Tree II

考点: DFS



### 694. Number of Distinct Islands

考点: DFS BFS

这道题 让我们count 有多少种不同的 岛屿布局

```text
Input: grid = 
[[1,1,0,0,0],
 [1,1,0,0,0],
 [0,0,0,1,1],
 [0,0,0,1,1]]
 
Output: 1
```
我们使用 dfs 来遍历每一个 点，并且记录一个 遍历的 路径操作，这个遍历的路径就保证了这个 岛屿的布局的唯一性

```java
class Solution {
    private HashSet<String> set;
    public int numDistinctIslands(int[][] grid) {
        // 使用路径来作为 岛屿的 key
        // 每一个岛屿我们都是从左上角的元素开始遍历的
        int m = grid.length;
        int n = grid[0].length;
        set = new HashSet();
        for(int i = 0;i<m;i++){
            for(int j = 0;j<n;j++){
                if(grid[i][j] == 1){
                    StringBuilder sb = new StringBuilder();
                    dfs(grid,i,j,"o",sb);
                    set.add(new String(sb));
                }
            }
        }

        // System.out.println(set);
        return set.size();
    }

    public void dfs(int[][] grid, int i, int j,String opt, StringBuilder sb){
        if(i<0 || j<0 || i>=grid.length || j>=grid[0].length || grid[i][j] == 0){
            return;
        }
        
        grid[i][j] = 0;
        sb.append(opt);

        dfs(grid,i+1,j,"d",sb);
        dfs(grid,i-1,j,"u",sb);
        dfs(grid,i,j+1,"r",sb);
        dfs(grid,i,j-1,"l",sb);
        sb.append("b"); // back

        return;
    }
}
```

注意我们为什么要添加  “ sb.append("b");” 比如下面的例子

```text
1 1 0
0 1 1
0 0 0
1 1 1
0 1 0
```

如果不添加一个 b 那么两个 岛屿的 路径操作都会是 r->d->r, 这是因为下面的 岛屿在 r->d 之后就会超出了边界，此时就会回到上一个点开始其他方向上的 dfs，所以在这种情况下我们还需要添加一个 b，来作为记录当前点已经无路可走，需要返回上一个点的情况


### 426. Convert Binary Search Tree to Sorted Doubly Linked List

考点: DFS, path

在做 tree 一类的 问题的时候，**我们可以使用一个 全局变量 叫做 lastNode 来记录 当前 node 的 parent node 是什么**，这是一个非常重要的 技巧

二刷:
我们需要两个 全局变量 firstNode 和 lastNode，因为我们最终需要的是一个环
中序遍历的时候，如果我们遇见 lastNode 是 null 那么说明这个node 是我们的 firstNode，否则我们就将 当前 node.left 指向 lastNode，把 lastNode 的 right 指向当前 node

### 129. Sum Root to Leaf Numbers

考点: DFS BFS

这道题让我们计算从 root 到 leaf 所组成的数字的和，也就是 一条树枝上 1->2->3->4 所组成的数字 就是 1234，我们需要将所有这样的数字加到 result 当中

我们可以使用 DFS，使用的 是 top down approach，dfs method 使用一个 carry 来记录来自上一层的 carry，每当进入 node，首先计算 carry, 然后非常重要的是 我**们需要在 node.left == null && node.right==null 的时候 才将 carry 加入到 result 当中**
否则，我们得到的结果会是 2倍的 actual result，这是因为如果我们在 node==null 的时候 将 carry 加入到 result 那么 这个 node == null 可以来自左子树也可以来自右子树，所以我们会将结果计算两遍

也可以使用 BFS，使用 BFS 的时候 我们需要注意使用 Pair，来记录 Treenode 以及 来自上一层的 carry


### 124. Binary Tree Maximum Path Sum

这道题让我们计算tree 当中 最大 pathsum 的 path

```java
class Solution {
    public int result;
    public int maxPathSum(TreeNode root) {
        result = Integer.MIN_VALUE;
        dfs(root);
        return result;
    }
    public int dfs(TreeNode node){
        if(node == null )return 0;

        int leftMax = dfs(node.left);
        int rightMax = dfs(node.right);

        // leftMax+rightMax+node.val, leftMax + node.val, rightMax + node.val
        int pathSum = node.val;
        if(leftMax > 0){
            pathSum += leftMax;
        }
        if(rightMax > 0){
            pathSum += rightMax;
        }

        result = Math.max(pathSum,result);

        // 注意在 return 的时候 我们需要 return node.val + LeftMax 或者 node.val + rightMax，因为我们需要返回的是 左枝或者右枝的其中之一
        // 我们不能直接返回 pathSum，这会造成我们返回的是包含左右两支的子树
     
        if(leftMax < 0 && rightMax < 0){
            return node.val;
        }else{
            return node.val + Math.max(leftMax,rightMax);
        }

    }
}
```


## Graph

### 133. Clone Graph

考点: DFS BFS
这道题给了一个 graph node （undirected），通过 Adjacent list 的方式储存，让我们实现 deep copy，

这道题的核心就在于 使用一个 hashmap ，key 作为 original node，value 作为 copy node，

BFS 的做法，我们需要提前在 hashmap 当中构建 node -> copy node 的映射，然后在每一层遍历的时候首先检查这个 neighbor 是否存在 hashmap 当中，如果不存在，则添加, 

**我们需要在什么情况下再将 node offer到 queue 当中去？**
我们相当于是把这个 map 当坐了一个 visited

```java
/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
    public Node cloneGraph(Node node) {
        if(node == null)return node;
        Map<Node,Node> map = new HashMap<>();
        map.put(node,new Node(node.val));
        Queue<Node> queue = new LinkedList<>();
        queue.offer(node);
        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            for(Node neighbor : curNode.neighbors){
                if(!map.containsKey(neighbor)){
                    map.put(neighbor,new Node(neighbor.val));
                    queue.offer(neighbor); // 思考一下为什么当 neighbor 不再 map当中的时候 就加入到 queue
                }
                map.get(curNode).neighbors.add(map.get(neighbor));
            }
        }

        return map.get(node);

    }
}
```

DFS 的做法，需要注意的是我们需要使用 hashmap 来防止重复的 访问 某一个 node，也就是说 DFS 的停止条件是 

```text
if(map.contains(node)){return;}

```

### 210. Course Schedule II

考点 DFS

```java
class Solution {

    public List<Integer> result;
    public Map<Integer,List<Integer>> adj;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // build the adj map, [a,b] b->a
        adj = new HashMap<>();
        for(int i = 0;i<numCourses;i++){
            adj.put(i,new ArrayList<>());
        }

        for(int[] pre : prerequisites){
            adj.get(pre[1]).add(pre[0]);
        }

        // dfs, start from each course, dfs should return a boolean indicates if there is a cycle
        // if circle detceted, return empty int[]
        int[] visited = new int[numCourses]; // 0 is not visited, 1 is visiting, -1 departured
        result = new ArrayList<>();

        for(int i =0;i<numCourses;i++){
            if(visited[i]==0){
                if(dfs(i, visited)){
                    return new int[0];
                }
            }
        }

        // list of course, reverse the list result
        Collections.reverse(result);

        return result.stream().mapToInt(i->i).toArray(); // 注意这里是 toArray 并不是 asArray


    }

    public boolean dfs(int start, int[] visited){
        
        if(visited[start] == 1){
            return true;
        }
        if(visited[start] == -1){
            return false; // 这里为什么要 return false, 因为这个 点可能是多个点的 target，其中一个 点先遍历到了这个点 并将这个 点标记成了 -1，之后其他的点又遍历到了这里，此时我们直接返回 false 防止重复
        }

        visited[start] = 1;
        for(int neighbor : adj.get(start)){
            if(dfs(neighbor,visited)){
                return true;
            }
        }
        visited[start] = -1;
        result.add(start);

        return false;
    }
}
```

### 787. Cheapest Flights Within K Stops

考点: Dijkstra's algorithm， 带有权重的 edge 问题, graph

![img_1.png](img_1.png)
```text
Example 1:

Input: n = 4, flights = [[0,1,100],[1,2,100],[2,0,100],[1,3,600],[2,3,200]], src = 0, dst = 3, k = 1
Output: 700
Explanation:
The graph is shown above.
The optimal path with at most 1 stop from city 0 to 3 is marked in red and has cost 100 + 600 = 700.
Note that the path through cities [0,1,2,3] is cheaper but is invalid because it uses 2 stops.
```

这道题 让我们寻找从 一个城市 src 坐飞机到另一个 城市 dst 的 最小的 cost，flights 数组代表的是 [start,end,price]，并且 还要满足一个条件就是，中转的次数 不能超过 k

通常这类带有权重的 edge 的 graph 问题并且要求我们求最小值的情况下，都是需要通过 Dijkstra 来实现

Dijkstra 的算法用来求解单源最短路径问题，一个点到另一个点。该算法利用优先队列（通常是最小堆）来确保每一步选择的路径是当前代价最小的（贪心思想），从而逐步扩展最短路径，最终找到从起点到所有其他节点的最短路径。

```java
import java.util.*;

class Solution {
 public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
  // 构建带权重的邻接表
  Map<Integer, List<int[]>> adj = new HashMap<>();
  for (int i = 0; i < n; i++) {
   adj.put(i, new ArrayList<>());
  }

  for (int[] flight : flights) {
   int from = flight[0];
   int to = flight[1];
   int price = flight[2];
   adj.get(from).add(new int[]{to, price});
  }

  // 优先队列：按累积费用排序
  Queue<int[]> queue = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
  queue.offer(new int[]{src, 0, 0});  // 起点节点，累积费用，已使用步数

  // 记录到达每个节点在每个步数下的最低费用
  int[][] minPrice = new int[n][k + 2];
  for (int[] row : minPrice) {
   Arrays.fill(row, Integer.MAX_VALUE);
  }
  minPrice[src][0] = 0; // 从起点开始，中转 0 次的 cost，就是其本身，因为就没有出城市

  while (!queue.isEmpty()) {
   int[] cur = queue.poll();
   int node = cur[0];
   int cost = cur[1];
   int stops = cur[2];

   // 如果到达目的地
   if (node == dst) {
    return cost;
   }

   // 如果步数超出限制，则不再探索
   if (stops > k) {
    continue;
   }

   // 遍历邻接节点
   for (int[] next : adj.get(node)) {
    int nextNode = next[0];
    int nextPrice = next[1];
    int newCost = cost + nextPrice;

    // 若在当前步数的限制下发现更低费用，更新并加入队列
    if (newCost < minPrice[nextNode][stops + 1]) {
     minPrice[nextNode][stops + 1] = newCost;
     queue.offer(new int[]{nextNode, newCost, stops + 1});
    }
   }
  }

  // 返回最小费用或 -1（表示无解）
  return -1;
 }
}


```

这个 minPrice 的作用是什么?
minPrice[dst][k] 表示从起点 src 到节点 dst 在 k 次中转内的最低费用。


## Dynamic Programing

### 139. Word Break

考点: dp, two pointer

```text
Example 1:

Input: s = "leetcode", wordDict = ["leet","code"]
Output: true
Explanation: Return true because "leetcode" can be segmented as "leet code".
Example 2:

Input: s = "applepenapple", wordDict = ["apple","pen"]
Output: true
Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: false
```

给了我们一个 String s 和一个 List of String wordDict, 让我们判断一下这个 String 是否可以通过 wordDict 当中的 word 来组成，让我们返回 一个 boolean 值

我们使用动态规划来做 dp[i] 的意思是 从 index 0 到 index i-1 的位置上 是否可以使用 wordDict 当中的 word 来拼接成这个 String s，也就是说 在 i 之前（不包括 i）的 substring 是可以通过 wordDict 当中的 word 来拼接而成的

**问题一: 为什么 需要取从 index 0 到 index i-1？**

因为这样写我们的 动态转移方程比较好写

```text
dp[i] = dp[j] && wordSet.contains(s.substring(j, i));

我们需要每次将 j 从 0 开始移动直到 i，上面的 动态转移方程的意思是 从 某一个 j开始 （这个 j 之前的substring 是可以被 concat 的）
从 j 到 i-1 的 substring 也是可以通过 wordDict 来 construct 的，那么此时 dp[i] 就可以标记为 true
```

否则 如果 dp[i] 代表的是 从 0 到 i 的 string 可以被 construct，那么 动态转移方程将会非常复杂
```text
dp[i] = dp[j] && wordSet.contains(s.substring(j + 1, i + 1));

因为 dp[j] 是包括 index j 的，所以在使用 substring function 的时候就需要 j+1，并且最后的 index 也得是 i+1
```

下面是 code:

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length()+1]; // 为什么 dp 需要初始化 为 length+1 长度

        dp[0] = true;
        for(int i = 1;i<=s.length();i++){
            for(int j = 0;j<i;j++){ //为什么 j 指针 < i，为什么不是 j<=i ?
                if(dp[j] && wordSet.contains(s.substring(j,i))){ // 为什么 substring 是 从 j 到 i 的，substring 是不包括 i index 的 char的
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()]; // 最后为什么返回的是 dp[s.length()]
    }
}
```


**问题二: 为什么 j 指针 < i，为什么不是 j<=i ?**

因为如果 j == i，那么在 substring 当中就会是 s.substring(i,i) 此时substring 会返回空字符串，没有意义，（也就是说其实加上也可以）



## Top 150

### Array/String

#### 88. Merge Sorted Array

这道题 给了两个 数组，让我们将其 sort，要求直接将 merged 之后的 array 写在 nums1 上

follow up: 如果 给的是一个 listnode 怎么办，需要我们新建一个 ListNode 来 put Value

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // starting from the back of the nums1, compare m-1 index of nums1 and n-1 index of nums2
        // put the larger one on the writer index

        int writer = nums1.length-1;
        int pointer1 = m-1;
        int pointer2 = n-1;
        
        while(pointer1 >=0 && pointer2 >= 0){
            if(nums1[pointer1] >= nums2[pointer2] ){
                nums1[writer] = nums1[pointer1];
                pointer1--;
            }else{
                nums1[writer] = nums2[pointer2];
                pointer2--;
            }
            writer--;
        }

        while(pointer1 >= 0 ){
            nums1[writer--] = nums1[pointer1];
            pointer1--;
        }

        while(pointer2 >= 0 ){
            nums1[writer--] = nums2[pointer2];
            pointer2--;
        }

        return;
    }
}
```


#### 27. Remove Element

这道题 让我们 移除 array 当中的某一个 val，也就是将所有的这个 val 放到 数组的最后边，同时保持 数组的原来顺序不变

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        // storedIndex start fomr 0;
        // if the element is not equal to val, swap with storeIndex, sI++
        int storedIndex = 0;
        int count = 0; // count how many element not equals to val
        for(int i = 0;i<nums.length;i++){
            if(nums[i] != val){
                int temp = nums[i];
                nums[i] = nums[storedIndex];
                nums[storedIndex] = temp;
                storedIndex++; 
                count++;
            }
        }

        return count;
    }
}
```

#### * 26. Remove Duplicates from Sorted Array

这道题让我们从 duplicated array 当中移除重复数字，我们使用一个 storedIndex 从 0 开始，每当我们遇见 cur num 不等于 storedIndex 上的数字的时候
我们首先 storedIndex++ 然后将 storedIndex 赋值为 num

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 1) return 1;
        
        int storedIndex = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] != nums[storedIndex]){
             storedIndex++;
                nums[storedIndex] = nums[i]; 
            }
        }

        return storedIndex+1;
    }
}
```

#### 80. * Remove Duplicates from Sorted Array II

这道让我们 在一个 sorted array 当中移除 duplicated elements，和之前不同的是这次我们允许最多两次重复

解决方案，**我们需要判断 nums[storedIndex-2] 和当前的 cur num 是否相等** 如果不相等那就 swap ，storedIndex 是我们即将放置其他数字的位置, 我们需要保证 storedIndex 和 storedIndex-2 的位置上的数字是必须是不相等的，也就是说 storedIndex 这个位置是一定要变的
接下来我们需要思考的是 storedIndex 这个位置上的数字变成什么，这个位置上的数字应该变成为 “只要和 storedIndex-2 不相等” 的数字

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if(nums.length<=2) return nums.length;

        int storedIndex = 2;
        for(int i = 2;i<nums.length;i++){
            if(nums[storedIndex-2] != nums[i]){
                int temp = nums[storedIndex];
                nums[storedIndex] = nums[i];
                nums[i] = temp;
                storedIndex++;
            }
        }
        return storedIndex;
    }
}
```
**follow up: 如果我们允许最多 3 次 重复呢，k 次？**
依然可以使用上面的做法，只不过需要将 nums[storeIndex-3] 和 cur num 相比


#### 169. Majority Element

这道题让我们寻找 array 当中的 **众数**，我们可以使用一个 count 和一个 result，每当我们遇见和 result 相等的数字 count ++，否则 count--，当 count 减小到 0 的时候更新 count = 1, result = nums[i]

```java
class Solution {
    public int majorityElement(int[] nums) {
        int result = nums[0];
        int count = 1;
        for(int i = 1;i<nums.length;i++){
            if(nums[i] == result ){
                count++;
            }else{
                count--;
                if(count<=0){
                    result = nums[i];
                    count = 1;
                }
            }
        }
        return result;
    }
}
```

#### 189. Rotate Array

这道题让我们对 array 进行旋转 k 次，注意 如果 k 大于了 array length 那么就需要对 array length 取余,
我们通过对 array 进行 reverse 来实现

```java
class Solution {
    public void rotate(int[] nums, int k) {
        k %= nums.length;

        // reverse the array;
        reverse(nums,0,nums.length-1);

        // reverse the 0 -> k-1
        reverse(nums,0,k-1);

        // reverse k -> nums.length-1;
        reverse(nums,k,nums.length-1);
        return;


    }

    public void reverse(int[] nums, int start, int end){
        while(start<end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
```

#### 121. Best Time to Buy and Sell Stock

这道题 给了一个数组让我们判断从一个 index i 到 另一个 index j 的最大差值是多少, 注意 j 在 i 之后

也就是说让我们找两个位置的元素之差最大的值

那么就只要遇见小的则更新最小值，只要遇见大的，就更新 result

```java
class Solution {
    public int maxProfit(int[] nums ) {
        // buyIndex, nums[i] < nums[buyIndex], update buyIndex
        // for each step update the result with nums[i] - nums[buyIndex];
        int buyIndex = 0;
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] < nums[buyIndex]){
                buyIndex = i;
            }else {
                result = Math.max(nums[i]-nums[buyIndex],result);
            }
        }

        return result;
    }
}
```

#### 122. Best Time to Buy and Sell Stock II

这道题实际上就是让我们在找 所有的 递增 subarray 当中最大值和最小值的差，我们可以通过 不断在这个 递增 subarray 中 对 i 和 i-1 index 的数字最差并且将这些差 sum 起来就是我们最终的结果


```java
class Solution {
    public int maxProfit(int[] nums ) {
        // sum all the ASC subarray (max-min)
        int minIndex = 0;
        int result = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] < nums[minIndex]){
                minIndex = i;
            }else{
                result += nums[i]-nums[minIndex];
                minIndex = i;
            }
        }
        return result;
    }
}
```


#### 55. Jump Game

这道题 给了我们一个数组 让我们判断 我们是否可以从 index 0 跳跃到 数组的最后一个位置，数组的元素代表着当前 index 可以向后跳多远

```text
Example 1:

Input: nums = [2,3,1,1,4]
Output: true
Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
Example 2:

Input: nums = [3,2,1,0,4]
Output: false
Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it impossible to reach the last index.

```

维护一个 canReachIndex = nums.length-1 初始化为 数组的最后一个位置我们从后往前遍历 nums，只要找到一个 index 满足 i + nums[i] >= canReachIndex
那么我们就开始寻找 哪些 index 可以 reach 这个 canReachIndex，也就是 将 canReachIndex = i

```java
class Solution {
    public boolean canJump(int[] nums) {
        
        int canReachIndex = nums.length-1;

        for(int i = nums.length-1;i>=0;i--){
            if(i + nums[i] >= canReachIndex){
                canReachIndex = i;
            }
        }


        return canReachIndex == 0;
    }
}
```

#### * 45. Jump Game II

这道题 上一道题 jump game 的变种，和之前不同的是，此时我们需要寻找的是 最小的 step 来 jump 到 数组的最后一个位置，

比较 navie 的想法是，使用 nested loop，维持一个 dp，使用一个 变量 j 来更新 dp

```java
class Solution {
    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp,Integer.MAX_VALUE);
        dp[0] = 0;

        // dp[i] = min(dp[j] + 1); where j from 0 to i
        // nums[j] + j >= i
        for(int i = 1;i<nums.length;i++){
            for(int j = 0;j<i;j++){
                if(nums[j]+j<i){continue;}
                dp[i] = Math.min(dp[i],dp[j]+1);
            }
        }
        return dp[nums.length-1];
    }
}
```

这道题的最优解是使用 贪心的思想

我们维护三个变量
jump 作为 当前跳跃的次数
currentReach 作为当前这一轮跳跃的目的地 index
maxReach 作为下一轮跳跃的最大的可以到达的范围

从当前 index i 到 index i + nums[i] 的位置算作一轮 jump （可以把 jump 看出一个 level 比较好理解）
我们把 i + nums[i] 作为我们这一轮的 currentReach，也就是说这一轮的 jump 可以达到的 就是 currentReach 
在遍历数组 i 到 i+nums[i] （包括 i+nums[i]） 过程中我们更新 maxReach 作为我们下一轮 jump 可以达到的最大的 reach

```java
class Solution {
    public int jump(int[] nums) {
        int maxReach = 0;
        int jump = 0;
        int currentReach = 0;
        for(int i = 0; i<nums.length-1;i++){
            if(nums[i] + i > maxReach){
                maxReach = nums[i] + i;
            }
            if(i == currentReach){
                jump++;
                currentReach = maxReach;
            }
            if(currentReach >= nums.length-1){
                break;
            }
        }
        return jump;
    }
}
```


#### 380. Insert Delete GetRandom O(1)

这道题 让我们实现一下一个 set，这个 set 可以进行 get random number，要求所有的 operation 时间复杂度在 O1 

既然我们需要使用 random 那么 使用list 应该是必须的，但是 如果我们使用了 list在 remove 的时候 时间复杂度 就会是 On，因为找到这个 number 的时间复杂度 就需要我们遍历一遍 这个 list
但是 java List 当中有一个 关键的 method
**叫做 List.removeLast()**，这个 method 的时间复杂度 就是 O1

我们可以使用一个 hashmap 用来记录每一个 数字在 对应 list 当中的 index ，然后在我们 remove 这个 数字的时候 我们需要 首先 将 这个数字 和 list 当中最后一个 数字进行交换，更新 hashmap，然后 使用 List.removeLast() 来一处最后一个 element

```java
class RandomizedSet {
    public Map<Integer,Integer> map;
    public List<Integer> numList;
    public Random random;
    public RandomizedSet() {
        map = new HashMap<>();
        numList = new ArrayList<>();
        random = new Random();
    }
    
    public boolean insert(int val) {
        // if exist, return false
        if(map.containsKey(val)){
            return false;
        }else{
            numList.add(val);
            map.put(val,numList.size()-1);
        }
        return true;
    }
    
    public boolean remove(int val) {
        // check if exist, if not return false
        if(!map.containsKey(val)){
            return false;
        }else{
            
            int removeIndex = map.get(val);
            map.put(numList.get(numList.size()-1),removeIndex);
            Collections.swap(numList,removeIndex,numList.size()-1);
            map.remove(val);
            numList.removeLast();
        }
        return true;
    }
    
    public int getRandom() {
        int randomIndex = random.nextInt(0,numList.size());
        return numList.get(randomIndex);
    }
}
```

#### 238. Product of Array Except Self


这道题给了一个数组 让我们计算每一个 index 上 除了其自己，其他所有的 数字的 product

我们使用两个 array，一个 计算 当前 index 左边 所有的数字 product ，一个 用来记录右边

然后左右相乘就可以得到 结果


我们还可以优化一下，也就是只使用 两个 loop 来实现答案， 也就是先计算 leftProduct，然后计算 rightProduct 的同时 计算 result array

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] leftProduct = new int[nums.length];
        leftProduct[0] = 1;
        int[] rightProduct = new int[nums.length];
        rightProduct[rightProduct.length-1] = 1;


        for(int i = 1;i<nums.length;i++){
            leftProduct[i] = leftProduct[i-1] * nums[i-1];
        }

        int[] result = new int[nums.length];
        for(int i = nums.length-1;i>=0;i--){
            if(i<=nums.length-2){
                rightProduct[i] = rightProduct[i+1] * nums[i+1];
            }
            result[i] = rightProduct[i] * leftProduct[i];
        }


        return result;

    }
}
```

#### 134. Gas Station

非常 tricky 的题目，这道题给了两个 array 
gas[i] 代表 在 index i 的position 可以获得的 gas 数量 和 
cost[i] 代表着 在 index i 的 position 需要 cost 的数量来移动到 i+1

问我们 从某一个 start index 开始可以环状的遍历所有的 gas station 的 index 是什么？

```text
Example 1:

Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
Output: 3
Explanation:
Start at station 3 (index 3) and fill up with 4 unit of gas. Your tank = 0 + 4 = 4
Travel to station 4. Your tank = 4 - 1 + 5 = 8
Travel to station 0. Your tank = 8 - 2 + 1 = 7
Travel to station 1. Your tank = 7 - 3 + 2 = 6
Travel to station 2. Your tank = 6 - 4 + 3 = 5
Travel to station 3. The cost is 5. Your gas is just enough to travel back to station 3.
Therefore, return 3 as the starting index.
```
**这道题的核心要点就是我们需要知道 如果 total gas 小于 total cost 的话 我们是没有办法遍历整个环中的 gas station 的** 这种情况下 我们返回 -1

那么 我们从 index 0 开始维持一个 tank 用来记录 当前的 gas，如果 tank 小于 0 那么说明两种情况
1. 整个loop 就不可能实现 遍历整个 gas station ，也就是 total gas 是小于 total cost 的
2. 我们选择 的 startIndex 是有问题的，所以一旦 tank < 0 我们需要选择 i+1 作为 startIndex

```java
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        // if the sum of the total gas is larger than the sum of the total cost
        // then there must be a solution,otherwise, return -1
        int totalGas = 0;
        int totalCost = 0;
        int tank = 0;
        int startIndex = 0;
        for(int i = 0;i<gas.length;i++){
            totalGas += gas[i];
            totalCost += cost[i];
            tank = gas[i] - cost[i];
            if(tank < 0){
                startIndex = i+1;
                tank = 0;
            }
        
        return totalGas >= totalCost ? startIndex : -1;
    }
}
```

那么有一个问题就是 我们 将 startIndex 赋值为 i+1，那么 我们不会担心 数组越界的问题么，当 i = gas.length-1 的时候 startIndex 将会等于 n？

实际上如果 当 i == gas.length-1 的时候 startIndex 依然更新了，那么就说此时 tank 是小于的 0 的，也就是说 我们遍历了一遍 gas 数组我们发现，但了最后一个位置我们发现 tank 依然是小于 0 的
那么此时 total gas 一定是 total cost 的，所以最后的返回结果一定会是 -1

T: O(n)
S: O(1)



#### * 135. Candy

这道题 给了我们一个 rating array，意思是 按照 rating 来分配 candy，如果 ratings[i] 大于 左右两边，那么我们需要保证分配糖果的时候满足 index i 分配到的 糖果要大于左右两边
每一个 小朋友最少分配到一个 candy

我们维持一个 candies 数组用来代表我们分配的糖果的情况，既然每一个小孩最少分配一个糖果，那么我们首先 fill 这个 array 为 1
然后我们遍历 ratings array 来满足 每个小朋友的左边都满题目条件 只要 ratings[i] > ratings[i-1]，那么我们就将 candies[i] = candies[i+1]

之后我们从右往左遍历一遍 ratings 数组，目的是 满足所有小朋友的右侧都满足条件，但是注意 当条件 ratings[i] > ratings[i+1] 出现的时候会有两种情况
1. candies[i] 需要比 candies[i+1] 多一个 candy，那么 candies[i] 应该等于 candies[i+1] + 1
2. candies[i] 已经满足了 candies[i] 大于 candies[i+1] 的情况，为什么会有这种情况发生？
   比如说 ratings 数组在 i+1 之前 都是一直递增的 恰好到 i+1 的时候 ratings 开始递减了，那么此时 candies[i+1] 的位置上的 candy 是可能 等于 1 的
 那么我们就不能 简单的将 candies[i] = candies[i+1] + 1, because this will reduce the candies in position candies[i]

for example:

```text

ratings: 
[1,2,3,4,5,2]

candies:
[1,1,1,1,1,1]

arrange left neighbor:
[1,2,3,4,5,1]

arrange right neighbor:
[1,2,3,4,5,1]

我们不能把 candies[4] (5) 改为 candies[4+1] (1) + 1， 这样会让 candies[4] 位置上的 candies 减小, 我们应该取 candies[i] 和 candies[i+1] 的最大值



```

```java
class Solution {
    public int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        Arrays.fill(candies,1);

        for(int i = 1;i<ratings.length;i++){
            if(ratings[i] > ratings[i-1]){
                candies[i] = candies[i-1] + 1;
            }
        }

        for(int i = ratings.length-2;i>=0;i--){
            if(ratings[i] > ratings[i+1]){
                candies[i] = Math.max(candies[i], candies[i+1] + 1);
            }
        }

        int result = 0;
        for(int i = 0;i<candies.length;i++){
            result+= candies[i];
        }

        return result;

    }
}
```

#### 42. Trapping Rain Water

经典老题，都知道解决方案是使用两个 array 但是在一些细节上面需要注意

leftMax array 用来记录 index i （**包括 index i**）向左边的 最高的 height
rightMax array 用来记录 index i（**包括 index i**） 向右边的 最高的 height

```text
input :    [0,1,0,2,1,0,1,3,2,1,2,1]

leftMax:   [0,1,1,2,2,2,2,3,3,3,3,3]
rightMax:  [3,3,3,3,3,3,3,3,2,2,2,0]

sum 的结果  [0,0,1,0,1,2,1,0,0,1,0,0]

result = 1 + 1 + 2 + 1 + 1 = 6
```

```java
class Solution {
    public int trap(int[] height) {
        int[] rightMax = new int[height.length]; // the max height on the index right, including the index right
        int[] leftMax = new int[height.length]; // the max height on the index left, including index i

        int leftMaxHeight = height[0];
        for(int i = 1;i<height.length;i++){
            if(height[i] > leftMaxHeight){
                leftMax[i] = height[i];
                leftMaxHeight = height[i];
            }else{
                leftMax[i] = leftMaxHeight;
            }
        }

        int rightMaxHeight = height[height.length-1];
        int result = 0;
        for(int i = height.length-2;i>=0;i--){
            if(height[i] > rightMaxHeight){
                rightMax[i] = height[i];
                rightMaxHeight = height[i];
            }else{
                rightMax[i] = rightMaxHeight;
            }

            int sum = Math.min(leftMax[i],rightMax[i]) - height[i];
            result += (sum > 0) ? sum : 0;
        }

        return result;
    }
    
    public static void main(String[] args){
        int[] testcase1 = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        Solution solution    = new Solution();
        System.out.println(solution.trap(testcase1));
    }
}

```


#### 13. Roman to Integer

这道题让我们将 罗马数字转换成 integer，
input: String
output: int

```text
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
```

有一个规则就是 如果 I 出现在 V 之前，那么就是 相当于 V - I 也就是 5 - 1 = 4
同样的对于
```text
IV -> 4
IX -> 9
XL -> 40
XC -> 90
CD -> 400
CM -> 900
```

**那么这道题的核心就是 我们只要发现一个 character 他所对应的数字比这个 character 之后一位 index 上的 character 对应的数字小，那么在 result 中就将这个 character 对应的数字减去**

for example:
```text
result = 0
XL = -10 + 50 = 40
```

```java
class Solution {
    public int romanToInt(String s) {
        
        Map<Character,Integer> map = new HashMap<>();
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);

        char[] arr = s.toCharArray();
        int result = 0;
        for(int i = 0;i<arr.length-1;i++){
            if(map.get(arr[i]) < map.get(arr[i+1])){
                result -= map.get(arr[i]);
            }else{
                result += map.get(arr[i]);
            }
        }

        return result + map.get(arr[arr.length-1]);
    }
}
```

T: O(n)
S: O(1)

#### 6. Zigzag Conversion

这道题给了我们一个 String 和一个 numRows 让我们按照蛇形走位来从头开始先向下开始走 然后向上 开始走 然后周而复始

```text
Example 2:

Input: s = "PAYPALISHIRING", numRows = 4
Output: "PINALSIGYAHRPI"
Explanation:
P     I    N
A   L S  I G
Y A   H R
P     I
```

这道题的解法最直接的就是 直接 按照规则进行 移动，我们可以使用一个 StringBuilder 的 array 来记录每一行对应的 的 character
使用两个变量 一个叫做 boolean down 用来记录 当前的移动方向是向下的还是向上，使用另一个 变量叫做 curRow 用来记录当前的 row 是第几个，一旦 curRow == numRows 我们就将 方向变成 up 并且将 curRow--

```text
char c = s.charAt(i);

stringbuilder[curRow].append(c)

if down == true && curRow == numRow
    curRow--;
    down = false;

if down == true && curRow < numRow
    curRow++;

if down == false && curRow < 1
    curRow++;
    down = true;
    
if down == false && curRow >= 1
    curRow--;
```

下面是我写的代码 需要注意的细节就是，stringbuilder array 的 index 是 1 based 的

```java
class Solution {
    public String convert(String s, int numRows) {
        if(numRows==1) return s;
        
        StringBuilder[] sbs = new StringBuilder[numRows+1];
        for(int i = 0;i<sbs.length;i++){
            sbs[i] = new StringBuilder();
        }

        int curRow = 1;
        boolean down = true;
        for(int i = 0;i<s.length();i++){
            sbs[curRow].append(s.charAt(i));

            if(down && curRow==numRows){
                curRow--;
                down = false;
            }else if(down && curRow< numRows){
                curRow++;
            }else if(down == false && curRow == 1){
                curRow++;
                down = true;
            }else if(down == false && curRow > 1){
                curRow--;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 1;i<sbs.length;i++){
            sb.append(sbs[i]);
        }

        return sb.toString();
    }
}
```


#### 28. Find the Index of the First Occurrence in a String

这道题 给了我们两个 string 一个叫做 haystack 另一个叫做 needle，让我们寻找 在 haystack 当中第一次出现 needle 的 index，如果没有 则返回 -1

很自然的想法就是 遍历haystack 一旦我们发现当前的 index i 的 character 和 needle 的第一个 character 相等，那么我们就进入 needle 来判断 一整个 needle 是否存在于 haystack 当中

```java
class Solution {
    public int strStr(String haystack, String needle) {
        if(needle.length() > haystack.length()){
            return -1;
        }
        for(int i = 0;i<haystack.length();i++){
            if(haystack.charAt(i) == needle.charAt(0) && i+needle.length()<=haystack.length()){
                if(haystack.substring(i,i+needle.length()).equals(needle)){
                    return i;
                }
            }
        }
        return -1;
    }
}
```

这道题还有 KMP 解法


#### 68. Text Justification


这道题 给了我们一个 String array，和一个 maxWidth 让我们 rearrange 所有的 字符串 使得每一行不得超过 maxWidth
每一个 字符串之间需要至少被一个 space 给隔开，如果某一行只有一个字符串或者当前这一行是最后一行那么我们 进行 left justify，也就是说所有的 字符串从左开始之间只隔了一个 space，多余的 space 加到右侧
否则，多余的 space 需要 evenly 分配给 其他所有位置上的 spaces，如果无法实现 evenly，那么 我们需要 给左边安排更多的 space

```text
Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
```


我在做这道题的想法是首先判断每一行当中有哪些字符串是可以提取出来的，也就是使用一个 指针 j 来添加words[j] 的字符串长度，然后记录一下 curLineWordsLength，当前行 添加的 字符串的 数量就是 j-i，那么所需要的 spaces 也至少是 j-i
那么就判断一下 当前 行添加了 words[j] 之后以及 所需要的 space 是否大于 maxWidth，如果超出 那么就结束 while 循环

所以需要注意当跳出 while 循环的 时候 我们的 j 是不能添加的，真正可以添加的 字符串 是从 index i 到 index j-1

接着分为两类来思考，需要 left justify 和情况以及 不需要 left justify 而是需要 进行 evenly place 的情况

left justify 也就是当 当前行只有一个 字符串或者 当前行是最后一行

```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;

        while (i < words.length) {
            int curLineWordsLength = 0; // 注意这里我们记录的是所有添加的 words 的长度 并不包括 spaces
            int j = i;

            // 计算当前行可以容纳的单词数量
            while (j < words.length && curLineWordsLength + words[j].length() + (j - i) <= maxWidth) {
                curLineWordsLength += words[j].length();
                j++;
            }

            int numOfWords = j - i; // 实际上我们的可以添加到的 index 是 i -> j-1, 字符串数量是 (j-1-i+1) = j-i
            int numOfPosForSpace = numOfWords - 1;
            int extraSpaces = maxWidth - curLineWordsLength;
            StringBuilder sb = new StringBuilder();

            if (numOfWords == 1 || j == words.length) { // left justify 左对齐或最后一行
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) { // 每一行的最后一个单词之后是不添加 space 的
                        sb.append(" ");
                    }
                }
                // 补齐多余的空格
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } else { // 完全对齐
                int commonSpaces = extraSpaces / numOfPosForSpace; // 每一行至少需要添加的 space
                int incommonSpaces = extraSpaces % numOfPosForSpace; // 从左到右每一个单词之后需要添加的多余的 space

                for (int k = i; k < j - 1; k++) {
                    sb.append(words[k]);
                    if (incommonSpaces > 0) {
                        sb.append(" "); // 分配额外空格
                        incommonSpaces--;
                    }
                    sb.append(" ".repeat(commonSpaces));
                }
                sb.append(words[j - 1]); // 添加最后一个单词，单独拎出来的原因是最后一个单词 之后 是不添加 spaces
            }

            result.add(sb.toString());
            i = j; // 更新起点，真正能够添加的 index 是 从 i -> j-1，所以我们只需要将 index i 更新为 j 就可以
        }

        return result;
    }
}

```

### Two Pointers

#### 392. Is Subsequence

这道题给了我们两个 string s 和 t，问我们在 t 中是否包含 s 的 subsequence？

```text
Example 1:

Input: s = "abc", t = "ahbgdc"
Output: true
Example 2:

Input: s = "axc", t = "ahbgdc"
Output: false
```

这道题应该可以想得到使用 two pointers，也就是使用一个 index i 遍历 t，另一个 index j 遍历 s， 如果 s[j] == t[i] j++,i++
如果 在 s[j] == t[i] && j == s.length-1 （**也就是 j 到达最后一位**） 那么直接返回 true

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        if(s.length() == 0) return true;
        if(s.length() > t.length())return false;
        if(s.length()==t.length())return s.equals(t);

        int j = 0;
        for(int i = 0;i<t.length();i++){
            if(t.charAt(i)==s.charAt(j)){
                if(j == s.length()-1){
                    return true;
                }
                j++;
            }
        }

        return false;
    }
}
```

Time: O(T*S)   T is the length of t, S is length of s
Space: O(1)

这道题还有一个 follow up 就是 如果我们现在有多个 s，s1, s2, s3 ,s4 ... sk 那么怎么解决？

如果我们依然使用上面的解法，那么我们的时间复杂度将会是 Time: O(K*T*S), 当 s 和 t 的长度非常大的时候，时间复杂度将会非常的高

**Solution 2:**
我们可以使用 binary search + indexHashmap 的方法，建立一个关于 t 当中 所有 character 的 indexHashMap，（key:character, value:List<Index>）
然后遍历 s 并且记录之前遍历到的 s 的 character 在 t 中的 “prevIndex”，然后我们在 indexHashMap 中寻找当前遍历到的 character 的List Index 中比这个  prevIndex 大的 index
如果找到了那么久更新，如果没有找到则返回 -1

```java
class Solution{
    public boolean isSubsequence(String s, String t){
        if(s.length()==0)return true;
        Map<Character,List<Integer>> map = new HashMap<>();
        for(int i = 0;i<t.length();i++){
            char c = t.charAt(i);
            map.putIfAbsent(c, new ArrayList<>());
            map.get(c).add(i);
        }
        
        int prevIndex = -1;
        for(int i = 0;i<s.length();i++){
            char c = s.charAt(i);
            if(!map.containsKey(c)){
                return false;
            }else{
                List<Integer> indexs = map.get(c);
                int searchIndex = binarySearch(indexs,prevIndex); // searching the index that is larger than prevIndex
                if(searchIndex == -1){return false;}
//                prevIndex = searchIndex; // ******* 这里错了
                prevIndex = indexs.get(searchIndex); // searchIndex 是 binarySearch 返回的在 indexs list 当中比prevIndex 大的数字（实际上也是一个 index） 的 index
            }
        }
        
        return t.charAt(prevIndex) == s.charAt(s.length()-1);
        
    }
    
    public int binarySearch(List<Integer> nums, int target){
        int left = 0;
        int right = nums.size()-1;
        while(left<=right){
            int mid = (right-left)/2 + left;
            if(nums.get(mid) <= target){ // we want the index that is just larger than target
                left = mid + 1;
            }else{
                right = mid - 1; // we will always return left, so even right might be the answer, we will reduce
            }
        }
        
//        return nums.get(left) > target ? left : -1; // ******这里错了
          return left < nums.size() ? left : -1; // 我们应该判断的是  left index 是否在 nums list 的范围内，因为只有在其范围内才是一个有效的，否则返回 -1 
    }
}
```

#### 15. 3Sum

废话不多说 直接上代码，容易出错的地方在于 规避重复出现的 triplets，我们需要在三处特别注意

第一个是在主循环动中 如果 当 nums[i] == nums[i-1]
比如说

```text
-1, -1, -1, 1, 1, 2

第一个 -1 和 第三个 -1 的所产生的 triplets 是相同的, 所以我们应该跳过，知道第三个 -1
```

其他的是在我们找到 sum = 0 的时候此时我们需要移动 left ++ right --，并且我们还需要判断移动之后的 num 是不重复的

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // sort the array
        Arrays.sort(nums);

        // for each index, use two sum find the other two index, skiped the repeated elements
        for(int i = 0;i<nums.length-2;i++){
            if(i>0 && nums[i] == nums[i-1]){
                continue;
            }
            int left = i + 1;
            int right = nums.length-1;
            while(left < right){
                int sum = nums[i] + nums[left] + nums[right];
                if(sum == 0){
                    List<Integer> candi = new ArrayList<>();
                    candi.add(nums[i]);
                    candi.add(nums[left]);
                    candi.add(nums[right]);
                    result.add(candi);
                    left++;
                    right--;
                    while(left<right && nums[left] == nums[left-1]){
                        left++;
                    }
                    while(left<right && nums[right] == nums[right+1]){
                        right--;
                    }
                }else if(sum < 0){
                    left++;
                }else{
                    right--;
                }
            }
        }

        return result;

    }
}
```


### Sliding Window

#### 209. Minimum Size Subarray Sum

这道题让我们寻找一个 subarray 其 sum 大于或等于 target，这样的一个可能存在的 subarray 的最小的长度是什么？

sliding window 以后就按照这样写好了，一个 for loop 用来 扩展 window，另一个 while 用来 shrink 这个 window

```java
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int windowSum = 0;
        int i = 0;
        int result = nums.length+1;
        for(int j = 0;j<nums.length;j++){
            windowSum += nums[j];
            while(i<=j && windowSum >= target){
                result = Math.min(j-i+1,result);
                windowSum -= nums[i];
                i++;
            }
        }

        return result == nums.length+1 ? 0 : result;
    }
}
```
