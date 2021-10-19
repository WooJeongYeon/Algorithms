package day0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210805
 */
public class SW1218_BracketMatch_Array {
	static final int TC = 10;						// 테스트케이스 수
	static final int SIZE = 1000;					// 스택으로 사용할 배열의 크기
	// String left = "([{<";
	// String right = ")]}>; 이렇게 할 수 도 있대
	static char[] left = {'(', '[', '{', '<'};		// 왼쪽 괄호를 저장한 배열
	static char[] right = {')', ']', '}', '>'};		// 오른쪽 괄호를 저장한 배열
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 1 ; tc <= TC ; tc++) {
			int result = 1;		// 결과는 defalut로 1로 설정
			char[] stack = new char[SIZE];		// 스택배열 생성
			int top = 0;					// 스택의 크기를 저장(인덱스로 접근하기 위함)
			int N = Integer.parseInt(in.readLine());	// 들어온 괄호 String 길이
			char[] s = in.readLine().toCharArray();		// 괄호를 Char 배열에에 저장
			for(int i = 0 ; i < N ; i++) {		// 각 괄호마다 검사
				char c = s[i];			// 괄호 String에서 하나를 가져옴
				if(c == left[0] || c == left[1] || c == left[2] || c == left[3]) {	// 왼쪽괄호면
					stack[top++] = c;		// 스택배열에 넣음
				}		
				else {		// 오른쪽 괄호면
					if(top == 0) {		// 크기가 0이면
						result = 0;				// 맞는 왼쪽 괄호가 없으므로 0으로 저장
						break;					// 반복 중단
					}
					char now = stack[--top];		// 스택배열에서 괄호를 꺼내오면서 크기 -1
					int index = -1;				// 왼쪽 괄호에 맞는 인덱스
					for(int j = 0 ; j < left.length ; j++) {	// 왼쪽 괄호의 인덱스를 찾음
						if(left[j] == now) index = j;
					}
					if(c != right[index]) {		// 오른쪽 괄호와 다를 때
						result = 0;			// 괄호가 다르므로 0으로 저장
						break;
					}
				}
			}
			if(top != 0) result = 0;	// 모든 경우가 끝났는데 괄호가 존재하므로 올바른 괄호가 아니므로 0 저장
			System.out.println("#" + tc + " " + result);	// 결과 출력
		}
	}
}
