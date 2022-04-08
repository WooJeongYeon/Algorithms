package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ9372_상근이의여행_재 {
    static int TC, N, M, ans;
    static int[] parents;
    static int[][] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        StringTokenizer st;
        for(int tc = 0 ; tc < TC ; tc++) {
            st = new StringTokenizer(in.readLine(), " ");
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            edges = new int[M][2];
            ans = 0;
            make();
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(in.readLine(), " ");
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                edges[i] = new int[]{start, end};
            }
            for(int i = 0 ; i < M ; i++) {
                if(union(edges[i][0], edges[i][1])) {
                    if(++ans == N - 1) {
                        break;
                    }
                }
            }


            sb.append(ans + "\n");
        }
        System.out.println(sb);
    }

    static void make() {
        parents = new int[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            parents[i] = i;
        }
    }
    static int find(int a) {
        if(parents[a] == a) return a;
        return parents[a] = find(parents[a]);
    }
    static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if(aRoot == bRoot) return false;
        else {
            parents[aRoot] = bRoot;
            return true;
        }
    } 
}
