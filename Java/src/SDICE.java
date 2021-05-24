import java.io.*;
import java.util.StringTokenizer;

class SDICE {
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

    private long solve(long n) {
        if (n == 1) {
            return 20;
        }
        if (n == 2) {
            return 40;
        }
        if (n == 3) {
            return 54;
        }
        if (n == 4) {
            return 68;
        }
        // for anything greater than 4 use the following formula
        return ((n - 4) * 14) + 68;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(System.in);

        int t = scanner.nextInt();
        while (t-- > 0) {
            long n = scanner.nextInt();
            System.out.println(new SDICE().solve(n));
        }
    }
}
