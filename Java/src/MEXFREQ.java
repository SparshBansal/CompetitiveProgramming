import java.io.*;
import java.util.*;

public class MEXFREQ {
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


    public int findMex(List<Integer> nums, int from, int to) {
        // find mex of this
        int n = to - from + 1;
        int currentMex = 0;
        // so max mex can be n
        List<Integer> slice = nums.subList(from, to + 1);
        slice.sort(Comparator.naturalOrder());

        for (int i : slice) {
            if (i == currentMex) currentMex++;
            if (i > currentMex) return currentMex;
        }
        return currentMex;
    }

    public Map<Integer, Integer> generateMexFrequencyMap(List<Integer> numbers) {
        // generate frequency
        Map<Integer, Integer> mex = new HashMap<>();
        for (int i = 0; i < numbers.size(); i++) {
            for (int j = i; j < numbers.size(); j++) {
                // now we find the mex
                int mexVal = findMex(numbers, i, j);
                mex.put(mexVal , mex.getOrDefault(mexVal, 0) + 1);
            }
        }
        return mex;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        int ub = scanner.nextInt();

        // generate a list of numbers within ub
        List<Integer> numbers = new ArrayList<>();

        Random random = new Random(System.currentTimeMillis());
        for (int i = 0; i<n; i++) {
            numbers.add(random.nextInt(ub));
        }

        System.out.println("Array : " + numbers);
        // now we find the mex
        Map<Integer, Integer> mex = new MEXFREQ().generateMexFrequencyMap(numbers);
        // sort the map with values
        System.out.println("Mex vals : " + mex);
    }
}
