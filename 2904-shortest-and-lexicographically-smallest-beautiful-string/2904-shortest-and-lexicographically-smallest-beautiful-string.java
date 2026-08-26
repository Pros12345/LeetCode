class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        String result = "";
        int minLen = Integer.MAX_VALUE;
        
        for (int i = 0; i < s.length(); i++) {
            int count = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < s.length(); j++) {
                sb.append(s.charAt(j));
                if (s.charAt(j) == '1') {
                    count++;
                }
                
                if (count == k) {
                    int currentLen = sb.length();
                    if (currentLen < minLen) {
                        minLen = currentLen;
                        result = sb.toString();
                    } else if (currentLen == minLen) {
                        if (result.equals("") || sb.toString().compareTo(result) < 0) {
                            result = sb.toString();
                        }
                    }
                    break; 
                }
            }
        }
        
        return result;
    }
}
