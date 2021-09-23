import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class SHROUT {
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

    int[] solve(int[] ar, int[] b, int n, int m) {

        // create left and right array
        int[] left = new int[n];
        int[] right = new int[n];

        left[n-1] = ar[n-1] == 2 ? 0 : Integer.MAX_VALUE;
        for (int i=n-2; i>=0; i--) {
            // check if the current location is a train
            if (ar[i] == 2) {
                left[i] = 0;
                continue;
            }
            if (left[i+1] < Integer.MAX_VALUE) {
                left[i] = left[i+1] + 1;
                continue;
            }
            left[i] = Integer.MAX_VALUE;
        }
        right[0] = ar[0] == 1 ? 0 : Integer.MAX_VALUE;
        for (int i=1; i<n; i++) {
            if (ar[i] == 1) {
                right[i] = 0;
                continue;
            }
            if (right[i-1] < Integer.MAX_VALUE) {
                right[i] = right[i-1] + 1;
                continue;
            }
            right[i] = Integer.MAX_VALUE;
        }
        int[] optimal = new int[n];
        for (int i=0; i<n; i++) {
            optimal[i] = Math.min(left[i], right[i]);
        }

        int[] ans = new int[m];
        for (int i=0; i<m; i++) {
            if (b[i] == 0) {
                ans[i] = 0;
                continue;
            }
            ans[i] = optimal[b[i]] != Integer.MAX_VALUE ? optimal[b[i]] : -1;
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int[] ar = new int[n];
            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();
            int[] b = new int[m];
            for (int i=0; i<m; i++)
                b[i] = scanner.nextInt() - 1;
            System.out.println(Arrays.stream(new SHROUT().solve(ar, b, n, m)).mapToObj(String::valueOf).collect(
                    Collectors.joining(" ")));
        }
    }
}
