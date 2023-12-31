import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 210924
 * Thinking : 배열 하나만 있으면 됨
 * Method : 점화식 - max(memo[0][j - 2], memo[1][j - 2], memo[1 - i][j - 1]) if j > 2
 */

public class BJ9465_스티커 {
	static int TC, N;
	static int[][] memo;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		
		for(int tc = 1 ; tc <= TC ; tc++) {								// TC만큼 반복
			N = Integer.parseInt(in.readLine());	
			memo = new int[2][N + 1];									// 현재 위치의 스티커를 포함했을 때의 최댓값이 저장되도록
			for(int i = 0 ; i < 2 ; i++) {								// memo의 초기값은 스티커의 점수
				st = new StringTokenizer(in.readLine(), " ");
				for(int j = 1 ; j <= N ; j++) {
					memo[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			if(N >= 2) {												// N이 2보다 클 경우
				memo[0][2] = memo[0][2] + memo[1][1];					// 두번째 열의 값은 왼쪽 대각선에 위치한 스티커의 점수를 더함
				memo[1][2] = memo[1][2] + memo[0][1];
				for(int j = 3 ; j <= N ; j++) {							
					for(int i = 0 ; i < 2 ; i++) {						// 상, 하 두개의 스티커에 대해
						int max = Math.max(memo[0][j - 2], memo[1][j - 2]);		// 비교값 - 왼왼쪽 상, 하, 왼쪽값 총 3개 비교
						max = Math.max(max, memo[1 - i][j - 1]);
						memo[i][j] += max;								// 현재 위치의 스티커 값에 구한 최댓값을 더함
					}
				}
			}
			
			ans = Math.max(memo[0][N], memo[1][N]);						// 가장 오른쪽의 스티커는 무조건 포함하므로 상 하 두개 중 최댓값이 결과
			
			sb.append(ans + "\n");
		}
		
		System.out.println(sb);
	}
}
