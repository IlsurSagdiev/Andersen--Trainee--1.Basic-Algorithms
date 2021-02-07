package andersen;

import java.util.*;


 /**Graph with breadth-first search**/

public class BreadthFirstSearch {

    public static void main(String[] args) {
    }

    private Queue<Node> queue;
    static ArrayList<Node> nodes = new ArrayList<Node>();


    public BreadthFirstSearch() {
        queue = new LinkedList<Node>();
    }

    public void breadthFirstSearch(Node node) {
        queue.add(node);
        node.checked = true;
        while (!queue.isEmpty()) {

            Node element = queue.remove();
            List<Node> neighbours = element.getNeighbours();
            for (int i = 0; i < neighbours.size(); i++) {
                Node neighbour = neighbours.get(i);
                if (neighbour != null && !neighbour.checked) {
                    queue.add(neighbour);
                    neighbour.checked = true;
                }
            }
        }
    }

    /**Neighbours**/
    static class Node {
        boolean checked;
        List<Node> neighbours;

        public List<Node> getNeighbours() {
            return neighbours;
        }

        public void setNeighbours(List<Node> neighbours) {
            this.neighbours = neighbours;
        }
    }
}

