package InterviewByCompany.Oracle;


import java.util.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

public class WordFrequencyTracker {
    // Map to store word counts per time
    private final Map<Integer, Map<String, Integer>> record = new HashMap<>();

    /**
     * Records a word spoken at a given time.
     *
     * @param word The word spoken.
     * @param time The timestamp when the word was spoken.
     */
    public void recordWord(String word, int time) {
        // Retrieve the frequency map for the given time, or create a new one if it doesn't exist

        if(record.containsKey(time)) {
            Map<String, Integer> freqMap = record.get(time);
            if (freqMap.containsKey(word)) {
                freqMap.put(word, freqMap.get(word) + 1);
            } else {
                freqMap.put(word, 1);
            }

        }else{
            Map<String, Integer> freqMap = new HashMap<>();
            freqMap.put(word,1);
            record.put(time,freqMap);
        }
        // Map<String, Integer> freqMap = record.getOrDefault(time, new HashMap<>());

        // Update the count for the word
        // freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);

        // Put the updated frequency map back into the record
        // record.put(time, freqMap);
    }

    /**
     * Retrieves the top 'top' most frequent words spoken from 'startTime' to the latest time.
     *
     * @param startTime The start time for the frequency aggregation.
     * @param top       The number of top frequent words to return.
     * @return A list of the top 'top' frequent words.
     */
    public List<String> topFreqWordsWithTime(int startTime, int endTime, int top) {
        // Aggregate word frequencies from startTime onwards

        Map<String, Integer> aggregateFreq = new HashMap<>();

        // T: n: timeframe
        for (int i = startTime; i <= endTime; i++) {
            if(record.containsKey(i)){
                Map<String,Integer> freqMap = record.get(i);
                for(String key : freqMap.keySet()){
                    int count = freqMap.get(key);
                    aggregateFreq.put(key,aggregateFreq.getOrDefault(key,0)+count);
                }
            }
        }

        // Use a priority queue to keep track of top 'top' words

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ?
                        a.getKey().compareTo(b.getKey()) :
                        b.getValue() - a.getValue()
        );

        // T: mlogm - m: aggregate map size
        // heapify：使得⼀个unsorted array变成⼀个堆。 时间复杂度O(n)  (wiki)
        // log(1) + log2 + log3 +logm = logm + logm-1 + logm-2 = logm^m = mlogm
        pq.addAll(aggregateFreq.entrySet());

        // Extract the top 'top' words
        // logm +log(m-1) + log(m-2)... = log((m)^k)
        // klogm - heap operation
        List<String> result = new ArrayList<>();
        for (int i = 0; i < top && !pq.isEmpty(); i++) {
            result.add(pq.poll().getKey());
        }

        return result;

        // T: n + mlogm + klogm
    }

    // Optional: Method to display the current record (for debugging purposes)
    public void displayRecord() {
        for (Map.Entry<Integer, Map<String, Integer>> entry : record.entrySet()) {
            int time = entry.getKey();
            Map<String, Integer> freqMap = entry.getValue();
            System.out.println("Time " + time + ": " + freqMap);
        }
    }

    public static String truncateToStartOfMinute(String timestamp) {
        try {
            // Parse the input timestamp to an Instant
            Instant instant = Instant.parse(timestamp);

            // Truncate the Instant to the start of the minute
            Instant truncatedInstant = instant.truncatedTo(ChronoUnit.MINUTES);

            // Format the truncated Instant back to ISO 8601 string
            return DateTimeFormatter.ISO_INSTANT.format(truncatedInstant);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid timestamp format. Please use ISO 8601 format.", e);
        }
    }

    // Example usage
    public static void main(String[] args) {
        WordFrequencyTracker tracker = new WordFrequencyTracker();

        // Recording some words
        tracker.recordWord("hello", 1);
        tracker.recordWord("world", 2);
        tracker.recordWord("hello", 2);
        tracker.recordWord("java", 3);
        tracker.recordWord("world", 3);
        tracker.recordWord("hello", 4);
        tracker.recordWord("chatgpt", 5);
        tracker.recordWord("hello", 5);
        tracker.recordWord("world", 5);

        // Display the record
        tracker.displayRecord();

        // Query top 2 words from time 2 onwards
        List<String> topWords = tracker.topFreqWordsWithTime(3, 5,2);
        System.out.println("Top 2 words from time 2 onwards: " + topWords);
    }
}
