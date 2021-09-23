import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class MinJob {
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


    public int solve(int[] arr, int n) {
        int lb = 0;
        int max = Arrays.stream(arr).max().getAsInt();
        int ub = n * max;
        return binarySearch(arr, n, lb, ub);
    }

    private int binarySearch(int[] arr, int n, int lb, int ub) {
        int mid = lb + (ub - lb)/2;

        // check if possible in mid
        int total = 0;
        for (int j : arr) {
            total += mid/j;
        }
        if (total == n) {
            return mid;
        }
        if (total < n) {
            return binarySearch(arr, n, mid+1, ub);
        }
        if (lb == ub) {
            return lb;
        }
        return binarySearch(arr, n, lb, mid);
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        int num = scanner.nextInt();

        int arr[] = new int[num];
        for (int i=0; i<num; i++) arr[i] = scanner.nextInt();

        System.out.println(new MinJob().solve(arr, n));
    }
}
