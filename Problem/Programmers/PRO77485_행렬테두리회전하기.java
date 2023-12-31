package programmers;

public class PRO77485_행렬테두리회전하기 {
    int[] di = {1, 0, -1, 0};
    int[] dj = {0, 1, 0, -1};
    public int[] solution(int rows, int columns, int[][] queries) {
        int N = queries.length;
        int[] answer = new int[N];
        int[][] map = new int[rows][columns];
        int idx = 0;
        for(int i = 0 ; i < rows ; i++) {
            for(int j = 0 ; j < columns ; j++) {
                map[i][j] = ++idx;
            }
        }

        for(int i = 0 ; i < N ; i++) {
            int min = Integer.MAX_VALUE;
            int si = queries[i][0] - 1, sj = queries[i][1] - 1;
            int ei = queries[i][2] - 1, ej = queries[i][3] - 1;
            int nowI = si, nowJ = sj, nextI, nextJ;
            int tmp = map[nowI][nowJ];
            int d = 0;
            while(true) {
                min = Math.min(min, map[nowI][nowJ]);
                nextI = nowI + di[d];
                nextJ = nowJ + dj[d];
                if(nextI < si || nextI > ei || nextJ < sj || nextJ > ej) {
                    d = (d + 1) % 4;
                    continue;
                }
                map[nowI][nowJ] = map[nextI][nextJ];
                if(nextI == si && nextJ == sj) break;
                nowI = nextI;
                nowJ = nextJ;
            }
            map[nowI][nowJ] = tmp;

            answer[i] = min;
        }

        return answer;
    }
}
