package HackerRank;

class test {
    public static void main(String[] args) {
        String str1 = "0011110";
        String str2 = "1111000";
        System.out.println(getMaximumXor(str1,str2));
    }


    public static String getMaximumXor(String str1, String str2){
        StringBuilder sb=new StringBuilder();
        int numberOfOne=0;
        int numberOfZero=0;
        for(int i=0;i<str2.length();i++){
            if(str2.charAt(i)=='1') {
                numberOfOne++;
            }else if(str2.charAt(i)=='0'){
                numberOfZero++;
            }
        }

        char[] str1arr=str1.toCharArray();
        for(int j=0;j<str1arr.length;j++){
            System.out.println(sb);
            if(str1arr[j]=='1' && numberOfZero>0){
                sb.append(1);
                numberOfZero--;
            }else if(numberOfOne>0 && str1arr[j]=='0'){
                sb.append(1);
                numberOfOne--;
            }else{
                sb.append(0);
            }
        }

        return sb.toString();
    }


}