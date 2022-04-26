import java.io.*;
import java.util.Optional;
import java.util.StringTokenizer;

public class NOTUNIT {
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

    public static class Pair {
        int _1, _2;

        public Pair(int _1, int _2) {
            this._1 = _1;
            this._2 = _2;
        }
    }

    public Optional<Pair> solve(int a, int b) {
        if (Math.abs(a - b) < 3) return Optional.empty();
        if (Math.abs(a - b) == 3) {
            return a%2 == 0 ? Optional.of(new Pair(a, b)) : Optional.empty();
        }

        a = Math.min(a, b);
        // now greater than 3
        if (a % 2 == 0) return Optional.of(new Pair(a, a+2));
        return Optional.of(new Pair(a+1, a+3));
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();


            Optional<Pair> result = new NOTUNIT().solve(a, b);
            System.out.println(result.map(p -> p._1 + " " + p._2).orElse("-1"));
        }
    }
}
