import java.io.*;
import java.util.StringTokenizer;

public class SOCKS1 {
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


    public static void main(String[] args) throws FileNotFoundException {
//        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        FastReader scanner = new FastReader(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();

        // solve here only
        if (a == b || a == c || b == c) {
            System.out.println("YES");
            return;
        }
        System.out.println("NO");
    }
}
