package DynamicProgramming.ClassProblem;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

// Given a string and a dictionary of words,
// check whether the given string can be broken down into a space-separated sequence of one or more dictionary words.
//
//        Example One
//        {
//        "s": "helloworldhello",
//        "words_dictionary": ["world", "hello", "faang"]
//        }

//        Output:
//
//        1

// helloworldhello can be broken down as hello world hello.

public class WordBreak {

        public boolean wordBreak(String s, List<String> wordDict) {
            Set<String> wordset = new HashSet<String>(wordDict);
            // 其中 dp[i] 表示范围 [0, i) 内的子串是否可以拆分
            boolean[] dp = new boolean[s.length()+1];
            dp[0] = true;
            // 使用 i 遍历字符串，也就是不停的添加 字符
            // 使用 j 从最左边开始遍历，我们判断 substring(j,i) 是否是一个 valid string
            // 如果 substring(j,i) 是一个 valid string，并且 dp[j] 为 true （说明在 j位置上 之前的 string 也可以划分）
            // 那么 我们就可以在 dp[i] 位置上更新为 true
            for (int i = 1;i<=s.length();i++){
                for (int j = 0;j<i;j++){
                    // System.out.println(Arrays.toString(dp));
                    // System.out.println("j: " + j + " i: " + i);
                    // System.out.println(s.substring(j,i));
                    // System.out.println(" ");
                    if(dp[j] && wordset.contains(s.substring(j,i))){
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }

    public static void main(String[] args) {

    }
    }

// dp:
//  t       t
// "l e e t c o d e "
//            i
//  j

// wordDict = ["leet","code"]

// j: 0 i: 1
// l

// j: 0 i: 2
// le

// j: 1 i: 2
// e

// j: 0 i: 3
// lee

// j: 1 i: 3
// ee

// j: 2 i: 3
// e

// j: 0 i: 4
// leet

// j: 0 i: 5
// leetc

// j: 1 i: 5
// eetc

// j: 2 i: 5
// etc

// j: 3 i: 5
// tc

// j: 4 i: 5
// c

// j: 0 i: 6
// leetco

// j: 1 i: 6
// eetco

// j: 2 i: 6
// etco

// j: 3 i: 6
// tco

// j: 4 i: 6
// co

// j: 5 i: 6
// o

// j: 0 i: 7
// leetcod

// j: 1 i: 7
// eetcod

// j: 2 i: 7
// etcod

// j: 3 i: 7
// tcod

// j: 4 i: 7
// cod

// j: 5 i: 7
// od

// j: 6 i: 7
// d

// j: 0 i: 8
// leetcode

// j: 1 i: 8
// eetcode

// j: 2 i: 8
// etcode

// j: 3 i: 8
// tcode

// j: 4 i: 8
// code
