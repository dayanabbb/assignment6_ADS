
import java.util.HashMap;
import java.util.Map;

public class Vertex<V> {
    private V data;
    private Map<Vertex<V>, Double> adjacentVertices;

    public Vertex(V data) {
        this.data = data;
        this.adjacentVertices = new HashMap();
    }

    public V getData() {
        return this.data;
    }

    public void addAdjacentVertex(Vertex<V> destination, double weight) {
        this.adjacentVertices.put(destination, weight);
    }

    public Map<Vertex<V>, Double> getAdjacentVertices() {
        return this.adjacentVertices;
    }

    public void removeAdjacentVertex(Vertex<V> vertex) {
        if (!this.adjacentVertices.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + String.valueOf(vertex) + " isn't adjacent to this vertex");
        } else {
            this.adjacentVertices.remove(vertex);
        }
    }

    public double getWeight(Vertex<V> vertex) {
        if (!this.adjacentVertices.containsKey(vertex)) {
            throw new IllegalArgumentException("Vertex " + String.valueOf(vertex) + " isn't adjacent to this vertex");
        } else {
            return (Double)this.adjacentVertices.get(vertex);
        }
    }

    public boolean containsAdjacentVertex(Vertex<V> vertex) {
        return this.adjacentVertices.containsKey(vertex);
    }

    public void clearAdjacentVertices() {
        this.adjacentVertices.clear();
    }

    public int getDegree() {
        return this.adjacentVertices.size();
    }
}
