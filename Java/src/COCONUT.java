import java.io.*;
import java.util.StringTokenizer;

class COCONUT {
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

    public int solve(int xa, int xb, int Xa, int Xb) {
        int ans = (Xa / xa) + (Xb / xb);
        if (Xa % xa != 0) {
            ans++;
        }
        if (Xb % xb != 0) {
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int xa = scanner.nextInt();
            int xb = scanner.nextInt();
            int Xa = scanner.nextInt();
            int Xb = scanner.nextInt();
            System.out.println(new COCONUT().solve(xa, xb, Xa, Xb));
        }
    }
}
