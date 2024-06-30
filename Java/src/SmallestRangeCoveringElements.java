import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringElements {
    
    private class Range {
        private int s, e;
        private int length;
        
        public Range(int start, int end) {
            this.s = start;
            this.e = end;
            this.length = this.e - this.s;
        }

        public Range compare(Range other) {
            if (this.length == other.length) {
                return this.s < other.s ? this : other;
            }
            if (this.length < other.length) {
                return this;
            }
            return other;
        }
    }


    private static class ListElement {
        private int listIdx, elementIdx, val;
        public ListElement(int listIdx, int elementIdx, int val) {
            this.listIdx = listIdx;
            this.elementIdx = elementIdx;
            this.val = val;
        }

        public static Comparator<ListElement> compare = (l1, l2) -> {
            return Integer.compare(l1.val, l2.val);
        };
    }

    public int[] smallestRange(List<List<Integer>> nums) {
        // construct a priority queue with initial elements
        PriorityQueue<ListElement> pQueue = new PriorityQueue<>(ListElement.compare);
        
        int numLists = nums.size();
        int max = Integer.MIN_VALUE;
        for (int i=0; i<numLists; i++) {
            pQueue.add(new ListElement(i, 0, nums.get(i).get(0)));
            max = Math.max(max, nums.get(i).get(0));
        }
        Range ans = new Range(pQueue.peek().val, max);

        while(true) {
            // get the top of the pqueue
            ListElement minElement = pQueue.poll();
            Range curr = new Range(minElement.val, max);
            ans = ans.compare(curr);

            if (minElement.elementIdx == nums.get(minElement.listIdx).size() - 1) {
                // cannot go beyond this
                break;
            }
            // else move to the next index
            int val = nums.get(minElement.listIdx).get(minElement.elementIdx + 1);
            max = Math.max(max, val);

            ListElement next = new ListElement(minElement.listIdx, minElement.elementIdx + 1, val);
            pQueue.add(next);            
        }
        return new int[]{ans.s, ans.e};
    }
    public static void main(String[] args) {
        
        System.out.println(new SmallestRangeCoveringElements().smallestRange());
    }
}
