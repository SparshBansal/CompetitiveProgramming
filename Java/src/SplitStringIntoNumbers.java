public class SplitStringIntoNumbers {

    public long getNum(String val) {
        // convert the string to num
        long num = 0;
        for (int i=0; i<val.length(); i++) {
            num = num * 10 + (val.charAt(i) - '0');
        }
        return num;
    }

    public boolean splitString(String input) {
        // lets try for each prefix
        for (int i=0; i < input.length() - 1; i++) {
            // consider the prefix
            long prefix = getNum(input.substring(0, i+1));
            // for each string going forward we find the next value till
            long next = prefix - 1;
            int start = i+1;
            int end = i+2;
            while (end <= input.length()) {
                // keep increment end till we find the desired number
                if (getNum(input.substring(start, end)) == next) {
                    if (next == 0) {
                        // in this case only consume as many 0's as needed
                        while(end < input.length()) {
                            if (input.charAt(end) == '0') end++;
                            else break;
                        }
                    }
                    start = end;
                    end = start + 1;
                    next = next - 1;
                } else {
                    end++;
                }
            }
            if (start == input.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new SplitStringIntoNumbers().splitString("10009998"));
    }
}
