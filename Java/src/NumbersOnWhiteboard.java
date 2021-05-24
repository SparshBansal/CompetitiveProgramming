import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NumbersOnWhiteboard {
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

    public void solve(int n) {
        // start with n and go till 1
        int currentVal = n;
        System.out.println(2);
        for (int i=n-1; i>=1; i--) {
            System.out.println(currentVal + " " + i);
            currentVal = (int) Math.ceil((currentVal + i)/2.0);
        }
    }

    public static void main(String[] args) {
        FastReader fastReader = new FastReader();
        int t = fastReader.nextInt();
        while (t-- > 0) {
            int n = fastReader.nextInt();
            new NumbersOnWhiteboard().solve(n);
        }
    }
}
