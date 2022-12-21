这道题给了一个字符串，其中包含了数字和字母，需要我们将所有的字母分别转化为 大小写，如果是数字则不变，返回相对应的字符串

###### Example One

{
"s": "a1z"
}

Output:

["A1Z", "A1z", "a1Z", "a1z"]

###### Example Two

{
"s": "123"
}

Output:

["123"]

这道题名字叫做 permutation 但实际上是一道组合题，也就是希望我们见所有的字母变成大写 和小写并返回

static ArrayList<String> res;
static ArrayList<String> letter_case_permutations(String s) {
// Write your code here.
res = new ArrayList<>();
helper(s,0,new StringBuilder());
return res;
}

    static void helper(String s, int start, StringBuilder sb){
        if(sb.length()-s.length()==0){
            res.add(new String(sb));
            return;
        }
        
        for(int i = start;i<s.length();i++){
            char c = s.charAt(i);
            if(Character.isLetter(c)){
                sb.append(Character.toLowerCase(c));
                helper(s,i+1,sb);
                sb.deleteCharAt(sb.length()-1);
                sb.append(Character.toUpperCase(c));
                helper(s,i+1,sb);
                sb.deleteCharAt(sb.length()-1);
            }else{
                sb.append(c);
                helper(s,i+1,sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
        return;
    }

**注意 使用 for loop 的时候注意 recursive call 要使用 i + 1，而并不是 start + 1，因为 start 是死的， i 可以进入下一个循环**

**时间复杂度 O(n*2^n)**

只需要考虑 worst case，worst case 就是 所有的位置都是字母，这样我们就相当于二叉树，每个 node 都有两种选择


**空间复杂度**（不考虑返回结果时使用的 res，也就是单纯的考虑 auxiliary space）
O(1)