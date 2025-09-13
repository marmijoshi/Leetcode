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
    public ListNode partition(ListNode head, int x) {
        ListNode lesser = new ListNode(0);
        ListNode greater = new ListNode(0);

        ListNode lesserHead = lesser;
        ListNode greaterHead = greater;

        while(head != null) {
            if(head.val < x) {
                lesserHead.next = head;
                lesserHead = lesserHead.next;
            } else {
                greaterHead.next = head;
                greaterHead = greaterHead.next;
            }
            head = head.next;
        }
        greaterHead.next = null;
        lesserHead.next = greater.next;

        return lesser.next;
    }
}