import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class LengthOfValley {
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

    int[] solve(int[] ar) {
        final int n = ar.length;
        int[] left = new int[n];
        int[] right = new int[n];

        // now compute left and right values
        left[0] = 0;
        for (int i=1; i<n; i++) {
            if (ar[i] < ar[i-1]) {
                left[i] = left[i-1]+1;
            } else {
                left[i] = 0;
            }
        }
        right[n-1] = 0;
        for (int i=n-2; i>=0; i--) {
            if (ar[i] < ar[i+1]) {
                right[i] = right[i+1] + 1;
            } else {
                right[i] = 0;
            }
        }
        // now solve
        int[] ans = new int[n];
        for (int i=0; i<n; i++) {
            ans[i] = left[i] + right[i] + 1;
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int i=0; i<n; i++) {
                ar[i] = scanner.nextInt();
            }
            int[] ans = new LengthOfValley().solve(ar);
            // print the ans
            System.out.println(Arrays.stream(ans).boxed().map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
