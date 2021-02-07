package andersen;

import java.util.*;

/**
 * Dijkstra's algorithm, it only works with a positive weights
 **/
public class DijkstraSearch {
    public static void main(String[] args) {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");

        nodeA.addWay(nodeB, 23);
        nodeA.addWay(nodeC, 11);

        nodeB.addWay(nodeD, 15);
        nodeB.addWay(nodeF, 12);

        nodeC.addWay(nodeE, 85);

        nodeD.addWay(nodeE, 12);
        nodeD.addWay(nodeF, 12);

        nodeF.addWay(nodeE, 5);

        Graph graph = new Graph();

        graph.addNode(nodeA);
        graph.addNode(nodeB);
        graph.addNode(nodeC);
        graph.addNode(nodeD);
        graph.addNode(nodeE);
        graph.addNode(nodeF);

        graph = Graph.dijkstraSearch(graph, nodeA);

    }
}

class Graph {
    private Set<Node> nodes = new HashSet<>();

    public void addNode(Node node) {
        nodes.add(node);
    }

    public static Graph dijkstraSearch(Graph graph, Node node) {
        node.setDistance(0);

        Set<Node> selectNode = new HashSet<>();     // storing checked paths
        Set<Node> unSelectNode = new HashSet<>();   // storing unchecked paths

        unSelectNode.add(node);                     // put new Node in unchecked hashset

        while (unSelectNode.size() != 0) {
            Node currentNode = getLowDistanceNode(unSelectNode);    // take low distance from unchecked hashset
            currentNode.getAdjacentNodes()
                    .forEach((key, weight) -> {
                        if (!selectNode.contains(key)) {
                            compareActualDistWithNewDistance(key,weight,currentNode);
                            unSelectNode.add(key);
                        }
                    });
            selectNode.add(currentNode);       // add current node in settled Nodes set
            unSelectNode.remove(currentNode);  // remove low current Node from unSelect set
        }

        return graph;
    }

    /**
     * this method return low distance Node from unChecked set
     **/
    public static Node getLowDistanceNode(Set<Node> unsettledNodes) {
        Node lowestDistanceNode = null;
        int lowestDistance = Integer.MAX_VALUE;             // set value infinity
        for (Node node : unsettledNodes) {                  // search low distance
            int nodeDistance = node.getDistance();
            if (nodeDistance < lowestDistance) {
                lowestDistance = nodeDistance;
                lowestDistanceNode = node;
            }
        }
        return lowestDistanceNode;
    }

    /**
     * compares the actual distance with the newly calculated one, following the recently explored path
     **/
    public static void compareActualDistWithNewDistance(Node calcNode, Integer weight, Node sourceNode) {
        if (sourceNode.getDistance() + weight < calcNode.getDistance()) {
            calcNode.setDistance(sourceNode.getDistance() + weight);
            List<Node> shortPath = new LinkedList<>(sourceNode.getShortPath());
            shortPath.add(sourceNode);
            calcNode.setShortPath(shortPath);
        }
    }

    @Override
    public String toString() {
        return "Graph{" +
                "nodes=" + nodes +
                '}';
    }

    public Set<Node> getNodes() {
        return nodes;
    }
}

/**
 * Node has name, list of short path, distance default value maximum(infinity)
 **/
class Node {
    @Override
    public String toString() {
        return "Node{" +
                "name='" + name + '\'' +
                ", shortPath=" + shortPath +
                ", distance=" + distance +
                '}';
    }

    private String name;                                //Node name
    private List<Node> shortPath = new LinkedList<>();  //short path list
    private int distance = Integer.MAX_VALUE;           //distance default max value
    Map<Node, Integer> adjacentNodes = new HashMap<>();  //Adjacent Node map.It has neighbour Node and distance

    public Node(String name) {
        this.name = name;
    }

    public void addWay(Node nextNode, int distance) {
        adjacentNodes.put(nextNode, distance);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Node> getShortPath() {
        return shortPath;
    }

    public void setShortPath(List<Node> shortPath) {
        this.shortPath = shortPath;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public Map<Node, Integer> getAdjacentNodes() {
        return adjacentNodes;
    }

    public void setAdjacentNodes(Map<Node, Integer> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
    }
}

