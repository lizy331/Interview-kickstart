package String;

import java.util.*;
import java.util.stream.Collectors;

public class AmazonOA {


    public static int findReviewScore(String review, List<String> prohibitedWords) {
        review = review.toLowerCase();
        // Convert prohibited words to lower case for case-insensitivity
        List<String> lowerCaseProhibitedWords = prohibitedWords.stream()
                .map(String::toLowerCase)
                .collect(Collectors.toList());

        int maxLength = 0;
        for (int i = 0; i < review.length(); i++) {
            for (int j = i; j <= review.length(); j++) {
                String substring = review.substring(i, j);
                if (containsProhibitedWord(substring, lowerCaseProhibitedWords)) {
                    break;
                }
                maxLength = Math.max(maxLength, substring.length());
            }
        }
        return maxLength;
    }

    private static boolean containsProhibitedWord(String substring, List<String> prohibitedWords) {
        for (String word : prohibitedWords) {
            if (substring.contains(word)) {
                return true;
            }
        }
        return false;


    }


//    public static List<Integer> findTrucksForItems(List<Integer> trucks, List<Integer> items) {
//        List<Integer> result = new ArrayList<>();
//        for (int item : items) {
//            result.add(findSuitableTruck(trucks, item));
//        }
//        return result;
//    }
//
//    private static int findSuitableTruck(List<Integer> trucks, int itemWeight) {
//        int minTruckCapacity = Integer.MAX_VALUE;
//        int minTruckIndex = -1;
//
//        for (int i = 0; i < trucks.size(); i++) {
//            if (trucks.get(i) > itemWeight && trucks.get(i) < minTruckCapacity) {
//                minTruckCapacity = trucks.get(i);
//                minTruckIndex = i;
//            }
//        }
//
//        return minTruckIndex;
//    }


    public static List<Integer> findTrucksForItems(List<Integer> trucks, List<Integer> items) {
        // Map to store the minimum index for each unique truck capacity
        TreeMap<Integer, Integer> capacityToIndexMap = new TreeMap<>();

        for (int i = 0; i < trucks.size(); i++) {
            int capacity = trucks.get(i);
            capacityToIndexMap.putIfAbsent(capacity, i);
            if (i < capacityToIndexMap.get(capacity)) {
                capacityToIndexMap.put(capacity, i);
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int item : items) {
            result.add(findSuitableTruck(capacityToIndexMap, item));
        }
        return result;
    }

    private static int findSuitableTruck(TreeMap<Integer, Integer> capacityToIndexMap, int itemWeight) {
        // Find the least key strictly greater than the given key
        Map.Entry<Integer, Integer> suitableEntry = capacityToIndexMap.higherEntry(itemWeight);
        if (suitableEntry != null) {
            return suitableEntry.getValue();
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println("case 0");
//        List<Integer> trucks = new ArrayList<>(List.of(5, 3, 8, 1));
//        List<Integer> items = new ArrayList<>(List.of(6, 10));
//        List<Integer> result = findTrucksForItems(trucks, items);
//        for (int index : result) {
//            System.out.println(index);
//        }

        System.out.println("case 1");
        List<Integer> trucks = new ArrayList<>(List.of(1, 3, 5, 2, 3, 2));
        List<Integer> items = new ArrayList<>(List.of(1, 2, 3));
        List<Integer> result = findTrucksForItems(trucks, items);

        for (int index : result) {
            System.out.println(index);
        }
    }

}
