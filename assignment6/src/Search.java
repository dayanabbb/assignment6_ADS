import java.util.List;

public interface Search<V> {
    List<V> findPath(Vertex<V> var1, Vertex<V> var2);
}