package CodingGuru.MonotonicQueuePattern;

import java.util.*;

public class MinNumberOfCoinForFruits {

    public int minimumCoins(int[] prices) {
        int n = prices.length;
        Deque<Integer> deque = new LinkedList<>();
        deque.addLast(n);
        prices = Arrays.copyOf(prices, n + 1);
        prices[n] = 0;  // Add a dummy fruit with 0 coins to avoid boundary issues.

        // Iterate from the last fruit to the first
        for (int i = n - 1; i >= 0; i--) {
            // Calculate the maximum index that can be covered by the current fruit
            int maxCoveredIndex = Math.min(n, 2 * i + 2);

            // Remove all elements from the front that are outside the range of current fruit's reward
            while (!deque.isEmpty() && deque.peekFirst() > maxCoveredIndex) {
                deque.pollFirst();
            }

            // Update the cost of the current fruit by adding the minimum cost of reachable fruits
            prices[i] += prices[deque.peekFirst()];

            // Maintain the deque to keep it increasing
            while (!deque.isEmpty() && prices[i] < prices[deque.peekLast()]) {
                deque.pollLast();
            }

            // Add the current fruit index to the deque
            deque.addLast(i);
        }

        // Return the minimum cost to get all fruits starting from the first one
        return prices[0];
    }
}
