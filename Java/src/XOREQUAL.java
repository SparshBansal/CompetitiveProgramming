import java.io.*;
import java.util.StringTokenizer;

public class XOREQUAL {
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


    /* Iterative Function to calculate (x^y)%p in O(log y) */
    long MOD = 1000000000 + 7;

    long power(long x, long y)
    {
        long res = 1;     // Initialize result

        x = x % MOD; // Update x if it is more than or
        // equal to p

        if (x == 0) return 0; // In case x is divisible by p;

        while (y > 0)
        {
            // If y is odd, multiply x with result
            if ((y & 1)  == 1)
                res = (res*x) % MOD;

            // y must be even now
            y = y>>1; // y = y/2
            x = (x*x) % MOD;
        }
        return res;
    }


    public long solve(int n) {
        return power(2, n-1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            System.out.println(new XOREQUAL().solve(n));
        }
    }
}
