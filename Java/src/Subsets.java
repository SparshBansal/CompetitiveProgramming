import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Subsets {

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

    public void subs(int[] input, int idx, Deque<Integer> currentSet, List<List<Integer>> powerSet) {
        if (idx == input.length) {
            // copy the list and add to power set
            powerSet.add(List.copyOf(currentSet));
            return;
        }
        // exclude current element
        subs(input, idx+1, currentSet, powerSet);

        // include current element
        currentSet.addLast(input[idx]);
        subs(input, idx+1, currentSet, powerSet);
        currentSet.removeLast();
    }

    public List<List<Integer>> subsets(int[] input) {
        List<List<Integer>> powerSet = new ArrayList<>();
        subs(input, 0, new LinkedList<>(), powerSet);
        return powerSet;
    }

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int len = reader.nextInt();
        List<Integer> input = new ArrayList<>();
        for (int i=0; i<len; i++) {
            input.add(reader.nextInt());
        }
    }
}
