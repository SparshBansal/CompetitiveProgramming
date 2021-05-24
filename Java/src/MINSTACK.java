import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class MINSTACK {
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

    public void solve() {

        Stack stack = new Stack();
        System.out.println(stack.getMin());

        stack.push(2);
        System.out.println(stack.getMin());

        stack.push(3);
        System.out.println(stack.getMin());

        stack.push(1);
        System.out.println(stack.getMin());

        stack.push(4);
        System.out.println(stack.getMin());

        stack.pop();
        System.out.println(stack.getMin());

        stack.pop();
        System.out.println(stack.getMin());


        stack.pop();
        stack.pop();
        System.out.println(stack.getMin());

    }

    private class Stack {
        java.util.Stack<Long> mStack;

        long currentMin = Integer.MAX_VALUE;

        public Stack() {
            mStack = new java.util.Stack<>();
        }

        public void push(int element) {
            if (element < currentMin) {
                // push
                mStack.push(2 * element - currentMin);
                // update current min
                currentMin = element;
            } else {
                // just push
                mStack.push(element + currentMin);
            }
        }

        public long getMin() {
            return currentMin;
        }

        public void pop() {
            if (mStack.peek() - currentMin < currentMin) {
                // we need to update the current min
                currentMin = (2 * currentMin - mStack.peek());
            }
            mStack.pop();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        // need to
        new MINSTACK().solve();
    }
}
