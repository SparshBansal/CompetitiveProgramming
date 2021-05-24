import java.io.*;
import java.util.StringTokenizer;

class SSCRIPT {
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

    private boolean solve(String s, int n, int k) {
        // check for k consecutive same chars
        int ctr = 0;
        for (int i=0; i<n; i++) {
            if (s.charAt(i) == '*') {
                ctr++;
            } else {
                ctr = 0;
            }
            if (ctr >= k) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();

            String in = scanner.nextLine();
            System.out.println(new SSCRIPT().solve(in, n, k) ? "YES" : "NO");
        }
    }
}
