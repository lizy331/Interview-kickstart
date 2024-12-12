package InterviewByCompany.Oracle;

import java.util.*;

public class DesignHashMap {

    ////////////////////////////////////////////////////////
    class Pair {
        int key;
        int value;
        Pair(int key, int val) {
            this.key = key;
            this.value = val;
        }
    }

    List<List<Pair>> map;
    int size;

    public DesignHashMap() {
        size = 1000;
        map = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            map.add(new ArrayList<Pair>());
        }
    }

    public void put(int key, int value) {
        int index = key % size;
        for (Pair p : map.get(index)) {
            if (p.key == key) {
                p.value = value;
                return;
            }
        }
        Pair p = new Pair(key, value);
        map.get(index).add(p);
    }

    public int get(int key) {
        int index = key % size;
        for (Pair p : map.get(index)) {
            if (p.key == key) {
                return p.value;
            }
        }
        return -1;

    }

    public void remove(int key) {
        int index = key % size;
        List<Pair> bucket = map.get(index);
        // !!avoid ConcurrentModificationException
        Iterator<Pair> iterator = bucket.iterator();
        while (iterator.hasNext()) {
            Pair p = iterator.next();
            if (p.key == key) {
                iterator.remove(); // Safe removal
                return; // Assuming keys are unique, exit after removal
            }
        }
    }
}
