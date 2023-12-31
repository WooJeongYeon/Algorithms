package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 다시 - 그러게... 젤 먼저 도착하는 애 더하면 되는구만.. 그냥 BFS로 하면되네. cnt 셀 필요 없이

public class SW10966_물놀이를가자 {
    static int TC, N, M, ans;
    static boolean[][] visited;
    static char[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            ans = 0;
            st = new StringTokenizer(in.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new char[N][];
            visited = new boolean[N][M];
            queue = new LinkedList<>();

            for(int i = 0 ; i < N ; i++) {
                map[i] = in.readLine().toCharArray();
                for(int j = 0 ; j < M ; j++) {
                    if(map[i][j] == 'W')
                        queue.offer(new int[]{i, j});
                }
            }

            bfs();

            sb.append("#").append(tc).append(" ").append(ans).append("\n");
        }

        System.out.println(sb);
    }

    private static void bfs() {
        int dist = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int s = 0 ; s < size ; s++) {
                int[] now = queue.poll();
                for(int d = 0 ; d < 4 ; d++) {
                    int ni = now[0] + di[d];
                    int nj = now[1] + dj[d];
                    if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 'W' || visited[ni][nj])
                        continue;
                    visited[ni][nj] = true;
                    ans += dist;
                    queue.offer(new int[]{ni, nj});
                }
            }
            dist++;
        }
    }
}
