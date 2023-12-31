package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210810
 */
public class SW4698_SpecialDecimal {
	static int D;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

		final int TC = Integer.parseInt(in.readLine());		// 테스트케이스를 입력받음
		final int SIZE = 1000000;	// 소수인지 체크할 최대 수
		boolean[] arr = new boolean[SIZE + 1];
		for(int i = 2 ; i <= Math.sqrt(SIZE) ; i++) {		// 미리 소수인지 여부를 저장함
			int n = i * i;		// i * (i - 1)까지는 이미 이전 수들이 소수가 아니라고 체크해줬으므로 제곱부터 시작
			while(n <= SIZE) {	// 주어진 모든 수를 체크해
				arr[n] = true;	// 소수가 아님을 저장
				n += i; 
			}
		}
		for(int tc = 1 ; tc <= TC ; tc++) {		// tc번 반복
			StringTokenizer st = new StringTokenizer(in.readLine());
			D = Integer.parseInt(st.nextToken());		// 포함하는지 체크할 수
			int A = Integer.parseInt(st.nextToken());	// A이상 B이하에 대해 체크
			int B = Integer.parseInt(st.nextToken());
			int result = 0;			// 일치하는 결과의 수
			if(A == 1) A++;			// A가 1인 경우, 2부터 시작(1은 소수가 아님)
			for(int i = A ; i <= B ; i++) {		// A ~ B에 대해
				if(!arr[i] && check(i)) {		// 소수이고, D를 포함한다면
					result++;					// 결과 증가
				}
			}
			System.out.println("#" + tc + " " + result);	// 출력
		}
	}
	static boolean check(int n) {		// 해당 수를 포함하는지를 체크
		while(n > 0) {					// n이 0이 될때까지 반복
			if(n % 10 == D)				// 나머지가 n이면 
				return true;			// 해당 수를 포함하고 있음을 반환
			n /= 10;					// 다음 자리수 체크
		}
		return false;					// 해당 수를 포함하지 않음을 반환
	}
}
