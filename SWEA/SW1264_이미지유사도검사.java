package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class SW1264_이미지유사도검사 {
    static int TC, N;
    static double ans;
    static char[] s1, s2;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        TC = Integer.parseInt(in.readLine());
        for(int tc = 1 ; tc <= TC ; tc++) {
            N = Integer.parseInt(in.readLine().trim());
            dp = new int[N + 1][N + 1];
            s1 = in.readLine().toCharArray();
            s2 = in.readLine().toCharArray();
            for(int i = 1 ; i <= N ; i++) {
                for(int j = 1 ; j <= N ; j++) {
                    if(s1[i - 1] == s2[j - 1])
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    else
                        dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }

            ans = (double)dp[N][N] * 100 / N;

            sb.append("#").append(tc).append(" ").append(String.format("%.2f", ans)).append("\n");
        }

        System.out.println(sb);
    }
}
