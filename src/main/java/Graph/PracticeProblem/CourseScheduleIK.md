这又是 course Schedule 的问题，只不过是 IK 的版本，大致没有区别，就是 directed graph 寻找 cycle 的问题

```text
Example
{
"n": 4,
"prerequisites": [
[1, 0],
[2, 0],
[3, 1],
[3, 2]
]
}
Output:

[0, 2, 1, 3]

```

```java
class Solution{

    static ArrayList<Integer> res;
    static int timer;
    static ArrayList<Integer> course_schedule(Integer n, ArrayList<ArrayList<Integer>> prerequisites) {
        // Write your code here.
        res = new ArrayList<>();
        timer = 0;
        Map<Integer,ArrayList<Integer>> map = new HashMap<>();
        int[] arri = new int[n];
        int[] depa = new int[n];

        Arrays.fill(arri,-1);
        Arrays.fill(depa,-1);

        for(int i = 0;i<n;i++){
            map.put(i,new ArrayList<Integer>());
        }

        for(ArrayList<Integer> edge : prerequisites){
            map.get(edge.get(0)).add(edge.get(1));
        }

        for(int i = 0;i<n;i++){
            if(arri[i]==-1){
                if(dfs(i,map,arri,depa)){
                    ArrayList<Integer> dummy = new ArrayList<Integer>();
                    dummy.add(-1);
                    return dummy;
                }
            }
        }
        return res;
    }

    static boolean dfs(Integer source, Map<Integer,ArrayList<Integer>> map, int[] arri, int[] depa){
        arri[source] = timer;
        timer++;

        for(Integer des : map.get(source)){
            if(arri[des]==-1){
                if(dfs(des,map,arri,depa)){
                    return true;
                }
            }else{
                if(depa[des]==-1){
                    return true;
                }
            }
        }
        depa[source] = timer;
        timer++;
        res.add(source);
        return false;
    }

}
```