import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.StringTokenizer;
import java.util.function.Consumer;
import java.util.function.Function;

public class ReversePairs {

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

        public void traverseTree(Function<Node, Node> next) {
            Node travel = root;
            do {
                travel = next.apply(travel);
            } while (travel != null);
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

    private static class TravelContext {
        Integer data;
    }

    public static int reversePairs(int[] nums) {

        if (nums.length <= 1) {
            return 0;
        }

        BST bst = new BST(nums[nums.length - 1]);

        int totalAns = 0;
        int bruteForceTotalAns = 0;
        for (int i = nums.length - 2; i >= 0; i--) {
            // get all elements less than half
            int val = getVal(nums, i);
            TravelContext currentTravelContext = new TravelContext();
            currentTravelContext.data = 0;
            bst.traverseTree(root -> {
                if (root.data <= val) {
                    currentTravelContext.data += ((root.left != null ? root.left.size : 0) + root.count);
                    return root.right;
                }
                return root.left;
            });
            totalAns += currentTravelContext.data;

//            System.out.println(String.format("For num : %s, the reverse pairs are : %s", nums[i],
//                    currentTravelContext.data));

            // now we calculate via brute force as well
//            int bruteforceCnt = 0;
//            for (int j=i+1; j<nums.length; j++) {
//                if (nums[i] > (2*nums[j])) {
//                    bruteforceCnt++;
//                }
//            }
//
//            System.out.println(String.format("For num : %s, the reverse pairs (brute force) are : %s", nums[i],
//                    bruteforceCnt));
//            bruteForceTotalAns += bruteforceCnt;
            bst.addElement(nums[i]);
        }
//        System.out.println("Total brute force ans is : " + bruteForceTotalAns);
        return totalAns;
    }

    private static int getVal(int[] nums, int i) {
        if (nums[i] < 0) {
            return (nums[i] / 2) - 1;
        } else if (nums[i] > 0) {
            return (nums[i] - 1) / 2;
        }
        return -1;
    }


    public static void main(String[] args) {
        FastReader scanner = new FastReader();
        int n = scanner.nextInt();
        int num[] = new int[n];
        for (int i = 0; i < n; i++) {
            num[i] = scanner.nextInt();
        }
        System.out.println(reversePairs(num));
    }
}
