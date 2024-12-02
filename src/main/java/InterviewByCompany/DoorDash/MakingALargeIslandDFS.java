package InterviewByCompany.DoorDash;

import java.util.*;
public class MakingALargeIslandDFS {

    int landId = 2;
    int n;
    int m;
    int[][] DIRS = {{1,0},{0,-1},{-1,0},{0,1}};
    public int largestIsland(int[][] grid) {
        n = grid.length;
        m = grid[0].length;
        Map<Integer, Integer> map = new HashMap<>();

        // step1: mark island with series;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    int landArea = dfs(grid, i, j);
                    map.put(landId, landArea);
                    landId++;
                }
            }
        }

        // step2: for each water, change to 1 and check 4 DRIS connected lands, update the max
        int res = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 0) {
                    int area = 1;
                    Set<Integer> set = new HashSet<>();
                    for (int[] DIR : DIRS) {
                        int r = i + DIR[0];
                        int c = j + DIR[1];
                        if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] != 0) {
                            set.add(grid[r][c]);
                        }
                    }
                    for (int landId : set) {
                        area += map.get(landId);
                    }
                    res = Math.max(res, area);
                }
            }
        }
        return res > 0 ? res : m * n; // if all 1 then size
    }

    public int dfs(int[][] grid, int row, int col) {
        grid[row][col] = landId;
        int area = 1;
        for (int[] DIR : DIRS) {
            int r = row + DIR[0];
            int c = col + DIR[1];
            if (r >= 0 && c >= 0 && r < n && c < m && grid[r][c] == 1) {
                area += dfs(grid, r, c);
            }
        }
        return area;
    }
}
