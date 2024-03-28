import java.util.*;

public class OpenLock {
    public int solution(String[] deadends, String target){
        HashSet<String> deadset = new HashSet<>();
        for (String s: deadends)
            deadset.add(s);

        if (deadset.contains(target))
            return -1;

        if (deadset.contains("0000"))
            return -1;

        if (target.equals("0000"))
            return 0;

        Queue<String> queue = new LinkedList<>();
        HashMap<String, Integer> visited = new HashMap<>();

        queue.add("0000");
        visited.put("0000", 0);

        while(!queue.isEmpty()){
            String cur = queue.poll();
            char[] curArray = cur.toCharArray();

            ArrayList<String> nexts = new ArrayList<>();
            for (int i = 0; i < 4; i++){
                char o = curArray[i];

                curArray[i] = Character.forDigit((curArray[i] - '0' + 1) % 10, 10);
                nexts.add(new String(curArray));
                curArray[i] = o;

                curArray[i] = Character.forDigit((curArray[i] - '0' + 9) % 10, 10);
                nexts.add(new String(curArray));
                curArray[i] = o;
            }

            for (String next: nexts){
                if (!deadset.contains(next) && !visited.containsKey(next)){
                    queue.add(next);
                    visited.put(next, visited.get(cur) + 1);

                    if (next.equals(target))
                        return visited.get(next);
                }
            }
        }

        return -1;
    }
}