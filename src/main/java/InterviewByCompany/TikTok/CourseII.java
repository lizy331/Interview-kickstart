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
        Set<Integer> vertices = new HashSet();
        for (int[] edge : dependencies) {
            vertices.add(edge[0]);
            vertices.add(edge[1]);
        }

        int maxVertex = 0;
        for(int vertex : vertices){
            maxVertex = Math.max(maxVertex,vertex);
        }

        Map<Integer,List<Integer>> adj = new HashMap<>();
        for(int vertex : vertices){
            adj.put(vertex,new ArrayList<>());
        }

        for(int i = 0;i<dependencies.length;i++){
            int[] edge = dependencies[i];
            adj.get(edge[1]).add(edge[0]);
        }

        result = new ArrayList<>();

        // dfs
        int[] visited = new int[maxVertex+1]; //  0 not visited, 1 visiting, 2 visited
        for(int i : adj.keySet()){
            System.out.println("keyset:" + i);
            if(visited[i] == 0 && dfs(i,adj,visited)){
                // find cycle
                return new int[0];
            }
        }

        System.out.println("before reverse: " + result);
        Collections.reverse(result);

        int[] arrayResult = new int[result.size()];
        for(int i = 0;i<result.size();i++){
            arrayResult[i] = result.get(i);
        }

        return arrayResult;


    }

    public boolean dfs(int start, Map<Integer,List<Integer>> adj, int[] visited){
        System.out.println(start);
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

//        int[][] testcase1 = new int[][]{{1,3},{2,1},{3,0}};
//        int n1 = 4;
//
//        System.out.println(Arrays.toString(solution.findPackage(testcase1)));
//
//        int[][] testcase2 = new int[][]{{1,3},{2,1},{3,1},{2,3}};
//        int n2 = 4;
//        System.out.println(Arrays.toString(solution.findPackage(testcase2)));

        int[][] testcase3 = new int[][]{{2,0},{1,0},{3,1},{3,2}};
        System.out.println(Arrays.toString(solution.findPackage(testcase3)));// 0, 1, 2, 3 or 0, 2, 1, 3
        /*
        结果是 0, 1, 2, 3 的原因是 我们的 0 的 adjuncency list 当中 2 是先于 1 加入的，所以在循环 0 的 neighbor 的时候 2 会先被放进 result list 当中
        before reverse: [3, 2, 1, 0]

        然后我们对 result 进行了 reverse 就会变成
        [0, 1, 2, 3]

         */

    }
}
