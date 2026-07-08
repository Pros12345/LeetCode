class Solution {
    public int lengthOfLastWord(String s) {
    List<String> words = Arrays.asList(
    s.trim().replaceAll("\\s+", " ").split(" "));
    String lastWord = words.get(words.size() - 1);
    int length = lastWord.length();
    return length;
    }
}