import java.util.Arrays;
import java.util.stream.Collectors;

public class KMP {

    public int[] lps(String pattern) {
        int[] lpsTable = new int[pattern.length()];
        lpsTable[0] = 0;
        for (int i = 1; i < pattern.length(); i++) {
            int curr = lpsTable[i - 1];
            while (curr != 0) {
                if (pattern.charAt(curr) == pattern.charAt(i)) {
                    lpsTable[i] = curr + 1;
                    break;
                } else {
                    curr = lpsTable[curr - 1];
                }
            }
            if (curr == 0) {
                if (pattern.charAt(i) == pattern.charAt(0)) {
                    lpsTable[i] = 1;
                } else {
                    lpsTable[i] = 0;
                }
            }
        }
        // calculate lps table for a pattern
        return lpsTable;
    }

    public int match(String data, String pattern) {
        // find the lps table
        int[] lpsTable = this.lps(pattern);
        // once we have the pattern lps, lets match
        int i = 0, j = 0;
        while (i < data.length()) {
            // check if match
            if (data.charAt(i) != pattern.charAt(j)) {
                // if not a match 
                if (j==0) {
                    i++;
                } else {
                    j = lpsTable[j-1];
                }
                continue;
            }
            // else it is a match
            i++;
            j++;
            if (j == pattern.length()) {
                // found a full match return
                return i - pattern.length();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        // find the lis
        System.out.println(new KMP().match("ababcabcababdbabd", "ababd"));
    }
}
