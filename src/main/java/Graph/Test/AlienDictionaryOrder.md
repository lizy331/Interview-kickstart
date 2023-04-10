这道题给了我们一个 字符串数组，其中每个字符串的顺序是按照外星人的字典顺序排列的，（假设外星人也使用26个字母）我们要做的是通过这个外星人的字符串数组，找出包含所有字母的从小到大的 string（也就是生成外星人字典）

Examples:

```text
Example One
{
"words": ["baa", "abcd", "abca", "cab", "cad"]
}
Output:

"bdac"
Example Two
{
"words": ["caa", "aaa", "aab"]
}
Output:

"cab"
```

```text
        // we want to find something like a-b-c-d-e...
        // where we can found from words array is 
        // t<f because "wrt" is ahead of "wrf"
        // w<e because "wr..." is ahead of "e..."
        // r<t becuase "er" is ahead of "et..."
        // e<r becuase "er" and "e..." is ahead of "r..."
        
        // based on these relations, we can build a direct graph, 
        // where w->e indicating "w" next is "e"
        // r->t, t->f, e->r
        // one can form a map, key is the char and value is a set, 
        // indicsting a char's next are in the set
        // we now know a char's next are what, but what we need is the closest next
        // like a's next could be b,c,d,e, but we need "b", since it is the closest
        
        // thus we need a indegree of each character, if a char's indegree is 0
        // that means this char is the currently closest next char
        // why? because for example: A's indegree is how many char pointing to this A
        // if A's indegree is 0, which means no char's next is A, 
        // that makes A to be the next closest in the dictinoary
        
        // once we have these two maps, we can use BFS to form the dictionary
        // why BSF? because each char's next is a set, 
        // within the set we need to find which char's indegree is 0, then adds to the res
```

```java
class Solution{

    static String find_order(ArrayList<String> words) {
        // Write your code here.
        Map<Character,HashSet<Character>> next = new HashMap<>();
        Map<Character,Integer> indegree = new HashMap<>();
        //initiate the indegree map
        for(String word : words){
            for(char c : word.toCharArray()){
                if(indegree.containsKey(c))continue;
                indegree.put(c,0);
            }
        }

        for(int i = 0;i<words.size()-1;i++){
            String s = words.get(i);
            String t = words.get(i+1);
            if(s.length()>t.length() && s.substring(0,t.length()).equals(t)){return "";}
            for(int j = 0;j<Math.min(s.length(),t.length());j++){
                if(s.charAt(j)-t.charAt(j)==0)continue;
                next.putIfAbsent(s.charAt(j),new HashSet<Character>());
                // *** 只有可以添加入 set 中时才增加 indegree
                if(next.get(s.charAt(j)).add(t.charAt(j))){
                    indegree.put(t.charAt(j),indegree.get(t.charAt(j))+1);
                }
                break;
            }
        }
        // System.out.println(next);
        // System.out.println(indegree);
        Deque<Character> dq = new ArrayDeque();
        for(Character key : indegree.keySet()){
            if(indegree.get(key).equals(0)){
                dq.offer(key);
            }
        }

        StringBuilder res = new StringBuilder();
        while(!dq.isEmpty()){
            char c = dq.poll();
            res.append(c);
            if(next.containsKey(c)){
                for(Character des : next.get(c)){
                    indegree.put(des,indegree.get(des)-1);
                    if(indegree.get(des)==0){
                        dq.offer(des);
                    }
                }
            }

        }
        if(res.length()-indegree.size()!=0){
            return "";
        }else{
            return res.toString();
        }
        // return sb.toString();
    }

}
```
