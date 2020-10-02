import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;

class Node implements Comparator<Node> {
    int data;
    int cost;

    Node() {
    }

    Node(int data, int cost) {
        this.data = data;
        this.cost = cost;
    }

    @Override
    public int compare(Node node1, Node node2) {
        return Long.compare(node1.data, node2.data);
    }
}

public class WeightedGraph {
    ArrayList<ArrayList<Node>> adj;


    WeightedGraph(int size) {
        adj = new ArrayList<ArrayList<Node>>(size);
        for (int i = 0; i < size; i++) {
            adj.add(new ArrayList<Node>());
        }
    }

    void addEdge(int u, int v, int w) {
        adj.get(u).add(new Node(v, w));
//		 adj.get(v).add(new Node(u,w));
    }

    public int dijkstra(int[] dist, int pos, int dest) {

        PriorityQueue<Node> minheap = new PriorityQueue<Node>(new Node());

        minheap.add(new Node(pos, 0));
        dist[pos] = 0;
        while (!minheap.isEmpty()) {

            int u = minheap.remove().data;
            int d = dist[u];
            for (int i = 0; i < adj.get(u).size(); i++) {
                Node path = adj.get(u).get(i);
                int dd = path.data;
                int c = path.cost;
                int temp = d + c;
                if (temp < dist[dd]) {
                    dist[dd] = temp;
                    minheap.add(new Node(dd, dist[dd]));
                }

            }
        }
        if (dist[dest] == Integer.MAX_VALUE) {
            return -1;
        }
        return dist[dest];
    }

    static class Reader {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader() {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1) {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            } while ((c = read()) >= '0' && c <= '9');

            if (c == '.') {
                while ((c = read()) >= '0' && c <= '9') {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException {
            if (din == null)
                return;
            din.close();
        }
    }

    public static void main(String[] args) throws IOException {
        // Reader input = new Reader();
        Scanner input = new Scanner(System.in);
        int q = input.nextInt();
        int V = input.nextInt();
        WeightedGraph gr = new WeightedGraph(V);
        int[] dist = new int[V];

        int e = input.nextInt();
        for (int i = 0; i < e; i++) {
            int u = input.nextInt() - 1;
            int b = input.nextInt() - 1;
            int w = input.nextInt();
            gr.addEdge(u, b, w);

        }
        for (int j = 0; j < q; j++) {
            for (int i = 0; i < V; i++) {
                dist[i] = Integer.MAX_VALUE;
            }
            int a = input.nextInt() - 1;
            int b = input.nextInt() - 1;
            System.out.println(gr.dijkstra(dist, a, b));
        }

    }

}
