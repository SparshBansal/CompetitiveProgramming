import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class PalindromePartitioning {

    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
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

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        String input = scanner.nextLine();

        Solution solution = new Solution();
        solution.minCut(input);
    }


    static class Solution {
        public int minCut(String s) {
            final int n = s.length();
            boolean[][] isPalindrome = new boolean[n][n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    isPalindrome[i][j] = false;

            // calculate the base dp
            for (int i = 0; i < n; i++) {
                isPalindrome[i][i] = true;
            }

            // consider all possible lengths
            for (int l = 1; l <= n; l++) {
                for (int i = 0; i < n - l; i++) {
                    int j = i + l;

                    // calculate dp[i][j]
                    isPalindrome[i][j] = (s.charAt(i) == s.charAt(j)) && checkIsPalindrome(isPalindrome, i + 1, j - 1);
                }
            }

            // now we need to partition the string into palindromes
            int dp[] = new int[n];
            for (int i = 0; i < n; i++)
                dp[i] = -1;
            return solve(dp, isPalindrome, n - 1);
        }

        private int solve(int[] dp, boolean[][] isPalindrome, int idx) {
            if (idx < 0) {
                return 0;
            }

            if (dp[idx] != -1) {
                return dp[idx];
            }

            // compute the dp idx
            int minCuts = Integer.MAX_VALUE;
            for (int l = 1; idx - l + 1 >= 0; l++) {
                int i = idx - l + 1;
                if (checkIsPalindrome(isPalindrome, i, idx)) {
                    minCuts = Math.min(minCuts, solve(dp, isPalindrome, i - 1) + 1);
                }
            }
            dp[idx] = minCuts;
            return dp[idx];
        }

        private boolean checkIsPalindrome(boolean dp[][], int i, int j) {
            if (i > j) {
                return true;
            }
            return dp[i][j];
        }

    }
}
