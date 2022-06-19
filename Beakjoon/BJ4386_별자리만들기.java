package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Date : 2022.06.19
 * Level : BaekJoon Gold 4
 * Difficulty : 하
 * Why : MST문제
 * Method : MST(Prim)
 * Result : 엄... PQ썼는데 딱히 좋은거같지는 않네
 */

public class BJ4386_별자리만들기 {
    static int N;
    static double ans;
    static double[][] nodes;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        nodes = new double[N][2];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            nodes[i][0] = Double.parseDouble(st.nextToken());
            nodes[i][1] = Double.parseDouble(st.nextToken());
        }

        prim();

        System.out.println(ans);
    }
    static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> Double.compare(o1.w, o2.w));
        double[] minArr = new double[N];
        boolean[] visited = new boolean[N];
        int minVertex = 0;
        int cnt = 0;
        Arrays.fill(minArr, Double.MAX_VALUE);
        pq.offer(new Edge(minVertex, 0));
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            if(visited[edge.e])
                continue;

            minVertex = edge.e;
            ans += edge.w;
            visited[minVertex] = true;
            if(++cnt == N) {
                break;
            }

            for(int i = 0 ; i < N ; i++) {
                if(visited[i])
                    continue;
                double weight = Math.sqrt((nodes[minVertex][0] - nodes[i][0]) * (nodes[minVertex][0] - nodes[i][0])
                        + (nodes[minVertex][1] - nodes[i][1]) * (nodes[minVertex][1] - nodes[i][1]));
                pq.offer(new Edge(i, weight));
            }
        }

    }

    static class Edge {
        int e;
        double w;
        public Edge(int e, double w) {
            this.e = e;
            this.w = w;
        }
    }
}
