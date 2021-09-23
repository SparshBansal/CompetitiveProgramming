import java.io.*;
import java.util.*;

public class LongestSubstringWithoutRepeatingChars {

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

    public int solve(String s) {
        Set<Character> bag = new HashSet<>();

        if (s.length() == 0) return 0;

        int left=0, right=0;
        int length = Integer.MIN_VALUE;

        while (right < s.length()) {
            // we are going to add right
            char r = s.charAt(right);
            if (!bag.contains(r)) {
                // we can simply add this to the map
                bag.add(r);
                // see if we have hit a max length
                length = Math.max(length, right - left + 1);
            } else {
                // bag already contains r, remove left till we are able to hit r
                while(s.charAt(left) != r && left <= right) {
                    // remove char left from the bag
                    bag.remove(s.charAt(left));
                    left++;
                }
                left++;
            }
            right++;
        }
        return length;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            String line = scanner.nextLine();
            System.out.println(new LongestSubstringWithoutRepeatingChars().solve(line));
        }
    }
}
