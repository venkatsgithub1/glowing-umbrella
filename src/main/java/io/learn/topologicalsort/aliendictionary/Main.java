package io.learn.topologicalsort.aliendictionary;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Topological sort as an extension of sorting dependencies.
 */
public class Main {
    public static void main(String[] args) {
        alienOrder(List.of("ca", "aa", "ab"));
        alienOrder(List.of("wrt", "wrf", "er", "ett", "rftt"));
        System.out.println(alienOrder(List.of("mzosr", "mqov", "xxsvq", "xazv", "xazau", "xaqu", "suvzu", "suvxq",
                "suam", "suax", "rom", "rwx", "rwv")));
        System.out.println(alienOrder(List.of("m", "mx", "mxe", "mxer", "mxerl", "mxerlo", "mxerlos", "mxerlost",
                "mxerlostr", "mxerlostrpq", "mxerlostrp")) + " answer");
    }

    private static String alienOrder(List<String> words) {
        Set<Character> allCharacters = new HashSet<>();
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                allCharacters.add(ch);
            }
        }

        List<List<Character>> dependencies = new ArrayList<>();
        for (int i = 1; i < words.size(); i++) {
            String word1 = words.get(i - 1);
            String word2 = words.get(i);

            int iterTill = word1.length() < word2.length() ? word1.length() : word2.length();
            int j;
            for (j = 0; j < iterTill; j++) {
                if (word1.charAt(j) == word2.charAt(j)) {
                    continue;
                }
                dependencies.add(List.of(word2.charAt(j), word1.charAt(j)));
                break;
            }
            if (j >= word1.length() || j >= word2.length()) {
                if (word2.length() < word1.length()) {
                    return "";
                }
            }
        }

        // System.out.println(dependencies);
        StringBuilder result = new StringBuilder();

        Map<Character, Integer> indegreeMap = new HashMap<>();
        Map<Character, List<Character>> adjList = new HashMap<>();

        // create adj list, in degree map for all classes.
        // time complexity: looping times the dependencies, dependencies are edges, O(E)
        // in a graph.
        for (var dependencyItem : dependencies) {
            Character child = dependencyItem.get(0);
            Character parent = dependencyItem.get(1);
            adjList.putIfAbsent(parent, new ArrayList<>());
            adjList.putIfAbsent(child, new ArrayList<>());
            adjList.get(parent).add(child);
            indegreeMap.putIfAbsent(parent, 0);
            indegreeMap.put(child, indegreeMap.getOrDefault(child, 0) + 1);
        }

        for (char ch : allCharacters) {
            if (!indegreeMap.containsKey(ch)) {
                indegreeMap.put(ch, 0);
                adjList.put(ch, new ArrayList<>());
            }
        }

        // System.out.println(indegreeMap);

        // push all classes with indegree 0 into a queue
        // looping times the indegreeMap contains 0 indegree nodes, worst case O(V)
        Queue<Character> queue = new LinkedList<>();
        for (Character classCh : indegreeMap.keySet()) {
            if (indegreeMap.get(classCh) == 0) {
                queue.add(classCh);
            }
        }

        // process nodes in topological order
        // looping queue, O(V) expected, and adding to queue is based on O(E)
        // iterations.
        // O(V+E)
        while (!queue.isEmpty()) {
            var classCh = queue.poll();
            result.append(classCh);
            for (var child : adjList.get(classCh)) {
                indegreeMap.put(child, indegreeMap.get(child) - 1);
                if (indegreeMap.get(child) == 0) {
                    queue.add(child);
                }
            }
        }

        if (result.length() != adjList.size()) {
            return "";
        }

        // worst case O(V+E)
        return result.toString();
    }
}
