public class AddTwoNumbers {
    /**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
        }
        ListNode (int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode addLists(ListNode l1, ListNode l2) {
        int carry = 0;
        ListNode head = null;
        ListNode tail = null;

        // just a start condition
        while(l1 != null || l2 != null) {
            int dig1 = (l1 != null ? l1.val : 0);
            int dig2 = (l2 != null ? l2.val : 0);
            int resDig = (dig1 + dig2 + carry) % 10;
            carry = (dig1 + dig2 + carry) / 10;

            if (head == null) {
                head = new ListNode(resDig);
                tail = head;
            } else {
                tail.next = new ListNode(resDig);
                tail = tail.next;
            }
            // move l1 and l2
            l1 = (l1 != null ? l1.next : null);
            l2 = (l2 != null ? l2.next : null);
        }
        // last carry
        if (carry > 0) {
            tail.next = new ListNode(carry);
        }
        return head;
    }

    public static void print(ListNode l) {
        while(l != null) {
            System.out.print(l.val + " ");
            l = l.next;
        }
    }

    public static void main(String[] args) {
    
    }
    
}
