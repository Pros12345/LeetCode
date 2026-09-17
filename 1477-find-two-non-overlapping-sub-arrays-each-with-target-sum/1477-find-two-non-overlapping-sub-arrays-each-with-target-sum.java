class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int[] minLen = new int[n];
        int sum = 0, left = 0, res = Integer.MAX_VALUE, minSoFar = Integer.MAX_VALUE;
        java.util.Arrays.fill(minLen, Integer.MAX_VALUE);

        for (int right = 0; right < n; right++) {
            sum += arr[right];
            while (sum > target) {
                sum -= arr[left++];
            }
            if (sum == target) {
                int currLen = right - left + 1;
                if (left > 0 && minLen[left - 1] != Integer.MAX_VALUE) {
                    res = Math.min(res, currLen + minLen[left - 1]);
                }
                minSoFar = Math.min(minSoFar, currLen);
            }
            minLen[right] = minSoFar;
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
