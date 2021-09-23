import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumPartitions {
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

    public int function(int[] dp, char[] arr, int idx, int val) {
        if (idx < 0) {
            return 1;
        }
        if (dp[idx] != -1) {
            return dp[idx];
        }

        if (arr[idx] - '0' > val) {
            // we cant do anything here
            arr[idx] = 0;
            return arr[idx];
        }

        // else we just need to start partitioning
        int currentPow = 1;
        int currentVal = 0;
        int partitions = 0;
        int idxVal = idx;

        while (idx >= 0 && (currentVal + ((arr[idx] - '0') * currentPow) <= val)) {
            currentVal = (currentVal + ((arr[idx] - '0') * currentPow));
            currentPow *= 10;
            idx--;
            partitions += function(dp, arr, idx, val);
        }

        dp[idxVal] = partitions;
        return dp[idxVal];
    }

    public int solve(char[] arr, int val) {
        int[] dp = new int[arr.length];
        // solve for the base case
        // number of different partition

        Arrays.fill(dp, -1);
        return function(dp, arr, arr.length-1, val);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        String arr = scanner.nextLine();
        System.out.println(new NumPartitions().solve(arr.toCharArray(), n));
    }
}
