package io.learn.graphs.busroutes;

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
        System.out.println(numBusesToDestination(new int[][] { { 1, 2, 7 }, { 3, 6, 7 } }, 1, 6));
    }

    public static int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }
        // key=station routes[i][j], value = route indexes [0, 1]
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            int[] route = routes[i];
            for (int station : route) {
                adjList.putIfAbsent(station, new ArrayList<>());
                // one station can be in multiple routes.
                adjList.get(station).add(i);
            }
        }

        if (!adjList.containsKey(source)) {
            return -1;
        }

        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visitedRouteSet = new HashSet<>();
        // we start from source station, find in which routes, the station is?
        queue.add(new int[] { source, 0 });
        while (!queue.isEmpty()) {
            int[] stationInfo = queue.poll();
            int station = stationInfo[0];
            if (station == target) {
                return stationInfo[1];
            }
            for (int routeIdx : adjList.get(station)) {
                // in these routes, try to find destination and increment buses.
                if (visitedRouteSet.contains(routeIdx)) {
                    continue;
                }
                int[] stations = routes[routeIdx];
                for (int stationToQueue : stations) {
                    queue.add(new int[] { stationToQueue, stationInfo[1] + 1 });
                }
                visitedRouteSet.add(routeIdx);
            }
        }
        return -1;
    }

}
