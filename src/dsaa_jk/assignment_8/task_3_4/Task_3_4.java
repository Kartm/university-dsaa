package dsaa_jk.assignment_8.task_3_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Task_3_4 {
    private static class Road {
        public final City from;
        public final City to;
        public final int weight;

        public Road(City from, City to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    private static class City {
        private final String name;

        public City(String name) {
            this.name = name;
        }

        public String getCity() {
            return this.name;
        }

        @Override
        public String toString() {
            return this.name;
        }
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        AdjacencyListGraph<City> adjListGraph = new AdjacencyListGraph<>();
        Hashtable<String, City> cities = new Hashtable<String, City>();
        cities.put("Manowo", new City("Manowo"));
        cities.put("Warsaw", new City("Warsaw"));
        cities.put("Gdynia", new City("Gdynia"));
        cities.put("Gdansk", new City("Gdansk"));
        cities.put("Czarne", new City("Czarne"));
        cities.put("Serock", new City("Serock"));

        ArrayList<Road> roads = new ArrayList<>(Arrays.asList(
                new Road(cities.get("Manowo"), cities.get("Gdynia"), 5),
                new Road(cities.get("Manowo"), cities.get("Czarne"), 1),
                new Road(cities.get("Manowo"), cities.get("Serock"), 1),
                new Road(cities.get("Serock"), cities.get("Czarne"), 2),
                new Road(cities.get("Warsaw"), cities.get("Serock"), 7),
                new Road(cities.get("Czarne"), cities.get("Gdansk"), 5),
                new Road(cities.get("Gdansk"), cities.get("Gdynia"), 1),
                new Road(cities.get("Warsaw"), cities.get("Gdansk"), 3)
        ));

        for (City city : cities.values()) {
            adjListGraph.addNode(city);
        }

        for (Road road : roads) {
            try {
                adjListGraph.addEdge(road.from, road.to, road.weight);
                adjListGraph.addEdge(road.to, road.from, road.weight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(adjListGraph.toString());

        System.out.println("BFS starting in Warsaw: ");
        for(City city: adjListGraph.bfs(cities.get("Warsaw"))) {
            System.out.println(city.getCity());
        }
        System.out.println();

        System.out.println("DFS starting in Warsaw: ");
        for(City city: adjListGraph.dfs(cities.get("Warsaw"))) {
            System.out.println(city.getCity());
        }
        System.out.println();

        System.out.println("Dijkstra starting in Warsaw: ");
        for(DijkstraPath<City> path: adjListGraph.dijkstra(cities.get("Warsaw"))) {
            System.out.println(path.getTarget() + " - cost " + path.getCost() + " (predecessor " + path.getPredecessor() + ")");
        }
        System.out.println();
    }
}
