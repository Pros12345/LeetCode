import java.util.Arrays;

class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Extract prime factors of t
        long temp = t;
        int req2 = 0, req3 = 0, req5 = 0, req7 = 0;
        while (temp % 2 == 0) {
            req2++;
            temp /= 2;
        }
        while (temp % 3 == 0) {
            req3++;
            temp /= 3;
        }
        while (temp % 5 == 0) {
            req5++;
            temp /= 5;
        }
        while (temp % 7 == 0) {
            req7++;
            temp /= 7;
        }

        // If t has prime factors other than 2, 3, 5, 7, it's impossible
        if (temp > 1)
            return "-1";

        int n = num.length();

        // If the number of required slots is strictly greater than string length,
        // construct a brand new larger length number.
        if (getMinDigitsCount(req2, req3, req5, req7) > n) {
            return generateSmallestWithFactors(Math.max(n + 1, getMinDigitsCount(req2, req3, req5, req7)), req2, req3,
                    req5, req7);
        }

        char[] s = num.toCharArray();
        int[] cnt2 = new int[n + 1];
        int[] cnt3 = new int[n + 1];
        int[] cnt5 = new int[n + 1];
        int[] cnt7 = new int[n + 1];
        int firstZero = -1;

        for (int i = 0; i < n; i++) {
            cnt2[i + 1] = cnt2[i];
            cnt3[i + 1] = cnt3[i];
            cnt5[i + 1] = cnt5[i];
            cnt7[i + 1] = cnt7[i];

            if (s[i] == '0') {
                if (firstZero == -1)
                    firstZero = i;
            } else {
                int d = s[i] - '0';
                int val = d;
                while (val % 2 == 0) {
                    cnt2[i + 1]++;
                    val /= 2;
                }
                while (val % 3 == 0) {
                    cnt3[i + 1]++;
                    val /= 3;
                }
                while (val % 5 == 0) {
                    cnt5[i + 1]++;
                    val /= 5;
                }
                while (val % 7 == 0) {
                    cnt7[i + 1]++;
                    val /= 7;
                }
            }
        }

        // If the original number is zero-free and already divisible by t
        if (firstZero == -1 && cnt2[n] >= req2 && cnt3[n] >= req3 && cnt5[n] >= req5 && cnt7[n] >= req7) {
            return num;
        }

        // Step 2: Backtrack from the end to find the first place to increase a digit safely
        int limit = (firstZero == -1) ? n - 1 : firstZero;
        for (int i = limit; i >= 0; i--) {
            int currentDigit = s[i] - '0';
            for (int d = currentDigit + 1; d <= 9; d++) {
                int n2 = Math.max(0, req2 - cnt2[i]);
                int n3 = Math.max(0, req3 - cnt3[i]);
                int n5 = Math.max(0, req5 - cnt5[i]);
                int n7 = Math.max(0, req7 - cnt7[i]);

                // Reduce factors for the current tested digit d
                int val = d;
                while (val % 2 == 0) {
                    n2 = Math.max(0, n2 - 1);
                    val /= 2;
                }
                while (val % 3 == 0) {
                    n3 = Math.max(0, n3 - 1);
                    val /= 3;
                }
                while (val % 5 == 0) {
                    n5 = Math.max(0, n5 - 1);
                    val /= 5;
                }
                while (val % 7 == 0) {
                    n7 = Math.max(0, n7 - 1);
                    val /= 7;
                }

                int remSpace = n - 1 - i;
                if (getMinDigitsCount(n2, n3, n5, n7) <= remSpace) {
                    // Reconstruct prefix
                    StringBuilder sb = new StringBuilder();
                    for (int j = 0; j < i; j++)
                        sb.append(s[j]);
                    sb.append(d);

                    // Digit-by-digit search for suffix to ensure absolute lexicographical sorting
                    for (int j = i + 1; j < n; j++) {
                        for (int nextD = 1; nextD <= 9; nextD++) {
                            int t2 = n2, t3 = n3, t5 = n5, t7 = n7;
                            int v = nextD;
                            while (v % 2 == 0) {
                                t2 = Math.max(0, t2 - 1);
                                v /= 2;
                            }
                            while (v % 3 == 0) {
                                t3 = Math.max(0, t3 - 1);
                                v /= 3;
                            }
                            while (v % 5 == 0) {
                                t5 = Math.max(0, t5 - 1);
                                v /= 5;
                            }
                            while (v % 7 == 0) {
                                t7 = Math.max(0, t7 - 1);
                                v /= 7;
                            }

                            if (getMinDigitsCount(t2, t3, t5, t7) <= (n - 1 - j)) {
                                sb.append(nextD);
                                n2 = t2;
                                n3 = t3;
                                n5 = t5;
                                n7 = t7;
                                break;
                            }
                        }
                    }
                    return sb.toString();
                }
            }
        }

        // Step 3: If no adjustment within length n works, find the minimum expanded length
        int nextLen = Math.max(n + 1, getMinDigitsCount(req2, req3, req5, req7));
        return generateSmallestWithFactors(nextLen, req2, req3, req5, req7);
    }

    private int getMinDigitsCount(int c2, int c3, int c5, int c7) {
        int count = c7 + c5;
        count += (c3 + 1) / 2;
        int rem2 = c2;
        if (c3 % 2 != 0) {
            rem2 = Math.max(0, rem2 - 1);
        }
        count += (rem2 + 2) / 3;
        return count;
    }

    // Creates the absolute smallest number from scratch when length increases
    private String generateSmallestWithFactors(int length, int c2, int c3, int c5, int c7) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < length; j++) {
            for (int d = 1; d <= 9; d++) {
                int t2 = c2, t3 = c3, t5 = c5, t7 = c7;
                int v = d;
                while (v % 2 == 0) {
                    t2 = Math.max(0, t2 - 1);
                    v /= 2;
                }
                while (v % 3 == 0) {
                    t3 = Math.max(0, t3 - 1);
                    v /= 3;
                }
                while (v % 5 == 0) {
                    t5 = Math.max(0, t5 - 1);
                    v /= 5;
                }
                while (v % 7 == 0) {
                    t7 = Math.max(0, t7 - 1);
                    v /= 7;
                }

                if (getMinDigitsCount(t2, t3, t5, t7) <= (length - 1 - j)) {
                    sb.append(d);
                    c2 = t2;
                    c3 = t3;
                    c5 = t5;
                    c7 = t7;
                    break;
                }
            }
        }
        return sb.toString();
    }
}
