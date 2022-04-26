import java.io.*;
import java.util.StringTokenizer;

public class BITBLEND {
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

//    public int findCost()

    public int solve(int[] ar, int n) {

        // create a current parity array
        int[] parity = new int[n];
        for (int i =0; i<n; i++) {
            parity[i] = ar[i] % 2;
        }

        if (n == 1) {
            return 0;
        }

        // find the num ones and num zero
        int numOnes = 0;
        int numZeros = 0;
        for (int val : parity) {
            if (val == 0) numZeros++;
            else numOnes++;
        }
        if (numOnes == 0) {
            // can't make anything happen
            return -1;
        }
        // there can be two target arrays
        // [1, 0, 1, 0...]
        // [0, 1, 0, 1...]

        return 0;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

    }
}
