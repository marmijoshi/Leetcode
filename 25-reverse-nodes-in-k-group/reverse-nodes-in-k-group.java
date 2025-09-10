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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        int count = 0;

        //check if k nodes exists
        while(count < k) {
            if(temp == null) return head;
            temp = temp.next;
            count++;
        }

        // Recursively call for rest of the LL
        ListNode newNext = reverseKGroup(temp, k);

        // Reverse current group
        temp = head;
        count = 0;
        while(count < k) {
            ListNode next = temp.next;
            temp.next = newNext;
            newNext = temp;
            temp = next;
            count++;
        }
        return newNext;
    }
}