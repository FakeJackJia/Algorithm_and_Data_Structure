public class SubstringMatchHelper {

    private SubstringMatchHelper(){}

    public static void matchTest(String name, String s, String t){
        int pos = -1;

        long startTime = System.nanoTime();

        if (name.equals("brutoforce"))
            pos = SubstringMatch.bruteforce(s, t);
        else if (name.equals("rk"))
            pos = SubstringMatch.rabinKarp(s, t);
        else if (name.equals("kmp"))
            pos = SubstringMatch.KMP(s, t);

        long endTime = System.nanoTime();

        double time = (endTime - startTime) / 1000000000.0;

        if (s.indexOf(t) != pos)
            throw new IllegalArgumentException("failed");

        System.out.println(String.format("position = %d, time = %f s", pos, time));
    }

}
