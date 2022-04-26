import java.io.*;
import java.util.StringTokenizer;

public class XORPAL {
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

    public String solve(String ar, int n) {
        int numOnes = 0;
        for (char c : ar.toCharArray()) {
            switch (c) {
                case '1':
                    numOnes++;
            }
        }
        // if length is odd, then the only xor possible is zero
        if (n % 2 == 1) {
            return "YES";
        }
        // if n is even we can
        boolean zeroXorPossible = false, oneXorPossible = false;
        if (numOnes % 2 == 0) zeroXorPossible = true;
        if (numOnes == (n / 2)) oneXorPossible = true;
        return (zeroXorPossible || oneXorPossible) ? "YES" : "NO";
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String ar = scanner.nextLine();
            System.out.println(new XORPAL().solve(ar, n));
        }
    }
}
