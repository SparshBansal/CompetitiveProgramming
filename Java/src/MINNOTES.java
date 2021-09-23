import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

class MINNOTES {
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

    public long gcd(long a, long b) {
        if (a % b == 0) {
            return b;
        }
        return gcd(b, a % b);
    }

    public long solve(long[] ar, int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        long[] leftGCD = new long[n];
        long[] rightGCD = new long[n];

        leftGCD[0] = ar[0];
        rightGCD[n - 1] = ar[n - 1];
        for (int i = 1; i < n; i++) leftGCD[i] = gcd(leftGCD[i - 1], ar[i]);
        for (int i = n - 2; i >= 0; i--) rightGCD[i] = gcd(rightGCD[i + 1], ar[i]);

        long sum = Arrays.stream(ar).sum();

        long min = Long.MAX_VALUE;
        for (int i=0; i<n; i++) {
            // assume i is what we change, find the denomination
            long leftDen = i > 0 ? leftGCD[i-1] : rightGCD[i+1];
            long rightDen = i < n-1 ? rightGCD[i+1] : leftGCD[i-1];
            long denomination = gcd(leftDen, rightDen);

            // num notes
            long numNotes = (sum - ar[i])/denomination;
            min = Math.min(min, numNotes+1);
        }
        return min;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            long[] ar = new long[n];
            for (int i=0; i<n; i++) {
                ar[i] = scanner.nextLong();
            }
            System.out.println(new MINNOTES().solve(ar, n));
        }
    }
}
