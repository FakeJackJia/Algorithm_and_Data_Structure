public class SubstringMatch {

    private SubstringMatch(){}

    //look string t in string s
    //O(T * S) for T = t.length() and S = s.length()
    public static int bruteforce(String s, String t){
        if (t.length() > s.length())
            return -1;

        for (int i = 0; i + t.length() <= s.length(); i++){
            int j;
            for (j = 0; j < t.length(); j++) {
                if (s.charAt(i + j) != t.charAt(j))
                    break;
            }

            if (j == t.length())
                return i;
        }

        return -1;
    }

    //O(T * S) for T = t.length() and S = s.length()
    //same as brutoforce, but in most cases, much faster as different hash values would avoid comparing each char
    //amortized time complexity O(T + S) for T = t.length() and S = s.length()
    public static int rabinKarp(String s, String t){
        if (t.length() > s.length())
            return -1;

        if (t.length() == 0)
            return 0;

        long tHash = 0, MOD = (long)1e9 + 7, B = 256;
        for (int i = 0; i < t.length(); i++)
            tHash = (tHash * B + t.charAt(i)) % MOD;

        long hash = 0, P = 1;
        for (int i = 0; i < t.length() - 1; i++)
            P = P * B % MOD;

        for (int i = 0; i < t.length() - 1; i++)
            hash = (hash * B + s.charAt(i)) % MOD;

        for (int i = t.length() - 1; i < s.length(); i++){
            hash = (hash * B + s.charAt(i)) % MOD;

            if (hash == tHash && equal(s, i - t.length() + 1, t))
                return i - t.length() + 1;

            //add one extra MOD to avoid negative value and %MOD to avoid overflow
            hash = (hash - s.charAt(i - t.length() + 1) * P % MOD + MOD) % MOD;
        }

        return -1;
    }

    private static boolean equal(String s, int l, String t){
        for (int i = 0; i < t.length(); i++){
            if (t.charAt(i) != s.charAt(l + i))
                return false;
        }

        return true;
    }

    //amortized time complexity O(S + T) for s.length() and t.length()
    //similarly, amortized into two operations
    public static int KMP(String s, String t){
        if (s.length() < t.length()) return -1;
        if (t.length() == 0) return 0;

        int[] lps = getLPS(t);

        int i = 0, j = 0;

        while (i < s.length()){
            if (s.charAt(i) == t.charAt(j)){
                i++;
                j++;

                if (j == t.length())
                    return i - t.length();
            }
            else if (j > 0)
                    j = lps[j - 1];
            else i++;
        }

        return -1;
    }

    //LPS: Longest proper Prefix which is also Suffix
    //amortized time complexity: O(T) for T = t.length()
    private static int[] getLPS(String t){
        int[] lps = new int[t.length()];

        for (int i = 1; i < t.length(); i++){
            int a = lps[i - 1];

            //not triggered every time
            //can be amortized into 2 operation in each loop
            //since each add(at most 1) corresponding to each minus in a in worst case
            while (a > 0 && t.charAt(i) != t.charAt(a))
                a = lps[a - 1];

            if (t.charAt(i) == t.charAt(a))
                lps[i] = a + 1;
        }

        return lps;
    }

    public static void main(String[] args){
//        String s = "Jack";
//        String t = "ac";
//
//        SubstringMatchHelper.matchTest("brutoforce", s, t);

        //worst case for brutoforce
        int n = 1000000, m = 1000;

        StringBuilder s1 = new StringBuilder();
        for (int i = 0; i < n; i++)
            s1.append('a');


        StringBuilder t1 = new StringBuilder();
        for (int i = 0; i < m - 1; i++)
            t1.append('a');
        t1.append('b');

        SubstringMatchHelper.matchTest("brutoforce", s1.toString(), t1.toString());

        //rk
        SubstringMatchHelper.matchTest("rk", s1.toString(), t1.toString());

        //kmp
        SubstringMatchHelper.matchTest("kmp", s1.toString(), t1.toString());
    }
}