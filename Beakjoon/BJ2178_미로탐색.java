package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ2178_미로탐색 {
    static int N, M;
    static char[][] map;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i = 0 ; i < N ; i++) {
            map[i] = in.readLine().toCharArray();
        }

        System.out.println(bfs());
    }

    static int bfs() {
        int time = 0;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        map[0][0] = '2';

        while(!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for(int s = 0 ; s < size ; s++) {
                int[] now = queue.poll();
                if(now[0] == N - 1 && now[1] == M - 1) {
                    return time;
                }

                for(int d = 0 ; d < 4 ; d++) {
                    int ni = now[0] + di[d];
                    int nj = now[1] + dj[d];
                    if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] != '1') {
                        continue;
                    }
                    map[ni][nj] = '2';
                    queue.offer(new int[]{ni, nj});
                }
            }
        }

        return 0;
    }
}
