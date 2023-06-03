
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Main {
    public Main() {
    }

    public static void main(String[] args) {
        WeightedGraph<String> graph = new WeightedGraph();
        Vertex<String> a = new Vertex("Dayana");
        Vertex<String> b = new Vertex("Alissa");
        Vertex<String> c = new Vertex("Merey");
        Vertex<String> d = new Vertex("Daniyar");
        Vertex<String> e = new Vertex("Rasul");
        Vertex<String> f = new Vertex("Alizhan");


        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);
        graph.addVertex(e);
        graph.addVertex(f);


        graph.addEdge(a, b, 4.0);
        graph.addEdge(a, c, 2.0);
        graph.addEdge(b, c, 1.0);
        graph.addEdge(b, d, 5.0);
        graph.addEdge(c, d, 8.0);
        graph.addEdge(c, e, 10.0);
        graph.addEdge(d, e, 2.0);
        graph.addEdge(d, f, 6.0);
        graph.addEdge(e, f, 3.0);


        BreadthFirstSearch<String> bfs = new BreadthFirstSearch(graph);
        System.out.println("BreadthFirstSearch traversal:");
        List<Vertex<String>> bfsTraversal = bfs.BreadthFirstSearch(a);
        Iterator var10 = bfsTraversal.iterator();

        while(var10.hasNext()) {
            Vertex<String> vertex = (Vertex)var10.next();
            System.out.print((String)vertex.getData() + " ");
        }

        System.out.println();
        DijkstraSearch<String> dijkstraSearch = new DijkstraSearch(graph);
        System.out.println("Shortest distances using Dijkstra's algorithm:");
        Map<Vertex<String>, Double> shortestDistances = dijkstraSearch.dijkstraSearch(a);
        Iterator var12 = shortestDistances.keySet().iterator();

        while(var12.hasNext()) {
            Vertex<String> vertex = (Vertex)var12.next();
            double distance = (Double)shortestDistances.get(vertex);
            String distanceString = distance == Double.POSITIVE_INFINITY ? "Infinity" : String.valueOf(distance);
            PrintStream var10000 = System.out;
            String var10001 = (String)vertex.getData();
            var10000.println("Vertex: " + var10001 + ", Distance: " + distanceString);
        }

        System.out.println("Shortest path from A to E:");
        List<String> shortestPath = dijkstraSearch.findPath(a, e);
        Iterator var20 = shortestPath.iterator();

        while(var20.hasNext()) {
            String vertexData = (String)var20.next();
            System.out.print(vertexData + " ");
        }

        System.out.println();
    }
}
