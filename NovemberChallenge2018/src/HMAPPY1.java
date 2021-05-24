import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class HMAPPY1 {
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

    static class Node {
        int val, count;

        Node(int val, int count) {
            this.val = val;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        Deque<Node> deque = new ArrayDeque<>();
        FastReader fastReader = new FastReader();

        int n,q,k;
        n = fastReader.nextInt();
        q = fastReader.nextInt();
        k = fastReader.nextInt();

        int[] ar = new int[n];
        int[] solution = new int[n];

        for (int i=0; i<n; i++)
            ar[i] = fastReader.nextInt();

        int count=1, prev = ar[0];
        for (int i=1; i<n; i++) {
            if (ar[i] == prev) {
                count++;
            } else {
                deque.addLast(new Node(prev, count));
                prev = ar[i];
                count = 1;
            }
        }
        deque.addLast(new Node(prev, count));

        // create a max heap
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

        // add to heap all current values of chains
        for (Node node : deque) {
            if (node.val == 1)
                priorityQueue.add(node.count);
        }

        // lets compute answer for each cycle
        if (priorityQueue.peek() == null)
            solution[0] = 0;
        else
            solution[0] = priorityQueue.peek();

        for (int i=1; i<n; i++) {
            // for current cycle check deque
            if (deque.peekFirst().val == 1 && deque.peekLast().val == 1) {
                int first_val = deque.peekFirst().count;
                priorityQueue.remove(first_val);
                priorityQueue.add(first_val+1);

                int last_val = deque.peekLast().count;
                priorityQueue.remove(last_val);
                priorityQueue.add(last_val-1);

            }
            else if (deque.peekFirst().val == 0 && deque.peekLast().val == 1) {
                int last_val = deque.peekLast().count;
                priorityQueue.remove(last_val);
                priorityQueue.add(last_val-1);
                priorityQueue.add(1);

            }
            if (priorityQueue.peek() == null)
                solution[i] = 0;
            else
                solution[i] = priorityQueue.peek();

            // change the deque
            if (deque.peekLast().val == 1 && deque.peekFirst().val == 1) {
                deque.peekLast().count--;
                deque.peekFirst().count++;
            }
            else if (deque.peekLast().val == 0 && deque.peekFirst().val == 0) {
                deque.peekLast().count--;
                deque.peekFirst().count++;
            }
            else if (deque.peekLast().val == 0 && deque.peekFirst().val == 1) {
                deque.peekLast().count--;
                deque.addFirst(new Node(0,1));
            }
            else if (deque.peekLast().val == 1 && deque.peekFirst().val == 0) {
                deque.peekLast().count--;
                deque.addFirst(new Node(1,1));
            }

            if (deque.peekLast().count == 0)
                deque.removeLast();
        }

        String queryString = fastReader.nextLine();
        int ptr = 0;
        for (int i=0; i<queryString.length(); i++) {
            char query = queryString.charAt(i);
            if (query == '!')
                ptr = ((ptr+1)%n);
            else if (query == '?')
                if (solution[ptr] > k)
                    System.out.println(k);
                else
                    System.out.println(solution[ptr]);
        }
    }
}
