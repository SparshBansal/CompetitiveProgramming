import java.io.*;
import java.util.StringTokenizer;

public class BINBASBASIC {
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


    public String solve(String binary , int k) {
        // evaluate necessary
        boolean isOdd = binary.length() % 2 == 1;
        int l = 0, r = binary.length() - 1;

        int necessary = 0;

        while (l < r) {
            if (binary.charAt(l) != binary.charAt(r)) {
                necessary++;
            }
            l++;
            r--;
        }
        if (necessary > k) {
            return "NO";
        }
        // now if necessary is less than k
        int remaining = k - necessary;
        if (isOdd) return "YES";
        return remaining % 2 == 0 ? "YES" : "NO";
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            String binary = scanner.nextLine();
            System.out.println(new BINBASBASIC().solve(binary, k));
        }
    }
}
