import java.io.*;
import java.util.StringTokenizer;

public class MinimumWindowSubstring {
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


    public String solve(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        // compute the frequency map of t
        int[] freqT = new int[52];
        for (int i = 0; i < t.length(); i++) {
            freqT[index(t.charAt(i))]++;
        }

        // now we use a two pointer method to figure out the window
        int left = 0, right = 0;
        int ansLeft = -1, ansRight = -1, minLength = Integer.MAX_VALUE;

        int[] freqWindow = new int[52];

        while(right < s.length()) {
            // add current element
            freqWindow[index(s.charAt(right))]++;
            while (isPossible(freqWindow, freqT) && left <= right) {
                // current window is a valid answer, check if we can use ti
                int length = right - left + 1;
                if (length < minLength) {
                    minLength = length;
                    ansLeft = left;
                    ansRight = right;
                }
                // remove the freq from the map
                freqWindow[index(s.charAt(left))]--;
                left++;
            }
            right++;
        }

        if (ansLeft != -1) {
            return s.substring(ansLeft, ansRight + 1);
        }
        return "";
    }

    public int index(char c) {
        if (c >= 'a' && c <= 'z') return c - 'a';
        return 26 + (c - 'A');
    }

    private boolean isPossible(int[] sub, int[] superSet) {
        for (int i=0; i<sub.length; i++) {
            if (sub[i] < superSet[i]) return false;
        }
        return true;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();

        while(t-- > 0) {
            String s = scanner.next();
            String p = scanner.next();
            System.out.println(new MinimumWindowSubstring().solve(s, p));
        }
    }
}
