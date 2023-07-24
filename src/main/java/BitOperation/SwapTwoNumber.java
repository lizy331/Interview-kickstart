package BitOperation;

public class SwapTwoNumber {

    public static void main(String[] args) {
        int a = 5;
        int b = 10;

        System.out.println("Before swapping: a = " + a + ", b = " + b);

        a = a ^ b; // XOR to get the bitwise result of a and b
        System.out.println(a);
        b = a ^ b; // XOR with b to get the original value of a
        System.out.println(b);
        a = a ^ b; // XOR with a to get the original value of b
        System.out.println(a);

        System.out.println("After swapping: a = " + a + ", b = " + b);
    }

//step 1.
//    a = 0101
//    b = 1010
//    ---------
//a = a ^ b = 1111 = 15

//    Then, when you perform b = a ^ b, b will be updated to the original value of a since (a ^ b) ^ b is equal to a.

//step 2.
//    b = 1010
//    a = 1111
//    --------
//    b = a ^ b = 0101 = original "a"

//step 3.
//     a = 1111
//     b = 0101
//    ----------
//    a = a ^ b = 1010 = original "b"
}
