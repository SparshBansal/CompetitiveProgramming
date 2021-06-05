import java.io.*;
import java.util.StringTokenizer;

public class PerfectlyImperfectArray {
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

    public boolean solve(int ar[], int n) {
        for (int i=0; i<n; i++) {
            if (!isPerfectSq(ar[i])) {
                return true;
            }
        }
        return false;
    }

    private boolean isPerfectSq(int num) {
        int ul = ((int) Math.sqrt(num)) + 1;
        for (int i=1; i<=ul; i++) {
            if (num == (i * i))
                return true;
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();
            if (new PerfectlyImperfectArray().solve(ar, n))
                System.out.println("YES");
            else
                System.out.println("NO");
        }
    }
}
