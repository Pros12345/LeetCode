import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reserved = new HashMap<>();

        // Group reserved seats by row
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            reserved.putIfAbsent(row, new HashSet<>());
            reserved.get(row).add(col);
        }

        // Rows with no reservations can seat 2 groups each
        int totalGroups = (n - reserved.size()) * 2;

        // Check each row that has at least one reservation
        for (Map.Entry<Integer, Set<Integer>> entry : reserved.entrySet()) {
            Set<Integer> seats = entry.getValue();
            
            boolean left = !seats.contains(2) && !seats.contains(3) && !seats.contains(4) && !seats.contains(5);
            boolean right = !seats.contains(6) && !seats.contains(7) && !seats.contains(8) && !seats.contains(9);
            boolean middle = !seats.contains(4) && !seats.contains(5) && !seats.contains(6) && !seats.contains(7);

            if (left && right) {
                totalGroups += 2; // Can seat on both left and right blocks
            } else if (left || right || middle) {
                totalGroups += 1; // Can seat on either left, right, or middle block
            }
        }

        return totalGroups;
    }
}
