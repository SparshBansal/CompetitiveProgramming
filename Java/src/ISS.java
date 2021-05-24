import java.io.*;
import java.util.*;

public class ISS {
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


    long _gcd(long a, long b)
    {
        if (a == 0)
            return b;
        return _gcd(b % a, a);
    }

    public long[] solve(int k) {
        // generate 2k+1, numbers find gcd and solve
        long[] ans = new long[k+1];

        long ul = 2*k + 1;
        long sum = 0;
        long prev = k+1;
        for (int i=2; i<=ul; i++) {
            long curr = k + (i * i);
            long gcd = _gcd(curr, prev);
            sum += gcd;
            if (i%2 == 1) {
                ans[(i-1)/2] = sum;
            }
            prev = curr;
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        List<Integer> tests = new ArrayList<>();
        int maxK = Integer.MIN_VALUE;
        while (t-- > 0) {
            int k = scanner.nextInt();
            tests.add(k);
            maxK = Math.max(maxK, k);
        }

        long[] ansMap = new ISS().solve(maxK);
        for (int i : tests) {
            System.out.println(ansMap[i]);
        }
    }
}
