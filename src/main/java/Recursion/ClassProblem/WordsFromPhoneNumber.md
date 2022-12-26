Words From Phone Number

这道题让我们编写一个九宫格程序，返回我们所数组的数字所对应的所有可能的组合

###### Example One

{
"phone_number": "1234567"
}

Output:

[
"adgjmp",
"adgjmq",
"adgjmr",
"adgjms",
"adgjnp",
...
"cfilns",
"cfilop",
"cfiloq",
"cfilor",
"cfilos"
]

这道题 也是combination 的变种，不同点在于每一个位置上可选择的字母是固定的，之前求字符串所有的 subset 的时候我们总是添加当前字符串位置上的后一个字母
helper(i+1,s,sb)

但是这里我们对于每一个位置需要 使用 for loop 来循环所有可以选择的字母



    static ArrayList<String> res;
    static HashMap<Character,Character[]> map;
    static ArrayList<String> get_words_from_phone_number(String phone_number) {
        // Write your code here.
        res = new ArrayList<>();
        map = new HashMap<>();
        
        // map.put('1',new Character[]);
        map.put('2',new Character[]{'a','b','c'});
        map.put('3',new Character[]{'d','e','f'});
        map.put('4',new Character[]{'g','h','i'});
        map.put('5',new Character[]{'j','k','l'});
        map.put('6',new Character[]{'m','n','o'});
        map.put('7',new Character[]{'p','q','r','s'});
        map.put('8',new Character[]{'t','u','v'});
        map.put('9',new Character[]{'w','x','y','z'});
        helper(phone_number.replace("1","").replace("0",""),0,new StringBuilder());
        return res;
    }
    
    static void helper(String s,int start, StringBuilder sb){
        // System.out.println(sb);
        if(sb.length()-s.length()==0){
            res.add(new String(sb));
            return;
        }
        
        for(int i = start;i<s.length();i++){
            if(s.charAt(i)-'1'==0){continue;}
            for(char c : map.get(s.charAt(i))){
                sb.append(c);
                helper(s,i+1,sb);
                sb.deleteCharAt(sb.length()-1);
            }
        }
        
        return;
    }


第 63 行就是我们进行每一个数字所对应的字母的 for loop，在每一次loop 中都进行 增删的操作，这样对于每一个数字最多会进行 4次 loop，因为按键最多可以对应4个字母

###### 时间复杂度 O(n*4^n)

最坏的情况是 所有的数字对应的字母数都是四个, n 是用来 deep copy 字符串的时间

###### 额外空间复杂度 O(n)

partial solution 所使用的空间
