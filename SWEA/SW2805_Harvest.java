package day0804;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210804
 */
public class SW2805_Harvest {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int TC = Integer.parseInt(br.readLine());		// 테스트 케이스 입력받음
		for(int tc = 0 ; tc < TC ; tc++) {				// TC번 실행
			int n = Integer.parseInt(br.readLine());	// 농장의 크기를 받음
			int profit = 0; 							// 최종 결과인 총 수익
			for(int i = 0 ; i < n ; i++) {				// 농장 N*N의 값을 받아오면서 마름모 안에있는 값들 더하기
				String values = br.readLine();			// 한 줄을 받아옴
				for(int j = 0 ; j < n ; j++) {			// 개행문자로 구분되지 않으므로 각 인덱스 참조
					int num = values.charAt(j) - '0';	// char의 숫자를 int 숫자로 변경해 저장
					int temp = (n / 2 - i) >= 0 ? (n / 2 - i) : -(n / 2 - i);	// 마름모 바로 시작인덱스
					if(j >= temp && j < n - temp) profit += num;	// 마름모 안에 있는 값이라면 수익에 더함
				}
			}
			System.out.println("#" + (tc + 1) + " " + profit);	// 최종 수익 출력
		}
	}
}
