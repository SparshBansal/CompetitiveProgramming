import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SameDifferences {
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

    public long solve(int[] ar, int n) {
        Map<Long, Long> counts = new HashMap<>();
        long ans = 0;
        for (int i=0; i<n; i++) {
            long current = ar[i] - (i+1);
            ans += counts.getOrDefault(current, 0L);
            counts.put(current, counts.getOrDefault(current, 0L) + 1);
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();
            System.out.println(new SameDifferences().solve(ar, n));
        }
    }
}
