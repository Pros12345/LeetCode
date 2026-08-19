import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        Map<Integer, Set<Integer>> reserved = new HashMap<>();
        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            reserved.putIfAbsent(row, new HashSet<>());
            reserved.get(row).add(col);
        }
        int totalGroups = (n - reserved.size()) * 2;
        for (Map.Entry<Integer, Set<Integer>> entry : reserved.entrySet()) {
            Set<Integer> seats = entry.getValue();
            boolean left = !seats.contains(2) && !seats.contains(3) && !seats.contains(4) && !seats.contains(5);
            boolean right = !seats.contains(6) && !seats.contains(7) && !seats.contains(8) && !seats.contains(9);
            boolean middle = !seats.contains(4) && !seats.contains(5) && !seats.contains(6) && !seats.contains(7);
            if (left && right) {
                totalGroups += 2; 
            } else if (left || right || middle) {
                totalGroups += 1; 
            }
        }
        return totalGroups;
    }
}
