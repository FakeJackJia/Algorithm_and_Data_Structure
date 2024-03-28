import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class findRepeatDNASequence {
    public List<String> solution(String s){
        HashSet<String> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        for (int i = 0; i + 10 <= s.length(); i++){
            String key = s.substring(i, i + 10);
            if (seen.contains(key))
                res.add(key);
            else
                seen.add(key);
        }

        return new ArrayList<String>(res);
    }

    //using idea of rolling hash
    public List<String> solution2(String s){
        if (s.length() < 10)
            return new ArrayList<>();

        int[] map = new int[256];
        map['A'] = 1;
        map['B'] = 2;
        map['G'] = 3;
        map['T'] = 4;

        HashSet<Long> seen = new HashSet<>();
        HashSet<String> res = new HashSet<>();

        long hash = 0, ten9 = (long)1e9;

        for (int i = 0; i < 9; i++)
            hash = hash * 10 + map[s.charAt(i)];

        for (int i = 9; i < s.length(); i++){
            hash = hash * 10 + map[s.charAt(i)];

            if (seen.contains(hash))
                res.add(s.substring(i - 9, i + 1));
            else
                seen.add(hash);

            hash -= map[s.charAt(i - 9)] * ten9;
        }

        return new ArrayList<String>(res);
    }
}