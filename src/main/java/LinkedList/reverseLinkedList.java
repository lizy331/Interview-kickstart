package LinkedList;


public class reverseLinkedList {

    public static void reverse(ListNode pre, ListNode cur){

        while(cur!=null && cur.next!=null){
            ListNode temp = cur.next;
            cur.next = temp.next;
            temp.next = pre.next;
            pre.next = temp;
        }
    }
    public static void main(String[] args) {

        ListNode dummy = new ListNode(-1);

        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        dummy.next = node1;

        reverse(dummy,node1);

        ListNode printer = dummy.next;
        while(printer!=null){
            System.out.println(printer.val);
            printer = printer.next;
        }

    }
}
