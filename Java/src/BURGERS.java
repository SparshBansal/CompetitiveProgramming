import java.io.*;
import java.util.StringTokenizer;

class BURGERS {
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

    int bitCount(long val) {
        int count = 0;
        while (val > 0) {
            count++;
            val = val >> 1;
        }
        return count;
    }

    public long solve(long x, long y) {
        if (y % x != 0) {
            return -1;
        }
        long sum = y / x;
        int bitCount = bitCount(sum);

        long[] power2 = new long[bitCount];
        long[] incr = new long[bitCount];

        power2[0] = 1;
        incr[0] = 1;
        for (int i=1; i<bitCount; i++) {
            power2[i] = (power2[i-1] << 1) + 1;
            incr[i] = incr[i-1] + 1;
        }

        long currentSum = 0;
        long totalMins = 0;
        boolean isFirst = true;
        for (int i=bitCount-1; i>=0; i--) {
            if (currentSum + power2[i] <= sum) {
                currentSum += power2[i];
                totalMins += incr[i];
                if (isFirst) {
                    isFirst = false;
                } else {
                    totalMins += 1;
                }
            }
            if (currentSum == sum) {
                return totalMins;
            }
        }
        return -1;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            long x = scanner.nextLong();
            long y = scanner.nextLong();
            System.out.println(new BURGERS().solve(x, y));
        }
    }
}
