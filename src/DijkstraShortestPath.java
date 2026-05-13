import java.util.*;

public class DijkstraShortestPath {

    static class Edge {
        String to;
        int weight;

        Edge(String to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    static class Node {
        String city;
        int dist;

        Node(String city, int dist) {
            this.city = city;
            this.dist = dist;
        }
    }

    private static Map<String, List<Edge>> graph = new HashMap<>();

    static {
        graph.put("Edinburgh", Arrays.asList(
                new Edge("Stirling", 37),
                new Edge("Perth", 44),
                new Edge("Glasgow", 46)
        ));

        graph.put("Stirling", Arrays.asList(
                new Edge("Edinburgh", 37),
                new Edge("Glasgow", 28),
                new Edge("Perth", 35),
                new Edge("Dundee", 48)
        ));

        graph.put("Perth", Arrays.asList(
                new Edge("Edinburgh", 44),
                new Edge("Stirling", 35),
                new Edge("Dundee", 22),
                new Edge("Inverness", 110)
        ));

        graph.put("Glasgow", Arrays.asList(
                new Edge("Edinburgh", 46),
                new Edge("Stirling", 28)
        ));

        graph.put("Dundee", Arrays.asList(
                new Edge("Stirling", 48),
                new Edge("Perth", 22),
                new Edge("Aberdeen", 67)
        ));

        graph.put("Aberdeen", Arrays.asList(
                new Edge("Dundee", 67),
                new Edge("Inverness", 105)
        ));

        graph.put("Inverness", Arrays.asList(
                new Edge("Perth", 110),
                new Edge("Aberdeen", 105)
        ));
    }

    public static void dijkstra(String start, String target) {

        Map<String, Integer> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();

        for (String city : graph.keySet()) {
            dist.put(city, Integer.MAX_VALUE);
        }

        dist.put(start, 0);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.dist));
        pq.add(new Node(start, 0));

        while (!pq.isEmpty()) {

            Node current = pq.poll();
            String city = current.city;

            if (city.equals(target)) break;

            for (Edge e : graph.get(city)) {

                int newDist = dist.get(city) + e.weight;

                if (newDist < dist.get(e.to)) {
                    dist.put(e.to, newDist);
                    prev.put(e.to, city);
                    pq.add(new Node(e.to, newDist));
                }
            }
        }

        List<String> path = new ArrayList<>();
        String step = target;

        while (step != null) {
            path.add(step);
            step = prev.get(step);
        }

        Collections.reverse(path);

        System.out.println("Shortest path:");
        System.out.println(String.join(" -> ", path));
        System.out.println("Distance: " + dist.get(target));
    }

    public static void main(String[] args) {
        dijkstra("Edinburgh", "Dundee");
    }
}