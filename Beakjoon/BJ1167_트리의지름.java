package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// long으로 바꿈
// nullpointer 에러 뭐지..? -> 간선 정보가 정점 순서대로 들어오지 않음!(예시만 1 ~ 5순으로 들어왔네)

public class BJ1167_트리의지름 {
    static int N, ansVertex;
    static long ans;
    static LinkedList<Edge>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        edges = new LinkedList[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            edges[s] = new LinkedList<>();          // 얘때매 그랬네.
            int e = Integer.parseInt(st.nextToken());
            int w = -1;
            while(e != -1) {
                w = Integer.parseInt(st.nextToken());
                edges[s].offer(new Edge(e, w));

                e = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, 1, 0);
        dfs(0, ansVertex, 0);

        System.out.println(ans);
    }

    static void dfs(int last, int idx, long len) {
        if(ans < len) {
            ans = len;
            ansVertex = idx;
        }

        for(Edge edge : edges[idx]) {
            if(edge.e != last) {
                dfs(idx, edge.e, len + edge.w);
            }
        }
    }

    static class Edge {
        int e, w;

        public Edge(int e, int w) {
            this.e = e;
            this.w = w;
        }
    }
}
