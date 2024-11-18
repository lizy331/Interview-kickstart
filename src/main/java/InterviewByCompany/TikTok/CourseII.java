package InterviewByCompany.TikTok;

/**
 As a system admin, one day you need to install a set of packages. The customer gives you a list of  dependencies where dependencies[i] = [a_i, b_i] indicates that you must install package b_i first if you want to install package a_i.

 Implement a function that returns the ordering of packages you should follow to successfully install all packages without stopping. If there are many valid answers, return any of them. If it is impossible to install all packages, return an empty array.
 **/


import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class CourseII {

    public List<Integer> result;
    public int[] findPackage(int[][] dependencies) {



        // build the graph
        int maxVertex = 0;
        Set<Integer> Vertex = new HashSet();

        Map<Integer,List<Integer>> adj = new HashMap<>();
        for(int i = 0;i<dependencies.length;i++){
            int[] edge = dependencies[i];
            Vertex.add(edge[0]);
            Vertex.add(edge[1]);
            adj.put(edge[1],new ArrayList<>());
            adj.get(edge[1]).add(edge[0]);
        }

        int max = Integer.MIN_VALUE;
        for(int vertex : Vertex){
            max = Math.max(max,vertex);
        }

        result = new ArrayList<>();

        // dfs
        int[] visited = new int[max]; // 0 not visited, 1 visiting, 2 visited
        for(int i : adj.keySet()){
            if(dfs(i,adj,visited)){
                // find cycle
                return new int[0];
            }
        }

        Collections.reverse(result);

        int[] arrayResult = new int[result.size()];
        for(int i = 0;i<result.size();i++){
            arrayResult[i] = result.get(i);
        }

        return arrayResult;


    }

    public boolean dfs(int start, Map<Integer,List<Integer>> adj, int[] visited){
        if(visited[start] == 1){
            return true;
        }
        if(visited[start] == 2){
            return false;
        }
        visited[start] = 1;

        for(int neighbor : adj.get(start)){
            if(dfs(neighbor,adj,visited)){
                return true;
            }
        }
        result.add(start);
        visited[start] = 2;

        return false;
    }

    public static void main(String[] args) {
        CourseII solution = new CourseII();

        int[][] testcase1 = new int[][]{{1,3},{2,1},{3,0}};
        int n1 = 4;

        System.out.println(Arrays.toString(solution.findPackage(testcase1)));

        int[][] testcase2 = new int[][]{{1,3},{2,1},{3,1},{2,3}};
        int n2 = 4;
        System.out.println(Arrays.toString(solution.findPackage(testcase2)));

    }
}
