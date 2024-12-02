package InterviewByCompany.DoorDash;

import java.util.*;
public class MakingALargeIslandBFS {

    public Map<Integer,Integer> islandMap;
    public int result;
    public int[][] dirs;
    public int largestIsland(int[][] grid) {

        // step 1: create islandMap
        islandMap = new HashMap<>();
        int islandNumber = 2;
        dirs = new int[][]{{-1,0},{1,0},{0,-1},{0,1}}; // up, down, left, right
        for(int i = 0; i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j] == 1){
                    int area = bfs(grid,i,j,islandNumber);
                    islandMap.put(islandNumber,area);
                    islandNumber++;
                }
            }
        }

        // System.out.println(islandMap);
        // for(int[] land : grid){
        //     System.out.println(Arrays.toString(land));
        // }

        // step 2: for each water, try to change it to land
        result = 0;
        for(int i = 0; i<grid.length;i++){
            for(int j = 0;j<grid[0].length;j++){
                if(grid[i][j] == 0){
                    connectLands(grid,i,j);
                }
            }
        }

        return result > 0 ? result : grid.length*grid[0].length;
    }

    public void connectLands(int[][] grid, int i, int j){
        Set<Integer> set = new HashSet<>();
        int maxResult = 0;
        for(int[] dir : dirs){
            int newX = i + dir[0];
            int newY = j + dir[1];

            if(newX < 0 || newY < 0|| newX >= grid.length || newY >= grid[0].length){
                continue;
            }
            int signal = grid[newX][newY];
            if(signal != 0 && !set.contains(signal)){
                maxResult += islandMap.get(signal);
                set.add(signal);
            }

        }
        maxResult += 1;
        if(maxResult > result){
            result = maxResult;
        }
        return;
    }

    public int bfs(int[][] grid, int i, int j, int id){

        Queue<int[]> queue = new LinkedList<>();
        int result = 0;
        queue.offer(new int[]{i,j});
        grid[i][j] = id;
        while(!queue.isEmpty()){
            int[] loc = queue.poll();
            result++;

            for(int[] dir : dirs){
                int newX = loc[0] + dir[0];
                int newY = loc[1] + dir[1];


                if(newX < 0 || newY < 0|| newX >= grid.length || newY >= grid[0].length || grid[newX][newY] != 1){
                    continue;
                }

                grid[newX][newY] = id;
                queue.offer(new int[]{newX,newY});

            }

        }

        return result;
    }




}
