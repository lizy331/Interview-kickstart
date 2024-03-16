package InterviewByCompany.Apple;

public class MergeTwoNodeList {

    public static ListNode mergeTwoNodeList(ListNode node1, ListNode node2){

        // create pointer of res.next
        ListNode res = new ListNode(-1);
        ListNode pointer = res;

        // traverse node 1 and node 2, until one of then is null
        // pointer is the sum of two nodes val + carry
        // reset the carry, if sum of nodes value are less than 10
        int carry = 0;
        while(node1 != null && node2 != null){
            carry = node1.val + node2.val + carry;
//            System.out.println(carry);
            pointer.next = new ListNode(carry%10);
            pointer = pointer.next;
            if(carry>=10){
                carry/=10;
            }else{
                carry = 0;
            }

            node1 = node1.next;
            node2 = node2.next;

        }

        while(node1 !=null){
            pointer = new ListNode(node1.val);
            node1 = node1.next;
            pointer = pointer.next;
        }

        while(node2 !=null){
            pointer = new ListNode(node2.val);
            node2 = node2.next;
            pointer = pointer.next;
        }

        return res.next;

    }


    public static void main(String[] args) {
        ListNode node1 = new ListNode(4); // 4 -> 3 -> 1
        node1.next = new ListNode(3);
        node1.next.next = new ListNode(1);

        ListNode node2 = new ListNode(3); // 3 -> 7 -> 3
        node2.next = new ListNode(7);
        node2.next.next = new ListNode(3);

        ListNode res1 = mergeTwoNodeList(node1,node2);
        while(res1!=null){
            System.out.println(res1.val); // 7 -> 0 -> 5
            res1 = res1.next;
        }

    }
}
