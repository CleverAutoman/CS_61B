import java.util.LinkedList;
import java.util.Queue;

public class Trie {
    private trieNode root;

    public Trie() {
       root = new trieNode();
    }

    public void inputString(String s) {
        trieNode node = root;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!node.containsKey(chars[i])) {
                node.addKey(chars[i]);
            }
            node = node.getNode(chars[i]);
        }
        node.setEnd();
    }

    public boolean containsString(String s) {
        trieNode node = root;
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (!node.containsKey(chars[i])) {
                return false;
            }
            node = node.getNode(chars[i]);
        }
        return node.isEnd;
    }

    /**
     *  s is not included
     * @param s
     * @return
     */
    public String findNearestWord(String s) {
        trieNode node = root;
        char[] chars = s.toCharArray();
//
        for (int i = 0; i < chars.length; i++) {
            node = node.getNode(chars[i]);
        }

        StringBuilder sb = new StringBuilder(s);
        Queue<Pair<trieNode, String>> q = new LinkedList<>();
        q.add(new Pair<>(node, s));

        while (!q.isEmpty()) {
            Pair<trieNode, String> polled = q.poll();
            trieNode nextNode = polled.getKey();
            String nextSB = polled.getValue();

            if (nextNode.isEnd) return nextSB.toString();

            for (int i = 0; i < nextNode.innerMap.length; i++) {
                if (nextNode.innerMap[i] != null)
                    q.add(new Pair<>(nextNode.innerMap[i], nextSB + (char) (i + 'a')));
            }
        }
        return null;
    }

//        if (node == null) return "";  // Prefix not found
//
//        Queue<Pair<trieNode, String>> queue = new LinkedList<>();
//        queue.add(new Pair<>(node, s));
//
//        while (!queue.isEmpty()) {
//            Pair<trieNode, String> pair = queue.poll();
//            trieNode currentNode = pair.getKey();
//            String currentPath = pair.getValue();
//
//            if (currentNode.isEnd) {
//                return currentPath;  // Return the word corresponding to the nearest end node
//            }
//
//            for (int i = 0; i < 26; i++) {
//                trieNode child = currentNode.innerMap[i];
//                if (child != null) {
//                    char ch = (char) (i + 'a');
//                    queue.add(new Pair<>(child, currentPath + ch));
//                }
//            }
//        }
//        return "";
//    }



    private class trieNode {
        // store the value of ascii of each character
        private trieNode[] innerMap;
        // whether the node is at the end of a word
        private boolean isEnd;

        public trieNode() {
            this.innerMap = new trieNode[26];
            this.isEnd = false;
        }

        public void setEnd() {
            isEnd = true;
        }

        public boolean containsKey(char c) {
            return this.innerMap[c - 'a'] != null;
        }

        public void addKey(char c) {
            this.innerMap[c - 'a'] = new trieNode();
        }


        public trieNode getNode(char c) {
            return innerMap[c - 'a'];
        }
    }
}

// Helper class to manage node and path pairs in the queue
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}
