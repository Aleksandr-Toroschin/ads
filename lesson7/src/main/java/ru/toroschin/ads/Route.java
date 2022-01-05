package ru.toroschin.ads;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private List<Edge> edges;
    private int commonWeight;

    public Route() {
        edges = new ArrayList<>();
    }

    public int addEdge(Edge edge) {
        edges.add(edge);
        return refreshWeight();
    }

    public int addEdges(List<Edge> edges) {
        this.edges.addAll(edges);
        return refreshWeight();
    }

    private int refreshWeight() {
        commonWeight = 0;
        for (Edge edge : edges) {
            commonWeight += edge.getWeight();
        }
        return commonWeight;
    }

    public boolean deleteEdge() {
        if (edges.size() == 0) {
            return false;
        }
        edges.remove(edges.remove(edges.size() - 1));
        return true;
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public int getCommonWeight() {
        return commonWeight;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Маршрут {");
        sb.append(edges.get(0).getStartVertex().getLabel());
        for (Edge edge : edges) {
            sb.append(" - > ").append(edge.getEndVertex().getLabel());
        }
        sb.append("} Вес маршрута: ").append(commonWeight);
        return sb.toString();
    }
}
