
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public DijkstraSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    public Map<Vertex<V>, Double> dijkstraSearch(Vertex<V> startVertex) {
        Map<Vertex<V>, Double> distances = new HashMap();
        PriorityQueue<DijkstraNode<V>> priorityQueue = new PriorityQueue();
        Iterator var4 = this.graph.getVertices().iterator();

        Vertex currentVertex;
        while(var4.hasNext()) {
            currentVertex = (Vertex)var4.next();
            distances.put(currentVertex, currentVertex.equals(startVertex) ? 0.0 : Double.POSITIVE_INFINITY);
        }

        priorityQueue.offer(new DijkstraNode(startVertex, 0.0));

        while(true) {
            double currentDistance;
            do {
                if (priorityQueue.isEmpty()) {
                    return distances;
                }

                DijkstraNode<V> currentNode = (DijkstraNode)priorityQueue.poll();
                currentVertex = currentNode.getVertex();
                currentDistance = currentNode.getDistance();
            } while(currentDistance > (Double)distances.get(currentVertex));

            List<WeightedGraph<V>.Edge<V>> edges = this.graph.getEdges(currentVertex);
            Iterator var9 = edges.iterator();

            while(var9.hasNext()) {
                WeightedGraph<V>.Edge<V> edge = (WeightedGraph$Edge)var9.next();
                Vertex<V> neighborVertex = edge.getDestination();
                double edgeWeight = edge.getWeight();
                double distanceThroughCurrent = currentDistance + edgeWeight;
                if (distanceThroughCurrent < (Double)distances.get(neighborVertex)) {
                    distances.put(neighborVertex, distanceThroughCurrent);
                    priorityQueue.offer(new DijkstraNode(neighborVertex, distanceThroughCurrent));
                }
            }
        }
    }

    public void printDijkstra(Vertex<V> startVertex) {
        Map<Vertex<V>, Double> distances = this.dijkstraSearch(startVertex);
        System.out.println("Dijkstra's Algorithm Results:");
        Iterator var3 = distances.keySet().iterator();

        while(var3.hasNext()) {
            Vertex<V> vertex = (Vertex)var3.next();
            Double distance = (Double)distances.get(vertex);
            String distanceString = distance == Double.POSITIVE_INFINITY ? "Infinity" : String.valueOf(distance);
            PrintStream var10000 = System.out;
            String var10001 = String.valueOf(vertex.getData());
            var10000.println("Vertex: " + var10001 + ", Distance: " + distanceString);
        }

    }

    public List<V> findPath(Vertex<V> source, Vertex<V> destination) {
        Map<Vertex<V>, Double> distances = new HashMap();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap();
        PriorityQueue<DijkstraNode<V>> priorityQueue = new PriorityQueue();
        Iterator var6 = this.graph.getVertices().iterator();

        Vertex currentVertex;
        while(var6.hasNext()) {
            currentVertex = (Vertex)var6.next();
            distances.put(currentVertex, currentVertex.equals(source) ? 0.0 : Double.POSITIVE_INFINITY);
        }

        priorityQueue.offer(new DijkstraNode(source, 0.0));

        while(true) {
            double currentDistance;
            do {
                if (priorityQueue.isEmpty()) {
                    return this.buildPath(parentMap, destination);
                }

                DijkstraNode<V> currentNode = (DijkstraNode)priorityQueue.poll();
                currentVertex = currentNode.getVertex();
                currentDistance = currentNode.getDistance();
            } while(currentDistance > (Double)distances.get(currentVertex));

            List<WeightedGraph<V>.Edge<V>> edges = this.graph.getEdges(currentVertex);
            Iterator var11 = edges.iterator();

            while(var11.hasNext()) {
                WeightedGraph<V>.Edge<V> edge = (WeightedGraph$Edge)var11.next();
                Vertex<V> neighborVertex = edge.getDestination();
                double edgeWeight = edge.getWeight();
                double distanceThroughCurrent = currentDistance + edgeWeight;
                if (distanceThroughCurrent < (Double)distances.get(neighborVertex)) {
                    distances.put(neighborVertex, distanceThroughCurrent);
                    parentMap.put(neighborVertex, currentVertex);
                    priorityQueue.offer(new DijkstraNode(neighborVertex, distanceThroughCurrent));
                }
            }
        }
    }

    private List<V> buildPath(Map<Vertex<V>, Vertex<V>> parentMap, Vertex<V> destination) {
        List<V> path = new ArrayList();

        for(Vertex<V> currentVertex = destination; currentVertex != null; currentVertex = (Vertex)parentMap.get(currentVertex)) {
            path.add(0, currentVertex.getData());
        }

        return path;
    }

    private static class DijkstraNode<V> implements Comparable<DijkstraNode<V>> {
        private Vertex<V> vertex;
        private double distance;

        public DijkstraNode(Vertex<V> vertex, double distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        public Vertex<V> getVertex() {
            return this.vertex;
        }

        public double getDistance() {
            return this.distance;
        }

        public int compareTo(DijkstraNode<V> other) {
            return Double.compare(this.distance, other.distance);
        }
    }
}
