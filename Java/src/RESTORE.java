import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

class RESTORE {
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

    final int MAXL = 4 * 1000000;

    int[] sequence(int[] a) {
        // now to solve for it
        int currentCounter = MAXL;
        final int n = a.length;
        int[] b = new int[n];

        for (int i=n-1; i>=0; i--) {
            if (a[i] != (i+1)) {
                b[i] = b[a[i]-1];
            } else {
                b[i] = currentCounter--;
            }
        }
        return b;
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] a = new int[n];

            for (int i=0; i<n; i++)
                a[i] = scanner.nextInt();

            int[] b = new RESTORE().sequence(a);
            String sequence = Arrays.stream(b).mapToObj(String::valueOf).collect(Collectors.joining(" "));
            System.out.println(sequence);
        }
    }
}
