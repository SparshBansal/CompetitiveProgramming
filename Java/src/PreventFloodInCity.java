import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.stream.Collectors;

public class PreventFloodInCity {

    private class LakeWithNext {
        private int lake;
        private int next;
        
        public LakeWithNext(int lake, int next) {
            this.lake = lake;
            this.next = next;
        }

        public int getLake() {
            return lake;
        }

        public int getNext() {
            return next;
        }
    }

    public int[] avoidFlood(int[] rains) {
        PriorityQueue<LakeWithNext> pQueue = new PriorityQueue<>((l, r)-> {
            return Integer.compare(l.next, r.next);
        });
        // next Idx
        final int n = rains.length;

        int[] nextIdx = new int[n];
        Map<Integer, Integer> nextMap = new HashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            int currentElement = rains[i];
            if (nextMap.containsKey(currentElement)) {
                nextIdx[i] = nextMap.get(currentElement);
            } else {
                nextIdx[i] = Integer.MAX_VALUE;
            }
            nextMap.put(currentElement, i);
        }

        Set<Integer> lakesFilled = new HashSet<>();

        int[] finalAnswer = new int[n];
        for (int i = 0; i < n; i++) {
            int lake = rains[i];
            if (lake != 0) {
                // we will check if this lake is already filled, we cannot prevent flood here
                if (lakesFilled.contains(lake)) {
                    return new int[0];
                }
                // lake is not filled , lets fill the lake
                lakesFilled.add(lake);
                // put the next time it will rain in the pQue
                pQueue.add(new LakeWithNext(lake, nextIdx[i]));
                finalAnswer[i] = -1;
            } else {
                // we need to check which lake to dry or empty
                if (lakesFilled.size() == 0) {
                    finalAnswer[i] = 1;
                    continue;
                }
                LakeWithNext lakeWithNext = pQueue.poll();
                int lakeToDry = lakeWithNext.getLake();
                finalAnswer[i] = lakeToDry;
                lakesFilled.remove(lakeToDry);
            }
        }
        return finalAnswer;
    }

    public static void main(String[] args) {
        System.out.println(Arrays
                .stream(new PreventFloodInCity().avoidFlood(new int[] { 1,2,0,2,3,0,1 }))
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

}
