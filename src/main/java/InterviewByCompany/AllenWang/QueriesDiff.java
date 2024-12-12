package InterviewByCompany.AllenWang;

import java.util.*;

public class QueriesDiff {
    public static int[] processQueries(String[] queries, int diff) {
        Map<Integer, Integer> map = new HashMap<>(); // Store number frequencies
        List<Integer> result = new ArrayList<>();
        int count = 0; // Tracks pairs with the given difference

        for (String query : queries) {
            char operation = query.charAt(0); // + or -
            int num = Integer.parseInt(query.substring(1)); // Extract the number

            if (operation == '+') {
                // Add operation
                count += map.getOrDefault(num - diff, 0); // Count pairs (num, num-diff)
                count += map.getOrDefault(num + diff, 0); // Count pairs (num, num+diff)

                map.put(num, map.getOrDefault(num, 0) + 1); // Add num to map
            } else if (operation == '-') {
                // Remove operation
                count -= map.getOrDefault(num - diff, 0); // Reduce pairs (num, num-diff)
                count -= map.getOrDefault(num + diff, 0); // Reduce pairs (num, num+diff)

                // Update map
                if (map.get(num) == 1) {
                    map.remove(num); // Remove num completely if its frequency becomes 0
                } else {
                    map.put(num, map.get(num) - 1); // Decrease the frequency of num
                }
            }

            result.add(count); // Add current pair count to the result
        }

        return result.stream().mapToInt(i -> i).toArray(); // Convert list to array
    }

    public static void main(String[] args) {
        String[] queries1 = {"+3", "+4", "+5", "-3"};
        int diff1 = 1;
        System.out.println(Arrays.toString(processQueries(queries1, diff1))); // Output: [0, 1, 2, 1]

        String[] queries2 = {"+2", "+2", "+4", "+3", "-2"};
        int diff2 = 1;
        System.out.println(Arrays.toString(processQueries(queries2, diff2))); // Output: [0, 0, 0, 3, 2]
    }
}
