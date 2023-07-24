package DynamicProgramming.ClassProblem;

public class UniquePathII {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {

        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        // create a 2D matrix, same dimensions as obstacleGrid
        int[][] grid = new int[m][n];

        // init the first row as 1, if there are obstacle break
        for(int i = 0;i<n;i++){
            if(obstacleGrid[0][i]==1){break;}
            grid[0][i] = 1;
        }

        // same for the first column
        for(int i = 0;i<m;i++){
            if(obstacleGrid[i][0]==1){break;}
            grid[i][0] = 1;
        }

        // loop all the space, if there are obs, ignore it
        for(int i = 1;i<m;i++){
            for(int j = 1;j<n;j++){
                if(obstacleGrid[i][j]==1){
                    grid[i][j]=0;
                    continue;}
                grid[i][j] = grid[i-1][j] + grid[i][j-1];
            }
        }

        return grid[m-1][n-1];

    }
}
