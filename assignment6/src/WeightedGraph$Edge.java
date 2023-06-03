

public class WeightedGraph$Edge<V> {
    private Vertex<V> source;
    private Vertex<V> destination;
    private double weight;

    public WeightedGraph$Edge(WeightedGraph this$0, Vertex source, Vertex destination, double weight) {
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
