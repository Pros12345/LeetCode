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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 1. Create a dummy node to act as the head anchor
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;

        // 2. Iterate while both lists have remaining elements
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next; // Advance the tracking pointer
        }

        // 3. Connect the remaining nodes of the non-empty list
        current.next = (list1 != null) ? list1 : list2;

        // 4. Return the head of the sorted merged list
        return dummy.next;
    }
}
