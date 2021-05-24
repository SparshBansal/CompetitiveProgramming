import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class NAM {
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

    int[][] solve(int n) {
        int[][] ans = new int[n][n];
        if (n == 1) {
            ans[0][0] = 1;
            return ans;
        }
        if (n == 2) {
            return null;
        }
        // now for n>=3 ans always exists
        // allocate all in order and rotate every alternate column
        int start = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans[i][j] = start++;
            }
        }

        // rotate every alternate column
        for (int j=1; j<n; j+=2) {
            // rotate column j
            int tmp = ans[0][j];
            for (int i=0; i<n-1; i++) {
                ans[i][j] = ans[i+1][j];
            }
            ans[n-1][j] = tmp;
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[][] ans = new NAM().solve(n);
            if (ans == null) {
                System.out.println(-1);
                continue;
            }
            for (int i = 0; i < n; i++) {
                System.out.println(Arrays.stream(ans[i])
                        .mapToObj(String::valueOf).collect(Collectors.joining(" ")));
            }
        }
    }
}
