def minimumSteps(loggedMoves):
    # Initialize a stack to simulate the directory structure
    stack = []

    for move in loggedMoves:
        if move == "../":  # Move to parent directory
            if stack:  # Only pop if stack is not empty (not at root)
                stack.pop()
        elif move == "./":  # Remain in the same directory
            continue
        else:  # Move to a child directory
            stack.append(move)

    # The size of the stack indicates the number of directories to go back to root
    return len(stack)



# Example usage
loggedMoves = ["x/", "../", "y/", "z/"]
print(minimumSteps(loggedMoves))  # Output: 2
