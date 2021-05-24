import java.util.*;

public class DifferentCharacters {

    private void putOrIncrement(Map<Character, Integer> map, Character c) {
        Integer intval = map.getOrDefault(c, 0);
        ++intval;
        map.put(c, intval);
    }

    private void decrementOrRemove(Map<Character, Integer> map, Character c) {
        Integer intval = map.getOrDefault(c, 1);
        --intval;
        if (intval == 0) {
            map.remove(c);
        } else {
            map.put(c, intval);
        }
    }

    public int solution(String s, int k) {

        // use two pointer technique
        // com
        Map<Character, Integer> map = new HashMap<>();

        // add all distinct with multiplicity
        for (int i = s.length() - 1; i >= 0; --i) {
            putOrIncrement(map, s.charAt(i));
        }

        if (map.size() == k) {
            return 0;
        }

        decrementOrRemove(map, s.charAt(0));

        int ans = Integer.MAX_VALUE;

        // run two pointer approach
        int start = 0, end = 0;

        while (start <= end) {
            // need to see this new position
            if (map.size() == k) {
                ans = Math.min(ans, end - start + 1);
                putOrIncrement(map, s.charAt(start));
                ++start;
            }
            else if (map.size() < k) {
                // move start pointer a little ahead
                putOrIncrement(map, s.charAt(start));
                ++start;
            }
            else {
                // move the end

                // if end is already at the end of the string
                // there is nothing we can do
                if (end == s.length()-1) {
                    putOrIncrement(map, s.charAt(start));
                    ++start;
                }
                else {
                    decrementOrRemove(map, s.charAt(end+1));
                    ++end;
                }
            }
        }

        if (ans == Integer.MAX_VALUE) {
            return -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        int k = scanner.nextInt();

        DifferentCharacters differentCharacters = new DifferentCharacters();
        System.out.println(differentCharacters.solution(input, k));
    }
}