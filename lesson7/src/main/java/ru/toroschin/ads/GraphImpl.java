package ru.toroschin.ads;

import java.util.*;
import java.util.stream.Collectors;

public class GraphImpl implements Graph {
    private final List<Vertex> vertexList;
    private final int[][] adjMatrix;

    private Map<Integer, Route> routes;

    public GraphImpl(int maxVertexCount) {
        this.vertexList = new ArrayList<>(maxVertexCount);
        this.adjMatrix = new int[maxVertexCount][maxVertexCount];
        this.routes = new HashMap<>();
    }


    @Override
    public void addVertex(String label) {
        vertexList.add(new Vertex(label));
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel, int weight) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(secondLabel);

        if (startIndex == -1 || endIndex == -1) {
            return false;
        }

        adjMatrix[startIndex][endIndex] = weight;
        return false;
    }

    private int indexOf(String label) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (vertexList.get(i).getLabel().equals(label)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean addEdge(String startLabel, String secondLabel, int[] weights, String... others) {
        boolean result = addEdge(startLabel, secondLabel, weights[0]);

        int i = 1;

        for (String other : others) {
            result &= addEdge(startLabel, other, weights[i++]);
        }

        return result;
    }


    @Override
    public int getSize() {
        return vertexList.size();
    }

    @Override
    public void display() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < getSize(); i++) {
            sb.append(vertexList.get(i));
            for (int j = 0; j < getSize(); j++) {
                if (adjMatrix[i][j] != 0) {
                    sb.append(" -> ").append(vertexList.get(j));
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void dfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Неверная вершина: " + startLabel);
        }

        Stack<Vertex> stack = new Stack<>();
        resetVertexVisited();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(stack, vertex);
        while (!stack.isEmpty()) {
            vertex = getNearUnvisitedVertex(stack.peek());
            if (vertex != null) {
                visitVertex(stack, vertex);
            } else {
                stack.pop();
            }
        }
        System.out.println();
    }

    @Override
    public Route dfs(String startLabel, String endLabel) {
        int startIndex = indexOf(startLabel);
        int endIndex = indexOf(endLabel);
        if (startIndex == -1 || endIndex == -1) {
            throw new IllegalArgumentException("Неверная вершина: " + startLabel + " или " + endLabel);
        }

        int bestRouteWeight = Integer.MAX_VALUE;
        int bestRouteIndex = 1;

        int route = 1;
        routes.clear();
        routes.put(route, new Route());

        Stack<Vertex> stack = new Stack<>();
        resetVertexVisited();

        visitVertex(stack, startIndex, routes.get(route));
        int routeWeight;
        int vertexIndex;
        while (!stack.isEmpty()) {
            vertexIndex = getNearUnvisitedVertexIndex(stack.peek(), route, startIndex);
            if (vertexIndex != -1) {
                routeWeight = visitVertex(stack, vertexIndex, routes.get(route));
                if (vertexIndex == endIndex) {
                    if (bestRouteWeight > routeWeight) {
                        bestRouteWeight = routeWeight;
                        bestRouteIndex = route;
                    }
                    route++;
                    routes.put(route, new Route());
                    routes.get(route).addEdges(routes.get(route - 1).getEdges());
                }
            } else {
                stepBack(stack, route);
            }
        }
        System.out.println();

        return routes.get(bestRouteIndex);
    }

    private int getNearUnvisitedVertexIndex(Vertex vertex, int route, int startIndex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] != 0) {
                if (!vertexList.get(i).isVisited() || !enableRoute(vertexList.get(i), route, startIndex)) {
                    return i;
                }
            }
        }
        return -1;
    }

    private String getLabelsForRoute(List<Edge> edges, int limit) {
        List<Vertex> vertexes = edges.stream().map(Edge::getStartVertex).collect(Collectors.toList());
        vertexes.add(edges.get(edges.size() - 1).getEndVertex());
        return vertexes.stream().limit(limit).map(Vertex::getLabel).reduce((a, b) -> a + b).orElse("");
    }

    private boolean enableRoute(Vertex vertex, Integer route, int startIndex) {
        List<Edge> edges = new ArrayList<>(routes.get(route).getEdges());
        Vertex startVertex = vertexList.get(startIndex);
        if (!edges.isEmpty()) {
            startVertex = edges.get(edges.size() - 1).getEndVertex();
        }
        edges.add(new Edge(startVertex, vertex, 0));
        int limit = edges.size() + 1;
        String newRoute = getLabelsForRoute(edges, limit);

        for (Route value : routes.values()) {
            if (getLabelsForRoute(value.getEdges(), limit).equals(newRoute)) {
                return true;
            }
        }
        return false;
    }

    private void stepBack(Stack<Vertex> stack, int route) {
        routes.get(route).deleteEdge();
        stack.pop();
    }

    private int visitVertex(Stack<Vertex> stack, int vertexIndex, Route route) {
        Vertex vertex = vertexList.get(vertexIndex);

        int commonWeight = 0;
        if (!stack.isEmpty()) {
            int startVertexIndex = vertexList.indexOf(stack.peek());
            Vertex startVertex = stack.peek();
            commonWeight = route.addEdge(new Edge(startVertex, vertex, adjMatrix[startVertexIndex][vertexIndex]));
        }
        stack.add(vertex);
        vertex.setVisited(true);
        return commonWeight;
    }

    private Vertex getNearUnvisitedVertex(Vertex vertex) {
        int currentIndex = vertexList.indexOf(vertex);
        for (int i = 0; i < getSize(); i++) {
            if (adjMatrix[currentIndex][i] !=0 && !vertexList.get(i).isVisited()) {
                return vertexList.get(i);
            }
        }
        return null;
    }

    private void visitVertex(Stack<Vertex> stack, Vertex vertex) {
        System.out.print(vertex.getLabel() + " ");
        stack.add(vertex);
        vertex.setVisited(true);
    }

    private void visitVertex(Queue<Vertex> queue, Vertex vertex) {
        System.out.print(vertex.getLabel() + " ");
        queue.add(vertex);
        vertex.setVisited(true);
    }

    private void resetVertexVisited() {
        for (Vertex vertex : vertexList) {
            vertex.setVisited(false);
        }
    }

    @Override
    public void bfs(String startLabel) {
        int startIndex = indexOf(startLabel);
        if (startIndex == -1) {
            throw new IllegalArgumentException("Неверная вершина: " + startLabel);
        }

        Queue<Vertex> queue = new LinkedList<>();
        resetVertexVisited();
        Vertex vertex = vertexList.get(startIndex);

        visitVertex(queue, vertex);
        while (!queue.isEmpty()) {
            vertex = getNearUnvisitedVertex(queue.peek());
            if (vertex != null) {
                visitVertex(queue, vertex);
            } else {
                queue.remove();
            }
        }
        System.out.println();
    }
}
