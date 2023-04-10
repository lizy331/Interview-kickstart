这道题就是 Word Ladder II，给了我们一个 word list，和一个 start word 和一个 stop word，问我们从 start word 不断变换一个字母最终

成为 stop word 的最短距离，注意 可以变换的 word 必须存在于 word list 中，或者 直接变换成为 stop word

如果我们把每个 string 都看出一个 vertex，那么 这个 vertex 的 neighbor 的获取方法有一些讲究

```text
1. Visit every string in words array and check. There are no_of_words strings in words array and each has length length. So, for one string to find neighbour strings, time taken will be O(no_of_words * length). And we will find neighbours for O(no_of_words) strings, hence time complexity of this solution will be O(no_of_words2 * length). When no_of_words is big, this solution will time out.

2. For current string we will generate all possible strings having different character at exactly one position, and we will update strings that are in words array i.e. they are neighbours. We can use hashmap to check if any string is in words array or not in O(length) time, instead of O(no_of_words * length) time using simple array search.

Now there can be O(26 * length) different strings having different character at exactly one position. And for each string we will spend O(length) time to check if it is in words array or not. We will find neighbours for O(no_of_words) strings, hence time complexity of this solution will be O(no_of_words * length2 * 26). Now when string length is high, this solution will time out.

So, we can combine both methods in one solution to bring down time complexity to O((no_of_words * length * min((no_of_words, 26 * length)). When no_of_words <= 26 * length use first method and when no_of_words > 26 * length use the second method!
```

也就是说 当每一个 word length 非常长的时候，我们就使用第一种方法

当 word list 中 word 的数量非常多的时候 我们使用第二中方法

```java
class Solution{

    static ArrayList<String> string_transformation(ArrayList<String> words, String start, String stop) {
        // Write your code here.
        //1. Create Adj-List
        //2. BFS
        //3. Outer loop

        Set<String> dict = new HashSet<>(words);
        dict.remove(start);
        dict.remove(stop);
        if(start.equals(stop) && words.size() == 0){
            return new ArrayList<>(Arrays.asList("-1"));
        }
        if(wordDifferbyOneChar(start,stop) && words.size() == 0){
            return new ArrayList<>(Arrays.asList(start, stop));
        }
        return bfs(dict, start, stop);

    }

    static ArrayList<String> createAns(String stop, String curr, Map<String, String> parent){
        ArrayList<String> ans = new ArrayList<>();
        ans.add(stop);
        while(curr != null){
            ans.add(curr);
            curr = parent.get(curr);
        }
        Collections.reverse(ans);
        return ans;
    }

    static ArrayList<String> bfs(Set<String> dict, String start, String stop){
        Queue<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        Map<String, String> parent = new HashMap<>();

        q.offer(start);
        visited.add(start);

        while(!q.isEmpty()){
            String currWord = q.poll();
            Set<String> neighbors = new HashSet<>();
            if(currWord.length()*26 < dict.size()){
                neighbors = getNeighbors2(dict, currWord, stop);
            }
            else{
                neighbors = getNeighbors1(dict, currWord, stop);
            }

            for(String n : neighbors){
                if(n.equals(stop)){
                    return createAns(stop, currWord, parent);
                }
                if(!visited.contains(n)){
                    visited.add(n);
                    parent.put(n, currWord);
                    q.offer(n);
                }
            }
        }
        return new ArrayList<String>(Arrays.asList("-1"));
    }


    static Set<String> getNeighbors1(Set<String> dict, String currWord, String stop){
        Set<String> neighbors = new HashSet<>();
        if(wordDifferbyOneChar(currWord, stop)){
            neighbors.add(stop);
        }
        for(String w: dict){
            if(wordDifferbyOneChar(w, currWord)){
                neighbors.add(w);
            }
        }
        return neighbors;
    }

    static Set<String> getNeighbors2(Set<String> dict, String currWord, String stop){
        Set<String> neighbors = new HashSet<>();
        for(int i = 0; i< currWord.length(); i++){
            char[] candidate = currWord.toCharArray();
            char orig = candidate[i];
            for(char ch = 'a' ; ch < 'z'; ch++){
                if(ch != orig){
                    candidate[i] = ch;
                    String candStr = String.valueOf(candidate);
                    if(dict.contains(candStr) || candStr.equals(stop)){
                        neighbors.add(candStr);
                    }
                }
            }
        }
        return neighbors;
    }

    static boolean wordDifferbyOneChar(String word1,String word2){
        int diff =0;
        int i =0;
        while(i < word1.length() && i < word2.length()){
            if(word1.charAt(i) != word2.charAt(i)){
                diff++;
            }
            if(diff > 1){
                return false;
            }
            i++;
        }
        return diff==1;
    }
}
```


注意 我们如何使用 BFS 并记录下来 path？我们知道 DFS 可以按照顺序记录下来 path

但是 BFS 不可以，所以我们必须使用一个 parent hashmap，来记录每一个 vertex 的parent，最后通过 从 stop word 往回寻找最后得到 path