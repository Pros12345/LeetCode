class Solution {
    public int strStr(String haystack, String needle) {
        int r = 0;
        if (!haystack.contains(needle)) {
            r = -1;
        } else if (haystack.contains(needle)) {
            r = haystack.indexOf(needle);
        }
        return r;
    }
}