class Solution {
    public int maxProduct(int n) {
        char[] arr = String.valueOf(n).toCharArray();

        int ans = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                ans = Math.max(ans,
                        (arr[i] - '0') * (arr[j] - '0'));
            }
        }

        return ans;
    }
}