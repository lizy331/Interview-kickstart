package HackerRank;

public class demo {
    public static String solution(String s, String t){
        StringBuilder res = new StringBuilder();
        int countZeroS = 0;
        int countOneT = 0;
        for(int i = 0;i<s.length();i++){
            if(s.charAt(i)-'0'==0){
                countZeroS++;
            }
            if(t.charAt(i)-'1'==0){
                countOneT++;
            }
        }

        if(countOneT<countZeroS){
            // fill the zeros
            for(int i = 0;i<s.length();i++){
                if(s.charAt(i)-'0'==0 && countOneT>0){
                    countOneT--;
                    res.append('1');
                }else{
                    if(s.charAt(i)-'1'==0){
                        res.append("1");
                    }else{
                    }
                }
            }
        }else{
            // fill the ones from left, and decide which one to be canceled
            for(int i = 0;i<s.length();i++){
                if(countOneT>0){
                    countOneT--;
                    res.append('1');
                }else{
                    if(s.charAt(i)-'1'==0){
                        res.append("1");
                    }else{
                        res.append("0");
                    }
                }
            }

        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "0011110";
        String t = "1111000";
        System.out.println(solution(s,t));
    }
}
