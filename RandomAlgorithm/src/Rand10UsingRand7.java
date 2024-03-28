/**
 * The rand7() API is already defined in the parent class SolBase.
 * public int rand7();
 * @return a random integer in the range 1 to 7
 */
class Rand10UsingRand7 extends SolBase {
    public int rand10() {
        while (true){
            //treat them as index of 2D array
            //   0  1  2  3  4  5  6
            // 0 0  1  2  3  4  5  6
            // 1 7  8  9  10 11 12 13
            // 2 14 15 16 17 18 19 20
            // 3 21 22 23 24 25 26 27
            // 4 28 29 30 31 32 33 34
            // 5 35 36 37 38 39 40 41
            // 6 42 43 44 45 46 47 48
            //By mapping to the 2D array, only need up to 39 to obtain a value in rand10
            //Technique: Rejection Sampling
            int a = rand7() - 1;
            int b = rand7() - 1;

            //get the value at position (a, b)
            int t = a * 7 + b;

            if (t >= 40)
                continue;

            return t % 10 + 1;
        }
    }
}
