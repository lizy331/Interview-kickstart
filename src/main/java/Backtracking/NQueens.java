package Backtracking;

import java.util.*;

public class NQueens {
    List<List<String>> res;

    // make a global 2D map, to store the temporary result
    char[][] grid;
    int size;

    public List<List<String>> solveNQueen(int n) {
        res = new ArrayList<>();
        size = n;
        grid = new char[n][n];

        // make a function call to start filling from line 0
        for(int i = 0;i<n;i++){
            for(int j = 0;j<n;j++){
                grid[i][j] = '.';
            }
        }
        helper(0);


        return res;
    }

    public void helper(int i) {
        // stopping condition, where the i is exceeded the last line, where i == size
        if (i == size) {

            List<String> out = new ArrayList<>();
            for(int k = 0;k<size;k++){
                out.add(new String(grid[k]));
            }
            res.add( new ArrayList<>(out));
            return;
        }

        // starting from index 0 for each column try to replace with Queen
        for(int j = 0;j<size;j++){
            if(isvalid(i,j)){
                // if it is valid then move to the next line
                grid[i][j] = 'Q';
                helper(i+1);

            }
            grid[i][j] = '.';
        }
    }

    public boolean isvalid(int x, int y){

        // check the vertical line is valid or not
        for(int i = 0;i<size;i++){
            if(grid[i][y] == 'Q'){
                return false;
            }
        }

        // check horizontal
        for(int j = 0;j<size;j++){
            if(grid[x][j] == 'Q'){
                return false;
            }
        }

        // check diagnal from upper left to lower right
        int i = x;
        int j = y;
        while(i>=0 && j>=0){
            if(grid[i][j]=='Q'){
                return false;
            }
            i--;
            j--;
        }

        // check diagnal from x,y  upper right
        i = x;
        j = y;
        while(i>=0 && j<size){
            if(grid[i][j]=='Q'){
                return false;
            }
            i--;
            j++;
        }

        return true;
    }

    public static void main(String[] args) {
        NQueens sol = new NQueens();
        List<List<String>> res = sol.solveNQueen(8);


        for(int i = 0;i<res.size();i++){
            System.out.println(res.get(i));
        }

    }
}
