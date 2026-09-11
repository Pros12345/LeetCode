class Solution {
    public int totalNumbers(int[] digits) {
        int[] count = new int[10];
        for (int d : digits) {
            count[d]++;
        }

        int distinctEvenCount = 0;

        // Iterate through all 3-digit even numbers
        for (int num = 100; num < 1000; num += 2) {
            if (isValid(num, count)) {
                distinctEvenCount++;
            }
        }

        return distinctEvenCount;
    }

    private boolean isValid(int num, int[] availableCount) {
        int[] numCount = new int[10];
        while (num > 0) {
            int digit = num % 10;
            numCount[digit]++;
            num /= 10;
        }

        // Check if we have enough copies of each digit
        for (int i = 0; i < 10; i++) {
            if (numCount[i] > availableCount[i]) {
                return false;
            }
        }

        return true;
    }
}
