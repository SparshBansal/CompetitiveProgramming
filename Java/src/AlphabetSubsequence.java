import java.io.*;
import java.util.StringTokenizer;

public class AlphabetSubsequence {
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

    public void solve(String in) {
        in = in.toLowerCase();
        int[] lastOccurrence = new int[26];
        for (int i = 0; i < 26; i++) {
            lastOccurrence[i] = -1;
        }

        int global_ans_len = Integer.MAX_VALUE;
        int global_ans_start = -1;


        for (int i = 0; i < in.length(); i++) {
            if (in.charAt(i) < 'a' || in.charAt(i) > 'z') {
                continue;
            }
            int idx = in.charAt(i) - 'a';

            // check if previous exists, if exists, we can append this into that
            if (idx == 0) {
                lastOccurrence[idx] = i;
                continue;
            }

            if (lastOccurrence[idx - 1] != -1) {
                lastOccurrence[idx] = lastOccurrence[idx-1];
                lastOccurrence[idx - 1] = -1;
            }
            // if we have completed a full chain
            if (idx == 25 && lastOccurrence[idx] != -1) {
                // check if we have got a new global best
                if (i - lastOccurrence[idx] + 1 < global_ans_len) {
                    global_ans_len = i - lastOccurrence[idx] + 1;
                    global_ans_start = lastOccurrence[idx];
                }
            }
        }

        // print ans
        System.out.println("Minimum length of substring is : " + global_ans_len + " starting from : " +
                global_ans_start);
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        int t = scanner.nextInt();

        while (t-- > 0) {
            String in = scanner.nextLine();
            new AlphabetSubsequence().solve(in);
        }
    }
}
