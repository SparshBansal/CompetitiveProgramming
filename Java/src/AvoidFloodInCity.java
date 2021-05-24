import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class AvoidFloodInCity {
    static class Solution {
        public int[] avoidFlood(int[] rains) {
            Set<Integer> floodCandidates = new HashSet<Integer>();
            List<Integer> floodDays = new ArrayList<>();
            List<Integer> dryDays = new ArrayList<>();
            List<Integer> floodLakes = new ArrayList<>();

            for (int i = 0; i < rains.length; i++) {
                if (rains[i] == 0) {
                    dryDays.add(i);
                    continue;
                }
                if (floodCandidates.contains(rains[i])) {
                    floodCandidates.remove(rains[i]);
                    floodDays.add(i);
                    floodLakes.add(rains[i]);
                } else {
                    floodCandidates.add(rains[i]);
                }
            }

            // now we have a list of sorted flood days and dry days
            int i = 0, j = 0, k=0;
            List<Integer> dryingOrder = new ArrayList<>();
            while (i < floodDays.size() && j < dryDays.size()) {
                if (dryDays.get(j) < floodDays.get(i)) {
                    dryingOrder.add(floodLakes.get(i));
                    i++;
                    j++;
                } else {
                    break;
                }
            }

            if (i < floodDays.size()) {
                return new int[]{};
            }

            // there are still days we cannot dry out
            int[] ans = new int[rains.length];
            for (int it=0,ij=0; it<rains.length; it++) {
                if (rains[it] > 0) {
                    ans[it] = -1;
                } else {
                    if (ij < dryingOrder.size()) {
                        ans[it] = dryingOrder.get(ij++);
                    } else {
                        ans[it] = 1;
                    }
                }
            }
            return ans;
        }
    }

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

    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int t = scanner.nextInt();
        while (t-- > 0) {
            int n = scanner.nextInt();

            int[] rains = new int[n];
            for (int i=0; i<n; i++) {
                rains[i] = scanner.nextInt();
            }

            Solution solution = new Solution();
            int[] ans = solution.avoidFlood(rains);
            for (int i : ans) {
                System.out.println(i);
            }
        }
    }
}
