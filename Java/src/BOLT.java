import java.io.*;
import java.util.StringTokenizer;

class BOLT {

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



    final double RECORD = 9.58;

    private boolean solve(double k1, double k2, double k3, double v) {
        double vfinal = v * k1 * k2 * k3;

        // round to 2 decimal digits
        double time = 100.0/vfinal;
        double roundOff = (double) Math.round(time * 100) / 100;
        return roundOff < RECORD;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();
        while (t-- > 0) {

            double k1 = scanner.nextDouble();
            double k2 = scanner.nextDouble();
            double k3 = scanner.nextDouble();
            double v = scanner.nextDouble();

            if (new BOLT().solve(k1, k2, k3, v)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }


}
