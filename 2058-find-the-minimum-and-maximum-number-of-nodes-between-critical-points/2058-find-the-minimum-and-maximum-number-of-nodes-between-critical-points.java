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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        // If the list has fewer than 3 nodes, it's impossible to have a critical point
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int minDistance = Integer.MAX_VALUE;
        int firstCritical = -1;
        int prevCritical = -1;
        
        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1; // 0-indexed tracking

        while (curr.next != null) {
            ListNode nextNode = curr.next;

            // Check if curr is a local maxima or local minima
            if ((curr.val > prev.val && curr.val > nextNode.val) || 
                (curr.val < prev.val && curr.val < nextNode.val)) {
                
                if (firstCritical == -1) {
                    // Record the very first critical point found
                    firstCritical = currentIndex;
                } else {
                    // Update minDistance using the current and previous critical points
                    minDistance = Math.min(minDistance, currentIndex - prevCritical);
                }
                
                // Keep track of the most recent critical point
                prevCritical = currentIndex;
            }

            // Move the pointers forward
            prev = curr;
            curr = nextNode;
            currentIndex++;
        }

        // If we found fewer than 2 critical points, return [-1, -1]
        if (firstCritical == prevCritical) {
            return new int[]{-1, -1};
        }

        // maxDistance is always the distance between the first and the last critical point
        int maxDistance = prevCritical - firstCritical;

        return new int[]{minDistance, maxDistance};
    }
}
