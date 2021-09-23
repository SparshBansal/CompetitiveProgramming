import java.io.*;
import java.util.StringTokenizer;

public class Candy {
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


    public int solve(int[] arr) {
        // find the lowest point in the arr;
        int lowestIdx = 0;
        int lowestValue = 1;
        int currentValue = 1;
        for (int i=1; i<arr.length; i++) {
            if (arr[i] > arr[i-1]) {
                // we need to increase the current value;
                currentValue++;
            } else if (arr[i] < arr[i-1]) {
                currentValue--;
                if (currentValue < lowestValue) {
                    lowestValue = currentValue;
                    lowestIdx = i;
                }
            }
        }

        // now we have the lowest value we will assign that lowest index 1 candy
        int sum = 1;
        // assign candies to right
        int currentCandy = 1;
        for (int i=lowestIdx + 1; i<arr.length; i++) {
            if (arr[i] < arr[i-1]) sum += (--currentCandy);
            else if (arr[i] > arr[i-1]) sum += (++currentCandy);
            else sum+= currentCandy;
        }

        currentCandy=1;
        // assign to left
        for (int i=lowestIdx-1; i>=0; i--) {
            if (arr[i] < arr[i+1]) sum += (--currentCandy);
            else if (arr[i] > arr[i+1]) sum += (++currentCandy);
            else sum+= currentCandy;
        }
        return sum;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);

        int n = scanner.nextInt();
        int[] arr = new int[n];
        for (int i=0; i<n; i++) arr[i] = scanner.nextInt();

        System.out.println(new Candy().solve(arr));
    }
}
