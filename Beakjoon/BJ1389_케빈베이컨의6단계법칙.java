package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1389_케빈베이컨의6단계법칙 {
    static int N, M, min, ans;
    static int[][] vArr;
    static int INF = 5000000;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        vArr = new int[N + 1][N + 1];
        for(int i = 1 ; i <= N ; i++) {
            Arrays.fill(vArr[i], INF);
        }
        for(int i = 0 ; i < M ; i++) {
            st = new StringTokenizer(in.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            vArr[s][e] = 1;
            vArr[e][s] = 1;
        }

        for(int k = 1 ; k <= N ; k++) {
            for(int i = 1 ; i <= N ; i++) {
                for(int j = 1 ; j <= N ; j++) {
                    if(k == i || k == j)
                        continue;
                    vArr[i][j] = Math.min(vArr[i][j], vArr[i][k] + vArr[k][j]);
                }
            }
        }

        min = INF;
        for(int i = 1 ; i <= N ; i++) {
            int now = 0;
            for(int j = 1 ; j <= N ; j++) {
                if(i != j)
                    now += vArr[i][j];
            }
            if(now < min) {
                min = now;
                ans = i;
            }
        }

        System.out.println(ans);
    }
}
