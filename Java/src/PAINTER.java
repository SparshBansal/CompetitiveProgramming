import java.io.*;
import java.util.StringTokenizer;

public class PAINTER {
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


    public enum Color {
        U(new int[]{0, 0, 0}),
        R(new int[]{1, 0, 0}),
        Y(new int[]{0, 1, 0}),
        B(new int[]{0, 0, 1}),
        O(new int[]{1, 1, 0}),
        P(new int[]{1, 0, 1}),
        G(new int[]{0, 1, 1}),
        A(new int[]{1, 1, 1});

        int[] state;

        Color(int[] state) {
            this.state = state;
        }
    }

    private int numStrokes(Color from, Color to) {
        int strokes = 0;
        for (int i = 0; i < 3; i++) {
            if (from.state[i] != to.state[i] && from.state[i] == 0) strokes++;
        }
        return strokes;
    }

    public int solve(String painting) {
        Color current = Color.U;
        // now we move to the states and figure out number of strokes needed
        int total = 0;
        for (char c : painting.toCharArray()) {
            Color to = Color.valueOf(String.valueOf(c));
            total += numStrokes(current, to);
            current = to;
        }
        return total;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        int q = 1;
        while (t-- > 0) {
            int n = scanner.nextInt();
            String painting = scanner.nextLine();
            System.out.println("Case #" + (q++) + ": " + new PAINTER().solve(painting));
        }
    }
}
