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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //Create dummy node to handle edge cases
        ListNode dummy = new ListNode(-1);
        dummy.next = head;

        // Create the two pointers
        ListNode p1 = dummy;
        ListNode p2 = dummy;
        
        // Move p2 for n spaces
        for(int i=0; i<n; i++){
            p2 = p2.next;
        }
        
        // Move both pointers until p2 reaches the last node
        while(p2.next != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        
        // p1.next is to be removed
        p1.next = p1.next.next;

        return dummy.next;
    }
}