这道题 来自 leetcode 909， 是一个类似大富翁棋盘游戏，也就是给了我们一个 n*n 的二维矩阵

这道题给了一个 NxN 大小的二维数组，从左下角从1开始，蛇形游走，到左上角或者右上角到数字为 NxN，中间某些位置会有梯子，就如同传送门一样，直接可以到达另外一个位置。现在就如同玩大富翁 Big Rich Man 一样，有一个骰子，可以走1到6内的任意一个数字，现在奢侈一把，有无限个遥控骰子，**每次都可以走1到6以内指定的步数**，**问最小能用几步快速到达终点 NxN 位置**。博主刚开始做这道题的时候，看是求极值，以为是一道动态规划 Dynamic Programming 的题，结果发现木有办法重现子问题，没法写出状态转移方程，只得作罢。但其实博主忽略了一点，**求最小值还有一大神器，广度优先搜索 BFS**，最直接的应用就是在迷宫遍历的问题中，求从起点到终点的最少步数，也可以用在更 general 的场景，只要是存在确定的状态转移的方式，可能也可以使用。这道题基本就是类似迷宫遍历的问题，可以走的1到6步可以当作六个方向，这样就可以看作是一个迷宫了，唯一要特殊处理的就是遇见梯子的情况，要跳到另一个位置。这道题还有另一个难点，就是数字标号和数组的二维坐标的转换，这里起始点是在二维数组的左下角，且是1，而代码中定义的二维数组的 (0, 0) 点是在左上角，需要转换一下，还有就是这道题的数字是蛇形环绕的，即当行号是奇数的时候，是从右往左遍历的，转换的时候要注意一下。

input:

```text
Input: [
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,-1,-1,-1,-1,-1],
[-1,35,-1,-1,13,-1],
[-1,-1,-1,-1,-1,-1],
[-1,15,-1,-1,-1,-1]]
Output: 4
Explanation:
At the beginning, you start at square 1 [at row 5, column 0].
You decide to move to square 2, and must take the ladder to square 15.
You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
You then decide to move to square 14, and must take the ladder to square 35.
You then decide to move to square 36, ending the game.
It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
```

```java
class Solution {
    public int snakesAndLadders(int[][] board) {
        int res = 0;
        int n = board.length;
        // int[][] visited = new int[n][n];
        Deque<Integer> dq = new ArrayDeque();
        dq.offer(1);
        while(!dq.isEmpty()){
            int size = dq.size();
            for(int i = 0;i<size;i++){
                Integer cur = dq.poll();
                if(cur - n*n==0){
                    return res;
                }
                for(int k = cur+1;k<=cur+6 && k<=n*n;k++){
                int[] pos = getPosition(k,board);
                // System.out.println(k);
                if(board[pos[0]][pos[1]]!=0){
                    if(board[pos[0]][pos[1]]!=-1){
                        dq.offer(board[pos[0]][pos[1]]);
                    }else{
                        dq.offer(k);
                    }
                    
                    board[pos[0]][pos[1]] = 0;
                }
            }
            // System.out.println(" ");
            }
            res++;
        }
        return -1;

    }

    public int[] getPosition(int k, int[][] grid){
        int n = grid.length;
        int level = k/n;
        if(k%n==0){
            level = level-1;
        }
        int s = k-(level*n+1);
        boolean reverse = false;
        if(level%2 != 0){
            reverse = true;
        }

        return reverse ?  new int[]{n-1-level,n-1-s} : new int[]{n-1-level,s};
    }
}
```

这道题实际上是 一道 directed 有向 graph，使用 广度 BFS 来寻找最小值 也就是步数

时间复杂度 O(n) 没有 m 这是因为，此时我们每一个 vertex 相邻的或者可选的 des vertex，是通过骰子摇出来的，也就是 当前 label + 1 到 label + 6

所以实际上 
O(6n)