package io.learn.graphs.clonegraph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Node node = new Node(1);
        Node nodeChild1 = new Node(2);
        Node nodeChild2 = new Node(3);
        Node nodeChild1Child1 = new Node(4);
        node.neighbors.add(nodeChild1);
        node.neighbors.add(nodeChild2);
        nodeChild1.neighbors.add(nodeChild1Child1);
        cloneGraph(node);
    }

    public static Node cloneGraph(Node node) {
        return clone(node, new HashMap<>());
    }

    private static Node clone(Node node, Map<Node, Node> cloneMap) {
        if (node == null) {
            return null;
        }

        Node newNode = new Node(node.val);
        cloneMap.put(node, newNode);

        for (Node neighborNode : node.neighbors) {
            Node newNeighborNode;
            if (cloneMap.containsKey(neighborNode)) {
                newNeighborNode = cloneMap.get(neighborNode);
            } else {
                newNeighborNode = clone(neighborNode, cloneMap);
                cloneMap.put(neighborNode, newNeighborNode);
            }
            newNode.neighbors.add(newNeighborNode);
        }

        return newNode;
    }

    public static class Node {
        int val;
        List<Node> neighbors;

        public Node(int val) {
            this.val = val;
            this.neighbors = new ArrayList<>();
        }
    }
}
