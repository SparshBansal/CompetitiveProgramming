package dec_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class VACCINE2 {
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

    private static int solve(int[] ages, int d) {
        int count_risk = 0, count_safe = 0;
        for (int age : ages) {
            if (age >= 80 || age <= 9) {
                count_risk++;
            } else {
                count_safe++;
            }
        }
        return getDays(count_risk, d) + getDays(count_safe, d);
    }

    private static int getDays(int n, int d) {
        if (n % d == 0) {
            return n/d;
        }
        return (n/d)+1;
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int d = scanner.nextInt();

            int[] ages = new int[n];

            for (int i=0; i<n; i++) {
                ages[i] = scanner.nextInt();
            }

            System.out.println(solve(ages, d));
        }
    }
}
