class Solution {
    public boolean uniformArray(int[] nums1) {
        int minOdd = Integer.MAX_VALUE;
        
        // Find the minimum odd number in the array
        for (int x : nums1) {
            if (x % 2 != 0) {
                minOdd = Math.min(minOdd, x);
            }
        }
        
        // If there are no odd numbers, all numbers are already even
        if (minOdd == Integer.MAX_VALUE) {
            return true;
        }
        
        // Check if any even number is strictly smaller than the minimum odd number
        for (int x : nums1) {
            if (x % 2 == 0 && x < minOdd) {
                return false;
            }
        }
        
        return true;
    }
}
