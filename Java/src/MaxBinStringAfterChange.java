public class MaxBinStringAfterChange {
    public String afterChange(String binary) {
        // count number of zeros
        int cnt = 0;
        for (int i=0; i<binary.length(); i++) {
            if (binary.charAt(i) == '0') cnt++;
        }
        if (cnt <= 1) {
            return binary;
        }
        // construct the final string
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<binary.length(); i++) {
            if (binary.charAt(i) == '1') builder.append('1');
            else break;
        }
        // now we add cnt - 1 , '1s'
        for (int i=0; i<cnt - 1; i++) builder.append('1');
        builder.append('0');
        // for remaining append '1s'
        int rem = binary.length() - builder.length();
        for (int i=0; i<rem; i++) builder.append('1');
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(new MaxBinStringAfterChange().afterChange("01"));
    }
}
