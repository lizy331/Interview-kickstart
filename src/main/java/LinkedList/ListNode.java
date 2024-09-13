package LinkedList;

public class ListNode {

    int val;

    ListNode next;

    public ListNode(int val){
        this.val = val;
    }

    public ListNode(ListNode next, int val){
        this.next = next;
        this.val = val;
    }
}
