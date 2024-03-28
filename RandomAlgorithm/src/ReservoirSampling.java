import java.util.Random;

public class ReservoirSampling {
    private ListNode head;
    private Random rnd;

    public ReservoirSampling(ListNode head){
        this.head = head;
        rnd = new Random();
    }

    public int getRandom(){
        int res = head.val;
        int index = 1;

        for (ListNode cur = head.next; cur != null; cur = cur.next, index++){
            int j = rnd.nextInt(index + 1);

            if (j == 0)
                res = cur.val;
        }

        return res;
    }
}
