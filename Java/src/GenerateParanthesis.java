import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class GenerateParanthesis {
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


    public void recurse(List<String> ans, char[] current, int idx, int opened, int rem) {
        if (idx == current.length) {
            // we can add the constructed string to ans
            ans.add(new String(current));
            return;
        }
        if (rem > 0) {
            // we can open a paresn
            current[idx] = '(';
            recurse(ans, current, idx + 1, opened + 1, rem - 1);
        }
        if (opened > 0) {
            // we can close a parens
            current[idx] = ')';
            recurse(ans, current, idx + 1, opened - 1, rem);
        }
    }

    public List<String> solve(int val) {
        List<String> ans = new ArrayList<>();
        char[] current = new char[2 * val];
        recurse(ans, current, 0, 0, val);
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        System.out.println(new GenerateParanthesis().solve(n));
    }
}
