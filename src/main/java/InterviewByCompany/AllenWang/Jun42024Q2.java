package InterviewByCompany.AllenWang;

public class Jun42024Q2 {
    public static int solution(int[] A) {
        int max_sum = Integer.MIN_VALUE; // Initialize max_sum to the smallest possible value
        int current_sum = 0; // Initialize current_sum to 0
        boolean hasNonNegative = false; // Flag to check if there is any non-negative number in the array

        for (int i = 0; i < A.length; i++) {
            if (A[i] >= 0) {
                current_sum += A[i]; // Add to current_sum if the element is non-negative
                hasNonNegative = true; // Set flag to true as we have found a non-negative number
            } else {
                if (current_sum > max_sum) {
                    max_sum = current_sum; // Update max_sum if current_sum is greater
                }
                current_sum = 0; // Reset current_sum
            }
        }

        // Final check for the last slice
        if (current_sum > max_sum) {
            max_sum = current_sum;
        }

        return hasNonNegative ? max_sum : -1; // Return -1 if no non-negative slice was found
    }

    public static void main(String[] args) {
        // Test case 1: Array with all non-negative numbers
        int[] test1 = {1, 2, 3, 4, 5};
        // Expected calculation: 1 + 2 + 3 + 4 + 5 = 15
        System.out.println("Test 1: " + solution(test1)); // Expected: 15

        // Test case 2: Array with all negative numbers
        int[] test2 = {-1, -2, -3, -4, -5};
        // Expected calculation: No non-negative slices, so result is -1
        System.out.println("Test 2: " + solution(test2)); // Expected: -1

        // Test case 3: Array with a mix of negative and non-negative numbers
        int[] test3 = {1, 2, -3, 4, 5, -6};
        // Expected calculation:
        // Slices: (0, 1) -> 1 + 2 = 3
        //         (3, 4) -> 4 + 5 = 9
        // Maximum sum is 9
        System.out.println("Test 3: " + solution(test3)); // Expected: 9

        // Test case 4: Array with no non-negative slices
        int[] test4 = {-1, -2, -3, -4, -5};
        // Expected calculation: No non-negative slices, so result is -1
        System.out.println("Test 4: " + solution(test4)); // Expected: -1

        // Test case 5: Array with only one element
        int[] test5 = {5};
        // Expected calculation: Single element slice (0, 0) -> 5
        System.out.println("Test 5: " + solution(test5)); // Expected: 5

        int[] test6 = {-5};
        // Expected calculation: No non-negative slices, so result is -1
        System.out.println("Test 6: " + solution(test6)); // Expected: -1

        // Test case 7: Array with alternating negative and non-negative numbers
        int[] test7 = {1, -2, 3, -4, 5, -6, 7, -8, 9};
        // Expected calculation:
        // Slices: (0, 0) -> 1
        //         (2, 2) -> 3
        //         (4, 4) -> 5
        //         (6, 6) -> 7
        //         (8, 8) -> 9
        // Maximum sum is 9
        System.out.println("Test 7: " + solution(test7)); // Expected: 9

        // Additional test cases
        int[] test8 = {1, 2, 3, -4, 5, 6, 7, -8, 9, 10};
        // Expected calculation:
        // Slices: (0, 2) -> 1 + 2 + 3 = 6
        //         (4, 6) -> 5 + 6 + 7 = 18
        //         (8, 9) -> 9 + 10 = 19
        // Maximum sum is 19
        System.out.println("Test 8: " + solution(test8)); // Expected: 19

        int[] test9 = {-1, 2, 3, -4, 5, 6, -7, 8, 9};
        // Expected calculation:
        // Slices: (1, 2) -> 2 + 3 = 5
        //         (4, 5) -> 5 + 6 = 11
        //         (7, 8) -> 8 + 9 = 17
        // Maximum sum is 17
        System.out.println("Test 9: " + solution(test9)); // Expected: 17

        int[] test10 = {0, 0, 0, 0, 0};
        // Expected calculation: Entire array slice (0, 4) -> 0 + 0 + 0 + 0 + 0 = 0
        System.out.println("Test 10: " + solution(test10)); // Expected: 0
    }
}
