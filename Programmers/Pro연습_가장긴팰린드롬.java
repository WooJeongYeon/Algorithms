import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 2021.10.08
 * Level : Programmers Level 3
 * Thinking - 문자열에서 모든 경우의 범위를 결정해 펠린드롭 여부를 확인. 해당 범위가 답보다 작으면 할 필요 X
 * Method : 완전탐색
 * Error1 : 효율성 테스트 2번째가 시간초과 남 -> 해당 범위가 답보다 작으면 검사할 필요 없게 함
 * Result : 프로그래머스에서 작업하는게 안익숙한데 많이 해봐야겠다. 이 방법이 예제가 부족해서 통과하는 줄 알았는데 유진님 말씀 들어보니 괜찮은 방법인거 같다.
 */
public class Pro연습_가장긴팰린드롬 {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String s = in.readLine();
		System.out.println(solution(s));
	}
    static int solution(String s)
    {
        int ans = 1;					// 자기 자신만 포함하는 경우가 가장 짧은 팰린드롭 -> 길이 1
        boolean isPossible = true;		// 팰린드롭 가능한지 여부 저장

        for(int i = 0 ; i < s.length() - 1 ; i++) {		// i를 0부터 N - 2까지
            for(int j = i + 1 ; j < s.length() ; j++) {	// j는 i + 1부터 N - 1까지 펠린드롭 검사할 범위 찾기
                if(j - i + 1 < ans) continue;			// 이 범위의 길이가 이미 찾은 답보다 짧으면 검사할 필요 X
                isPossible = true;			
                for(int k = 0 ; k < (j - i + 1) / 2 ; k++) {		// 해당 범위의 왼쪽/오른쪽부터 하나씩 비교하기
                    //System.out.println(i + " " + j + " : " + (i + k) + " " + (j - k));
                    if(s.charAt(i + k) != s.charAt(j - k)) {		// 다른 값이 나오면
                        isPossible = false;							// 더이상 할 필요 없으므로
                        break;										// 끝냄
                    }
                }
                if(isPossible) ans = Math.max(ans, j - i + 1);		// 팰린드롭이 가능하면 답 최댓값으로 갱신
            }
        }

        return ans;
    }
}
