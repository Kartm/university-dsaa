package dsaa_jk.assignment_8.task_2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;

public class Task2 {
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
        AdjacencyMatrixGraph<City> adjMatrixGraph = new AdjacencyMatrixGraph<>();
        AdjacencyListGraph<City> adjListGraph = new AdjacencyListGraph<>();
        IncidenceMatrixGraph<City> incMatrixGraph = new IncidenceMatrixGraph<>();

        Hashtable<String, City> cities = new Hashtable<String, City>();
        cities.put("Manowo", new City("Manowo"));
        cities.put("Warsaw", new City("Warsaw"));
        cities.put("Gdynia", new City("Gdynia"));
        cities.put("Gdansk", new City("Gdansk"));
        cities.put("Czarne", new City("Czarne"));
        cities.put("Serock", new City("Serock"));

        ArrayList<Road> roads = new ArrayList<>(Arrays.asList(
                new Road(cities.get("Manowo"), cities.get("Gdynia"), 6),
                new Road(cities.get("Manowo"), cities.get("Czarne"), 1),
                new Road(cities.get("Manowo"), cities.get("Serock"), 1),
                new Road(cities.get("Serock"), cities.get("Czarne"), 2),
                new Road(cities.get("Warsaw"), cities.get("Serock"), 7),
                new Road(cities.get("Czarne"), cities.get("Gdansk"), 5),
                new Road(cities.get("Gdansk"), cities.get("Gdynia"), 1),
                new Road(cities.get("Warsaw"), cities.get("Gdansk"), 3)
        ));

        for (City city : cities.values()) {
            adjMatrixGraph.addNode(city);
            adjListGraph.addNode(city);
            incMatrixGraph.addNode(city);
        }

        for (Road road : roads) {
            try {
                adjMatrixGraph.addEdge(road.from, road.to, road.weight);
                adjMatrixGraph.addEdge(road.to, road.from, road.weight);

                adjListGraph.addEdge(road.from, road.to, road.weight);
                adjListGraph.addEdge(road.to, road.from, road.weight);

                // incidence matrix implementation is not directed
                incMatrixGraph.addEdge(road.from, road.to, road.weight);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(adjMatrixGraph.toString());
        System.out.println(adjListGraph.toString());
        System.out.println(incMatrixGraph.toString());

        System.out.println(adjMatrixGraph.getMinimumSpanningTree());
        System.out.println(adjListGraph.getMinimumSpanningTree());
        System.out.println(incMatrixGraph.getMinimumSpanningTree());
    }
}
