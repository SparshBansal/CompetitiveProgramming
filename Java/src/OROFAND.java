import java.io.*;
import java.util.StringTokenizer;

public class OROFAND {
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

    public static class Pair<T> {
        private T _1, _2;

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

    static int getAns(int[] count) {
        // there are 31 bits
        int ans = 0;
        for (int i=0,j=1; i<31; i++, j*=2) {
            if (count[i] >= 1) {
                ans += j;
            }
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int q = scanner.nextInt();

            // can solve it here only
            // the max number of bits is 31

            int[] ar = new int[n];
            int[] count = new int[31];

            for (int i=0; i<n; i++) {
                ar[i] = scanner.nextInt();
                int idx = 0;
                int num = ar[i];
                while (idx < 31) {
                   if (num % 2 != 0) {
                       count[idx]++;
                   }
                   num = num / 2;
                   idx++;
                }
            }
            // print ans
            System.out.println(getAns(count));

            // for each query , we do some following things
            for (int i=0; i<q; i++) {
                int x =  scanner.nextInt();
                int y = scanner.nextInt();
                x--;
                // remove the element at position x
                int num = ar[x];
                // decrease the count at the positions
                int idx = 0;
                while (idx < 31) {
                    if (num % 2 != 0) {
                        count[idx]--;
                    }
                    num = num / 2;
                    idx++;
                }

                // also add the idxes of new elements
                num = y;
                idx = 0;
                while (idx < 31) {
                    if (num % 2 != 0) {
                        count[idx]++;
                    }
                    num = num / 2;
                    idx++;
                }
                ar[x] = y;
                System.out.println(getAns(count));
            }
        }
    }
}
