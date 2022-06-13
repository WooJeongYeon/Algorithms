package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ11725_트리의부모찾기 {
    static int N;
    static int[] arr;
    static LinkedList<Integer>[] edges;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(in.readLine());
        arr = new int[N + 1];
        edges = new LinkedList[N + 1];
        for(int i = 1 ; i <= N ; i++) {
            edges[i] = new LinkedList<>();
        }
        for(int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            edges[s].offer(e);
            edges[e].offer(s);
        }

        dfs(0, 1);

        for(int i = 2 ; i <= N ; i++) {
            sb.append(arr[i]).append("\n");
        }

        System.out.println(sb.toString());
    }

    static void dfs(int parent, int now) {
        arr[now] = parent;
        for(int next : edges[now]) {
            if(next != parent) {
                dfs(now, next);
            }
        }
    }
}
