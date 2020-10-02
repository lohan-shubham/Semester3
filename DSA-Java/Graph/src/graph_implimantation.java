import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class graph_implimantation {
    static void addEdge(ArrayList<ArrayList<Integer>> gr, int u, int v) {
        gr.get(u).add(v);
        gr.get(v).add(u);
    }

    static void printGraph(ArrayList<ArrayList<Integer>> gr) {
        for (int i = 0; i < gr.size(); i++) {
            System.out.println("\nAdjacency list of vertex" + (i + 1));
            for (int j = 0; j < gr.get(i).size(); j++) {
                System.out.print(" -> " + (gr.get(i).get(j) + 1));
            }
            System.out.println();
        }
    }

    static void BFS(ArrayList<ArrayList<Integer>> gr, int pos) {
        boolean visited[] = new boolean[gr.size()];
        visited[pos] = true;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(pos);
        while (!q.isEmpty()) {
            int u = q.remove();
            System.out.print(u + " ");
            for (int i = 0; i < gr.get(pos).size(); i++) {
                int n = gr.get(pos).get(i);
                if (!visited[n]) {
                    visited[n] = true;
                    q.add(n);
                }
            }
        }
    }

    static void DFS(ArrayList<ArrayList<Integer>> gr, int pos, boolean visited[]) {
        visited[pos] = true;
        System.out.print((pos + 1) + " ");
        for (int i = 0; i < gr.get(pos).size(); i++) {
            int u = gr.get(pos).get(i);
            if (!visited[u]) {
                DFS(gr, u, visited);
            }
        }

    }

    static boolean cycle_detection(ArrayList<ArrayList<Integer>> gr, int pos, boolean[] visited, int parent) {
        visited[pos] = true;
        if (visited[pos] && parent != pos) {
            return true;
        }
        for (int i = 0; i < gr.get(pos).size(); i++) {
            int u = gr.get(pos).get(i);
            if (!visited[u]) {
                parent = pos;
                System.out.print((parent + 1) + " ");
                cycle_detection(gr, u, visited, parent);
            }
        }
        return false;

    }

    static void is_connected_graph(ArrayList<ArrayList<Integer>> gr, boolean visited[]) {
        DFS(gr, 1, visited);
        for (int i = 0; i < gr.size(); i++) {
            if (!visited[i]) {
                System.out.println();
                DFS(gr, i, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int V = input.nextInt();
        ArrayList<ArrayList<Integer>> gr = new ArrayList<ArrayList<Integer>>(V);
        for (int i = 0; i < V; i++) {
            gr.add(new ArrayList<Integer>());
        }
        while (input.hasNextInt()) {
            int u = input.nextInt() - 1;
            int v = input.nextInt() - 1;
            addEdge(gr, u, v);
        }
        printGraph(gr);
        boolean visited[] = new boolean[V];
        System.out.println();
//        is_connected_graph(gr, visited);
        System.out.println(cycle_detection(gr, 0, visited, -1));

    }
}
