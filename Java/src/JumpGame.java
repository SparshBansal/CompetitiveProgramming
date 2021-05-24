import java.io.*;
import java.util.*;

public class JumpGame {
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

    private static class ValIdx {
        int val;
        int idx;

        public ValIdx(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }
    }

    public int maxJumps(int[] arr, int d) {
        final int n = arr.length;
        // push sentinal values from the start and end of the stack
        final int SENTINAL_START = (1000001);
        final int SENTINAL_END = (1000000);

        final int[] dp = new int[n + 2];
        final int[] mVal = new int[n + 2];
        mVal[0] = SENTINAL_START;
        mVal[1] = SENTINAL_END;
        // base case
        for (int i=0; i<n+2; i++)
            dp[i] = 1;

        for (int i=0; i<n; i++) {
            mVal[i+1] = arr[i];
        }
        dp[0] = 0;
        List<ValIdx> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            list.add(new ValIdx(arr[i], i + 1));
        }
        list.add(new ValIdx(SENTINAL_END, n + 1));

        Stack<ValIdx> mStack = new Stack<>();
        mStack.add(new ValIdx(SENTINAL_START, 0));

        // now iterate over the list
        for (ValIdx current : list) {
            ValIdx top = mStack.peek();

            int currentIdx = current.idx;

            while (top.val <= current.val) {
                // need to pop this element, and compute
                int topIdx = top.idx;
                // now the max from the right side is
                int ub = Math.min(topIdx + d, currentIdx - 1);

                for (int i = topIdx + 1; i <= ub; i++) {
                    dp[topIdx] = Math.max(dp[topIdx], dp[i] + 1);
                }
                mStack.pop();
                top = mStack.peek();
            }

            // now compute the dp value for this idx
            int lb = Math.max(currentIdx - d, top.idx + 1);
            for (int i = currentIdx - 1; i >= lb; i--) {
                if (mVal[currentIdx] == mVal[i]) {
                    continue;
                }
                dp[current.idx] = Math.max(dp[currentIdx], dp[i] + 1);
            }
            mStack.push(current);
        }
        // now we just need to find max of dp element;
        int ans = 0;
        for (int i=1; i<=n; i++) {
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int d = scanner.nextInt();
            int[] arr = new int[n];
            for (int i=0; i<n; i++) {
                arr[i] = scanner.nextInt();
            }
            System.out.println(new JumpGame().maxJumps(arr, d));
        }
    }
}
