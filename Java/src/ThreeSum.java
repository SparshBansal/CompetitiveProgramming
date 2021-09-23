import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ThreeSum {
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

    private class NTuple {
        int[] vals;

        public NTuple(int[] vals) {
            this.vals = vals;
            Arrays.sort(vals);
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            NTuple nTuple = (NTuple) object;
            return Arrays.equals(vals, nTuple.vals);
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(vals);
        }
    }

    public List<List<Integer>> solve(int[] nums) {
        Arrays.sort(nums);
        Set<NTuple> ans = new HashSet<>();
        final int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            int t = i + 1, f = n - 1;
            while (t < f) {
                int currentSum = nums[i] + nums[t] + nums[f];
                if (currentSum == 0) {
                    ans.add(new NTuple(new int[]{nums[i], nums[t], nums[f]}));
                }
                if (currentSum <= 0) t++;
                else f--;
            }
        }
        return ans.stream().map(nTuple -> Arrays.stream(nTuple.vals).boxed().collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        int[] ar = new int[n];
        for (int i =0; i<n; i++) ar[i] = scanner.nextInt();
        System.out.println(new ThreeSum().solve(ar));
    }
}
