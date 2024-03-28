public class RangSumQuery {
    private SegmentTree<Integer> tree;

    public void update(int index, int val){
        tree.set(index, val);
    }

    public RangSumQuery(int[] nums){
        if (nums.length > 0){
            Integer[] data = new Integer[nums.length];
            for (int i = 0; i < nums.length; i++)
                data[i] = nums[i];
            tree = new SegmentTree<>(data, (a, b) -> a + b);
        }
    }

    public int sumRange(int i, int j){
        return tree.query(i, j);
    }
}