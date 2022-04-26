import java.io.*;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

public class PSEUDOSORT {
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

        ConcurrentHashMap
    }


    public boolean solve(int[] arr) {
        int times = 0;
        for (int i=1; i<arr.length; i++) {
            if (i == 0) continue;
            if (arr[i] < arr[i-1]) {
                // swap and increase
                swap(arr, i, i-1);
                times++;

                i -= 2;
                if (times >= 2) break;
            }
        }
        return times <= 1;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while(t-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i=0; i<n; i++) arr[i] = scanner.nextInt();

            System.out.println(new PSEUDOSORT().solve(arr) ? "YES" : "NO");
        }
    }
}
