package PracticalExperiments.Map;

import java.util.*;

public class DemoSynchronizedMap {
    public static void main(String[] args) {
        Map<String, Integer> map = Collections.synchronizedMap(new HashMap<String,Integer>());

        map.put("123",123);
        map.get("123");

        System.out.println(map);

//        List<Integer> list = new List<>();
        Queue<Integer> queue = new ArrayDeque<>();

    }
}
