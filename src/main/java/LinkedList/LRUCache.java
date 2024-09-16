package LinkedList;

import java.util.*;

public class LRUCache {

    // we need a double linkedlist to reterivce the head and the tail at anytime
    class ListNode{
        int key;
        int val;
        ListNode next;
        ListNode prev;

        public ListNode(int key, int val){
            this.val = val;
            this.key = key;
        }
    }

    // we need a cache to store the key and the node itself
    // mainly focus on the reterive data
    private Map<Integer,ListNode> cache;
    private int size;
    private int cap;
    private ListNode head;
    private ListNode tail;



    // node.head is the last time used function

    public LRUCache(int capacity) {
        this.cap = capacity;
        size = 0;
        cache = new HashMap<>();
        // we need two nodes.which  is the head and the tail of the list node
        this.head = new ListNode(-1,-1);
        this.tail = new ListNode(-1,-1);
        this.head.next = tail;
        this.tail.prev = head;

    }

    public int get(int key) {
        // get the key will check the hashmap, if it exist then return otherwise return -1
        if(cache.containsKey(key)){

            // once a node is get, then it wil update the cache
            // move the relative node to the front of the cache
            moveToFront(cache.get(key));

            return cache.get(key).val;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(cache.containsKey(key)){
            // updateh the val
            // delete the relative node first
            // then add the new node to the font of the linked list
            deleteNode(cache.get(key));
            ListNode nodeToAdd = new ListNode(key,value);
            addNode(nodeToAdd);
            cache.put(key,nodeToAdd);
        }else{
            // adding a new node
            if(size>=cap){
                // will evict the node that is before tail, then add the new node
                ListNode nodeToAdd = new ListNode(key,value);
                cache.remove(tail.prev.key);
                deleteNode(tail.prev);

                addNode(nodeToAdd);
                cache.put(key,nodeToAdd);

                //
            }else{
                // just add the node
                ListNode nodeToAdd = new ListNode(key,value);
                addNode(nodeToAdd);
                cache.put(key,nodeToAdd);

                size++;
            }
        }
    }


    public void deleteNode(ListNode node){
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void addNode(ListNode node){
        // add the node to the position to be the next of the head
        node.next = head.next;
        head.next.prev = node;
        node.prev = head;
        head.next = node;
    }

    public void moveToFront(ListNode node){
        deleteNode(node);
        addNode(node);
    }
}
