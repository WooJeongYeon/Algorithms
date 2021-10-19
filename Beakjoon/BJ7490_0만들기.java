// 중복순열(최대 3 ^ 8)
// 끝에서 수식을 다 모아놓는 방법과 매번 연산해주는 방법 중 고민 -> 매번 연산하기로 결정
// 공백이 여러개 연달아 있는 경우를 고려하지 않았다가 수정(이전값을 전달받도록)

// 후기
// 프론트엔드와 DB 할 동안 시간이 부족해 문제를 풀지 못했어서 오랜만에 문제를 푸는데 배운 지식들을 적용하기 어려웠다.
// 문제를 풀면서 부족함을 느꼈고, 문제를 매일 풀어야겠다고 결심하게 되었다. 
// 스터디를 통해 다른 조원들과 풀이를 공유하면서 문제에 대해 여러 시각으로 볼 수 있었고, 생각하지 못했던 부분들을 알게 되어서 좋았다.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
/*
 * Date : 210908
 */
public class BJ7490_0만들기 {
	static int TC, N;
	static ArrayList<String> result;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());	
		for(int i = 0 ; i < TC ; i++) {								// TC만큼 반복
			N = Integer.parseInt(in.readLine());					// N을 입력받음
			result = new ArrayList<String>();						// 결과 String을 저장할 ArrayList
			makeZero(1, 2, 1, "1");									// 1 ~ 2 사이의 연산자부터 시작하도록(처음엔 1만 포함)
			
			Collections.sort(result);								// 결과 String을 sort함
			for(String s : result) {								// 모든 결과를 sb에 더함
				sb.append(s + "\n");
			}
			sb.append("\n");
		}
		System.out.println(sb);										// TC만큼 끝난 후 출력
	}

	// 0이 되는 경우, 해당 문자열을 결과 ArrayList에 추가해줌
	// 매개변수 - 이전값, 현재값, 현재까지의 합, 현재까지의 수식 
	static void makeZero(int last, int now, int sum, String s) {
		// System.out.println(now + " " + sum + " " + s);
		// 끝에서 처리하기 귀찮아서 sum과 s를 계속 계산해줌
		if(now > N) {												// 모든 값에 대해 연산을 했고,
			if(sum == 0) result.add(s);								// 결과가 0이면 추가해줌
			return;													// 반환
		}
		makeZero(now, now + 1, sum + now, s + '+' + now);			// +연산 진행
		makeZero(-now, now + 1, sum - now, s + '-' + now);			// -연산 진행(이전값을 -로 설정)
		makeZero(Integer.parseInt(last + "" + now), now + 1, 		// 이전 수에 이어붙임 - 이전 값으로 숫자 이어붙여서 설정, sum을 갱신 
				sum + Integer.parseInt(last + "" + now) - last, s + ' ' + now);
	}
}