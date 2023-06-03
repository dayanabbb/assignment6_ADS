

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch<V> implements Search<V> {
    private WeightedGraph<V> graph;

    public BreadthFirstSearch(WeightedGraph<V> graph) {
        this.graph = graph;
    }

    public List<Vertex<V>> BreadthFirstSearch(Vertex<V> startVertex) {
        List<Vertex<V>> visited = new ArrayList();
        Queue<Vertex<V>> queue = new LinkedList();
        visited.add(startVertex);
        queue.add(startVertex);

        while(!queue.isEmpty()) {
            Vertex<V> currentVertex = (Vertex)queue.poll();
            List<WeightedGraph<V>.Edge<V>> edges = this.graph.getEdges(currentVertex);
            Iterator var6 = edges.iterator();

            while(var6.hasNext()) {
                WeightedGraph<V>.Edge<V> edge = (WeightedGraph$Edge)var6.next();
                Vertex<V> neighborVertex = edge.getDestination();
                if (!visited.contains(neighborVertex)) {
                    visited.add(neighborVertex);
                    queue.add(neighborVertex);
                }
            }
        }

        return visited;
    }

    public void printBFS(Vertex<V> startVertex) {
        Set<Vertex<V>> visited = new HashSet();
        Queue<Vertex<V>> queue = new LinkedList();
        visited.add(startVertex);
        queue.offer(startVertex);

        while(!queue.isEmpty()) {
            Vertex<V> currentVertex = (Vertex)queue.poll();
            System.out.print(String.valueOf(currentVertex.getData()) + " ");
            List<WeightedGraph<V>.Edge<V>> edges = this.graph.getEdges(currentVertex);
            Iterator var6 = edges.iterator();

            while(var6.hasNext()) {
                WeightedGraph<V>.Edge<V> edge = (WeightedGraph$Edge)var6.next();
                Vertex<V> adjacentVertex = edge.getDestination();
                if (!visited.contains(adjacentVertex)) {
                    visited.add(adjacentVertex);
                    queue.offer(adjacentVertex);
                }
            }
        }

        System.out.println();
    }

    public List<V> findPath(Vertex<V> source, Vertex<V> destination) {
        List<Vertex<V>> path = new ArrayList();
        Queue<Vertex<V>> queue = new LinkedList();
        Map<Vertex<V>, Vertex<V>> parentMap = new HashMap();
        queue.offer(source);
        parentMap.put(source, (Object)null);

        while(!queue.isEmpty()) {
            Vertex<V> currentVertex = (Vertex)queue.poll();
            if (currentVertex.equals(destination)) {
                path.add(destination);

                for(Vertex<V> parent = (Vertex)parentMap.get(destination); parent != null; parent = (Vertex)parentMap.get(parent)) {
                    path.add(0, parent);
                }

                return path;
            }

            List<WeightedGraph<V>.Edge<V>> edges = this.graph.getEdges(currentVertex);
            Iterator var8 = edges.iterator();

            while(var8.hasNext()) {
                WeightedGraph<V>.Edge<V> edge = (WeightedGraph$Edge)var8.next();
                Vertex<V> neighborVertex = edge.getDestination();
                if (!parentMap.containsKey(neighborVertex)) {
                    queue.offer(neighborVertex);
                    parentMap.put(neighborVertex, currentVertex);
                }
            }
        }

        return path;
    }
}
