import java.io.*;
import java.util.*;

public class BinaryLiterature {
    static class FastReader {
        private static BufferedReader br;
        private static StringTokenizer st;

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

    private class Pair {
        char el;
        int count;

        public Pair(char el, int count) {
            this.el = el;
            this.count = count;
        }
    }

    private Deque<Pair> constructStack(String q) {
        Deque<Pair> queue = new LinkedList<>();
        char prev = q.charAt(0);
        int count = 1;
        for (int i=1; i<q.length(); i++) {
            if (prev == q.charAt(i)) {
                count++;
            } else {
                queue.add(new Pair(prev, count));
                prev = q.charAt(i);
                count = 1;
            }
        }
        queue.add(new Pair(prev, count));
        return queue;
    }

    private void addChars(StringBuilder builder, Pair pair) {
        for (int i=0; i<pair.count; i++) {
            builder.append(pair.el);
        }
    }

    private String createSuperString(String a, String b) {
        StringBuilder builder = new StringBuilder();
        int cA = 0, cB = 0;
        Deque<Pair> stackA = constructStack(a);
        Deque<Pair> stackB = constructStack(b);

        // now while the stacks are not empty
        while ((!stackA.isEmpty())  || (!stackB.isEmpty())) {
            // consider the top the stacks
            if (stackA.isEmpty()) {
                addChars(builder, stackB.remove());
            } else if (stackB.isEmpty()) {
                addChars(builder, stackA.remove());
            } else {
                // check whichever is min
                Pair topA = stackA.peek();
                Pair topB = stackB.peek();
                if (topA.el == topB.el) {
                    if (topA.count < topB.count) {
                        addChars(builder, topA);
                        stackA.remove();
                        stackB.remove();
                        stackB.offerFirst(new Pair(topA.el, topB.count - topA.count));
                    }  else if (topA.count > topB.count) {
                        addChars(builder, topB);
                        stackA.pop();
                        stackB.pop();
                        stackA.offerFirst(new Pair(topA.el, topA.count - topB.count));
                    } else {
                        addChars(builder, topB);
                        stackA.pop();
                        stackB.pop();
                    }
                } else {
                    // in case chars are not equal pop the smaller of the two and add
                    if (topA.count < topB.count) {
                        addChars(builder, topA);
                        stackA.pop();
                    } else {
                        addChars(builder, topB);
                        stackB.pop();
                    }
                }
            }
        }
        return builder.toString();
    }

    private String solve(int n, String a, String b, String c) {
        // now 3 possible strings need to be created from a, b, c
        String ans = createSuperString(a, b);
        if (ans.length() <= (3 * n)) {
            return ans;
        }
        ans = createSuperString(b, c);
        if (ans.length() <= (3 * n)) {
            return ans;
        }
        ans = createSuperString(a, c);
        if (ans.length() <= (3 * n)) {
            return ans;
        }
        return ans;
    }

    public static void main(String[] args) throws FileNotFoundException {
//        FastReader scanner = new FastReader(new FileInputStream(new File("input.txt")));
        FastReader scanner = new FastReader(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
            String a = scanner.nextLine();
            String b = scanner.nextLine();
            String c = scanner.nextLine();

            System.out.println(new BinaryLiterature().solve(n, a, b, c));
        }
    }
}
