package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ15686_치킨배달 {
    static int N, M, hCnt, cCnt, ans;
    static Point[] hArr, cArr;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        ans = Integer.MAX_VALUE;

        hArr = new Point[N * 2];
        cArr = new Point[13];

        for(int i = 0 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            for(int j = 0 ; j < N ; j++) {
                int now = Integer.parseInt(st.nextToken());
                if(now == 1)
                    hArr[hCnt++] = new Point(i, j);
                else if(now == 2) {
                    cArr[cCnt++] = new Point(i, j);
                }
            }
        }

        int max = (1 << cCnt);
        for(int i = 1 ; i < max ; i++) {
            int result = 0;
            int[] lenArr = new int[hCnt];
            Arrays.fill(lenArr, Integer.MAX_VALUE);

            int cnt = 0;
            for(int j = 0 ; j < cCnt ; j++) {
                if(((i >> j) & 1) == 1)
                    cnt++;
            }
            if(cnt > M)
                continue;

            for(int j = 0 ; j < cCnt ; j++) {
                if(((i >> j) & 1) == 1) {
                    for(int k = 0 ; k < hCnt ; k++) {
                        lenArr[k] = Math.min(lenArr[k], Math.abs(cArr[j].i - hArr[k].i) + Math.abs(cArr[j].j - hArr[k].j));
                    }
                }
            }

            for(int j = 0 ; j < hCnt ; j++) {
                result += lenArr[j];
            }
            ans = Math.min(ans, result);
        }

        System.out.println(ans);
    }
    static class Point {
        int i, j;
        public Point(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }
}
