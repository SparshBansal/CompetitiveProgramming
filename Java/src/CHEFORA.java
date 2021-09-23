import java.io.*;
import java.util.*;

class CHEFORA {
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

    public static class CheforaGenerator implements Iterator<Long> {
        long left, center;

        public CheforaGenerator() {
            left = 0;
            center = 1;
        }

        @Override
        public boolean hasNext() {
            return true;
        }

        @Override
        public Long next() {
            long returnVal = left;
            if (left == 0) {
                returnVal = center;
            } else {
                // stitch the numbers together
                returnVal = (returnVal * 10) + center;
                long cache = left;
                while (cache != 0) {
                    returnVal = (returnVal * 10) + (cache % 10);
                    cache = cache / 10;
                }
            }
            // increment the center
            center = (center + 1) % 10;
            if (center == 0) {
                left++;
            }
            return returnVal;
        }

    }

    static long pow(long base, long exp, long MOD) {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res % MOD;
    }

    public static class Pair<T> {
        private T _1, _2;

        public T _1() {
            return _1;
        }

        public T _2() {
            return _2;
        }

        public Pair(T _1, T _2) {
            this._1 = _1;
            this._2 = _2;
        }
    }

    private static final long MOD = 1000000007;

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        int q = scanner.nextInt();
        List<Pair<Integer>> queries = new ArrayList<>();
        // figure out the max
        for (int i=0; i<q; i++) {
            queries.add(new Pair<>(scanner.nextInt(), scanner.nextInt()));
        }

        // find the max
        int maxVal = queries.stream().map(Pair::_2).max(Comparator.comparingInt(v -> v)).get();
        long[] vals = new long[maxVal + 1];
        long[] suff = new long[maxVal + 2];

        CheforaGenerator generator = new CheforaGenerator();
        for (int i=1; i<=maxVal; i++) {
            vals[i] = generator.next();
        }

        suff[maxVal] = vals[maxVal];
        suff[maxVal+1] = 0;

        for (int i=maxVal-1; i>0; i--) suff[i] = suff[i+1] + vals[i];

        // now solve queries
        for (Pair<Integer> query : queries) {
            int l = query._1();
            int r = query._2();
            System.out.println(pow(vals[l], suff[l+1] - suff[r+1], MOD));
        }
    }
}
