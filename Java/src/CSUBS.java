import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class CSUBS {
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

    public int solve(int[] ar, int n, int k) {
        int ans = 0;
        for (int i=0; i<k; i++) {
            Map<Integer, Integer> freq = new HashMap<>();
            int idx = i;
            while (idx < n) {
                freq.put(ar[idx], freq.getOrDefault(ar[idx], 0) + 1);
                idx += k;
            }
            int total = freq.values().stream().mapToInt(val -> val).sum();
            int max = freq.values().stream().mapToInt(val -> val).max().getAsInt();
            ans += (total - max);
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int[] ar = new int[n];
            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();
            System.out.println(new CSUBS().solve(ar, n, k));
        }
    }
}
