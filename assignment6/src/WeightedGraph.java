
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class WeightedGraph<V> {
    private Map<Vertex<V>, List<WeightedGraph<V>.Edge<V>>> adjacencyList = new HashMap();

    public WeightedGraph() {
    }

    public void addVertex(Vertex<V> vertex) {
        this.adjacencyList.put(vertex, new ArrayList());
    }

    public void addEdge(Vertex<V> source, Vertex<V> destination, double weight) {
        this.validateVertex(source);
        this.validateVertex(destination);
        List<WeightedGraph<V>.Edge<V>> edges = (List)this.adjacencyList.get(source);
        edges.add(new Edge(this, source, destination, weight));
        this.adjacencyList.put(source, edges);
    }

    public void validateVertex(Vertex<V> vertex) {
        if (!this.adjacencyList.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + String.valueOf(vertex) + " is out of range");
        }
    }

    public List<Vertex<V>> getVertices() {
        return new ArrayList(this.adjacencyList.keySet());
    }

    public List<WeightedGraph<V>.Edge<V>> getEdges() {
        List<WeightedGraph<V>.Edge<V>> allEdges = new ArrayList();
        Iterator var2 = this.adjacencyList.values().iterator();

        while(var2.hasNext()) {
            List<WeightedGraph<V>.Edge<V>> edges = (List)var2.next();
            allEdges.addAll(edges);
        }

        return allEdges;
    }

    public void printGraphInformation() {
        System.out.println("Vertices:");
        Iterator var1 = this.adjacencyList.keySet().iterator();

        while(var1.hasNext()) {
            Vertex<V> vertex = (Vertex)var1.next();
            System.out.println(vertex.getData());
        }

        System.out.println();
        System.out.println("Edges with Weights:");
        var1 = this.adjacencyList.values().iterator();

        while(var1.hasNext()) {
            List<WeightedGraph<V>.Edge<V>> edges = (List)var1.next();
            Iterator var3 = edges.iterator();

            while(var3.hasNext()) {
                WeightedGraph<V>.Edge<V> edge = (Edge)var3.next();
                System.out.println("Source: " + String.valueOf(edge.getSource().getData()));
                System.out.println("Destination: " + String.valueOf(edge.getDestination().getData()));
                System.out.println("Weight: " + edge.getWeight());
                System.out.println();
            }
        }

    }

    public List<WeightedGraph<V>.Edge<V>> getEdges(Vertex<V> vertex) {
        this.validateVertex(vertex);
        return (List)this.adjacencyList.get(vertex);
    }

    public class Edge<V> {
        private Vertex<V> source;
        private Vertex<V> destination;
        private double weight;

        public Edge(WeightedGraph this$0, Vertex source, Vertex destination, double weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public Vertex<V> getSource() {
            return this.source;
        }

        public Vertex<V> getDestination() {
            return this.destination;
        }

        public double getWeight() {
            return this.weight;
        }
    }
}
