package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 불이랑 똑같은 문제

public class BJ3055_탈출 {
    static int R, C;
    static char[][] map;
    static Point me;
    static Queue<Point> queue;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][];
        queue = new LinkedList<>();
        for (int i = 0; i < R; i++) {
            map[i] = in.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') queue.offer(new Point(i, j, map[i][j]));
                else if (map[i][j] == 'S') me = new Point(i, j, map[i][j]);
            }
        }
        int result = bfs();
        System.out.println(result == -1 ? "KAKTUS" : result);
    }

    static int bfs() {
        int time = 0;
        queue.offer(me);        // 물이 먼저 휩쓸고 고슴도치가 움직이도록 마지막으로 넣어야 함
        while (!queue.isEmpty()) {
            int size = queue.size();
            time++;
            for (int s = 0; s < size; s++) {
                Point now = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int ni = now.i + di[d];
                    int nj = now.j + dj[d];
                    if (ni < 0 || ni >= R || nj < 0 || nj >= C || map[ni][nj] == '*' || map[ni][nj] == 'X') continue;
                    // 고슴도치 갔던 데면 갈 필요 X, 물은 비버의 소굴에 못감
                    if (now.c == 'S' && map[ni][nj] == 'S' || now.c == '*' && map[ni][nj] == 'D') continue;
                    if (now.c == 'S' && map[ni][nj] == 'D') return time;        // 비버에게 도착했으면 시간 반환
                    map[ni][nj] = now.c;
                    queue.offer(new Point(ni, nj, now.c));
                }
            }
        }
        return -1;
    }

    static class Point {
        int i, j;
        char c;

        public Point(int i, int j, char c) {
            this.i = i;
            this.j = j;
            this.c = c;
        }
    }
}
