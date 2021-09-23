import java.io.*;
import java.util.StringTokenizer;

class SPCTRIPS {
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


    int[] pairs;

    int MAX_VAL = 100000;

    public SPCTRIPS() {
        pairs = new int[MAX_VAL+1];

        // now we fill this array with what?
        pairs[0] = 0;
        pairs[1] = 0;

        for (int i = 2; i < MAX_VAL; i++) {
            int total = 1 + pairs[i-1];
            for (int j = 2; j <= Math.sqrt(i); j++) {
                if (i % j == 0) {
                    int other = i / j;
                    if (j == other) total += 1;
                    else total += 2;
                }
            }
            pairs[i] = total;
        }
    }

    public int solve(int n) {
        // maybe we can fix c
        // lets start with c
        int ans = 0;
        for (int c = 1; ((n-c)/c) >= 1; c++) {
            ans += ((n/c) - 1);
            ans += pairs[((n-c)/c)];
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        SPCTRIPS solver = new SPCTRIPS();
        while (t-- > 0) {
            int n = scanner.nextInt();
            System.out.println(solver.solve(n));
        }
    }
}
