import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GMEDIAN {

    static final long MOD = 1000000007;
    static final int MAXL = 1001;

    static long[][] ncr = new long[MAXL][MAXL];
    static long[][] sum = new long[MAXL][MAXL];

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

    private static void init() {

        for (int i=1; i<MAXL; i++) {
            ncr[i][0] = 1;
            ncr[i][i] = 1;
        }

        for (int i=2; i<MAXL; i++) {
            for (int j=1; j<i; j++) {
                ncr[i][j] = ncr[i-1][j]%MOD + ncr[i-1][j-1];
            }
        }

        for (int i=1; i<6; i++) {
            for (int j=0; j<=i; j++) {
                System.out.print(ncr[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int t,n;
        FastReader fastReader = new FastReader();

        init();
        t = fastReader.nextInt();
        n = fastReader.nextInt();
//
//        while (t-- > 0) {
//            n = fastReader.nextInt();
//            int ar[] = new int[n];
//            int pre[] = new int[2*n + 1];
//
//            Arrays.fill(pre, 0);
//
//            for (int i = 0; i < n; i++) {
//                ar[i] = fastReader.nextInt();
//                pre[ar[i]]++;
//            }
//
//            pre[0] = 0;
//            for (int i=0; i<pre.length; i++) {
//                pre[i] = pre[i-1] + pre[i];
//            }
//
//            // for each element find number of elements less
//        }
    }
}
