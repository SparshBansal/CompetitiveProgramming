import java.io.*;
import java.util.StringTokenizer;

public class ScrambleString {
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


    public int _dp(int[][][][] dp, int i, int j, int x, int y, String s, String t) {
        if (dp[i][j][x][y] != -1) return dp[i][j][x][y];
        if (Math.abs(j-i) != Math.abs(y - x)) {
            // not possible
            dp[i][j][x][y] = 0;
            return dp[i][j][x][y];
        }
        // base case
        if (i == j && x == y) {
            dp[i][j][x][y] = s.charAt(i) == t.charAt(x) ? 1 : 0;
            return dp[i][j][x][y];
        }

        // else we verify
        // we will start making cuts
        dp[i][j][x][y] = 0;
        for (int cut = i+1; cut <=j ; cut++) {
            int lengthOfCut = cut - 1 - i;
            // make a cut
            // now two possibilities
            // 1. either we did not swap
            if (_dp(dp, i, cut-1, x, x + lengthOfCut, s, t) == 1 &&
                    _dp(dp, cut, j, x + lengthOfCut + 1, y, s, t) == 1) {
                // then this is possible
                dp[i][j][x][y] = 1;
                return dp[i][j][x][y];
            }

            // 2. or we did swap
            if (_dp(dp, i, cut-1, y - lengthOfCut, y, s, t) == 1 &&
                    _dp(dp, cut, j, x, y - lengthOfCut - 1, s, t) == 1) {
                dp[i][j][x][y] = 1;
                return dp[i][j][x][y];
            }
        }
        return dp[i][j][x][y];
    }


    public boolean solve(String s, String t) {
        if (s.length()  != t.length()) return false;
        final int n = s.length();

        int dp[][][][] = new int[n][n][n][n];
        init(n, dp);

        // now we solve
        int ans = _dp(dp, 0, n-1, 0, n-1, s, t);
        return ans == 1;
    }

    private void init(int n, int[][][][] dp) {
        for (int i = 0; i<n; i++)
            for (int j = 0; j<n; j++)
                for (int k = 0; k<n; k++)
                    for (int l = 0; l<n; l++)
                        dp[i][j][k][l] = -1;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        String s = scanner.nextLine();
        String t = scanner.nextLine();
        System.out.println(new ScrambleString().solve(s, t));
    }
}
