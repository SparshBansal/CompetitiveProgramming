import java.io.*;
import java.util.StringTokenizer;

public class ArrangingTheSheep {
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


    public long solve(String sheeps, int n) {
        if (n==1) {
            return 0;
        }
        // get the count of sheep in prefix and suffix arrays
        long[] countLeft = new long[n];
        long[] countRight = new long[n];
        populateLeft(sheeps, n, countLeft);
        populateRight(sheeps, n, countRight);
        // now we can solve for left and right separately
        long[] costLeft = new long[n];
        long[] costRight = new long[n];
        costLeft[0] = 0;
        for (int i=1; i<n; i++) {
            if (sheeps.charAt(i) == '*') {
                costLeft[i] = costLeft[i-1];
            } else {
                costLeft[i] = costLeft[i-1] + (countLeft[i-1]);
            }
        }
        costRight[n-1] = 0;
        for (int i=n-2; i>=0; i--) {
            if (sheeps.charAt(i) == '*') {
                costRight[i] = costRight[i+1];
            } else {
                costRight[i] = costRight[i+1] + countRight[i+1];
            }
        }
        long globalMin = Long.MAX_VALUE;
        for (int i=0; i<n-1; i++) {
            globalMin = Math.min(globalMin, costLeft[i] + costRight[i+1]);
        }
        return globalMin;
    }

    private void populateRight(String sheeps, int n, long[] ar) {
        int prevCount = 0;
        for (int i=n-1; i>=0; i--) {
            if (sheeps.charAt(i) == '*') {
                ar[i] = (++prevCount);
            } else {
                ar[i] = prevCount;
            }
        }
    }

    private void populateLeft(String sheeps, int n, long[] ar) {
        int prevCount = 0;
        for (int i=0; i<n; i++) {
            if (sheeps.charAt(i) == '*') {
                ar[i] = (++prevCount);
            } else {
                ar[i] = prevCount;
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String input = scanner.nextLine();
            System.out.println(new ArrangingTheSheep().solve(input, n));
        }
    }
}
