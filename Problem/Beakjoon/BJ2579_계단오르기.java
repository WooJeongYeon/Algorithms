import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * Date : 210924
 * Thinking : 3계단 연속 불가. memo에 자기 자신을 포함하는 최댓값을 저장하도록. memo[N]이 정답
 * Method : 3가지 경우 비교해 최댓값 저장
 * error1 : 틀렸다 뜸. 2계단 넘게 뛰어오르도록했네
 * 		-> 계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다. 즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
 * result : 포도주랑 비슷했네. 위의 조건만 다름
 * Plus1 : 2개만 비교해도 되네 
 * Plus2 : stair랑 메모 크기를 301로 해놓고 if문 없이 써도 되네... 
 * Plus3 : memo 2~3이 if 걸어놨어서 다른분꺼 참고해서 배열 크기를 늘려놓음 memo, values[N + 1] -> [N + 3]
 * Plus4 : dfs로 풀어도 좋을듯
 */

public class BJ2579_계단오르기 {
	static int N;
	static int[] stair, memo;
	static int ans;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		stair = new int[N + 3];				// 계단의 점수 저장
		memo = new int[N + 3];				// 해당 계단을 포함한(자기 자신을 포함한) 해당 계단까지의 최댓값 저장
		for(int i = 1 ; i <= N ; i++) {
			stair[i] = Integer.parseInt(in.readLine());
		}
		
		memo[1] = stair[1];					
		memo[2] = stair[1] + stair[2];	// 두번째 계단에서는 첫번째 계단의 점수를 더함
		// 세번째 계단에서부터 연속 3개 계단이 안되므로 2개씩 더해 비교(자기 자신을 포함)
		memo[3] = Math.max(stair[1] + stair[3], stair[2] + stair[3]);
		for(int i = 4 ; i <= N ; i++) {		// 네번째 계단부터는 두 가지를 비교해 저장
			memo[i] = Math.max(memo[i - 2], memo[i - 3] + stair[i - 1]) + stair[i];
		}	
		System.out.println(memo[N]);		
	}
}
