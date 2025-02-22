package io.learn.graphs.networkdelaytime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        System.out.println(findDelay(1, 4, new int[][] { { 1, 2, 1 }, { 2, 3, 7 }, { 1, 3, 4 }, { 2, 1, 2 } }));
    }

    private static int findDelay(int k, int n, int[][] times) {
        Map<Integer, List<int[]>> adjList = new HashMap<>();

        // build adjList.
        for (int i = 0; i < n; i++) {
            adjList.put(i + 1, new ArrayList<>());
        }

        for (int[] time : times) {
            int fromNode = time[0];
            int toNode = time[1];
            int timeTaken = time[2];
            adjList.get(fromNode).add(new int[] { toNode, timeTaken });
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.add(new int[] { k, 0 });
        Set<Integer> visited = new HashSet<>();
        int maxDelay = -1;

        while (!pq.isEmpty()) {
            int[] currentNodeInfo = pq.poll();
            int node = currentNodeInfo[0];
            if (!visited.contains(node)) {
                visited.add(node);
                int delayToNode = currentNodeInfo[1];
                maxDelay = Integer.max(maxDelay, delayToNode);
                for (int[] neighborInfo : adjList.get(node)) {
                    pq.add(new int[] { neighborInfo[0], delayToNode + neighborInfo[1] });
                }
            }
        }

        if (visited.size() == n) {
            return maxDelay;
        }
        return -1;
    }

}
