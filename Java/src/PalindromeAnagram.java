import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class PalindromeAnagram {
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

    static FastReader scanner = new FastReader();

    public int solve(String s) {
        Map<String, Integer> prefix = new HashMap<>();

        int ans = 0;

        // now iterate over the string keeping counts of chars
        int[] counts = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            // increment the count of current
            counts[current - 'a']++;

            // we can get all odd elements
            List<Character> odds = getAllOdds(counts);

            // now check if this entire substring can be a palindrome
            if (isAnagramPalindrome(counts)) {
                // update the max if required
                final int length = i + 1;
                ans = Math.max(ans, length);
            } else {
                // iterate over possible odds and assume rest should be even
                for (int j = 0; j < odds.size(); j++) {
                    StringBuilder queryBuilder = new StringBuilder();
                    for (int k = 0; k < odds.size(); k++) {
                        if (j != k) {
                            queryBuilder.append(odds.get(k));
                        }
                    }

                    // search in the map
                    String query = queryBuilder.toString();
                    if (!prefix.containsKey(query)) {
                        ans = Math.max(ans, 1);
                    } else {
                        // find the index of prefix
                        int prefixIndex = prefix.get(query);
                        final int length = i - prefixIndex;
                        ans = Math.max(ans, length);
                    }
                }
            }

            if (odds.size() > 0) {
                // insert if not present into the map
                StringBuilder keyBuilder = new StringBuilder();
                odds.forEach(keyBuilder::append);
                final String key = keyBuilder.toString();
                if (!prefix.containsKey(key)) {
                    prefix.put(key, i);
                }
            }
        }
        return ans;
    }

    private List<Character> getAllOdds(int[] counts) {
        List<Character> odds = new ArrayList<>();
        for (int i=0; i<counts.length; i++) {
            int count = counts[i];
            if (count % 2 == 1) {
                odds.add((char) ('a' + i));
            }
        }
        return odds;
    }

    private boolean isAnagramPalindrome(int[] counts) {
        // anagram can be a palindrome if at max 1 odd count exists
        int oddCount = 0;
        for (int count : counts) {
            if (count % 2 == 1) {
                oddCount++;
            }
        }
        return oddCount <= 1;
    }

    public static void main(String[] args) {
        String input = scanner.nextLine();
        System.out.println(new PalindromeAnagram().solve(input));
    }
}
