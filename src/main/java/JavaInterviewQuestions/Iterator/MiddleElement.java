package JavaInterviewQuestions.Iterator;

import java.util.List;
import java.util.ListIterator;

public class MiddleElement {

    public static <T> T findMiddleElement(List<T> list) {
        if (list.isEmpty()) {
            return null; // Handle the case when the list is empty
        }

        ListIterator<T> slowIterator = list.listIterator();
        ListIterator<T> fastIterator = list.listIterator();

        // Move the fast iterator two steps at a time and the slow iterator one step at a time
        while (fastIterator.hasNext() && fastIterator.nextIndex() < list.size() - 1) {
            fastIterator.next();
            if (fastIterator.hasNext()) {
                fastIterator.next();
            }
            slowIterator.next();
        }

        return slowIterator.next();
    }
}
