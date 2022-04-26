import java.io.*;
import java.util.StringTokenizer;

public class AIRLINE {
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

    public boolean solve(int a, int b, int c, int d, int e) {
        // assume a is checkin
        if (a <= e && (b + c) <= d) return true;
        else if (b <= e && (a + c) <= d) return true;
        else return c <= e && (a + b) <= d;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            System.out.println(new AIRLINE().solve(scanner.nextInt(), scanner.nextInt(), scanner.nextInt(),
                    scanner.nextInt(), scanner.nextInt()) ? "YES" : "NO");
        }
    }
}
