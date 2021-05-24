import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class ADADISH {

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

    int minMinutes(int ar[]) {
        int n = ar.length;

        Arrays.sort(ar);

        int sumA = ar[n-1];
        int sumB = 0;

        for (int i=n-2; i>=0; i--) {
            if (sumA > sumB) {
                sumB += ar[i];
            } else {
                sumA += ar[i];
            }
        }
        return Math.max(sumA, sumB);
    }

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int ar[] = new int[n];
            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();

            System.out.println(new ADADISH().minMinutes(ar));
        }
    }
}
