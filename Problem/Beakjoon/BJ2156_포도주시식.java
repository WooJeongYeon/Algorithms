
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 210924
 * Thinking : 계단 잘못풀었던거 넣으니까 통과~
 * Method : 자기 자신 포함하는 그때까지의 최댓값 저장하도록. 4가지 경우를 비교해 최댓값 memo에 저장
 * result : 계단오르기랑 비슷. 여기서는 2칸도 건너 뛸 수 있는게 다름
 * Plus1 : memo 2~3이 if 걸어놨어서 다른분꺼 참고해서 배열 크기를 늘려놓음 memo, values[N + 1] -> [N + 3]
 */

public class BJ2156_포도주시식 {
	static int N;
	static int[] values, memo;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(in.readLine());
		values = new int[N + 3];
		memo = new int[N + 3];
		for(int i = 1 ; i <= N ; i++) {
			values[i] = Integer.parseInt(in.readLine());
		}
				
		memo[1] = values[1];
		memo[2] = values[1] + values[2];
		memo[3] = Math.max(values[1] + values[3], values[2] + values[3]);
		for(int i = 4 ; i <= N ; i++) {
			int max = Math.max(memo[i - 2], memo[i - 3]);
			max = Math.max(max, memo[i - 3] + values[i - 1]);
			max = Math.max(max, memo[i - 4] + values[i - 1]);		// 2칸 건너 뛸 수 있으므로
			memo[i] = max + values[i];
		}	
		System.out.println(Math.max(memo[N - 1], memo[N]));
	}
}
