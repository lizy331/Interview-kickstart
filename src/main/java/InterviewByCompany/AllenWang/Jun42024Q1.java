package InterviewByCompany.AllenWang;

import java.util.HashMap;
import java.util.Map;

public class Jun42024Q1 {

    public static int solution(String[] A, String S) {
        // Count available ingredients in S
        Map<Character, Integer> availableIngredients = new HashMap<>();
        for (char c : S.toCharArray()) {
            availableIngredients.put(c, availableIngredients.getOrDefault(c, 0) + 1);
        }

        int validRecipes = 0;

        // Check each recipe
        for (String recipe : A) {
            // Create a copy of available ingredients for each recipe check
            Map<Character, Integer> ingredientsCopy = new HashMap<>(availableIngredients);
            if (canPrepareRecipe(recipe, ingredientsCopy)) {
                validRecipes++;
            }
        }

        return validRecipes;
    }

    private static boolean canPrepareRecipe(String recipe, Map<Character, Integer> availableIngredients) {
        // Count required ingredients for the current recipe
        Map<Character, Integer> requiredIngredients = new HashMap<>();
        for (char c : recipe.toCharArray()) {
            requiredIngredients.put(c, requiredIngredients.getOrDefault(c, 0) + 1);
        }

        // Check if we have enough ingredients
        for (Map.Entry<Character, Integer> entry : requiredIngredients.entrySet()) {
            char ingredient = entry.getKey();
            int requiredAmount = entry.getValue();
            int availableAmount = availableIngredients.getOrDefault(ingredient, 0);

            if (availableAmount < requiredAmount) {
                return false;
            }

            // Decrease the count of the available ingredient
            availableIngredients.put(ingredient, availableAmount - requiredAmount);
        }

        return true;
    }

    public static void main(String[] args) {

        // Test case 1
        String[] A1 = {"toast", "bread", "breada", "cheese"};
        String S1 = "abcdeeehrs";
        System.out.println("Test case 1: " + solution(A1, S1)); // Expected output: 2

        // Test case 2
        String[] A2 = {"az", "azz", "zza", "zzz", "zzzz"};
        String S2 = "azzz";
        System.out.println("Test case 2: " + solution(A2, S2)); // Expected output: 4

        // Test case 3
        String[] A3 = {"apple", "banana", "grape"};
        String S3 = "aabbnnaa";
        System.out.println("Test case 3: " + solution(A3, S3)); // Expected output: 1

        // Test case 4
        String[] A4 = {"pasta", "soup", "salad"};
        String S4 = "ppsssaaauuttd";
        System.out.println("Test case 4: " + solution(A4, S4)); // Expected output: 1

        // Test case 5
        String[] A5 = {"cookie", "cake", "muffin"};
        String S5 = "ooookkkaammmffffiii";
        System.out.println("Test case 5: " + solution(A5, S5)); // Expected output: 0

        // Test case 6
        String[] A6 = {"az", "azz", "azzz", "zzz", "zzzz"};
        String S6 = "azzz";
        System.out.println("Test case 6: " + solution(A6, S6)); // Expected output: 4

        // Test case 7
        String[] A7 = {"toast", "bread", "breada", "cheese"};
        String S7 = "abcdeeehrs";
        System.out.println("Test case 7: " + solution(A7, S7)); // Expected output: 4
    }
}
