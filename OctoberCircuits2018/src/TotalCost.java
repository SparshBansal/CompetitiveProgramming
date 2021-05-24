import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class TotalCost {

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

    public static void main(String[] args) {
        FastReader fastReader = new FastReader();
        int p = fastReader.nextInt(),
                s = fastReader.nextInt(),
                t = fastReader.nextInt(),
                h = fastReader.nextInt(),
                x = fastReader.nextInt();

        int currentAvailable = s;
        long sol = 0;
        for (int i = 0; i < x; i++) {

            if (currentAvailable <= t)
                sol += h;
            else
                sol += p;
            currentAvailable--;
        }
        System.out.println(sol);
    }
}
