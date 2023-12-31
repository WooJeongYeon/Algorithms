package baekjoon;

// 매번 똑같이 계산하게 되는 부분을 한번 계산해서 저장해두자!
// 오... 큐를 따로 안써도 되네? 해당 애가 map상에서 불인지 아닌지 보면 되자너? 그리고 해당 map값을 저장하면 되자나... -> 코드의 중복을 줄이자!
// map에 있는 값은 불떄문에 이전 값이 덮여질 수 있음 -> 이걸로 하지 말고 Point에 저장해서
// 그리고 불때문에 갈 수 있는 부분이 조금씩 없어져서 visited 체크 안해도 되긴 함. 시간이 좀 늘지 않을까?
// 불이 먼저 휩쓸고 -> 사람이 이동

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ5427_불 {
    static int TC, R, C, ans;
    static char[][] map;
    static Queue<Point> queue;
    static Point now;
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        TC = Integer.parseInt(in.readLine());
        for(int tc = 0 ; tc < TC ; tc++) {
            st = new StringTokenizer(in.readLine(), " ");
            C = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            map = new char[R][];
            queue = new LinkedList<>();
            for(int i = 0 ; i < R ; i++) {
                map[i] = in.readLine().toCharArray();
                for(int j = 0 ; j < C ; j++) {
                    if(map[i][j] == '@') {
                        now = new Point(i, j, '@');
                    }
                    else if(map[i][j] == '*') {
                        queue.offer(new Point(i, j, '*'));
                    }
                }
            }

            ans = bfs();
            if(ans == -1) sb.append("IMPOSSIBLE\n");
            else sb.append(ans + "\n");

        }
        System.out.println(sb.toString());
    }

    private static int bfs() {
        int ans = -1;
        int time = 0;
        queue.offer(now);
        while(!queue.isEmpty()) {
            time++;
            int size = queue.size();
            for(int s = 0 ; s < size ; s++) {
                Point nowPos = queue.poll();
                char now = nowPos.val;
                for(int d = 0 ; d < 4 ; d++) {
                    int ni = nowPos.i + di[d];
                    int nj = nowPos.j + dj[d];
                    if(ni < 0 || ni >= R || nj < 0 || nj >= C) {
                        if(now != '*') return time;
                        else continue;
                    }
                    if(map[ni][nj] != '.') continue;
                    map[ni][nj] = now;
                    queue.offer(new Point(ni, nj, now));
                }
            }
        }

        return ans;
    }

    static class Point {
        int i, j;
        char val;
        public Point(int i, int j, char val) {
            this.i = i;
            this.j = j;
            this.val = val;
        }
    }
}
