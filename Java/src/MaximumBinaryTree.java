public class MaximumBinaryTree {

    public static class SegmentTree {

        public static class Node {
            int l, r;
            int maxVal, maxIdx;
            Node left, right;

            public Node(int l, int r, int maxVal, int maxIdx) {
                this.l = l;
                this.r = r;
                this.maxVal = maxVal;
                this.maxIdx = maxIdx;
            }
        }

        // construct a segment tree
        private Node root;

        public void construct(int[] vals) {
            this.root = constructInternal(vals, 0, vals.length - 1);
        }

        public Node constructInternal(int[] vals, int l, int r) {
            if (l == r) {
                // create a new node
                return new Node(l, r, vals[l], l);
            }
            // else find mid
            int m = (l + (r - l) / 2);
            Node left = constructInternal(vals, l, m);
            Node right = constructInternal(vals, m + 1, r);

            int maxVal, maxIdx = 0;
            if (left.maxVal > right.maxVal) {
                maxVal = left.maxVal;
                maxIdx = left.maxIdx;
            } else {
                maxVal = right.maxVal;
                maxIdx = right.maxIdx;
            }
            Node curr = new Node(l, r, maxVal, maxIdx);
            curr.left = left;
            curr.right = right;
            return curr;
        }

        public static class Result {
            int maxVal, maxIdx;

            public Result(int maxVal, int maxIdx) {
                this.maxIdx = maxIdx;
                this.maxVal = maxVal;
            }
        }

        public Result query(int rangeL, int rangeR) {
            return this.queryInternal(this.root, rangeL, rangeR);
        }

        private Result queryInternal(Node root, int rangeL, int rangeR) {
            int l = root.l, r = root.r;
            if (root.l == rangeL && root.r == rangeR) {
                return new Result(root.maxVal, root.maxIdx);
            }
            // if the ranges don't overlap
            int m = l + (r - l) / 2;
            if (rangeR <= m) {
                return queryInternal(root.left, rangeL, rangeR);
            }
            if (rangeL > m) {
                return queryInternal(root.right, rangeL, rangeR);
            }
            // else we need to split
            Result lResult = queryInternal(root.left, rangeL, m);
            Result rResult = queryInternal(root, m + 1, rangeR);
            return lResult.maxVal > rResult.maxVal ? lResult : rResult;
        }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        SegmentTree tree = new SegmentTree();
        tree.construct(nums);
        return makeBinaryTree(tree, 0, nums.length - 1);
    }

    public TreeNode makeBinaryTree(SegmentTree segmentTree, int l, int r) {
        if (l > r) {
            return null;
        }
        // find max
        SegmentTree.Result result = segmentTree.query(l, r);
        if (l == r) {
            return new TreeNode(result.maxVal);
        }
        // query the max value
        TreeNode left = makeBinaryTree(segmentTree, l, result.maxIdx - 1);
        TreeNode right = makeBinaryTree(segmentTree, result.maxIdx + 1, r);

        return new TreeNode(result.maxVal, left, right);
    }

    public static void main(String[] args) {
        
    }

}
