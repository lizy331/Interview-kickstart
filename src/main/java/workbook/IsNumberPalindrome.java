package workbook;

public class IsNumberPalindrome {

    // 545  true
    // 80 false
    // 123 false
    // 134431 true
    // -1  false
    // 0 true

    public static boolean isPal(int num){

        if(num<0){return false;}

        int sum = 0;
        int ori = num;

        // sum = sum*10 + num%10
        // num /= 10

        while(num>0){
            sum = sum*10 + num%10;
            num/=10;
        }

        return num==ori ? true : false;
    }
}
