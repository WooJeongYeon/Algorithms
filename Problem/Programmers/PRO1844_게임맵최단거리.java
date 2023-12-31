package baekjoon;

import java.util.LinkedList;
import java.util.Queue;
/*
 * Date : 2022.05.09
 * Level : Programmers level 2
 * Method : BFS
 */
public class PRO1844_게임맵최단거리 {
    int[] di = {-1, 1, 0, 0}; 
    int[] dj = {0, 0, -1, 1};
    public int solution(int[][] maps) {
        return bfs(maps);
    }

    int bfs(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        int time = 0;
        boolean[][] visited = new boolean[N][M];
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;
        while(!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for(int s = 0 ; s < size ; s++) {
                int[] pos = queue.poll();
                if(pos[0] == N - 1 && pos[1] == M - 1)
                    return time;
                for(int d = 0 ; d < 4 ; d++) {
                    int ni = pos[0] + di[d];
                    int nj = pos[1] + dj[d];
                    if(ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj] || maps[ni][nj] == 0)
                        continue;
                    visited[ni][nj] = true;
                    queue.offer(new int[]{ni, nj});
                }
            }
        }
        return -1;
    }
}
