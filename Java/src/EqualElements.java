import java.io.*;
import java.util.StringTokenizer;

public class EqualElements {
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

    private int solve(int ar[]) {
        final int n = ar.length;

        int oddCnt = 0, evenCnt = 0;
        for (int i = 0; i < n; i++) {
            if (ar[i] % 2 == 0) {
                evenCnt++;
            } else {
                oddCnt++;
            }
        }
        return Math.min(evenCnt, oddCnt);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));

        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int[] ar = new int[n];
            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();
            System.out.println(new EqualElements().solve(ar));
        }
    }
}
