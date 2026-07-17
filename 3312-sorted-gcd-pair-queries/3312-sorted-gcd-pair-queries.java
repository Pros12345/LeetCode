class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int max = 0;
        for (int x : nums) {
            max = Math.max(max, x);
        }

        int[] freq = new int[max + 1];
        for (int x : nums) {
            freq[x]++;
        }

        long[] exactGcd = new long[max + 1];

        // Count pairs having gcd exactly g
        for (int g = max; g >= 1; g--) {
            long count = 0;

            for (int multiple = g; multiple <= max; multiple += g) {
                count += freq[multiple];
            }

            long pairs = count * (count - 1) / 2;

            for (int multiple = g * 2; multiple <= max; multiple += g) {
                pairs -= exactGcd[multiple];
            }

            exactGcd[g] = pairs;
        }

        // Prefix count of sorted gcd values
        long[] prefix = new long[max + 1];
        for (int g = 1; g <= max; g++) {
            prefix[g] = prefix[g - 1] + exactGcd[g];
        }

        int[] answer = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long k = queries[i] + 1; // convert to 1-based position

            int left = 1, right = max;
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (prefix[mid] >= k) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }

            answer[i] = left;
        }

        return answer;
    }
}