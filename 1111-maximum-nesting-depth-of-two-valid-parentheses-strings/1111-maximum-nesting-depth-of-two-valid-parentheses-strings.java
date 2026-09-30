class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int n = seq.length();
        int[] answer = new int[n];
        int depth = 0;

        for (int i = 0; i < n; i++) {
            char c = seq.charAt(i);
            if (c == '(') {
                // Assign to A (0) if depth is even, or B (1) if depth is odd
                answer[i] = depth % 2;
                depth++;
            } else {
                depth--;
                // Assign to A (0) if depth is even, or B (1) if depth is odd
                answer[i] = depth % 2;
            }
        }

        return answer;
    }
}
