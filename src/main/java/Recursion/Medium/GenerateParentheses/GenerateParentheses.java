package Recursion.Medium.GenerateParentheses;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

class Solution1 {
    List<String> res;

    public List<String> generateParenthesis(int n) {
        res = new ArrayList<String>();
        Deque<Character> dq = new ArrayDeque() ;
        helper(n,new StringBuilder(), dq);
        return res;

    }


    public void helper(Integer n, StringBuilder sb, Deque<Character> dq){
        if(sb.length()-2*n==0 && dq.isEmpty()){
            res.add(new String(sb));
            return;
        }

        if(sb.length()>2*n){return;}


        if(dq.size()>n){return;}

        sb.append('(');
        dq.offer('(');
        helper(n,sb,dq);
        sb.deleteCharAt(sb.length()-1);
        dq.poll();

        sb.append(')');
        if(!dq.isEmpty() && dq.peek()-'('==0){
            dq.poll();
            helper(n,sb,dq);
            dq.offer('(');
        }
        sb.deleteCharAt(sb.length()-1);

        return;
    }
}

class Solution2 {
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<String>();
        backtracking(ans,"",0,0,n);
        return ans;

    }
    private void backtracking(List<String> ans, String cur ,int open, int close, int max ){
        if (cur.length() == max*2){
            ans.add(cur);
            return;
        }
        if (open < max){
            backtracking(ans, cur + "(", open+1, close, max);
        }
        if (close<open){
            backtracking(ans, cur + ")", open, close+1, max);
        }
    }
}
