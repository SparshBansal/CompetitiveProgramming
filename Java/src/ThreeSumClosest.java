import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class ThreeSumClosest {
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


    public int solve(int[] nums, int target) {
        Arrays.sort(nums);
        if (nums.length == 3) return nums[0] + nums[1] + nums[2];

        final int n = nums.length;
        int minDiff = Integer.MAX_VALUE;
        int sum = 0;

        for (int i = 0; i < n - 2; i++) {
            // we fix the current number as the smallest number we need
            // we start the two pointers
            // we start with smallest numbers
            int newTarget = target - nums[i];

            int t = i + 1, f = n - 1;
            while (t < f) {
                int currentSum = nums[t] + nums[f];
                if (Math.abs(newTarget - currentSum) < minDiff) {
                    minDiff = Math.abs(newTarget - currentSum);
                    sum = currentSum + nums[i];
                }
                // now we see if the current sum is less than the target, we increase
                if (currentSum <= newTarget) t++;
                else f--;
            }
        }
        return sum;
    }


    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        int target = scanner.nextInt();

        int ar[] = new int[n];
        for (int i = 0; i<n; i++) ar[i] = scanner.nextInt();
        System.out.println(new ThreeSumClosest().solve(ar, target));
    }
}
