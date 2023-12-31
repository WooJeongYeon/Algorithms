package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 2022.06.20
 * Level : BaekJoon Silber 1
 * Difficulty : 중하
 * URL : https://www.acmicpc.net/problem/1932
 * Thinking : 1차원 배열로 할 수 있지 않을까 생각했는데 입력받으면서 하기는 어려울거같아서 2차원 배열 사용
 * Method : DP
 */

public class BJ1932_정수삼각형 {
    static int N, ans;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(in.readLine());
        dp = new int[N][N];
        dp[0][0] = Integer.parseInt(in.readLine());

        for(int i = 1 ; i < N ; i++) {
            st = new StringTokenizer(in.readLine());
            dp[i][0] = dp[i - 1][0] + Integer.parseInt(st.nextToken());
            for(int j = 1 ; j < i ; j++) {
                dp[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] += Math.max(dp[i - 1][j - 1], dp[i - 1][j]);
            }
            dp[i][i] = dp[i - 1][i - 1] + Integer.parseInt(st.nextToken());
        }

        for(int j = 0 ; j < N ; j++) {
            ans = Math.max(ans, dp[N - 1][j]);
        }

        System.out.println(ans);
    }
}
