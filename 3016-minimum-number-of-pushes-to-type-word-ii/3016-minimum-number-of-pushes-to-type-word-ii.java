import java.util.Arrays;

public class Solution {
    public int minimumPushes(String word) {
        int[] frequency = new int[26];
        for (char c : word.toCharArray()) {
            frequency[c - 'a']++;
        }

        Arrays.sort(frequency);

        int totalPushes = 0;
        int distinctLettersProcessed = 0;

        for (int i = 25; i >= 0; i--) {
            if (frequency[i] == 0) {
                break;
            }

            int pushesPerKey = (distinctLettersProcessed / 8) + 1;
            totalPushes += frequency[i] * pushesPerKey;

            distinctLettersProcessed++;
        }

        return totalPushes;
    }
}
