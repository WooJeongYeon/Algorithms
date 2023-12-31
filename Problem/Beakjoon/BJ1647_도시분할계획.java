package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * Date : 2022.06.18
 * Level : BaekJoon Gold 4
 * Difficulty : 중하
 * Why : 특정 알고리즘으로 풀 수 있음
 * URL : https://www.acmicpc.net/problem/1647
 * Select1 :
 * Thinking : kruskal로 이은다음 가장 긴 엣지를 빼면 마을 두 개로 나눌 수 있음
 * Method : MST(Kruskal)
 */

public class BJ1647_도시분할계획 {
    static int N, M;
    static long ans;
    static int[] parents;
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        pq = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(s, e, w));
        }

        make();

        int cnt = 0;
        while(true) {
            Edge now = pq.poll();
            if(union(now.s, now.e)) {
                cnt++;
                if(cnt == N - 1)
                    break;
                ans += now.w;
            }
        }

        System.out.println(ans);
    }

    static void make() {
        for(int i = 1 ; i <= N ; i++) {
            parents[i] = i;
        }
    }

    static int find(int n) {
        if(parents[n] == n)
            return n;
        return parents[n] = find(parents[n]);
    }

    static boolean union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if(parentA == parentB)
            return false;
        parents[parentA] = parentB;
        return true;
    }

    static class Edge {
        int s, e, w;

        public Edge(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }
}
