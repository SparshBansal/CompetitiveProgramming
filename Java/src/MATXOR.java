import java.io.*;
import java.util.StringTokenizer;

class MATXOR {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

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

    private long xorTill(long n) {
        if (n % 4 == 0) {
            return n;
        }
        if (n % 4 == 1)
            return 1;

        // If n%4 gives remainder 2
        if (n % 4 == 2)
            return n + 1;

        // If n%4 gives remainder 3
        return 0;
    }

    public long solve(int n, int m, int k) {
        long xor = 0;
        for (int i=1; i<=n; i++) {
            long start = xorTill(k + i);
            long end = xorTill(k + i + m);
            long rowXor = end ^ start;
            xor = xor ^ rowXor;
        }
        return xor;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int k = scanner.nextInt();

            System.out.println(new MATXOR().solve(n, m, k));
        }
    }
}
