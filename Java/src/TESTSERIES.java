import java.io.*;
import java.util.StringTokenizer;

public class TESTSERIES {
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

    public String solve(int[] ar) {
        int ind = 0, eng = 0;
        for (int i : ar)   {
            if (i == 1) ind++;
            if (i == 2) eng++;
        }
        if (ind == eng) return "DRAW";
        return ind > eng ? "INDIA" : "ENGLAND";
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while (t-- > 0) {
            int[] ar = new int[5];
            for (int i = 0; i<5; i++) ar[i] = scanner.nextInt();
            System.out.println(new TESTSERIES().solve(ar));
        }
    }
}
