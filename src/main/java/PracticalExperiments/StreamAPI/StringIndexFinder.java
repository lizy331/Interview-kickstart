package PracticalExperiments.StreamAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

public class StringIndexFinder {

    public static OptionalInt findStringIndex(List<String> strings, String targetString) {
        return IntStream.range(0, strings.size())
                .filter(i -> strings.get(i).equals(targetString))
                .findFirst();
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();

        strings.add("apple");
        strings.add("banana");
        strings.add("orange");
        strings.add("grape");
        String targetString = "orange";

        OptionalInt stringIndex = findStringIndex(strings, targetString);
        if (stringIndex.isPresent()) {
            System.out.println("String found at index: " + stringIndex.getAsInt());
        } else {
            System.out.println("String not found in the list");
        }
    }
}
