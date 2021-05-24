import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class QuestionPaper {
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

    private static boolean checkQuestionCount(long base, long diff_in_marks, long diff_to_be_covered, long n) {
        if (diff_to_be_covered % diff_in_marks == 0)
            return (base + (diff_to_be_covered / diff_in_marks) * 2) <= n;
        else
            return false;
    }

    private static boolean handleNegativeMarks(long marks, int n, int a, int b) {
        if ((-marks%b) == 0)
            return (-marks/b) <= n;

        long difference = a - b;
        if (difference > 0) {
            long num_questions = (((-marks / b)) + 1);
            long diff = (num_questions * b) + marks;
            return checkQuestionCount(num_questions, difference, diff, n);
        }
        if (difference < 0) {
            long num_questions = ((-marks / b));
            long diff = (num_questions * b) + marks;
            return checkQuestionCount(num_questions, -difference, diff, n);
        }
        return marks%b == 0 && marks/b <= n;
    }

    private static boolean handlePositiveMarks(long marks, int n, int a, int b) {

        if (marks % a == 0)
            return marks/a <= n;

        long difference = a - b;
        if (difference > 0) {
            long diff = marks - (marks / a) * a;
            if (diff % difference == 0)
                return checkQuestionCount(marks / a, a - b, diff, n);
            else
                return false;
        }
        if (difference < 0) {
            long diff = marks - ((marks / a) + 1) * a;
            if (diff % difference == 0)
                return checkQuestionCount((marks / a) + 1, difference, diff, n);
            else
                return false;
        }
        return marks % a == 0 && marks / a <= n;
    }

    public static void main(String[] args) {
        FastReader reader = new FastReader();
        int t = reader.nextInt();
        while (t-- > 0) {
            int n = reader.nextInt(), a = reader.nextInt(), b = reader.nextInt();

            // iterate over all positive values of marks
            long distinct = 1;
            long maxMarks = n * a, minMarks = (-b) * n;
            for (int i = 1; i <= maxMarks; i++) {
                // check if marks possible
                if (handlePositiveMarks(i, n, a, b)) {
                    System.out.println("Possible marks : " + String.valueOf(i));
                    distinct++;
                }
            }

            for (int i=-1; i >= minMarks; i--) {
                if (handleNegativeMarks(i, n, a, b)) {
                    System.out.println("Possible marks : " + String.valueOf(i));
                    distinct++;
                }
            }
            System.out.println(distinct);
        }
    }
}
