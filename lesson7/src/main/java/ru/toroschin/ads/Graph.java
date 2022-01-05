package ru.toroschin.ads;

public interface Graph {

    void addVertex(String label);

    boolean addEdge(String startLabel, String secondLabel, int[] weights, String... others);
    boolean addEdge(String startLabel, String secondLabel, int weight);

    int getSize();

    void display();

    /**
     * англ. Depth-first search, DFS
     */
    void dfs(String startLabel);

    Route dfs(String startLabel, String endLabel);

    /**
     * англ. breadth-first search, BFS
     */
    void bfs(String startLabel);

}
