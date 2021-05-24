import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class CHHAPPY {
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

    private static final int MAXL=100001;

    public static void main(String[] args) {
        int t,n;
        int[] ar = new int[MAXL];
        int[] lr = new int[MAXL];

        FastReader fastReader = new FastReader();
        t = fastReader.nextInt();

        while (t-- > 0) {
            n = fastReader.nextInt();
            ar[0] = -1;
            for (int i = 1; i <= n; i++) {
                ar[i] = fastReader.nextInt();
                lr[i] = -1;
            }
            lr[n] = -1;
            boolean contains = false;
            for (int i=1; i<=n; i++) {
                int val = ar[ar[i]];
                if (lr[val] == -1)
                    lr[val] = ar[i];

                if (lr[val] != ar[i]) {
                    contains = true;
                    break;
                }
            }

            if (contains)
                System.out.println("Truly Happy");
            else
                System.out.println("Poor Chef");
        }
    }
}
