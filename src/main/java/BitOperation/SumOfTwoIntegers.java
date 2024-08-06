package BitOperation;

public class SumOfTwoIntegers {
        public static int getSum(int a, int b) {

            while(b!=0){
                int answer = a ^ b;
                int carry =  (a & b) << 1;
                a = answer;
                b = carry;

            }
            return a;
    }

    public static void main(String[] args) {
        System.out.println(getSum(3,5));
    }

    /*

     a & b 进行按位与操作（bitwise AND），计算两个数在每个位上的共同1
     (a & b) << 1 将结果左移一位（left shift），得到进位。


     也就是说 a^b 操作相当于在 对两个二进制数字进行加减
     a&b 然后左移相当于在计算 carry

     然后我们将上一轮 a^b 得到之后的
     结果再接着和 carry 进行 ^ 操作 (相当于 二进制相加)


     */
}
