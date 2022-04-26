import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class WILDRPL {
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

    public enum Type {
        LEAF,
        OPERATION_PLUS,
        OPERATION_MINUS
    }

    public static class Expression {
        private Type type;
        private int min, max;

        private Expression left, right;

        public Expression(Type t, Expression l, Expression r) {
            this.type = t;
            this.left = l;
            this.right = r;
        }
    }

    public void postOrderFill(Expression root) {
        // check the current type
        switch (root.type) {
            case LEAF:
                root.min = 0;
                root.max = 1;
                break;
            case OPERATION_PLUS:
                postOrderFill(root.left);
                postOrderFill(root.right);
                root.min = root.left.min + root.right.min;
                root.max = root.left.max + root.right.max;
                break;
            case OPERATION_MINUS:
                postOrderFill(root.left);
                postOrderFill(root.right);
                root.min = root.left.min - root.right.max;
                root.max = root.left.max - root.right.min;
                break;
        }
    }

    public void solve(String s) {
        Expression root = buildExpressionTree(s);
        postOrderFill(root);

        System.out.println();
    }

    private Expression buildExpressionTree(String s) {
        if (s.length() == 1) {
            // single node tree possible
            return new Expression(Type.LEAF, null, null);
        }
        Stack<Expression> operands = new Stack<>();
        Stack<Character> operations = new Stack<>();
        // we will use stacks to construct the tree
        for (char c : s.toCharArray()) {
            switch (c) {
                case '?':
                    operands.push(new Expression(Type.LEAF, null, null));
                    break;
                case '+':
                case '-':
                    operations.push(c);
                    break;
                case '(':
                    break;
                case ')':
                    // an expression just ended
                    Expression right = operands.pop();
                    Expression left = operands.pop();
                    Character operation = operations.pop();
                    Expression result = new Expression(operation == '+' ? Type.OPERATION_PLUS : Type.OPERATION_MINUS,
                            left, right);
                    operands.push(result);
                    break;
            }
        }

        // at the last we should have the root at the top of the stack
        return operands.pop();
    }

    public static void main(String[] args) throws FileNotFoundException {
        FastReader scanner = new FastReader(args);
        new WILDRPL().solve("(?-(?-?))");
    }
}
