package InterviewByCompany.Startup;

public class FrogJump {

    public int solution(int[] blocks) {
        int n = blocks.length;

        // Arrays to store the maximum reach to the left and right for each block
        int[] leftReach = new int[n];
        int[] rightReach = new int[n];

        // Fill the leftReach array
        leftReach[0] = 0; // First block cannot go left
        for (int i = 1; i < n; i++) {
            if (blocks[i] <= blocks[i - 1]) { // Can jump to the left
                leftReach[i] = leftReach[i - 1] + 1;
            } else {
                leftReach[i] = 0;
            }
        }

        // Fill the rightReach array
        rightReach[n - 1] = 0; // Last block cannot go right
        for (int i = n - 2; i >= 0; i--) {
            if (blocks[i] <= blocks[i + 1]) { // Can jump to the right
                rightReach[i] = rightReach[i + 1] + 1;
            } else {
                rightReach[i] = 0;
            }
        }

        // Calculate the maximum possible distance
        int maxDistance = 0;
        for (int i = 0; i < n; i++) {
            int totalDistance = leftReach[i] + rightReach[i] + 1; // Include the block itself
            maxDistance = Math.max(maxDistance, totalDistance);
        }

        return maxDistance;
    }

    public static void main(String[] args) {
        FrogJump frogJump = new FrogJump();

        int[] test1 = new int[]{2, 6, 8, 5};
        int[] test2 = new int[]{1, 5, 5, 2, 6};
        int[] test3 = new int[]{1, 1};

        System.out.println(frogJump.solution(test1)); // Expected: 3
        System.out.println(frogJump.solution(test2)); // Expected: 4
        System.out.println(frogJump.solution(test3)); // Expected: 2
    }
}
