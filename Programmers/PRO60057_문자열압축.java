/*
 * Date : 2022.01.05
 * Level : Programmers level 2
 * Difficulty : 중
 * Time : 1시간 
 * URL : https://programmers.co.kr/learn/courses/30/lessons/60057
 * Select1 : 스택 vs 이전값저장(이걸로)
 * Thinking : 스택은 하나 값만 저장해놓는거라 사용 안함
 * Method : 문자열
 * Error1 : i + l >= N일 때 중단했었는데, substring에서 endIndex까지를 포함 안하므로 i + l > N 해줘야 함 
 * Error2 : 계속 에러나서 헤멨다. 숫자가 일의자리일 때만 고려했었음 -> 십의자리 이상일 때 그 길이를 len에 더해주도록 
 * Result : 쉬울줄 알았는데 에러나서 헤멨다ㅠ 생각보다 어려움
 * Plus1 : 재귀로 풀기도 하네..!
 */
public class PRO60057_문자열압축 {
	class Solution {
		public int solution(String s) {
			int N = s.length();
			int answer = N;
			for (int l = 1; l <= N / 2; l++) {
				int len = 0;
				String lastS = "";
				String subS = "";
				int num = 1;
				for (int i = 0; i < N; i += l) {
					if (i + l > N) {				// l의 길이가 안되는 나머지 문자열은 더해주고 중단
						len += N - i;
						break;
					}
					subS = s.substring(i, i + l);
					if (!lastS.equals(subS)) {		// 이전과 같은 값이 아니면
						if (num != 1)				// 숫자 길이 더해주고
							len += (num + "").length();
						len += l;					// 지금 이 문자열 길이 더함
						lastS = subS;				// 이전값, 개수 갱신
						num = 1;
					} else {
						num++;
					}
				}
				if (num != 1)						// 끝나면 중복된 문자열 개수 더해줌
					len += (num + "").length();
				answer = Math.min(answer, len);
			}
			return answer;
		}
	}
}
