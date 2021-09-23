import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

class ARRFILL {
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

    private static class Query {
        long x, y;

        public Query(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }


    public long _gcd(long a, long b) {
        if (a%b == 0) return b;
        return _gcd(b, a%b);
    }

    public long solve(long n, List<Query> queries) {
        // now we need to sort the queries by y and then by x
        queries.sort((l, r)-> {
            if (l.x != r.x) {
                return Long.compare(r.x , l.x);
            }
            return Long.compare(r.y , l.y);
        });


        queries = queries.stream().filter(q -> q.y != 1).collect(Collectors.toList());

        if (queries.size() == 0) return 0;

        long totalSum = 0;
        long remaining = n;
        long lcm = 1;
        long product = 1;
        long gcd = -1;

        // lets start with the first element
        Query first = queries.get(0);

        // how many will be remaining
        remaining = n / first.y;
        totalSum += (n - remaining) * first.x;
        product = first.y;
        lcm = first.y;
        gcd = first.y;

        for (int i=1; i<queries.size(); i++) {
            // calculate gcd and lcm
            Query q = queries.get(i);
            long newGcd = _gcd(q.y, gcd);
            long newLcm = (product * q.y) / _gcd(q.y, gcd);

            // all indexes already covered
            if (newLcm == lcm) continue;

            // else find out new remaining
            long newRemaining = n / newLcm;

            totalSum += ((remaining - newRemaining) * q.x);

            remaining = newRemaining;
            gcd = newGcd;
            lcm = newLcm;

            if (remaining == 0) return totalSum;
        }
        return totalSum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        int t = scanner.nextInt();
        while (t-- > 0) {
            long n = scanner.nextInt();
            long m = scanner.nextInt();

            List<Query> queries = new ArrayList<>();
            LongStream.range(0, m).forEach(val -> queries.add(new Query(scanner.nextInt(), scanner.nextInt())));
            System.out.println(new ARRFILL().solve(n, queries));
        }
    }
}
