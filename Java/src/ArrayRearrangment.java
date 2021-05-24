import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class ArrayRearrangment {
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

    public boolean solve(Integer[] a, Integer[] b, int x) {
        Arrays.sort(a,  Collections.reverseOrder());
        Arrays.sort(b);

        for (int i=0; i<a.length; i++) {
            if (a[i] + b[i] > x) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();

            Integer a[] = new Integer[n];
            Integer b[] = new Integer[n];

            for (int i=0; i<n; i++) {
                a[i] = scanner.nextInt();
                b[i] = scanner.nextInt();
            }

            if (new ArrayRearrangment().solve(a, b, x)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
