import java.io.*;
import java.util.StringTokenizer;

public class BalanceTheBits {
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

    public void solve(String in, int n) {
        // basic checks
        if (n % 2 != 0) {
            // odd elements not possible to balance
            System.out.println("NO");
            return;
        }
        // first and last chars should not be 0
        if (in.charAt(0) == '0' || in.charAt(n - 1) == '0') {
            System.out.println("NO");
            return;
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (in.charAt(i) == '0') cnt++;
        }
        if (cnt % 2 != 0) {
            System.out.println("NO");
            return;
        }
        char[] a = new char[n];
        char[] b = new char[n];

        // we can solve it in two passes
        char prev = ')';
//        for (int i=0; i<n; i++) {
//            if (in.charAt(i) == '1') {
//                prev = flip(prev);
//                a[i] = prev;
//                b[i] = prev;
//            }
//        }
//        // second pass should fill the 0
//        prev = ')';
//        for (int i=0; i<n; i++) {
//            if (in.charAt(i) == '0') {
//                prev = flip(prev);
//                a[i] = prev;
//                b[i] = flip(prev);
//            }
//        }
//        for (int i=0; i<n; i++) {
//            prev = flip(prev);
//            a[i] = prev;
//            if (in.charAt(i) == '0') {
//                b[i] = flip(prev);
//            } else {
//                b[i] = prev;
//            }
//        }

        a[0] = '(';
        b[0] = '(';
        a[n - 1] = ')';
        b[n - 1] = ')';
        for (int i = 1; i < n - 1; i++) {
            if (in.charAt(i) == '0') {
                prev = flip(prev);
                a[i] = prev;
                b[i] = flip(prev);
            } else {
                a[i] = '.';
                b[i] = '.';
            }
        }

        // try with opening first and then closing
        prev = ')';
        Pair<StringBuilder> mPair = createStrings(a, b, n, prev);
        if (!areBalanced(mPair._1().toString(), mPair._2().toString())) {
            prev = '(';
            mPair = createStrings(a, b, n, prev);
        }
        System.out.println("YES");
        System.out.println(mPair._1().toString());
        System.out.println(mPair._2().toString());

        prev = '(';

    }

    private Pair<StringBuilder> createStrings(char[] a, char[] b, int n, char prev) {
        StringBuilder aBuilder = new StringBuilder(n);
        StringBuilder bBuilder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            if (a[i] != '.') {
                aBuilder.append(a[i]);
                bBuilder.append(b[i]);
            } else {
                prev = flip(prev);
                aBuilder.append(prev);
                bBuilder.append(prev);
            }
        }
        return new Pair<>(aBuilder, bBuilder);
    }

    public static class Pair<T> {
        private final T _1, _2;

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


    private boolean areBalanced(String a, String b) {
        return isBalanced(a) && isBalanced(b);
    }

    private boolean isBalanced(String a) {
        int n = a.length();
        int counter = 0;
        for (int i = 0; i < n; i++) {
            if (a.charAt(i) == '(')
                counter++;
            else
                counter--;
            if (counter < 0)
                return false;
        }
        return true;
    }

    private char flip(char prev) {
        if (prev == '(')
            return ')';
        return '(';
    }

    private char getChar(char prev) {
        if (prev == '(') {
            return ')';
        }
        return '(';
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();
            String in = scanner.nextLine();
            new BalanceTheBits().solve(in, n);
        }
    }
}
