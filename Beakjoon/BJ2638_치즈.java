package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 0 - 치즈X, 1 = 치즈
/*
 * Date : 2022.06.20
 * Level : BaekJoon Gold 4
 * Difficulty : 중하
 * URL : https://www.acmicpc.net/problem/2638
 * Thinking : 처음엔 LinkedList에 저장하려 했는데 cnt 계산하는걸로 바꿈
 *          - map에 다 저장할 수 있지 않을까 생각했는데 visited 체크하는 것 때문에 따로 배열 만듬
 *          - 치즈 두번 닿았는지 체크 -> dfs 돌렸을 때 2번 방문하면 됨! -> 이 경우 이후에 다시 안가도록 표시해줌(치즈부분을 공백으로 바꿈)
 * Method : DFS
 * Result : 예전 치즈문제랑 조금 다르네. 이건 두 면이 닿아야지 없어짐
 */

public class BJ2638_치즈 {
    static int N, M, nowCnt, cnt, ans;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0 ; j < M ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    cnt++;
                }
            }
        }

        while(cnt > 0) {
            visited = new boolean[N][M];
            ans++;
            nowCnt = 0;

            dfs(0, 0);

            cnt -= nowCnt;
        }

        System.out.println(ans);
    }

    static void dfs(int i, int j) {
        visited[i][j] = true;
        for(int d = 0 ; d < 4 ; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni < 0 || ni >= N || nj < 0 || nj >= M)
                continue;

            if(map[ni][nj] == 0 && !visited[ni][nj])
                dfs(ni, nj);
            else if(map[ni][nj] == 1 && !visited[ni][nj])
                visited[ni][nj] = true;
            else if(map[ni][nj] == 1 && visited[ni][nj]) {
                map[ni][nj] = 0;
                nowCnt++;
            }
        }
    }
}
