package day0805;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * Date : 210805
 */
public class BJ2493_Tower {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());		// 탑의 수 입력받음
		StringTokenizer st = new StringTokenizer(in.readLine());
		Stack<Tower> stack = new Stack<Tower>();		// 아직 비교할 가치가 있는 탑들을 저장
		StringBuilder sb = new StringBuilder();			// 결과를 모아놓을 문자열
		sb.append("0 ");						// 첫 번째 탑의 수신탑은 없으므로 0을 추가
		int max = Integer.parseInt(st.nextToken());
		stack.push(new Tower(1, max));			// 첫 번째 탑을 넣음
		int idx = -1;		// 현재 탑을 수신하는 탑의 번호를 저장
		for(int i = 2 ; i <= N ; i++) {			// 2 ~ N번 탑에 대해 유효한 왼쪽 탑들을 검사
			int x = Integer.parseInt(st.nextToken());	// 현재 탑의 높이
			if(max <= x) {		// 가장 높은 탑이라면
				stack.clear();	// 스택을 비우고
				stack.push(new Tower(i, x));	// 스택에 탑 클래스를 넣음
				max = x;		// 최대값은 현재 탑이 됨
				idx = 0;		// 가장 높은 탑이므로 수신탑이 없음
			}
			else {				// 가장 높은 탑이 아니라면
				while(true) {	// 자기보다 높은 탑이 나올 때까지 반복
					Tower m = stack.peek();		// 스택의 탑을 꺼내서(가장 가까운 유효한 탑)
					if(m.value > x) {			// 자기보다 높은 탑이라면
						stack.push(new Tower(i, x));	// 현재 탑을 스택에 넣고
						idx = m.idx;			// 수신탑을 선택된 탑 번호로 설정
						break;					// 찾았으므로 while 중단
					}		
					stack.pop();				// 낮거나 같은 탑이므로 스택에서 제거
				}				
			}
			
			sb.append(idx + " ");				// 결과에 수신탑 추가
		}
		System.out.println(sb);					// 결과 출력
	}
	public static class Tower{		// 탑의 번호와 높이를 가지는 클래스
		public int idx;
		public int value;
		public Tower(int idx, int value) {
			this.idx = idx;
			this.value = value;
		}
	}
}
