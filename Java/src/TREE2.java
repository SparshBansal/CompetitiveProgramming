import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TREE2 {

    static class Scanner {
        BufferedReader br;
        StringTokenizer st;

        public Scanner() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
        }

        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner();
        int t = scanner.nextInt();

        while(t-- > 0) {
            int n = scanner.nextInt();
            // get the numbers
            List<Long> numbers = IntStream.range(0, n).asLongStream().map(idx -> scanner.nextLong())
                    .boxed().collect(Collectors.toList());
            // sort the list
            numbers.sort(Comparator.naturalOrder());

            if (numbers.size() == 1) {
                if (numbers.get(0) == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(1);
                }
                continue;
            }

            long start = numbers.get(0);
            int cutsRequired = start == 0 ? 0 : 1;
            for (int i=1; i<numbers.size(); i++) {
                if (numbers.get(i) != start) {
                    cutsRequired++;
                    start = numbers.get(i);
                }
            }
            System.out.println(cutsRequired);
        }
    }
}
