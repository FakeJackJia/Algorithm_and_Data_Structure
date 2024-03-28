import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RandomPickIndex {
    private HashMap<Integer, ArrayList<Integer>> map;
    private Random rnd;

    public RandomPickIndex(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++){
            if (!map.containsKey(nums[i]))
                map.put(nums[i], new ArrayList<>());
            map.get(nums[i]).add(i);
        }

        rnd = new Random();
    }

    public int pick(int target) {
        int size = map.get(target).size();

        return map.get(target).get(rnd.nextInt(size));
    }
}