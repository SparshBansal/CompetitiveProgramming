import java.io.*;
import java.util.StringTokenizer;

public class SHUFFLIN {
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


    public int solve(int n, int[] a) {
        // now we need to find the even numbers and odd numbers
        int evenNums = 0, oddNums = 0;
        for (int val : a) {
            if (val % 2 == 0) evenNums++;
            else oddNums++;
        }
        int evenPosns = n / 2;
        int oddPosns = n - evenPosns;
        return (Math.min(evenNums, oddPosns) + Math.min(oddNums, evenPosns));
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while(t-- > 0) {
            int n =scanner.nextInt();
            int ar[] = new int[n];
            for (int i = 0; i<n; i++) ar[i] = scanner.nextInt();
            System.out.println(new SHUFFLIN().solve(n, ar));
        }
    }
}
