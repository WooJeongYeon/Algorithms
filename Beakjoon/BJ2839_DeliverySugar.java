package day0817;

import java.util.Scanner;
/*
 * Date : 210817
 */
public class BJ2839_DeliverySugar {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int N = in.nextInt();		
		int result = 0;
		int M = N;
		if(M >= 15) {		// 15개보다 같거나 크면
			result = (N - 15) / 5;		// 15를 뺀 만큼은 5로 채움
			M = 15 + (N - 15) % 5;
		}
		while(M >= 15) {	// 5로 나눈 나머지를 더하고서도 15를 넘으면 
			result++;		// 5로 1개 더 채움
			M -= 5;			// 5를 빼줌
		}
		while(true) {		// 3으로 나눠질 수 있거나 더이상 설탕을 채울 수 없을 때까지
			if(M % 3 == 0) {		// 3으로 나누어지면
				result += M / 3;	// 그만큼을 3으로 채움
				M = 0;
			}
			if(M < 5) break;		// 5보다 작으면 더이상 설탕을 채울 수 없으므로 중단
			result++;				// 5kg를 하나 추가
			M -= 5;
		}
		if(M > 0) result = -1;		// 끝났는데 아직 못채웠다면 -1 출력
		System.out.println(result);	// 결과 출력
	}
}
