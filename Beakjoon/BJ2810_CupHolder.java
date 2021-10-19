// 이 문제에서는 보충 교수님이 보여주셨던 방식 사용함
// 문제 간단하게 생각해보자
// 커플석 사이일 때만 컵홀더를 놓을 수 없음
// -> 커플석 사이인가? 아닌가?만 확인하면 됨
// -> 커플석 개수가 홀수개일 때 커플석 사이임
// -> 이 때를 제외하고 컵홀더를 놓을 수 있음!
package day0818;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210818
 */
public class BJ2810_CupHolder {
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());		// 좌석 수
		String s = in.readLine();						// 좌석 상태를 저장
		int lNum = 0;									// 현재까지의 L의 개수(L이 짝수개이면 컵홀더를 1개 더할 수 있음)
		int sum = 2;									// 맨 왼쪽, 맨 오른쪽의 컵홀더를 더함
		for(int i = 0 ; i < N - 1 ; i++) {				// 좌석의 오른쪽에 컵홀더를 더하는 느낌(N - 2까지 실행)
			if(s.charAt(i) == 'L') lNum++;				// 해당 문자가 L이면 lNum을 증가
			if(lNum % 2 == 0) {							// lNum이 짝수개이면 커플석 사이가 아니므로 컵홀더를 놓을 수 있으므로
				sum++;									// 컵홀더 개수 증가
			}
		}
		System.out.println(Math.min(sum, N));
	}
}
