package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class SW1251_하나로 {
    static int TC, N;
    static double e, ans;
    static int[][] posArr;
    static int[] parents;
    static PriorityQueue<Edge> pq;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            pq = new PriorityQueue<>((o1, o2) -> {
                return Double.compare(o1.w, o2.w);
            });
            N = Integer.parseInt(in.readLine());
            posArr = new int[N + 1][2];
            make();
            st = new StringTokenizer(in.readLine());
            for(int i = 1 ; i <= N ; i++) {
                posArr[i][0] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(in.readLine());
            for(int i = 1 ; i <= N ; i++) {
                posArr[i][1] = Integer.parseInt(st.nextToken());
            }
            e = Double.parseDouble(in.readLine());
            for(int i = 1 ; i < N ; i++) {
                for(int j = i + 1 ; j <= N ; j++) {
                    double dist = (double)(posArr[i][0] - posArr[j][0]) * (posArr[i][0] - posArr[j][0]) + (double)(posArr[i][1] - posArr[j][1]) * (posArr[i][1] - posArr[j][1]);
                    pq.offer(new Edge(i, j, dist));
                }
            }

            int cnt = 0;
            while(!pq.isEmpty()) {
                Edge now = pq.poll();
                if(union(now.s, now.e)) {
                    ans += now.w;
                    if(++cnt == N - 1) {
                        break;
                    }
                }
            }


            sb.append("#").append(tc).append(" ").append(String.format("%.0f", e * ans)).append("\n");
        }

        System.out.println(sb);
    }

    static class Edge {
        int s, e;
        double w;

        public Edge(int s, int e, double w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }
    }

    static void make() {
        parents = new int[N + 1];
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
        int rootA = find(a);
        int rootB = find(b);
        if(rootA == rootB)
            return false;
        else {
            parents[rootA] = rootB;
            return true;
        }
    }
}