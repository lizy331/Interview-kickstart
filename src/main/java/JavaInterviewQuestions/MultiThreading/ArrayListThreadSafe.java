package JavaInterviewQuestions.MultiThreading;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ArrayListThreadSafe {

    // this method will throw a ConcurrentModificationException,
    // since one thread is traversing the list, while another is modifying(adding elements) the Arraylist
    public static void notThreadSafeExample() {
        List<String> list = new ArrayList<>();
        list.add("lizy");
        list.add("guo");
        list.add("thea");
        // Thread 1
        Thread thread1 = new Thread(() -> {
            for (String str : list){
                System.out.println("thread 1 printing: " + str);
            }
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            for (int i = 0;i<100;i++) {
                list.add("thread 2 adding: " + i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the non-synchronized list
        System.out.println("Print the non-synchronized list");
        for (String element : list) {
            System.out.println(element);
        }
    }

    public static void threadSafeExample() {
        List<String> list = new ArrayList<>();
        List<String> synchronizedList = Collections.synchronizedList(list);

        // Thread 1
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronizedList.add("Thread 1 - Element " + i);
            }
        });

        // Thread 2
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronizedList.add("Thread 2 - Element " + i);
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print the synchronized list
        for (String element : synchronizedList) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        notThreadSafeExample();

//        threadSafeExample();
    }

}
