package io.learn.graphs.graphvalidtree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println(validTree(5, new int[][] { { 0, 1 }, { 0, 2 }, { 0, 3 }, { 1, 4 } }));
    }

    public static boolean validTree(int n, int[][] edges) {
        if (edges.length != n - 1) {
            return false;
        }
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            adjList.put(i, new ArrayList<>());
        }
        for (int[] edge : edges) {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }
        Set<Integer> visited = new HashSet<>();
        traverseGraph(adjList, visited, 0);
        if (visited.size() != n) {
            return false;
        }
        return true;
    }

    private static void traverseGraph(Map<Integer, List<Integer>> adjList, Set<Integer> visited, int node) {
        visited.add(node);
        for (int neighbor : adjList.get(node)) {
            if (visited.contains(neighbor)) {
                continue;
            }
            traverseGraph(adjList, visited, neighbor);
        }
    }

}
