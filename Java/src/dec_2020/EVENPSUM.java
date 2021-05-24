package dec_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class EVENPSUM {
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

    static FastReader scanner = new FastReader();

    private static long solve(int a, int b) {
        long n_odd_a = (a + 1) / 2;
        long n_odd_b = (b + 1) / 2;
        long n_even_a = a / 2;
        long n_even_b = b / 2;

        return (n_odd_a * n_odd_b) + (n_even_a * n_even_b);
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();
        while (t-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            System.out.println(solve(a, b));
        }
    }
}
