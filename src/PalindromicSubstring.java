public class PalindromicSubstring {
    public static void main(String[] arg){
        PalindromicSubstring palindromicSubstring = new PalindromicSubstring();
        String[] test = new String[]{"babad", "cbbd", "abbahopxpo","abc534cba"};
        for(String str:test){
            System.out.println(palindromicSubstring.longestPalindrome2(str));
        }
    }
    /**
     * Method 1: Brute Force
     * 遍历substring(i, j)，
     * 对每个substring(i,j)，判断是否为回文
     * N的三方
     */
    private boolean isPalindromic(String s){
        int len = s.length();
        for(int i=0;i<len/2;i++){
            if(s.charAt(i)!=s.charAt(len-i-1))
                return false;
        }
        return true;
    }
    public String longestPalindrome1(String s){
        int len = s.length();
        if(len<=1)return s;
        int maxLen = 0;
        String res = "";
        for(int i=0;i<len;i++){
            for(int j=i+1;j<=len;j++){
                String sub = s.substring(i, j);
                if(isPalindromic(sub) && sub.length()>maxLen){
                    res = sub;
                    maxLen = sub.length();
                }
            }
        }
        return res;
    }

    /**
     * Method 2 求两个字符串的最长公共字串
     * 即求原字符串与翻转字符串的最长公共字串
     * 其中关键的一点是：最长公共子串不一定是最长回文子串，需要添加一定的限制条件
     * 如果是回文的话：倒置后的坐标与倒置前的坐标 两者之间的距离等于回文长度
     * i+1 - (len-j-1) = dp[i][j]
     */
    public String longestPalindrome2(String s){
        int len = s.length();
        if(len<=1)return s;
        int maxLen = 0;
        int maxEnd = 0;
        String reverse = new StringBuilder(s).reverse().toString();
        int[][] dp = new int[len][len];
        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                if(s.charAt(i) == reverse.charAt(j)){
                    if(i>0&&j>0){
                        dp[i][j] = dp[i-1][j-1]+1;
                    }else{
                        dp[i][j] = 1;
                    }
                }
                if(dp[i][j]>maxLen){
                    if(i+j+2-len==dp[i][j]){
                        maxLen = dp[i][j];
                        maxEnd = i;
                    }
                }
            }
        }
        return s.substring(maxEnd-maxLen+1, maxEnd+1);
    }

    /**
     * Method 3 --- Brute Force Update, require O(n) space
     *P(i, j) = charAt(i) == charAt(j) && P(i+1, j-1);
     */
    public String longestPalindrome3(String s){
        int length = s.length();
        boolean[][] P = new boolean[length][length];
        int maxLen = 0;
        String maxPal = "";
        for (int len = 1; len <= length; len++) //遍历所有的长度
            for (int start = 0; start < length; start++) {
                int end = start + len - 1;
                if (end >= length) //下标已经越界，结束本次循环
                    break;
                P[start][end] = (len == 1 || len == 2 || P[start + 1][end - 1])
                        && s.charAt(start) == s.charAt(end); //长度为 1 和 2 的单独判断下
                if (P[start][end] && len > maxLen) {
                    maxPal = s.substring(start, end + 1);
                }
            }
        return maxPal;
    }
    /**
     * Method 4 扩展中心
     * 回文串一定是对称的，所以我们可以每次循环选择一个中心，
     * 进行左右扩展，判断左右字符是否相等即可。
     * 由于存在奇数的字符串和偶数的字符串，所以我们需要从一个字符开始扩展，
     * 或者从两个字符之间开始扩展，所以总共有 n + n - 1 个中心
     */
    public String longestPalindrome4(String s) {
        if (s == null || s.length() < 1) return "";
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }

    /**
     * Method 5 马拉车算法
     *
     */
    public String longestPalindrome5(String s){
        //TODO
        return null;
    }


}
