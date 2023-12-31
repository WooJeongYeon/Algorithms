import java.util.Scanner;
/*
 * Date : 2021.10.17(재)
 * Level : SWEA D4
 * URL : https://swexpertacademy.com/main/code/problem/problemDetail.do?contestProbId=AWT-lPB6dHUDFAVT
 * Thinking : 최대 칼로리(L)까지 가깝게 채울 수 있는 최대 점수 - 1차원 배열로 
 * Method : DP(0/1 Knapsack)
 * Result : 전에는 부분집합?조합?으로 풀고 이번엔 냅색이랑 완전 같네. 교수님이 전에 이걸로도 풀 수 있다길래 품
 */
public class SW5215_햄버거다이어트_재 {
	static int TC, N, L;
	static int[] dp;
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		TC = in.nextInt();
		for(int tc = 1 ; tc <= TC ; tc++) {
			N = in.nextInt();
			L = in.nextInt();
			dp = new int[L + 1];
			for(int i = 1 ; i <= N ; i++) {
				int score = in.nextInt();
				int calorie = in.nextInt();
				for(int j = L ; j >= calorie ; j--) {
					dp[j] = Math.max(dp[j], dp[j - calorie] + score);
				}
			}
			
			sb.append("#" + tc + " " + dp[L] + "\n");
		}
		System.out.println(sb);
	}
}
