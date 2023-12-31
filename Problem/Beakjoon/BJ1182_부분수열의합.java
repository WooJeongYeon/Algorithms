// 부분수열 선택 -> 부분집합 사용
// 재귀함수 내에서 백트레킹을 하려고 현재까지의 합이 목표값을 넘어버리면 return 했으나
// 음수인 경우 문제발생 ex) -8 -7 7 -> 이런 경우 위의 방법대로 하면 -8에서 끝내버려 뒤의 값들을 합쳐서 -8이 되도 넘겨버림
// 그러므로 양수일 경우에만 백트레킹을 하기로 결정
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 210908
 */
public class BJ1182_부분수열의합 {
	static int N, S;
	static int[] list;
	static int result;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		list = new int[N];
		st = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < N ; i++) {
			list[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(list);				// 정렬
		
		subset(0, 0);					// 부분집합 구하기 시작
		System.out.println(result);
	}
	static void subset(int idx, int value) {
		if(idx == N) return;			// 끝까지 다 봤으면 리턴
		if(value + list[idx] == S) result++;	// 다음 값을 더했더니 S와 같아졌으면 결과 증가
		if(value + list[idx] > S && value + list[idx] >= 0) return;	// 다음 값을 더했더니 S보다 커지고, 그 값이 0보다 크다면 더이상 할 필요 없으므로 return
		subset(idx + 1, value + list[idx]);	// 현재 값 포함
		subset(idx + 1, value);			// 현재 값 미포함
	}
}
