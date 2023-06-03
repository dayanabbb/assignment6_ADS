

class DijkstraSearch$DijkstraNode<V> implements Comparable<DijkstraSearch$DijkstraNode<V>> {
    private Vertex<V> vertex;
    private double distance;

    public DijkstraSearch$DijkstraNode(Vertex<V> vertex, double distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public Vertex<V> getVertex() {
        return this.vertex;
    }

    public double getDistance() {
        return this.distance;
    }

    public int compareTo(DijkstraSearch$DijkstraNode<V> other) {
        return Double.compare(this.distance, other.distance);
    }
}
