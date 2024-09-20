package Graph;

import java.util.ArrayDeque;
import java.util.Deque;

public class SnakeAndLadders {
    public int snakesAndLadders(int[][] board) {
        // need a function to locate the position while given a number

        // 1. queue 里面应该放什么？
        // 可以放坐标 int[]，也可以放 square number，可能放置 square number 让 queue 更加简单

        // 2. BFS 是如何保证我们得到的 步数是最小的?
        // 我们将每一步的六个坐标加入到 queue 当中 作为接下来的一轮 BFS， 这样以来就保证了我们在最小步数的情况下达到 左上角
        int n = board.length;
        int tar = n*n;
        Deque<Integer> queue = new ArrayDeque();
        queue.offer(1);
        int res = 0;
        boolean[] visited = new boolean[tar+1];

        while(!queue.isEmpty()){
            int len = queue.size();
            res++;
            for(int i = 0;i<len;i++){
                Integer cur = queue.poll();
                if(cur - tar ==0){
                    // 为什么是 res-1 ？
                    return res-1;
                }

                for(int j = cur + 1;j<=Math.min(cur + 6, tar);j++){
                    int[] los = locate(board,j);
                    int x = los[0];
                    int y = los[1];
                    int des = board[x][y]==-1 ? j : board[x][y];

                    if(visited[des]!=true){
                        visited[des] = true;
                        queue.offer(des);
                    }
                }
            }
        }

        return -1;


    }

    public int[] locate(int[][] board, int square){
        // 我们需要将 square  想象成为一个长的 chain， 然后我们将其分段，这个 chain 的 开头就是 bottom left，也就是 square 1，注意这里是 1 indexed，
        // 所以我们需要将其转化成为一个 0 indexed 的 坐标，也就是 square - 1
        // 比如说 square = 8， 那么 我们将其转化成为了 8 - 1 = 7，这个 7 就是 0 indexed 的 chain 的位置，也就是 row 的坐标, 但是需要注意的是 这个坐标是 从下到上的，我们还需要将其进行转化

        // 所以 x = n - (square - 1) / n - 1 = n - r - 1 = 6 - 1 - 1 = 4 (0 index x 坐标)
        int n = board.length;

        int r = (square - 1)/n;
        int c = (square - 1)%n;

        boolean reverse = false;


        int x = n - r -1;

        // 注意*** 这里的reverse 是一个大坑
        // reverse 的判断 是从 bottom left 开始的， 也就是如果我们将这个 棋盘想像成一个 长的 chain，
        // 那么 这个 chain 的 头 是从 bottom left 开始的，所以我们判断 reverse 也需要从 底部开始
        // 也就是 说 我们需要使用 r 来进行 判断 是否纵坐标 应该是 reverse 的，
        // 这是因为 r 这个 数字就相当于 定位了 当前的 这个 square 处在 第几个 group 当中
        // 第一个 group r = 0 是 从 left 到 right 的，第二个 group r = 1，是 从 right 到 left 的
        // 第三个 group r = 2，又回到了 从 left 到 right
        // 所以 如果 r%2 等于 0 时 就是 从 左往右，当 r%2 不等于 0 时 就是 从右往左
        reverse = r%2 == 0 ? false : true;
        int y = 0;
        if(reverse){
            y = n - c - 1;
        }else{
            y = c;
        }


        return new int[]{x,y};
    }
}
