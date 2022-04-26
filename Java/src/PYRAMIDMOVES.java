import java.io.*;
import java.util.StringTokenizer;

public class PYRAMIDMOVES {
    public static final long MOD = 1000000007;

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

    public long findLevel(long num) {
        int level = 1;
        while (true) {
            if ((level * (level + 1)) / 2 >= num) {
                return level;
            }
            ++level;
        }
    }

    public long findCoordinate(long num, long level) {
        long levelStart = ((level * (level + 1)) / 2) - level + 1;
        long startCoord = (-(level-1));

        long distance = (num - levelStart);
        return startCoord + (2 * distance);
    }

    long pow(long base, long exp, long MOD) {
        long res = 1;
        while (exp > 0) {
            if (exp % 2 == 1) res = (res * base) % MOD;
            base = (base * base) % MOD;
            exp /= 2;
        }
        return res % MOD;
    }


    long findTotal(long total, long movesA, long movesB) {
        long factorial = 1;
        long current = 0;
        long factA = 0, factB = 0, factN= 0;
        while (current <= total) {
            if (movesA == current) {
                factA = factorial;
            }
            if (movesB == current) {
                factB = factorial;
            }
            if (total == factorial) {
                factN = factorial;
            }
            factorial = ((factorial) * (++current)) % MOD;
        }

        long invA = pow(factA, MOD-2, MOD);
        long invB = pow(factB, MOD-2, MOD);
        long inv = (invA * invB) % MOD;
        return (factN * inv) % MOD;
    }

    public long solve(long s, long e) {
        long l_s = findLevel(s);
        long l_e = findLevel(e);

        if (l_e <= l_s) return 0;

        // find coordinates
        long c_s = findCoordinate(s, l_s);
        long c_e = findCoordinate(e, l_e);

        long totalMoves = l_e - l_s;
        long horizontalDistance = Math.abs(c_e - c_s);

        if (horizontalDistance > totalMoves) return 0;
        // else redundant moves
        long redundantMoves = totalMoves - horizontalDistance;
        if (redundantMoves % 2 == 1) return 0;

        long movesA = horizontalDistance + (redundantMoves / 2);
        long movesB = redundantMoves / 2;
        return findTotal(totalMoves, movesA, movesB);
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while(t-- > 0) {
            long s = scanner.nextLong();
            long e = scanner.nextLong();

            System.out.println(new PYRAMIDMOVES().solve(s, e));
        }
    }
}
