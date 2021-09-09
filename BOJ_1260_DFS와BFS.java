package ing;

import java.io.*;
import java.util.*;

public class BOJ_1260_DFS와BFS {

    private static int V, E, S;
    private static int[][] graph;
    private static int[] visit;
    private static ArrayList<Integer>[] edge;
    private static ArrayList<Integer> path_bfs;
    private static ArrayList<Integer> path_dfs;
    private static ArrayList<Integer> path_dfs_stack;

    public static void main(String[] args) throws Exception {

        // System.setIn(new FileInputStream("in_BJ_DFS와BFS.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken()); // 정점
        E = Integer.parseInt(st.nextToken()); // 간선
        S = Integer.parseInt(st.nextToken()); // 시작

        edge = new ArrayList[V + 1];
        graph = new int[V + 1][V + 1];
        visit = new int[V + 1];

        // 엣지 생성
        for (int i = 1; i <= V; i++) {
            edge[i] = new ArrayList<>();
        }

        path_bfs = new ArrayList<>();
        path_dfs = new ArrayList<>();
        path_dfs_stack = new ArrayList<>();


        for (int e = 1; e <= E; e++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edge[from].add(to);
            edge[to].add(from);

            graph[from][to] = 1; // stack 으로 풀어보려고 추가함
            graph[to][from] = 1;
        }

        // 문제에서 작은 순으로 방문하라고 해서 정렬
        for (int i = 1; i <= V; i++) {
            Collections.sort(edge[i]);
        }

        path_bfs.clear();
        path_dfs.clear();
        path_dfs_stack.clear();

        bfs(S);
        Arrays.fill(visit, 0);

        dfs(S);
        Arrays.fill(visit, 0);

        dfs_stack(S);
        Arrays.fill(visit, 0);

        //System.out.println("print dfs");
        for (int i = 0; i < path_dfs.size(); i++) {
            System.out.print(path_dfs.get(i) + " ");
        }

        System.out.println();

        // System.out.println("print bfs");
        for (int i = 0; i < path_bfs.size(); i++) {
            System.out.print(path_bfs.get(i) + " ");
        }

        /*
        //System.out.println("print dfs_stack");
        for (int i = 0; i < path_dfs_stack.size(); i++) {
            System.out.print(path_dfs_stack.get(i) + " ");
        }
        */

    }

    private static void bfs(int start) {
        Queue<Integer> que = new ArrayDeque<>();
        visit[start] = 1;

        que.offer(start);
        path_bfs.add(start);

        while (!que.isEmpty()) {

            int curr = que.poll();
            for (int next : edge[curr]) {
                if (visit[next] == 0) {
                    que.offer(next);
                    visit[next] = visit[curr] + 1;
                    path_bfs.add(next);
                }
            }
        }
    }

    private static void dfs(int start) {
        visit[start] = 1;
        int curr = start;
        path_dfs.add(curr);

        for (int next : edge[curr]) {
            if (visit[next] == 0) {
                dfs(next);
            }
        }
    }

    private static void dfs_stack(int start) {

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {
            int curr = stack.pop();
            if (visit[curr] == 0) {
                visit[curr] = 1;
                path_dfs_stack.add(curr);

                for (int i = V; i > 0; i--) {
                    if (graph[curr][i] != 0 && visit[i] == 0) {
                        stack.push(i);
                    }
                }

            }
        }

    }


}
