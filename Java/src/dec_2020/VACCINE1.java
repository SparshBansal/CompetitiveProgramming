package dec_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class VACCINE1 {
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

    private static int solve(int d1, int v1, int d2, int v2, int p) {
        int rate = (d1 < d2) ? v1 : (d1 > d2 ? v2 : -1);
        if (rate != -1) {
            // check the number of produced
            int produced = (Math.max(d1, d2) - Math.min(d1, d2)) * rate;
            if (produced > p) {
                // can be produced by one factory only
                int days = findDays(p, rate);
                return Math.min(d1, d2) - 1 + (days);
            } else {
                // produced is less than rate
                int remainder = p - produced;
                int days = produced / rate;

                return Math.min(d1, d2) - 1 + days + findDays(remainder, v1 + v2);
            }
        } else {
            // both start at same days
            return Math.min(d1, d2) - 1 + findDays(p, v1 + v2);
        }
    }

    private static int findDays(int required, int rate) {
        int days = 0;
        if (required % rate == 0) {
            days = required / rate;
        } else {
            days = (required / rate) + 1;
        }
        return days;
    }

    public static void main(String[] args) {
        int d1 = scanner.nextInt();
        int v1 = scanner.nextInt();
        int d2 = scanner.nextInt();
        int v2 = scanner.nextInt();
        int p = scanner.nextInt();

        System.out.println(solve(d1, v1, d2, v2, p));
    }
}
