package InterviewByCompany.DoorDash;


import java.util.*;

/**

 https://leetcode.com/discuss/interview-question/1522955/Doordash-Onsite


 follow up:

 // grid里加上Customer location 用'C'表示。现在不用query shortest distance of each location，而是要返回serve最多customer的dashmart的坐标.

 */
public class DashMart {


    public List<Integer> getClosestDashMart(char[][] grid, List<int[]> locations){
        Queue<int[]> queue = new LinkedList<>();
        int m = grid.length;
        int n = grid[0].length;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                char DASHMART = 'D';
                if (grid[row][col] == DASHMART) {
                    queue.add(new int[] { row, col });
                }
            }
        }

        int[][] dirs = new int[][]{{0,-1},{-1,0},{0,1},{1,0}};
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 0;i<size;i++){
                int[] loc = queue.poll();
                int x = loc[0];
                int y = loc[1];
                for(int[] dir : dirs){
                    int newX = x + dir[0];
                    int newY = y + dir[1];
                    if(newX < m && newY < n && newX >= 0 && newY >= 0 && grid[newX][newY] == ' '){
                        queue.offer(new int[]{newX,newY});
                        grid[newX][newY] = (char)(grid[x][y]+1);
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for(int[] loc : locations){
            result.add(grid[loc[0]][loc[1]]-'D');
        }

        return result;
    }


    public static void main(String[] args){
        DashMart solution = new DashMart();

        char[][] locationBoard = {
                {'X', ' ', ' ', 'D', ' ', ' ', 'X', ' ', 'X'},
                {'X', ' ', 'X', 'X', ' ', ' ', ' ', ' ', 'X'},
                {' ', ' ', ' ', 'D', 'X', 'X', ' ', 'X', ' '},
                {' ', ' ', ' ', 'D', ' ', 'X', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X'},
                {' ', ' ', ' ', ' ', 'X', ' ', ' ', 'X', 'X'}};

        List<int[]> locations = new ArrayList<>();
        locations.add(new int[]{2,2});
        locations.add(new int[]{4,0});
        locations.add(new int[]{0,4});
        locations.add(new int[]{2,6});

        System.out.println(solution.getClosestDashMart(locationBoard,locations)); // 1, 4, 1 ,5

    }
}
