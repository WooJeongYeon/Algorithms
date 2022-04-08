package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1520_내리막길 {
    static int R, C, ans;
    static int[][] map, memo;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        memo = new int[R][C];
        for(int i = 0 ; i < R ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < C ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                memo[i][j] = -1;
            }
        }
        ans = dfs(0, 0);    // 0, 0 위치에서 출발했을 때의 경로 개수
        System.out.println(ans);

    }
    static int dfs(int i, int j) {
        if(i == R - 1 && j == C - 1) {      // 도착지에 도착했으면
            return 1;                       // 경로 1개 반환
        }
        int cnt = 0;
        for(int d = 0 ; d < 4 ; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni < 0 || ni >= R || nj < 0 || nj >= C) continue;
            if(map[i][j] > map[ni][nj]) {
                if(memo[ni][nj] == -1) cnt += dfs(ni, nj);  // 아직 안가봤으면 그쪽으로 갔을 때의 경로 개수 더함
                else cnt += memo[ni][nj];                   // 가본 곳이면 예전에 구한 경로 개수 더함
            }
        }
        memo[i][j] = cnt;   // 4 곳으로 모두 가본 후의 경로 개수 memo에 저장
        return cnt;         // 경로 개수 반환
    }
}
