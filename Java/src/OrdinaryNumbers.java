import java.io.*;
import java.util.StringTokenizer;

public class OrdinaryNumbers {
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

    public int solve(int n) {
        int ans = 0;
        int[] values = new int[9];
        for (int i = 1; i <= 9; i++) {
            values[i - 1] = i;
        }
        int ctr = 0;
        while (true) {
            if (values[ctr] > n)
                return ans;
            ans++;
            values[ctr] = (values[ctr] * 10) + (ctr + 1);
            ctr = (ctr + 1) % 9;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            System.out.println(new OrdinaryNumbers().solve(n));
        }
    }
}
