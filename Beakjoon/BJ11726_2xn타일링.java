package baekjoon;

import java.util.Scanner;

public class BJ11726_2xn타일링 {
    static int N;
    static int[] dp;
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        N = in.nextInt();
        dp = new int[N + 1];
        dp[0] = dp[1] = 1;
        for(int i = 2 ; i <= N ; i++) {
            dp[i] = (dp[i - 2] + dp[i - 1]) % 10007;
        }
        System.out.println(dp[N]);
    }
}
