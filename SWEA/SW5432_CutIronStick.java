package day0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
/*
 * Date : 210805
 */
public class SW5432_CutIronStick {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));	
		final int TC = Integer.parseInt(in.readLine());		// 테스트케이스 입력받음
		for(int tc = 1 ; tc <= TC ; tc++) {		// 테스트케이스만큼 반복
			String s = in.readLine();			// 배치String 받음
			Stack<Character> stack = new Stack<Character>();	// 스택 생성
			int result = 0;		// 쇠막대기 조각 수
			for(int i = 0 ; i < s.length() ; i++) {	// String 길이만큼 반복
				char c1 = s.charAt(i);		// 첫번째 값을 저장
				if(i < s.length() - 1) {	// 마지막 원소가 아닐 시
					char c2 = s.charAt(i + 1);	// 두번째 값을 저장
					if(c1 == '(' && c2 == ')') {	// 레이저인 경우
						result += stack.size();		// 스택에 저장된 쇠막대기 수만큼 더함
						i++;						// 두 값을 받았으므로 다음 반복을 넘기기 위해 i++
						continue;				// 나머지 패스
					}
				}
				if(c1 == '(') stack.push(c1);	// 쇠막대기 시작인 경우, 스택에 넣음
				else if(c1 == ')') {			// 쇠막대기 끝인 경우, 스택에서 빼고 나머지 조각 1개를 더함
					stack.pop();
					result++;
				}
			}
			System.out.println("#" + tc + " " + result);	// 결과 출력
		}
	}
}
