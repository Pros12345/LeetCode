class Solution {
    public boolean isPalindrome(int x) {
        // Negative numbers are not palindromes
        if (x < 0)
            return false;

        // Numbers ending with 0 (but not 0 itself) cannot be palindromes
        if (x % 10 == 0 && x != 0)
            return false;

        int reversedHalf = 0;
        // Reverse only half of the number
        while (x > reversedHalf) {
            reversedHalf = reversedHalf * 10 + x % 10;
            x /= 10;
        }

        // For even length numbers: x == reversedHalf
        // For odd length numbers: x == reversedHalf/10
        return x == reversedHalf || x == reversedHalf / 10;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        System.out.println(solver.isPalindrome(121)); // true
        System.out.println(solver.isPalindrome(-121)); // false
        System.out.println(solver.isPalindrome(10)); // false
        System.out.println(solver.isPalindrome(0)); // true
    }
}
