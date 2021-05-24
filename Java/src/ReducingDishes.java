import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ReducingDishes {

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

    public int maxSatisfaction(int[] satisfaction) {
        final int n = satisfaction.length;

        // inplace sort
        Arrays.sort(satisfaction);

        long ans = 0;
        long runningSum = 0;

        long suffixSum = 0;

        for (int i=satisfaction.length-1; i>=0; i--) {
            runningSum = runningSum + suffixSum + satisfaction[i];
            ans = Math.max(ans, runningSum);
            suffixSum += satisfaction[i];
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int n = scanner.nextInt();

        int[] satisfaction = new int[n];
        for (int i=0; i<n; i++)
            satisfaction[i] = scanner.nextInt();

        System.out.println(new ReducingDishes().maxSatisfaction(satisfaction));
    }
}
