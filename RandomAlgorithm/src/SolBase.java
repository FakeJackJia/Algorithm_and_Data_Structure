import java.util.Random;

public class SolBase {
    private Random rnd;

    public SolBase(){
        rnd = new Random();
    }

    public int rand7(){
        return rnd.nextInt(7) + 1;
    }
}
