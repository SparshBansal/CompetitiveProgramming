import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class PermutationsTwo {

    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
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

    public void genPerms(Map<Integer, Integer> candidates, int indx, int target, Deque<Integer> current, List<List<Integer>> all) {
        if(indx == target) {
            all.add(List.copyOf(current));
            return;
        }
        
        candidates.forEach((candidate, count) -> {
            if (count > 0) {
                current.addLast(candidate);
                candidates.put(candidate, count - 1);
                genPerms(candidates, indx + 1, target, current, all);
                candidates.put(candidate, count);
                current.removeLast();
            }
        });
    }

    public List<List<Integer>> permutations(int[] input) {
        Map<Integer, Integer> candidates = new HashMap<>();
        for (int val : input) {
            candidates.put(val, candidates.getOrDefault(val, 0) + 1);
        }
        List<List<Integer>> all = new ArrayList<>();
        genPerms(candidates, 0, input.length, new LinkedList<>(), all);
        return all;
    }

    public static void main(String[] args) {
        
    }
}
