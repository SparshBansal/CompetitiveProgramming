package dec_2020;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class HXOR {

    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new
                    InputStreamReader(System.in));
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

    static FastReader scanner = new FastReader();

    private static long[] solve(long[] ar, int x) {
        final int n = ar.length;

        Set<Long> opCache = new HashSet<>();
        int remaining = x;

        // loop
        for (int i=0; i<n-1; i++) {
            // get the bits of the current numbers
            long current = ar[i];

            // start from the outermost bit
            // try to play the opcache first on the number
            long number = playOpCache(current, opCache);

            // now just try to add more if we can to opcache
            while (number != 0 && remaining > 0) {
                long highestBit = Long.highestOneBit(number);
                opCache.add(highestBit);
                remaining--;
                number = number ^ highestBit;
            }
            ar[i] = number;
        }
        // now first we clear op cache on the last element
        if (!opCache.isEmpty()) {
            // apply all opcache elements on the last element
            ar[n-1] = applyOpCache(ar[n-1], opCache);
        }
        // if operations are remaining, we need to apply only set the last one bit to the elements
        if (remaining%2 == 1) {
            ar[n-2] = ar[n-2] ^ 1;
            ar[n-1] = ar[n-1] ^ 1;
        }
        return ar;
    }

    private static long playOpCache(long number, Set<Long> opCache) {
        if (opCache.isEmpty()) {
            return number;
        }

        // iterate over the op cache
        List<Long> appliedElements = new ArrayList<>();
        for (long element : opCache) {
            // check if can be applied to the number
            if ((number & element) > 0) {
                // apply opcache element here
                number = number ^ element;
                appliedElements.add(element);
            }
        }
        opCache.removeAll(appliedElements);
        return number;
    }

    private static long applyOpCache(long number, Set<Long> opCache) {
        for (long element : opCache) {
            number = number ^ element;
        }
        opCache.clear();
        return number;
    }

    public static void main(String[] args) {
        int t = scanner.nextInt();

        while(t-- > 0) {
            int n = scanner.nextInt();
            int x = scanner.nextInt();

            long[] ar = new long[n];
            for (int i=0; i<n; i++)
                ar[i] = scanner.nextInt();

            long[] ans = solve(ar, x);
            System.out.println(Arrays.stream(ans).mapToObj(String::valueOf).collect(Collectors.joining(" ")));
        }
    }
}
