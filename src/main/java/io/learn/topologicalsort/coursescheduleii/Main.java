package io.learn.topologicalsort.coursescheduleii;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        System.out.println(getSchedule(5, List.of(List.of(1, 0), List.of(2, 0), List.of(3, 1), List.of(4, 3))));
        System.out.println(getSchedule(4, List.of(List.of(1, 0), List.of(2, 0), List.of(3, 1), List.of(3, 2))));
        System.out.println(
                getSchedule(6, List.of(List.of(1, 0), List.of(2, 0), List.of(3, 2), List.of(2, 5), List.of(4, 5))));
        System.out.println(
                getSchedule(6, List.of(List.of(1, 0), List.of(0, 3), List.of(0, 2), List.of(3, 2), List.of(2, 5),
                        List.of(4, 5))));
        System.out.println(
                getSchedule(1, List.of()));
    }

    private static List<Integer> getSchedule(int n, List<List<Integer>> prereqs) {
        List<Integer> result = new ArrayList<>();
        Map<Integer, Set<Integer>> adjList = new HashMap<>();
        Map<Integer, Integer> indegreeMap = new HashMap<>();

        for (List<Integer> prereq : prereqs) {
            int parent = prereq.get(1);
            int child = prereq.get(0);
            adjList.putIfAbsent(parent, new HashSet<>());
            adjList.putIfAbsent(child, new HashSet<>());
            adjList.get(parent).add(child);
            indegreeMap.putIfAbsent(parent, 0);
        }

        for (int i = 0; i < n; i++) {
            adjList.putIfAbsent(i, new HashSet<>());
            indegreeMap.putIfAbsent(i, 0);
        }

        for (int key : adjList.keySet()) {
            for (int child : adjList.get(key)) {
                indegreeMap.put(child, indegreeMap.getOrDefault(child, 0) + 1);
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (Map.Entry<Integer, Integer> entry : indegreeMap.entrySet()) {
            if (entry.getValue() == 0) {
                queue.add(entry.getKey());
            }
        }

        while (!queue.isEmpty()) {
            var course = queue.poll();
            result.add(course);

            for (int child : adjList.get(course)) {
                indegreeMap.put(child, indegreeMap.get(child) - 1);
                if (indegreeMap.get(child) == 0) {
                    queue.add(child);
                }
            }
        }

        return result;
    }
}
