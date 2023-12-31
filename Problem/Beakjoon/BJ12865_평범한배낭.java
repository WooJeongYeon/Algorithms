import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 210924
 * Thinking : 일차원 배열로 만드는 배낭문제
 */

public class BJ12865_평범한배낭 {
	static int N, K;
	static int[] weights, values, dp;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		weights = new int[N + 1];
		values = new int[N + 1];
		dp = new int[K + 1];
		
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			weights[i] = Integer.parseInt(st.nextToken());
			values[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int n = 1 ; n <= N ; n++) {						// 물건 개수만큼
			for(int k = K ; k >= weights[n] ; k--) {		// 뒤에서부터 계산할 경우 일차원 배열로 끝낼 수 있음
				// 무게 k가 되도록 2가지 경우를 만들었을 때 가치 비교 - 현재 물건을 포함했을 때, 현재 물건을 포함하지 않았을 때(이전 dp배열값) 
				dp[k] = Math.max(dp[k], dp[k - weights[n]] + values[n]);		
			}
		}
		
		System.out.println(dp[K]);							// 무게 K일 때의 최대 가치값을 출력
	}
}
