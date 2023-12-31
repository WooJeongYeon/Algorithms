import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210916
 */
public class SW3307_LIS최장증가부분수열 {
	static int TC;
	static int N;
	static int[] arr, dp;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(bf.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			N = Integer.parseInt(bf.readLine());
			arr = new int[N];
			dp = new int[N];
			st = new StringTokenizer(bf.readLine());
			for(int i = 0 ; i < N ; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				dp[i] = 1;
			}
			
			int max = 0;
			for(int i = 0 ; i < N ; i++) {
				for(int j = 0 ; j < i ; j++) {
					if(arr[j] < arr[i] && dp[j] + 1 > dp[i]) {
						dp[i] = dp[j] + 1;
					}
				}
				max = Math.max(max, dp[i]);
			}
			
			sb.append("#" + tc + " " + max + "\n");
		}
		System.out.println(sb);
	}
}
