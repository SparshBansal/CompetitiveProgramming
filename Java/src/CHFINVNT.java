import java.io.*;
import java.util.StringTokenizer;

class CHFINVNT {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader(String[] args) throws FileNotFoundException {
            this(args.length > 0 ? new FileInputStream(new File(args[0])) : System.in);
        }

        public FastReader(InputStream inputStream) {
            br = new BufferedReader(new
                    InputStreamReader(inputStream));
        }

        public String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public int solve(int n, int p, int k) {
        // first we need to find
        n--;
        if (k > n) {
            return p+1;
        }

        if (p % k == 0) {
            return (p / k) + 1;
        }

        // else we need to identify
        int modVal = p % k;
        int iterationNumber = modVal + 1;
        int iterationsBefore = iterationNumber - 1;

        // now we need to check how many iterations before
        int iterationsFull = (n % k) + 1;

        int timeInFullIteration = (n / k) + 1;
        int timeInHalfIteration = timeInFullIteration - 1;

        int fullIterationCount = Math.min(iterationsBefore, iterationsFull);
        int halfIterationCount = iterationsBefore - fullIterationCount;

        int timeBeforeIteration = (fullIterationCount * timeInFullIteration) + (halfIterationCount * timeInHalfIteration);
        int timeInIteration = (p / k) + 1;
        return timeBeforeIteration + timeInIteration;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();

        while (t-- > 0) {
            System.out.println(new CHFINVNT().solve(scanner.nextInt(), scanner.nextInt(), scanner.nextInt()));
        }
    }
}
