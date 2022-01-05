package ru.toroschin.ads;

public class Main {
    public static void main(String[] args) {
        Graph graph = getGraph();

        Route route = graph.dfs("Москва", "Воронеж");

        System.out.println("Лучший маршрут");
        System.out.println(route);
    }

    private static Graph getGraph() {

        Graph graph = new GraphImpl(10);

        graph.addVertex("Москва");
        graph.addVertex("Тула");
        graph.addVertex("Рязань");
        graph.addVertex("Калуга");
        graph.addVertex("Липецк");
        graph.addVertex("Тамбов");
        graph.addVertex("Орел");
        graph.addVertex("Саратов");
        graph.addVertex("Курск");
        graph.addVertex("Воронеж");

        graph.addEdge("Москва", "Тула", new int[]{4, 3, 5}, "Рязань", "Калуга");
        graph.addEdge("Тула", "Липецк", 5);
        graph.addEdge("Липецк", "Воронеж", 4);
        graph.addEdge("Рязань", "Тамбов", 3);
        graph.addEdge("Тамбов", "Саратов", 5);
        graph.addEdge("Саратов", "Воронеж", 8);
        graph.addEdge("Калуга", "Орел", 4);
        graph.addEdge("Орел", "Курск", 1);
        graph.addEdge("Курск", "Воронеж", 2);
        graph.addEdge("Тула", "Тамбов", 1);
        graph.addEdge("Тамбов", "Орел", 2);

        return graph;
    }

}
