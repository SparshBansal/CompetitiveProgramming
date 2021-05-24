import java.io.*;
import java.util.*;

public class MODEQ {
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

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Pair<?> pair = (Pair<?>) object;
            return _1.equals(pair._1) &&
                    _2.equals(pair._2);
        }

        @Override
        public int hashCode() {
            return Objects.hash(_1, _2);
        }
    }


    List<Pair<Integer>> solve2(int n, int m) {
        int ans = 0;
        List<Pair<Integer>> pairs = new ArrayList<>();

        for (int a = 1; a <= n; a++) {
            for (int b = a + 1; b <= n; b++) {
                if ((m % a) % b == ((m % b) % a)) {
                    pairs.add(new Pair<>(a, b));
                    ans++;
                }
            }
        }
        return pairs;
    }

    List<Pair<Integer>> solve3(int n, int m) {
        Map<Integer, List<Integer>> mods = new HashMap<>();
        Map<Integer, Integer> smaller = new HashMap<>();

        // add mods counts
        for (int i = 2; i <= n; i++) {
            int mod = m % i;
            mods.computeIfAbsent(mod, val -> new ArrayList<>()).add(i);
        }

        List<Pair<Integer>> ansList = new ArrayList<>();
        int sum = 0;
        for (int a = 2; a <= n; a++) {
            int k = m % a;
            int f = 0;
//            int val = (k + f * a);
//            if (mods.containsKey(val)) {
//                for (Integer b : mods.get(val)) {
//                    if (b > a) {
//                        ansList.add(new Pair<>(a, b));
//                    }
//                }
//            }
//            f++;
            while ((k + f * a) <= n) {
                int val = k + (f * a);
                if (mods.containsKey(val)) {
                    for (Integer b : mods.get(val)) {
                        if (b > a) {
                            ansList.add(new Pair<>(a, b));
                        }
                    }
                    sum += (mods.get(val).size() - smaller.getOrDefault(val, 0));
                }
                f++;
//                smaller.put(k, (smaller.getOrDefault(k, 0))+1);
            }
            smaller.put(k, smaller.getOrDefault(k, 0) + 1);
            // sum --
            sum--;
        }

        System.out.println("Answer is coming out to be " + sum);
        return ansList;
    }

    long solve(int n, int m) {
        Map<Long, Long> mods = new HashMap<>();
        Map<Long, Long> smaller = new HashMap<>();

        // add mods counts
        for (int i = 2; i <= n; i++) {
            long mod = m % i;
            mods.put(mod, mods.getOrDefault(mod, 0L) + 1);
        }

        long sum = 0;
        for (int a = 2; a <= n; a++) {
            long k = m % a;
            long f = 0;
            while ((k + f * a) <= n) {
                long val = k + (f * a);
                sum += (mods.getOrDefault(val, 0L) - smaller.getOrDefault(val, 0L));
                f++;
            }
            smaller.put(k, smaller.getOrDefault(k, 0L) + 1);
            sum--;
        }

        // add n-1 for each 1 pair that can be formed
        return sum + (n-1);
    }

    private static class TestGenerator {
        public void generate(int t, int n, int m) throws IOException {
            // generate test
            FileOutputStream fileOutputStream = new FileOutputStream(new File("input.txt"));
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));

            bufferedWriter.write(String.format("%d", t));
            bufferedWriter.write("\n");

            Random random = new Random();

            for (int i=0; i<t; i++) {
                bufferedWriter.write(String.format("%d %d\n", random.nextInt(n+1), random.nextInt(m+1)));
            }
            bufferedWriter.close();
        }
    }

    public static void main(String[] args) throws IOException {
//        TestGenerator testGenerator = new TestGenerator();
//        testGenerator.generate(100, 10000, 1000000);

        FastReader scanner = new FastReader(args);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            final long ans = new MODEQ().solve(n, m);
            System.out.println(ans >=0 ? ans : 0);
//            System.out.println("Start -------------------- (" + n + " " + m + ")");
//            List<Pair<Integer>> ans2 = new MODEQ().solve3(n, m);
//            List<Pair<Integer>> ans3 = new MODEQ().solve2(n, m);
//
//            System.out.println("Test " + n + " " + m);
//            if (ans == ans3.size()) {
//                System.out.println("equal answers");
//            }
//            else {
//                System.out.println("Different answers : " + ans + ", " + ans3);
//            }
//            // check pairs of list not present in ans
//            Set<Pair<Integer>> aSet = new HashSet<>(ans2);
//            if (ans2.size() == ans3.size()) {
//                System.out.println("Equal sized answers " + ans2.size());
//            } else {
//                System.out.println("Not equal size : " + ans2.size() + " , " + ans3.size());
//            }
//            for (Pair<Integer> pair : ans3) {
//                if (!aSet.contains(pair)) {
//                    System.out.println("Not present " + pair._1() + " " + pair._2());
//                }
//            }
//            System.out.println("End --------------------");
//            if (ans1 != ans2) {
//                System.out.println("N : " + n + " M : " + m);
//                System.out.println("Ans1 : " + ans1 + " , Ans2 : " + ans2);
//            }
        }
    }
}
