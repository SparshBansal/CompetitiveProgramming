import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ReverseSort {
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
        int t = scanner.nextInt();

        int q = 0;
        while (t-- > 0) {
            q++;
            int n = scanner.nextInt();
            int ar[] = new int[n];

            for (int i=0; i<n; i++) {
                ar[i] = scanner.nextInt();
            }

            System.out.println("Case #" + q + ": " + new ReverseSort().solve(ar));
        }
    }

    private int solve(int[] ar) {
        int cost = 0;
        for (int i=0; i<ar.length-1; i++) {
            // find min
            int j = findMin(ar, i, ar.length);

            // reverse the ar till j
            reverse(ar, i, j);

            // now compute cost
            cost += ( j - i + 1 );
        }
        return cost;
    }

    private void reverse(int[] ar, int i, int j) {
        // inplace reverse
        for (int s = i, e = j; s < e; s++, e--) {
            int tmp = ar[s];
            ar[s] = ar[e];
            ar[e] = tmp;
        }
    }

    private int findMin(int[] ar, int start, int end) {
        int MIN = Integer.MAX_VALUE;
        int idx = -1;

        for (int i=start; i<end; i++) {
            if (ar[i] < MIN) {
                MIN = ar[i];
                idx = i;
            }
        }
        return idx;
    }
}
