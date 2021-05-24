import javafx.util.Pair;

import javax.swing.text.html.Option;
import java.util.*;

public class LongestConsecutiveSequence {
    public static void main(String[] args) {
        int[] ar = new int[]{1,100,-1,3,200,4,2,7,6,5};
        System.out.println(longestConsecutiveSequence(ar));
    }

    public static int longestConsecutiveSequence(int[] nums) {
        Map<Integer, Pair<Integer,Integer>> map = new HashMap<>();
        for (int num : nums) {
            // ignore if already exists
            if (map.containsKey(num))
                continue;

            // otherwise check if prepends
            if (map.containsKey(num + 1) && !map.containsKey(num-1)) {
                Pair p = map.get(num+1);
                map.put(num, new Pair<>(num, (Integer) p.getValue()));
                map.put((Integer)p.getValue(), new Pair<>(num, (Integer)p.getValue()));
            }

            // check if appends
            else if (map.containsKey(num-1) && !map.containsKey(num+1)) {
                Pair p = map.get(num-1);
                map.put(num, new Pair<>((Integer)p.getKey(), num));
                map.put((Integer)p.getKey(), new Pair<>((Integer) p.getKey(), num));
            }

            // else if it stiches 2 chains
            else if (map.containsKey(num-1) && map.containsKey(num+1)) {
                Pair l = map.get(num-1);
                Pair r = map.get(num+1);

                map.put((Integer)l.getKey(), new Pair<>((Integer) l.getKey(), (Integer)r.getValue()));
                map.put((Integer)r.getValue(), new Pair<>((Integer)l.getKey(), (Integer)r.getValue()));
                map.put(num, new Pair<>((Integer) l.getKey(),(Integer) r.getValue()));
            }
            else
                map.put(num , new Pair<>(num, num));
        }
        Optional<Integer> maxi = map.values().stream().map(p -> p.getValue() - p.getKey() + 1).max(Comparator.comparingInt(Integer::intValue));
        return maxi.get();
    }
}
