import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TSHIRTS {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader(InputStream inputStream) {
            br = new BufferedReader(new InputStreamReader(inputStream));
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

    private static long solve(long[][] dp, int numElephants, List<List<Integer>> tshirts, int mask, int tshirtIdx) {
        // mask
        // find the number of bits set in the mask
        if (mask == 0) {
            return 1;
        }

        if (tshirtIdx == -1) {
            return 0;
        }

        if (dp[mask][tshirtIdx] != -1) {
            return dp[mask][tshirtIdx];
        }

        // if elephants are greater than numshirt range
        final int elephants = Integer.bitCount(mask);
        if (elephants > tshirtIdx + 1) {
            // we cannot satisy elephants
            dp[mask][tshirtIdx] = 0;
            return dp[mask][tshirtIdx];
        }

        // else compute the dp
        // get the set bits in the mask
        int alterMask = mask;

        long sum  = 0;
        for (int i=0; i<numElephants; i++) {
            if ((alterMask & 1) == 1) {
                // the bit is set
                // check if this elephant can wear this tshirt
                if (tshirts.get(i).contains(tshirtIdx+1)) {
                    final int queryMask =  mask &  ~(1 << i);
                    final long subProblem = solve(dp, numElephants, tshirts, queryMask, tshirtIdx-1);
                    if (subProblem != 0) {
                        sum += subProblem;
                    }
                }
            }
            alterMask = alterMask >> 1;
        }

        // also assume that none of the elephants are wearing this tshirt idx
        sum += solve(dp, numElephants, tshirts, mask, tshirtIdx-1);
        dp[mask][tshirtIdx] = sum;
        return dp[mask][tshirtIdx];
    }

    public static void main(String[] args) throws IOException {
//        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();

        // precompute bitmasks for each elephant
        while (t-- > 0) {
            int n = scanner.nextInt();
            List<List<Integer>> tshirts = new ArrayList<>();

            for (int i=0; i<n; i++) {
                String shirts = scanner.nextLine();
                tshirts.add(Arrays.stream(shirts.split(" ")).map(Integer::parseInt)
                        .collect(Collectors.toList()));
            }
            // now that we have the tshirts
            final int upperLimit = (int) Math.pow(2, n);
            long[][] dp = new long[upperLimit][100];
            // dp[i][j] : i = bitmask of the elephants we are considering
            //          : j = tshirt ranging from 0 to j
            // dp[i][j] : for every having tshirt `j` sum of values dp[k][j-1] where k = i ^ (bitmask(elephant))

            for (int i=0; i<upperLimit; i++) {
                for (int j=0; j<100; j++) {
                    dp[i][j] = -1;
                }
            }

            solve(dp, n, tshirts, upperLimit-1, 99);
            System.out.println(dp[upperLimit-1][99]);
        }

//        TestGenerator testGenerator = new TestGenerator(t);
//        testGenerator.generate("input.txt");
    }

    private static class TestGenerator {
        int n;

        public TestGenerator(int numTests) {
            this.n = numTests;
        }

        public void generate(String path) throws IOException {
            File file = new File(path);
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            // now write the numtests to fiel
            writer.write(Integer.toString(n));
            writer.flush();

            // now for each test case generate
            final int low = 1;
            final int high = 11;

            final int minTshirts = 1;
            final int maxTshirts = 101;


            for (int i=0; i<n; i++) {
                // write the number of elephants
                int numElephants = (int) (low + Math.random() * (high - low));
                writer.newLine();
                writer.write(Integer.toString(numElephants));

                // for each num elephants generate a random number of tshrits
                for (int j=0; j<numElephants; j++) {
                    final int numTshirts = (int) ((Math.random() * (maxTshirts - minTshirts)) + minTshirts);
                    final List<Integer> tshirts = IntStream.range(1, 101).boxed().collect(Collectors.toList());
                    Collections.shuffle(tshirts);
                    List<Integer> owned = tshirts.subList(0, numTshirts);

                    writer.newLine();
                    writer.write(owned.stream().map(intVal -> Integer.toString(intVal))
                            .collect(Collectors.joining(" ")));
                    writer.flush();
                }
            }

            writer.flush();
        }
    }
}
