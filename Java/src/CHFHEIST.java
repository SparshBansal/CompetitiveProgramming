import java.io.*;
import java.util.StringTokenizer;

class CHFHEIST {
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

    public long solve(long D, long d, long p, long q) {
        long segments = D / d;
        long numTerms = segments;
        long commonDifference = q;
        long a = p;
        long sum = ((numTerms) * (2 * a + ((numTerms - 1) * commonDifference))) / 2;
        sum *= d;

        if (D%d == 0) {
            return sum;
        }
        long remaining = (D % d);
        sum += remaining * (p + ((segments) * q));
        return sum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            long D = scanner.nextLong();
            long d = scanner.nextLong();
            long p = scanner.nextLong();
            long q = scanner.nextLong();
            System.out.println(new CHFHEIST().solve(D, d, p, q));
        }
    }
}
