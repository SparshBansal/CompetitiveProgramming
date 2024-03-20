public class LengthOfLastWord {

    public int lengthOfWord(String word) {
        // find the length of last word.
        int lastWordLength = 0;
        int currentWordLength = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != ' ') {
                currentWordLength++;
            } else {
                if (currentWordLength > 0) {
                    lastWordLength = currentWordLength;
                }
                currentWordLength = 0;
           }
        }
        if (currentWordLength > 0) {
            lastWordLength = currentWordLength;
        }
        return lastWordLength;
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLastWord().lengthOfWord("luffy is still joyboy"));
    }

}
