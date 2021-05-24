import java.io.*;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ANUMLA {

    static class Scanner {
        public BufferedReader reader;
        public StringTokenizer st;

        public Scanner(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream));
            st = null;
        }

        public String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = reader.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                } catch (Exception e) {
                    throw (new RuntimeException());
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
    }

    private static class BST {

        Node root;

        public BST(int rootVal) {
            this.root = new Node(rootVal);
        }

        public void addElement(int val) {
            root = insertTree(root, val);
        }

        public boolean isEmpty() {
            return root == null;
        }

        private Node insertTree(Node root, int val) {
            if (root == null) {
                return new Node(val);
            }

            if (root.data == val) {
                root.increment();
                return root;
            } else if (root.data > val) {
                root.left = insertTree(root.left, val);
            } else {
                root.right = insertTree(root.right, val);
            }

            // now try to balance the tree
            calculateSizeAndHeight(root);

            // now check if tree is left or right heavy
            if (isLeftHeavy(root)) {
                // balance left heavy
                root = balanceLeftHeavy(root);
            } else if (isRightHeavy(root)) {
                root = balanceRightHeavy(root);
            }
            return root;
        }

        public int getMin() {
            Node travel = root;
            int minVal = 0;
            while (travel != null) {
                minVal = travel.data;
                travel = travel.left;
            }
            return minVal;
        }

        public void delete(int val) {
            Optional<Node> node = search(val);
            if (node.isPresent()) {
//                System.out.println("Found element : " + val);
                // delete the node from the tree
                root = deleteTree(root, val);
            }
        }

        private Node deleteTree(Node root, int val) {
            if (root == null) {
                return null;
            }

            // this is the element to delete
            if (root.data == val) {
                // get children count
                if (root.count > 1) {
                    root.decrement();
                    return root;
                }
                int childrenCount = getChildrenCount(root);
                if (childrenCount == 0) {
                    // just remove this node from tree, return null
                    return null;
                }
                if (childrenCount == 1) {
                    if (root.left != null) {
                        return root.left;
                    } else {
                        return root.right;
                    }
                } else {
                    deleteWithMultiChild(root);
                }
            }

            if (root.data > val) {
                root.left = deleteTree(root.left, val);
            } else {
                root.right = deleteTree(root.right, val);
            }

            calculateSizeAndHeight(root);
            root = balanceIfRequired(root);
            return root;
        }

        private Node balanceIfRequired(Node root) {
            if (isLeftHeavy(root)) {
                return balanceLeftHeavy(root);
            } else if (isRightHeavy(root)) {
                return balanceRightHeavy(root);
            }
            return root;
        }

        private void deleteWithMultiChild(Node root) {
            // replace the current node with inorder successor
            Node inorderSuccessor = getInorderSuccessor(root);
            // replace current node with inorder successors data
            int tempData = root.data;
            int tempCount = root.count;

            root.data = inorderSuccessor.data;
            root.count = inorderSuccessor.count;

            inorderSuccessor.data = tempData;
            inorderSuccessor.count = tempCount;

            // guaranteed to be either leaf or node with single child
            root.right = deleteTree(root.right, tempData);
        }

        public int getSize() {
            if (root == null) {
                return 0;
            }
            return root.size;
        }

        private Node getInorderSuccessor(Node root) {
            Node successor = root.right;
            while (successor.left != null) {
                successor = successor.left;
            }
            return successor;
        }

        private int getChildrenCount(Node root) {
            int childCount = 0;
            if (root.left != null) childCount++;
            if (root.right != null) childCount++;
            return childCount;
        }


        public void printSorted() {
            executeInorder(root, node -> System.out.println(node.data));
        }

        public void executeInorder(Node root, Consumer<Node> function) {
            if (root == null) {
                return;
            }
            executeInorder(root.left, function);
            function.accept(root);
            executeInorder(root.right, function);
        }

        private void calculateSizeAndHeight(Node root) {
            root.height = Math.max(getHeight(root.left), getHeight(root.right)) + 1;
            root.size = getSize(root.left) + getSize(root.right) + root.count;
        }

        private Node balanceRightHeavy(Node root) {
            root = leftRotate(root);
            if (isLeftHeavy(root)) {
                root = rightRotate(root);

                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }

            return root;
        }

        private Node balanceLeftHeavy(Node root) {
            root = rightRotate(root);
            // left right scenario
            if (isRightHeavy(root)) {
                // undo rotation
                root = leftRotate(root);

                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
            return root;
        }

        private Node leftRotate(Node root) {
            Node newRoot = root.right;
            root.right = newRoot.left;
            newRoot.left = root;
            calculateSizeAndHeight(root);
            calculateSizeAndHeight(newRoot);
            return newRoot;
        }

        private Node rightRotate(Node root) {
            Node newRoot = root.left;
            root.left = newRoot.right;
            newRoot.right = root;
            calculateSizeAndHeight(root);
            calculateSizeAndHeight(newRoot);
            return newRoot;
        }

        private boolean isLeftHeavy(Node root) {
            return getHeight(root.left) - getHeight(root.right) > 1;
        }

        private boolean isRightHeavy(Node root) {
            return getHeight(root.right) - getHeight(root.left) > 1;
        }

        private int getHeight(Node root) {
            return root == null ? -1 : root.height;
        }

        private int getSize(Node root) {
            return root == null ? 0 : root.size;
        }

        public Optional<Node> search(int val) {
            return searchTree(root, val);
        }


        private Optional<Node> searchTree(Node root, int val) {
            if (root == null) {
                return Optional.empty();
            }

            if (root.data == val) {
                return Optional.of(root);
            }

            if (root.data > val) {
                return searchTree(root.left, val);
            } else {
                return searchTree(root.right, val);
            }
        }

        private class Node {
            int data;
            int count;
            int height, size;

            Node left, right;

            public Node(int data) {
                this.data = data;
                this.count = 1;
                this.size = 1;
                this.height = 0;
            }

            public void increment() {
                this.count++;
                this.size++;
            }

            public void decrement() {
                this.count--;
                this.size--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
//        Scanner scanner = new Scanner(new FileInputStream(new File("input.txt")));
        Scanner scanner = new Scanner(System.in);
        int t = scanner.nextInt();

        while (t-- > 0) {
            int n = scanner.nextInt();
//            TestGenerator testGenerator = new TestGenerator();
//            testGenerator.generate(n);
            BST set = new BST(scanner.nextInt());

            // num iterations
            int iterations = (int) (Math.pow(2, n) - 1);

            // for each set add elements in the set
            long start = System.currentTimeMillis();
            IntStream.range(0, iterations).forEach(idx -> set.addElement(scanner.nextInt()));
            long end = System.currentTimeMillis();

//            System.out.println("Insertion time in ms : " + (end - start));

//            set.printSorted();

            // remove 0 element from the set
            set.delete(0);

            List<Integer> sumSet = new ArrayList<>();
            sumSet.add(0);


            List<Integer> ansSet = new ArrayList<>();

            start = System.currentTimeMillis();
            while (!set.isEmpty()) {
                int minVal = set.getMin();
                int listLen = sumSet.size();

//                System.out.println("Got element " + minVal);

                for (int i = 0; i < listLen; i++) {
                    int subSum = sumSet.get(i) + minVal;

//                    System.out.println("Deleting element " + subSum);
                    set.delete(subSum);
//                    System.out.println("Elements remaining " + set.getSize());
                    sumSet.add(subSum);
                }

                ansSet.add(minVal);
            }
            end = System.currentTimeMillis();

//            System.out.println("Time taken to delete all elements : " + (end-start));
            System.out.println(ansSet.stream().map(intVal -> Integer.toString(intVal))
                    .collect(Collectors.joining(" ")));
        }
    }


    private static class TestGenerator {

        public void generate(int n) throws IOException {

            int nums = (int) Math.pow(2, n);
            File file = new File("input.txt");

            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file));

            bufferedWriter.write(Integer.toString(1));
            bufferedWriter.write("\n");
            bufferedWriter.write(Integer.toString(n));
            bufferedWriter.write("\n");
            bufferedWriter.flush();


            // get all subset sums
            List<Integer> elements = new ArrayList<>();
            for (int i=0; i<n; i++) {
                elements.add((int) (Math.random() * (10e5)));
            }

            // now compute subset sums
            List<Integer> subsets = new ArrayList<>();

            generateSubsets(elements, 0, n, subsets, 0);

            assert subsets.size() == nums;

            bufferedWriter.write(Integer.toString(subsets.get(0)));

            for (int i=1; i<subsets.size(); i++) {
                bufferedWriter.write(String.format(" %d", subsets.get(i)));
            }
            bufferedWriter.flush();
        }

        private void generateSubsets(List<Integer> elements, int currentIdx, int totalElements,
                                     List<Integer> subsets, int currentSum) {
            if (currentIdx == totalElements) {
                subsets.add(currentSum);
                return;
            }

            // now include this element or exclude this element
            // exclude this element first
            generateSubsets(elements, currentIdx+1, totalElements, subsets, currentSum);
            generateSubsets(elements, currentIdx+1, totalElements, subsets, currentSum +
                    elements.get(currentIdx));
        }
    }
}