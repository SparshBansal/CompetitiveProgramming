
import java.io.*;
import java.util.StringTokenizer;

public class MoonsAndUmbrella {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

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


    public int solve(int c, int j, String s) {
        char[] chars = s.toCharArray();

        // check if entire string is ?
        long cnt = s.chars().filter(ch -> ch == '?').count();
        if (cnt == 0) {
            return computeCost(new String(chars), j, c);
        }
        if (cnt == s.length()) {
            // we can fill the entire string with any character
            fill(chars, 0, chars.length, 'C');
            return computeCost(new String(chars), j, c);
        }

        // fill the trailing and leading characters
        // find first non ? character
        int first = findFirst(chars, '?');
        if (first == 0) {
            // fill leading chars
            int cIdx = findFirst(chars, 'C');
            int jIdx = findFirst(chars, 'J');
            if (cIdx < jIdx) {
                fill(chars, 0, cIdx, 'C');
            } else {
                fill(chars, 0, jIdx, 'J');
            }
        }

        // check if trailing ?
        int last = findLast(chars, '?');
        if (last == chars.length - 1) {
            // trailing chars exist
            int cIdx = findLast(chars, 'C');
            int jIdx = findLast(chars, 'J');
            if (cIdx > jIdx) {
                fill(chars, cIdx + 1, chars.length, 'C');
            } else {
                fill(chars, jIdx + 1, chars.length, 'J');
            }
        }

        // no leading or trailing ? are left
        for (int i=1; i<chars.length; i++) {
            if (chars[i] == '?') {
                chars[i] = chars[i-1];
            }
        }

        return computeCost(new java.lang.String(chars), j, c);
    }

    private int computeCost(String s, int j, int c) {
        if (s.length() == 1) {
            return 0;
        }
        int cost = 0;
        for (int i=1; i<s.length(); i++) {
            if (s.charAt(i-1) == 'J' && s.charAt(i) == 'C') {
                cost += j;
            } else if (s.charAt(i-1) == 'C' && s.charAt(i) == 'J') {
                cost += c;
            }
        }
        return cost;
    }

    private void fill(char[] chars, int start, int end, char c) {
        for (int i = start; i < end; i++) {
            if (chars[i] == '?') {
                chars[i] = c;
            }
        }
    }

    private int findLast(char[] chars, char c) {
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == c) {
                return i;
            }
        }
        return -1;
    }

    private int findFirst(char[] chars, char c) {
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == c) {
                return i;
            }
        }
        return chars.length;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();

        int q = 0;
        while (t-- > 0) {
            q++;
            int x = scanner.nextInt();
            int y = scanner.nextInt();

            String in = scanner.next();
            System.out.println("Case #" + q + ": " + new MoonsAndUmbrella().solve(x, y, in));
        }
    }
}
