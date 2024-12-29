package io.learn.topologicalsort.compilationorder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {
        List<List<Character>> dependencies = new ArrayList<>();
        dependencies.add(List.of('C', 'A'));
        dependencies.add(List.of('B', 'A'));
        dependencies.add(List.of('D', 'C'));
        dependencies.add(List.of('E', 'B'));
        dependencies.add(List.of('E', 'D'));
        System.out.println(topologicalsort(dependencies));
    }

    private static List<Character> topologicalsort(List<List<Character>> dependencies) {
        List<Character> result = new ArrayList<>();

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
            result.add(classCh);
            for (var child : adjList.get(classCh)) {
                indegreeMap.put(child, indegreeMap.get(child) - 1);
                if (indegreeMap.get(child) == 0) {
                    queue.add(child);
                }
            }
        }

        if (result.size() != adjList.size()) {
            new ArrayList<>();
        }

        // worst case O(V+E)
        return result;
    }
}
