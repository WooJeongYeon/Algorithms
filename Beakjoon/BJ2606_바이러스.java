package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ2606_바이러스 {
    static int N, E, ans;
    static boolean[][] arr;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        E = Integer.parseInt(in.readLine());
        arr = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for(int i = 0 ; i < E ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr[s][e] = true;
            arr[e][s] = true;
        }

        dfs(1);

        System.out.println(ans - 1);
    }

    static void dfs(int idx) {
        ans++;
        visited[idx] = true;

        for(int i = 1 ; i <= N ; i++) {
            if(arr[idx][i] && !visited[i])
                dfs(i);
        }
    }
}
