import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DivideIntoMinGroups {

    private static class Event {
        private int val;
        private int startEnd;

        public Event(int val, int startEnd) {
            this.val = val;
            this.startEnd = startEnd;
        }

        public static Comparator<Event> ordering = (o1, o2) -> {
            if (o1.val == o2.val) {
                return Integer.compare(o1.startEnd, o2.startEnd);
            }
            return Integer.compare(o1.val, o2.val);
        };
    }

    public int minGroups(int[][] intervals) {
        List<Event> events = Arrays.stream(intervals)
                .flatMap(
                        interval -> Arrays.stream(new Event[] { new Event(interval[0], 0), new Event(interval[1], 1) }))
                .sorted(Event.ordering)
                .collect(Collectors.toList());

        int max = 0;
        int curr = 0;
        for (Event event : events) {
            if (event.startEnd == 0) {
                curr++;
            }
            if (event.startEnd == 1) {
                curr--;
            }
            max = Math.max(max, curr);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new DivideIntoMinGroups().minGroups(new int[][]{
            {1,3},
            {5,6},
            {8,10},
            {11,13}
        }));
    }
}
