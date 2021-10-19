package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210812
 */
public class BJ10799_IronStick {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String input = in.readLine();
		int ans = 0;		// 쇠막대기 조각 수
		int size = 1;		// 첫번째 원소는 패스하므로 크기가 1(쌓여있는 '('의 개수)	 
		char lastC = input.charAt(0);	// 첫번째 원소를 lastC에 저장
		for(int i = 1 ; i < input.length() ; i++) {	// 모든 input에 대해 실행
			char c = input.charAt(i);		// 해당 원소를 받아옴
			if(c == '(') size++;			// (이면 크기 증가
			else if(c == ')' && lastC == '(') {	// 이전 원소가 (이고 현재 원소가 )이면 -> 레이저
				size--;				// 레이저를 크기에 포함시켰으므로 1개 감소
				ans += size;		// 잘린 쇠막대기 조각 수 더해줌
			}
			else if(c == ')') {		// )이면
				size--;				// 쇠막대기가 끝났으므로 감소
				ans++;				// 마지막 조각이 있으므로 조각수 1 증가
			}
			lastC = c;				// 현재 원소를 lastC로 저장
		}
		System.out.println(ans);
	}
}
