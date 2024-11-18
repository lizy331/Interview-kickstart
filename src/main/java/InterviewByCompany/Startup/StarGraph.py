from collections import defaultdict
import heapq

def bestSumKStar(g_nodes, g_from, g_to, values, k):
    # Step 1: Build the adjacency list
    graph = defaultdict(list)
    for u, v in zip(g_from, g_to):
        # Adjust indices to 0-based indexing
        graph[u - 1].append(v - 1)
        graph[v - 1].append(u - 1)

    max_sum = float('-inf')  # Initialize maximum sum to negative infinity

    # Step 2: Iterate over all nodes to calculate the maximum k-star sum
    for node in range(g_nodes):
        # Collect values of all neighbors
        neighbor_values = [values[neighbor] for neighbor in graph[node] if values[neighbor] >= 0]
        
        # If there are fewer than k neighbors, take all non-negative neighbors
        # Otherwise, take the top k largest values
        top_k_values = heapq.nlargest(k, neighbor_values)
        
        # Calculate the total sum: center node value + top k neighbor values
        current_sum = values[node] + sum(top_k_values)
        
        # Update max_sum
        max_sum = max(max_sum, current_sum)

    return max_sum


# g_nodes = 5
# g_from = [3, 3, 3, 3]
# g_to = [1, 2, 4, 5]
# values = [10, 20, 30, 40, 50]
# k = 2

# g_nodes = 7
# g_from = [0,1,1,3,3,3]
# g_to = [1, 2, 3, 4, 5,6]
# values = [1,2,3,4,10,-10,-20]
# k = 2

g_nodes = 6
g_from = [0,1,1,3,3,4]
g_to = [1, 2, 3, 4, 5, 1]
values = [-3,-2,-3,-4,-5,-6]
k = 3



print(bestSumKStar(g_nodes, g_from, g_to, values, k))  # Output:
