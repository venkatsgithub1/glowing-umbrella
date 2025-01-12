package io.learn.topologicalsort.aliendictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main2 {
    public static void main(String[] args) {
        System.out.println(
                topologicalSort(new String[] { "mzosr", "mqov", "xxsvq", "xazv", "xazau", "xaqu", "suvzu", "suvxq",
                        "suam", "suax", "rom", "rwx", "rwv" }));
        System.out.println(
                topologicalSort(new String[] { "m", "mx", "mxe", "mxer", "mxerl", "mxerlo", "mxerlos", "mxerlost",
                        "mxerlostr", "mxerlostrpq", "mxerlostrp" }) + " answer");
    }

    private static String topologicalSort(String[] words) {
        StringBuilder builder = new StringBuilder();
        Map<Character, Set<Character>> adjList = new HashMap<>();
        Map<Character, Integer> indegreeMap = new HashMap<>();

        // populate indegreeMap with default value 0 for all occurring characters.
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                indegreeMap.putIfAbsent(ch, 0);
                adjList.putIfAbsent(ch, new HashSet<>());
            }
        }

        // populate adjList to represent graph.
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];

            int j;
            for (j = 0; j < word1.length() && j < word2.length(); j++) {
                char word1ch = word1.charAt(j);
                char word2ch = word2.charAt(j);
                if (word1ch != word2ch) {
                    if (!adjList.get(word1ch).contains(word2ch)) {
                        adjList.get(word1ch).add(word2ch);
                        indegreeMap.put(word2ch, indegreeMap.get(word2ch) + 1);
                    }
                }
            }

            if (j >= word1.length() || j >= word2.length()) {
                if (word2.length() < word1.length()) {
                    return "";
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (char ch : indegreeMap.keySet()) {
            if (indegreeMap.get(ch) == 0) {
                queue.add(ch);
            }
        }

        while (!queue.isEmpty()) {
            Character ch = queue.poll();
            builder.append(ch);
            if (adjList.containsKey(ch)) {
                var children = adjList.get(ch);
                for (char child : children) {
                    indegreeMap.put(child, indegreeMap.get(child) - 1);
                    if (indegreeMap.get(child) == 0) {
                        queue.add(child);
                    }
                }
            }
        }

        if (adjList.size() != builder.length()) {
            return "";
        }

        return builder.toString();
    }
}
