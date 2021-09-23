import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

class XXOR {
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

    public int solve(int[] ar, int n, int k) {
        // get the number of bits at each location
        Map<Integer, Integer> bitCount = new HashMap<>();

        for (int num : ar) {
            int loc = 0;
            while (num != 0) {
                // odd means bit is set
                if (num % 2 == 1) bitCount.put(loc, bitCount.getOrDefault(loc, 0) + 1);
                num = num >> 1;
                loc++;
            }
        }

        // iterate over map entries
        int ans = 0;
        for (int count : bitCount.values()) {
            ans += Math.ceil(((double)count)/k);
        }
        return ans;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            int k = scanner.nextInt();
            int ar[] = new int[n];
            for (int i=0; i<n; i++) {
                ar[i] = scanner.nextInt();
            }
            System.out.println(new XXOR().solve(ar, n, k));
        }
    }
}
