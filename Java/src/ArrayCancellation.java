import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ArrayCancellation {
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

    private class Value {
        int idx, val;

        public Value(int idx, int val) {
            this.idx = idx;
            this.val = val;
        }
    }

    long solve(int[] ar) {
        final int n = ar.length;

        long currentNegative = 0;
        long cost = 0;

        for (int i=n-1; i>=0; i--) {
            if (ar[i] < 0) {
               currentNegative += ar[i];
            } else if (ar[i] > 0) {
                // now two cases
                if (currentNegative != 0) {
                    // we carry a negative backlog
                    if (Math.abs(currentNegative) < ar[i]) {
                        // we will incur a cost
                        cost += (ar[i] + currentNegative);
                        currentNegative = 0;
                    } else {
                        // we will incur no cost
                        // just decrement current negative
                        currentNegative += ar[i];
                    }
                } else {
                    cost += ar[i];
                }
            }
        }
        return cost;
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];

            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();

            System.out.println(new ArrayCancellation().solve(ar));
        }
    }
}
