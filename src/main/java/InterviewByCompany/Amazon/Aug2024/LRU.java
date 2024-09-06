package InterviewByCompany.Amazon.Aug2024;

import java.util.HashMap;
import java.util.Map;

public class LRU {

    public class Node{
        int Id;
        Node next;
        Node prev;

        public Node (Integer id){
            this.Id = id;
        }

    }

    public Map<Integer,Node> cache;
    private int capacity;
    private int size;
    public Node head;
    public Node tail;

    public void init(){
        // init the double linkedlist
        cache = new HashMap<>();
        capacity = 5;
        size = 0;
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
    }

    public Node getSong(int id){
        // first check in the cache, if the song exist
        if(cache.containsKey(id)){
            // move this song to the front
            moveToFront(id);
            return cache.get(id);
        }else{
            return null;
        }
    }

    public void addSong(Node node){
        if(size < capacity){
            cache.put(node.Id,node);
            addToFront(node);
            size++;
        }else{
            // evict the prev of tail, and add the node
            cache.remove(tail.prev.Id);
            removeNode(tail.prev);
            addToFront(node);
            cache.put(node.Id,node);
        }
    }

    public void addToFront(Node node){
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

    public void removeNode(Node node){
        node.prev.next = node.next;
        node.next.prev = node .prev;
    }

    public void moveToFront(int id){
        // assume the cache contains the song that it search
        Node node = cache.get(id);
        removeNode(node);
        addToFront(node);
    }

    public static void main(String[] args) {

        LRU solution = new LRU();

        solution.init();

        // Instantiating nodes with the correct LRU instance
        Node node1 = solution.new Node(1);
        Node node2 = solution.new Node(2);
        Node node3 = solution.new Node(3);
        Node node4 = solution.new Node(4);
        Node node5 = solution.new Node(5);
        Node node6 = solution.new Node(6);

        solution.addSong(node1);
        System.out.println(solution.head.next.Id);
        solution.addSong(node2);
        System.out.println(solution.head.next.Id);
        solution.addSong(node3);
        solution.addSong(node4);
        solution.addSong(node5);

        System.out.println("adding 6th node, will evict the tail");
        System.out.println("tail:");
        System.out.println(solution.tail.prev.Id);
        solution.addSong(node6);
        System.out.println("cache should contains 5 nodes");
        System.out.println(solution.cache);
        System.out.println(solution.head.next.Id);
    }

}

