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
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;

        // Find middle
        ListNode temp = null;
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null && fast.next!=null){
            temp = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        temp.next = null;

        // Sort first half
        ListNode l1 = sortList(head);
        // Sort second half
        ListNode l2 = sortList(slow);

        // Sort the two sorted halves
        return mergeLists(l1, l2);
    }

    public ListNode mergeLists(ListNode h1, ListNode h2) {
        ListNode dummy = new ListNode(-1);
        ListNode head = dummy;
        while(h1!=null && h2!=null) {
            if(h1.val <= h2.val) {
                dummy.next = h1;
                h1 = h1.next;
            } else {
                dummy.next = h2;
                h2 = h2.next;
            }
            dummy = dummy.next;
        }
        if(h1 == null) {
            dummy.next = h2;
        } else if(h2 == null) {
            dummy.next = h1;
        }
        return head.next;
    }
}