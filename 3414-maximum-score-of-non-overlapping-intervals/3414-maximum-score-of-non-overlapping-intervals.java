import java.util.*;

public class Solution {

    // A helper class to group the total weight along with the sorted selected indices list
    private static class Result {
        long weight;
        List<Integer> selected;

        Result(long weight, List<Integer> selected) {
            this.weight = weight;
            this.selected = selected;
        }
    }

    // A structured representation of the augmented interval
    private static class Interval {
        int left, right, weight, originalIndex;

        Interval(int left, int right, int weight, int originalIndex) {
            this.left = left;
            this.right = right;
            this.weight = weight;
            this.originalIndex = originalIndex;
        }
    }

    private Result[][] memo;

    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        List<Interval> indexedIntervals = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            List<Integer> curr = intervals.get(i);
            indexedIntervals.add(new Interval(curr.get(0), curr.get(1), curr.get(2), i));
        }

        // Sort by start times (left boundary)
        indexedIntervals.sort((a, b) -> Integer.compare(a.left, b.left));

        // memo[i][quota] stores the result starting from interval i with 'quota' choices remaining
        memo = new Result[n][5];

        Result optimalResult = dp(indexedIntervals, 0, 4);

        // Convert the best indices list into a primitive array
        int[] ans = new int[optimalResult.selected.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = optimalResult.selected.get(i);
        }
        return ans;
    }

    private Result dp(List<Interval> intervals, int i, int quota) {
        if (i == intervals.size() || quota == 0) {
            return new Result(0, new ArrayList<>());
        }
        if (memo[i][quota] != null) {
            return memo[i][quota];
        }

        // Choice 1: Skip the current interval
        Result skipResult = dp(intervals, i + 1, quota);

        // Choice 2: Take the current interval
        Interval curr = intervals.get(i);
        // Find the first interval that starts strictly after the current one ends
        int nextValidIdx = findFirstGreater(intervals, i + 1, curr.right);
        Result takeNextResult = dp(intervals, nextValidIdx, quota - 1);

        // Construct the sequence if we choose 'take'
        long takeWeight = curr.weight + takeNextResult.weight;
        List<Integer> takeList = new ArrayList<>();
        takeList.add(curr.originalIndex);
        takeList.addAll(takeNextResult.selected);

        // Lexicographically sort the indices list for the current picked combination
        Collections.sort(takeList);
        Result takeResult = new Result(takeWeight, takeList);

        // Compare both choices to find the optimal result
        memo[i][quota] = getBetterResult(skipResult, takeResult);
        return memo[i][quota];
    }

    // Binary search to find the first interval whose left boundary > target right boundary
    private int findFirstGreater(List<Interval> intervals, int start, int targetRight) {
        int low = start;
        int high = intervals.size() - 1;
        int ans = intervals.size();

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (intervals.get(mid).left > targetRight) {
                ans = mid;
                high = mid - 1; // Try to look for a closer one on the left
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    // Handles the tie-breaking rules cleanly
    private Result getBetterResult(Result r1, Result r2) {
        if (r1.weight != r2.weight) {
            return r1.weight > r2.weight ? r1 : r2;
        }

        // Tie-breaker 1: If weight is equal, the lexicographically smaller list wins
        int len1 = r1.selected.size();
        int len2 = r2.selected.size();
        int minLen = Math.min(len1, len2);

        for (int i = 0; i < minLen; i++) {
            int idx1 = r1.selected.get(i);
            int idx2 = r2.selected.get(i);
            if (idx1 != idx2) {
                return idx1 < idx2 ? r1 : r2;
            }
        }

        // Tie-breaker 2: If values are identical, the shorter array is lexicographically smaller
        return len1 <= len2 ? r1 : r2;
    }
}
