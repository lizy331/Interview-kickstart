package String;

public class ReverseString {

    public static void main(String[] arg){
        String str = "ThisIsString";
        // output: gnirtSsIsihT
        char[] chars = str.toCharArray();
        int j = chars.length-1;
        for(int i = 0;i<chars.length;i++){
            if(i>=j){
                break;
            }
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            j--;
        }

        String out = new String(chars);
        System.out.println(out);
    }
}
