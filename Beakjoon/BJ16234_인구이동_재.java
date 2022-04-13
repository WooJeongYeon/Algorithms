package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BJ16234_인구이동_재 {
    static int N, L, R, ans, sum, cnt;
    static int[][] map;
    static boolean[][] visited;
    static LinkedList<int[]> points;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        points = new LinkedList<>();
        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0 ; j < N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            boolean isEnd = true;
            visited = new boolean[N][N];
            for(int i = 0 ; i < N ; i++) {
                for(int j = 0 ; j < N ; j++) {
                    if(visited[i][j]) continue;
                    sum = 0; cnt = 0;
                    dfs(i, j);
                    if(points.size() > 1) {
                        int newVal = sum / cnt;
                        for(int[] point : points) {
                            map[point[0]][point[1]] = newVal;
                        }
                        isEnd = false;
                    }
                    points.clear();
                }
            }

            if(isEnd) break;
            ans++;
        }


        System.out.println(ans);
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;
        cnt++;
        sum += map[i][j];
        points.offer(new int[]{i, j});
        for(int d = 0 ; d < 4 ; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if(ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj]) continue;
            int sub = Math.abs(map[i][j] - map[ni][nj]);
            if(L <= sub && sub <= R) dfs(ni, nj);
        }
    }
}
