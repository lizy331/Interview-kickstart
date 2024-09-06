Design and implement a playlist of a user’s most recently played songs on Amazon music. It should support the following operations: getSong and addSong.
getSong – user should be able to search and get the song from the playlist if the song exists.
addSong – add the song into the playlist if the song is not already present.

首先一点我们必须能够抓住关键词也就是 ”most recently“ 这个单词就代表着我们需要一个 caching 的系统，我们在刷题的时候也需要 多问自己 **Where else could this apply**

cache problem, 

does the get song returns the most recent used song when it returns? 
what is the meaning og the most recently played? 
what does it take to be determine as played?
once you add a song, or get a song, then this song is consider to be the most recently used.

double linked list, where the head is the most recent used node, and the tail is the least recent used node.
this double linked list have a capacity which is limited to be 100, 

hashmap, to store the node/song as a cache,
key = Id
value = Node

```java

public class Solution{
    
    class Node{
        Integer Id;
        Node next;
        Node prev;
        
    }
    
    private Map cache;
    private int capacity;
    private int size;
    private Node head;
    private Node tail;
    
    public void init(){
        // init the double linkedlist
        cache = new HashMap<Integer,Node>();
        capacity = 5;
        size = 0;
        head = new Node();
        tail = new Node();
        head.next = tail;
    } 
    
    public Node getSong(Integer id){
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
            // evict the tail, and add the node
            cache.remove(tail.Id);
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
    
    public void moveToFront(Integer id){
        // assume the cache contains the song that it search
        removeNode(cache.get(id));
        addToFront(cache.get(id));
    }

    public static void main(String[] args) {
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        
        Solution solution = new Solution();
        solution.init();
        solution.addSong(node1);
        System.out.println(head.next.Id);
        solution.addSong(node2);
        System.out.println(head.next.Id);
        solution.addSong(node3);
        solution.addSong(node4);
        solution.addSong(node5);

        System.out.println("adding 6th node, will evict the tail");
        System.out.println("tail:");
        System.out.println(solution.tail.prev.Id);
        solution.addSong(node6);
        System.out.println("cache should contains 5 nodes");
        System.out.println(solution.cache);
        System.out.println(head.next.Id);
    }
    
        }
```