**##### Subsets With Duplicate Characters**

这道题 让我们寻找一个字符串的所有 subset，但是 这个 字符串中存在相同的字母，在所有的subset 中我们需要避免相同的组合出现

###### Example One

{
"s": "aab"
}

Output:

["", "a", "aa", "aab", "ab", "b"]

###### Example Two

{
"s": "dc"
}

Output:

["", "c", "cd", "d"]

这道题，和之前的求一个字符串的所有 subset相似，但是这里我们需要注意重复出现的字母，也就是遇见相同的字母需要 **跳针**



    static ArrayList<String> res;
    static ArrayList<String> get_distinct_subsets(String s) {
        // Write your code here.
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String str = new String(chars);
        helper(str,0,new StringBuilder());
        return res;
    }

    static void helper(String s, int start, StringBuilder sb ){
        if(start >= s.length()){
            res.add(new String(sb));
            return;
        }
        
        
        sb.append(s.charAt(start));
        helper(s,start+1,sb);
        sb.deleteCharAt(sb.length()-1);
        
        while(start < s.length()-1 && s.charAt(start)-s.charAt(start+1)==0){
            start++;
        }
        
        helper(s,start+1,sb);
        
        return;
    }`

以上的写法在 helper function 中没有使用 for loop 更加容易理解，但是调用 helper function 的次数会比使用 for 循环的次数更多
这是因为，在我们 back propagate（也就是删除 String Builder 添加的最后一个字母之后）我们还需要调用一次 helper function

    static ArrayList<String> res;
    static ArrayList<String> get_distinct_subsets(String s) {
        // Write your code here.
        res = new ArrayList<>();
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        String str = new String(chars);
        helper(str,0,new StringBuilder());
        return res;
    }
    
    static void helper(String s, int start, StringBuilder sb ){
        res.add(new String(sb));
        if(start >= s.length()){
            return;
        }
        
        for(int i = start; i<s.length();i++){
            sb.append(s.charAt(i));
            helper(s,i+1,sb);
            sb.deleteCharAt(sb.length()-1);
            while(i < s.length()-1 && s.charAt(i)-s.charAt(i+1)==0){
                i++;
            }
        }
        
        
        return;
    }

以上写法是使用 for loop 在 helper function 中，注意由于使用 for 循环，start 有可能不能达到 string length，所以我们无论如何都要进行 对 sb 的添加
**跳针的操作应该在 for loop 之内，并且要在 string builder  移除最后添加的字母之后进行**