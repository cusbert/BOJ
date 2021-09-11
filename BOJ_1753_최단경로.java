import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// https://www.acmicpc.net/problem/1753
// 최단경로
// 다익스트라

public class BOJ_1753_최단경로 {

    private static int V, E, S;
    private static ArrayList<Node>[] edge;
    private static int[] distance;
    private static int INF = Integer.MAX_VALUE;
    private static boolean[] visit;

    public static void main(String[] args) throws Exception {


        // System.setIn(new FileInputStream("in_BOJ_최단경로.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점 (1≤V≤20,000, 1≤E≤300,000)
        E = Integer.parseInt(st.nextToken()); // 간선
        S = Integer.parseInt(br.readLine()); // 시작

        // 연결되지 않은 비용은 무한
        distance = new int[V + 1];
        Arrays.fill(distance, INF);

        // 방문 여부 저장
        visit = new boolean[V + 1];
        Arrays.fill(visit, false);

        edge = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            edge[i] = new ArrayList<>();
        }

        for (int e = 1; e <= E; e++) {
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edge[start].add(new Node(end, weight));

        }

        dijkstra(S);

        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                System.out.println("INF ");
            } else {
                System.out.println(distance[i] + " ");
            }
        }

    }
    private static void dijkstra(int start) {

        // 시작점은 0
        distance[start] = 0;

        PriorityQueue<Node> que = new PriorityQueue<>();
        Node startNode = new Node(start, 0);

        // 시작
        que.offer(startNode);

        while (!que.isEmpty()) {
            Node curr = que.poll();

            int curEnd = curr.end;

            if (visit[curEnd])
                continue;

            visit[curEnd] = true;

            for (Node next : edge[curEnd]) {
                if (distance[next.end] > distance[curEnd] + next.weight) { // 현재 가중치 + 다음 cost 가 더 작으면
                    distance[next.end] = distance[curEnd] + next.weight;
                    que.offer(new Node(next.end, distance[next.end]));
                }
            }
        }

    }

    static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Node [end=" + end + ", weight=" + weight + "]";
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }

    }
}
