import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static class Block {
        char ch;
        int length;

        Block(char ch, int length) {
            this.ch = ch;
            this.length = length;
        }
    }

    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                initialOnes++;
            }
        }

        String augmented = "1" + s + "1";
        List<Block> blocks = new ArrayList<>();
        int n = augmented.length();

        int i = 0;
        while (i < n) {
            int j = i;
            while (j < n && augmented.charAt(j) == augmented.charAt(i)) {
                j++;
            }
            blocks.add(new Block(augmented.charAt(i), j - i));
            i = j;
        }

        int maxGain = 0;

        for (int idx = 1; idx < blocks.size() - 1; idx++) {
            Block current = blocks.get(idx);

            if (current.ch == '1') {
                Block prev = blocks.get(idx - 1);
                Block next = blocks.get(idx + 1);

                if (prev.ch == '0' && next.ch == '0') {

                    int currentGain = prev.length + next.length;
                    maxGain = Math.max(maxGain, currentGain);
                }
            }
        }

        return initialOnes + maxGain;
    }
}
