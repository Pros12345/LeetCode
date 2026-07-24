class Solution {
    public int uniqueXorTriplets(int[] nums) {
        // Unique elements list
        boolean[] hasOne = new boolean[2048];
        int uniqueCount = 0;
        for (int num : nums) {
            if (!hasOne[num]) {
                hasOne[num] = true;
                uniqueCount++;
            }
        }

        int[] one = new int[uniqueCount];
        int idx = 0;
        for (int i = 0; i < 2048; i++) {
            if (hasOne[i]) {
                one[idx++] = i;
            }
        }

        // Find all unique pair XORs
        boolean[] hasTwo = new boolean[2048];
        for (int x : one) {
            for (int y : one) {
                hasTwo[x ^ y] = true;
            }
        }

        // Find all unique triplet XORs
        boolean[] hasThree = new boolean[2048];
        int ans = 0;
        for (int p = 0; p < 2048; p++) {
            if (hasTwo[p]) {
                for (int x : one) {
                    int tripletXor = p ^ x;
                    if (!hasThree[tripletXor]) {
                        hasThree[tripletXor] = true;
                        ans++;
                    }
                }
            }
        }

        return ans;
    }
}
