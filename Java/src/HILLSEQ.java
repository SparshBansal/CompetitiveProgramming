import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HILLSEQ {
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

    public Integer[] solve(Integer[] ar, int n) {
        if (n == 1) return ar;

        Arrays.sort(ar, Collections.reverseOrder());

        int max = ar[0];
        if (ar[1] == max) return null;

        // now we can check the frequency of each element, should not be more than 2
        Map<Integer, Integer> counts = new HashMap<>();
        for (int el : ar) counts.put(el, counts.getOrDefault(el, 0) + 1);
        if (counts.values().stream().anyMatch(val -> val > 2)) {
            return null;
        }

        // create the left hand side
        Integer[] solution = new Integer[n];

        List<Integer> duplicates = new ArrayList<>();
        List<Integer> singles = new ArrayList<>();

        counts.forEach((k, v)-> {
            if (v == 2) duplicates.add(k);
            else if (v == 1 && k != max) singles.add(k);
        });

        // sort the duplicates in increasing order
        duplicates.sort(Comparator.naturalOrder());
        int ctr = 0;
        for (int el : duplicates) solution[ctr++] = el;
        solution[ctr++] = max;

        List<Integer> rem = new ArrayList<>(singles);
        rem.addAll(duplicates);

        rem.sort(Comparator.reverseOrder());
        for (int el : rem) solution[ctr++] = el;
        return solution;
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();
        while(t-- > 0) {
            int n = scanner.nextInt();
            Integer[] ar = new Integer[n];
            for (int i = 0; i<n; i++) ar[i] = scanner.nextInt();

            Integer[] solution = new HILLSEQ().solve(ar, n);
            if (solution == null) System.out.println(-1);
            else System.out.println(Arrays.stream(solution).map(String::valueOf).collect(
                    Collectors.joining(" ")));
        }
    }
}
