package io.learn.tries;

class Trie {
    private final Trie[] children = new Trie[26];
    private boolean isWord;

    public Trie() {
    }

    public void insert(String word) {
        insert(word, 0);
    }

    private void insert(String word, int index) {
        if (index >= word.length()) {
            return;
        }
        char charHere = word.charAt(index);
        if (children[charHere - 97] == null) {
            children[charHere - 97] = new Trie();
        }
        Trie temp = children[charHere - 97];
        temp.insert(word, index + 1);
        if (index == word.length() - 1) {
            temp.isWord = true;
        }
    }

    public boolean search(String word) {
        return search(word, 0);
    }

    private boolean search(String word, int index) {
        if (word == null || word.isEmpty() || index >= word.length()) {
            return true;
        }
        Trie childTrie = children[word.charAt(index) - 97];
        if (childTrie == null) {
            return false;
        }
        boolean childFound = childTrie.search(word, index + 1);
        if (index == word.length() - 1) {
            return childTrie.isWord;
        }
        return childFound;
    }

    public boolean startsWith(String prefix) {
        return startsWith(prefix, 0);
    }

    private boolean startsWith(String prefix, int index) {
        if (prefix == null || prefix.isEmpty() || index >= prefix.length()) {
            return true;
        }
        Trie childTrie = children[prefix.charAt(index) - 97];
        if (childTrie == null) {
            return false;
        }
        return childTrie.startsWith(prefix, index + 1);
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("beer");
        trie.insert("add");
        trie.insert("jam");
        trie.insert("rental");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
        System.out.println(trie.startsWith("app"));
    }
}