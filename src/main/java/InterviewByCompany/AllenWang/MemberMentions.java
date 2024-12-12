package InterviewByCompany.AllenWang;

import java.util.*;

/**
 Input
 String[] members
 String[] messages



 output

 String[], “[user Id]=[mentions count]”

 Sorted by mention count in descending order, if tie sorted by “user id” in lexicographically ASC order


 Test case:

 Count number of message of each member that are mentioned

 multiple  mentions for same member count as one in each message

 “@” can display single in the message,

 “Hey @user123, @user123” -> count as 1


 Solution must better than
 O(members.length * messages.length * max(messages[i]).length)


 members = [“id123” ,”id234”, “id7”, “id321”]

 messages  = [

 “Hey, @id123,id321 please help @id123”,
 “hey , @id7 go@d job @id123”,
 “@id321, got it!”
 ]

 result

 [“id123=2”,”id7=1”, “id321=1”]

 */

public class MemberMentions {
    public static String[] countMentions(String[] members, String[] messages) {
        // Step 1: Add members to a set for fast lookup
        Set<String> memberSet = new HashSet<>(Arrays.asList(members));

        // Step 2: Count mentions using a map
        Map<String, Integer> mentionCount = new HashMap<>();
        for (String member : members) {
            mentionCount.put(member, 0);
        }

        for (String message : messages) {
            // Step 3: Extract mentions from the message
            Set<String> uniqueMentions = new HashSet<>();
            String[] tokens = message.split("[^@\\w]+");

            for (String token : tokens) {
                if (token.startsWith("@")) {
                    String userId = token.substring(1);
                    if (memberSet.contains(userId)) {
                        uniqueMentions.add(userId);
                    }
                }
            }

            // Step 4: Update the mention count for each unique mention
            for (String userId : uniqueMentions) {
                mentionCount.put(userId, mentionCount.get(userId) + 1);
            }
        }

        // Step 5: Sort the results by mention count and lexicographical order
        List<String> result = new ArrayList<>();
        mentionCount.entrySet().stream()
                .sorted((a, b) -> {
                    if (!a.getValue().equals(b.getValue())) {
                        return b.getValue() - a.getValue(); // Descending order of count
                    }
                    return a.getKey().compareTo(b.getKey()); // Ascending order of user ID
                })
                .forEach(entry -> result.add(entry.getKey() + "=" + entry.getValue()));

        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String[] members = {"id123", "id234", "id7", "id321"};
        String[] messages = {
                "Hey, @id123,id321 please help @id123",
                "hey , @id7 go@d job @id123",
                "@id321, got it!"
        };

        String[] result = countMentions(members, messages);
        System.out.println(Arrays.toString(result));
    }
}
