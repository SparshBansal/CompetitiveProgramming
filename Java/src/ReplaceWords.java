import java.util.*;
import java.util.stream.Collectors;

public class ReplaceWords {

    public class Node {
        private boolean isWordRoot;
        private String word;
        private Map<Character, Node> children;

        public Node(String word, boolean isWordRoot) {
            this.word = word;
            this.isWordRoot = isWordRoot;
            this.children = new HashMap<>();
        }
    }

    public class Trie {
        private Node root;
        public Trie() {
            this.root = new Node("", false);
        }

        public void insert(String word) {
            Node current = this.root;
            StringBuilder builder = new StringBuilder();
            for (int i=0; i<word.length(); i++) {
                // check if child exists
                builder.append(word.charAt(i));
                Node child = current.children.get(word.charAt(i));
                if (child == null) {
                    child = new Node(builder.toString(), false);
                    current.children.put(word.charAt(i), child);
                }
                current = child;
            }
            current.isWordRoot = true;
        }

        public String query(String target) {
            Node current = this.root;
            for (int i=0; i<target.length(); i++) {
                // find the child of the current node
                Node child = current.children.get(target.charAt(i));
                // if we could not find a child, return the whole word
                if (child == null) {
                    return target;
                }
                if (child.isWordRoot) {
                    return child.word;
                }
                current = child;
            }
            return target;
        }
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        Trie trie = new Trie();
        for (String word : dictionary) {
            trie.insert(word);
        }
        String[] words = sentence.split(" ");
        List<String> fin = new ArrayList<>();
        for (String word : words) {
            fin.add(trie.query(word));
        }
        return fin.stream().collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        System.out.println(new ReplaceWords().replaceWords(Arrays.asList("cat","bat","rat"), "the cattle was rattled by the battery"));       
    }
}
