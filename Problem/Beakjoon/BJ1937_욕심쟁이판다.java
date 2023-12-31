package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 예전에 어려워보였는데 산책하면서 고민하다보니 생각보다 쉽게 풀었당
// memo에 DFS로 현재부터 가능한 칸까지의 최대댓값을 저장
// memo를 재사용
// 비슷한 문제일거 같은... - 백준1520 내리막 길(다른문제자너... 거의 같은 문제네.. 얘는 경로개수 구하는 거), 프로그래머스 보행자 천국

public class BJ1937_욕심쟁이판다 {
    static int N, ans;
    static int[][] map, memo;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        map = new int[N][N];
        memo = new int[N][N];
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine(), " ");
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0 ; i < N ; i++) {
            for(int j = 0 ; j < N ; j++) {
                if(memo[i][j] == 0) dfs(i, j);  // 모든 칸에 대해 memo를 구함(해당 칸부터 가능한 최댓값 저장)
            }
        }

        System.out.println(ans);
    }
    static void dfs(int i, int j) {
        int max = 0;
        for(int d = 0 ; d < 4 ; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni < 0 || ni >= N || nj < 0 || nj >= N) continue;
            if(map[i][j] < map[ni][nj]) {   // 다음 칸이 가능한 칸이면
                if(memo[ni][nj] == 0) dfs(ni, nj);  // memo에 저장이 안되있으면 dfs로 구함
                max = Math.max(memo[ni][nj], max);  // 상하좌우 갔을때 중 가장 가능한 최댓값을 저장
            }
        }
        memo[i][j] = 1 + max;   // 나 자신도 포함이므로 1 더함
        ans = Math.max(memo[i][j], ans);    // 답 구함
    }
}
