package Graph.PracticeProblem.code;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class RottingOranges {

    static int row;
    static int col;
    static int timer;
    static Integer rotting_oranges(ArrayList<ArrayList<Integer>> grid) {
        // Write your code here.
        row = grid.size();
        col = grid.get(0).size();
        timer = -1;
        int[][] visited = new int[row][col];
        ArrayList<int[]> roted = new ArrayList<>();

        int counter = 0;
        for(int i =0;i<row;i++){
            for(int j = 0;j<col;j++){
                if(grid.get(i).get(j).equals(2)){
                    roted.add(new int[]{i,j});
                }
                else if(grid.get(i).get(j).equals(1)){
                    counter ++;
                }
            }
        }
        if(roted.size()==0){
            return counter > 0 ? -1 : 0;
        }

        bfs(grid,visited,roted);

        int res = 0;
        for(int i =0;i<row;i++){
            for(int j = 0;j<col;j++){
                if(grid.get(i).get(j).equals(1)){
                    res++;
                }
            }
        }
        return res > 0 ? -1 : timer;
    }

    static void bfs(ArrayList<ArrayList<Integer>> grid, int[][] visited,ArrayList<int[]> roted){

        Deque<int[]> dq = new ArrayDeque();
        for(int[] ro : roted){
            dq.offer(ro);
            visited[ro[0]][ro[1]] = 1;
        }
        while(!dq.isEmpty()){
            int n = dq.size();
            for(int i = 0;i<n;i++){

                int[] cord = dq.poll();
                // System.out.println("cord: "+cord[0]+" "+cord[1]);
                for(int[] des : getDes(cord,grid,visited)){
                    if(visited[des[0]][des[1]]!=1 && grid.get(des[0]).get(des[1])==1){
                        // System.out.println(des[0]+" "+des[1]);
                        dq.offer(des);
                        grid.get(des[0]).set(des[1],2);
                        visited[des[0]][des[1]]=1;
                    }
                }
            }
            // System.out.println(timer);
            // System.out.println(grid);
            // System.out.println(" ");
            timer++;
        }

    }

    static ArrayList<int[]> getDes(int[] cor, ArrayList<ArrayList<Integer>> grid, int[][] visited){
        ArrayList<int[]> result = new ArrayList<>();
        int x = cor[0];
        int y = cor[1];

        if(x+1<row && grid.get(x+1).get(y)!=0 && visited[x+1][y]!=1){
            result.add(new int[]{x+1,y});
        }
        if(x-1>=0 && grid.get(x-1).get(y)!=0 && visited[x-1][y]!=1){
            result.add(new int[]{x-1,y});
        }
        if(y+1<col && grid.get(x).get(y+1)!=0&& visited[x][y+1]!=1){
            result.add(new int[]{x,y+1});
        }
        if(y-1>=0 && grid.get(x).get(y-1)!=0&& visited[x][y-1]!=1){
            result.add(new int[]{x,y-1});
        }
        return result;
    }
}
