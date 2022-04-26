import java.io.*;
import java.util.*;

public class SubsetsTwo {
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

    public class Element {
        int val, count;

        public Element(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    public void genSubs(List<List<Integer>> bag, Deque<Integer> current, List<Element> ar, int currIdx) {
        if (currIdx == ar.size()) {
            // push the list into the bag
            bag.add(new ArrayList<>(current));
            return;
        }
        // now we have options
        // we can include current elements n number of times in this set
        genSubs(bag, current, ar, currIdx + 1);
        for (int i = 0; i < ar.get(currIdx).count; i++) {
            // we are going to add one element
            current.addLast(ar.get(currIdx).val);
            genSubs(bag, current, ar, currIdx + 1);
        }

        // remove all added elements
        int cnt = ar.get(currIdx).count;
        for (int i = 0; i < cnt; i++) current.removeLast();
    }

    public List<List<Integer>> solve(int[] nums) {
        Arrays.sort(nums);
        // construct an array of elements and counts

        int prevElement = nums[0], count = 1;

        List<Element> ar = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == prevElement) count++;
            else {
                ar.add(new Element(prevElement, count));
                prevElement = nums[i];
                count = 1;
            }
        }

        ar.add(new Element(prevElement, count));
        // generate subsets
        List<List<Integer>> soln = new ArrayList<>();
        genSubs(soln, new ArrayDeque<>(), ar, 0);
        return soln;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int n = scanner.nextInt();
        int[] ar = new int[n];
        for (int i = 0; i< n; i++) ar[i] = scanner.nextInt();
        System.out.println(new SubsetsTwo().solve(ar));
    }
}
