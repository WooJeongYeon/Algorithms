package day0816;

import java.util.Scanner;
/*
 * Date : 210816
 */
public class BJ3985_RollCake {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int L = in.nextInt();		// 롤케이크 길이
		int N = in.nextInt();		// 사람 명수
		int[] rollCake = new int[L + 1];
		int hopeMax = -1, hopeMaxIdx = -1, max = -1, maxIdx = -1;
		for(int i = 1 ; i <= N ; i++) {	// 각 사람이 롤케이크 조각을 선택
			int start = in.nextInt();
			int end = in.nextInt();
			if(end - start + 1 > hopeMax) {	// 가장 많이 받을 것으로 기대되는 번호와 값을 저장
				hopeMax = end - start + 1;
				hopeMaxIdx = i;
			}
			int num = 0;
			for(int j = start ; j <= end ; j++) {
				if(rollCake[j] == 0) {		// 이미 누군가 차지한 롤케이크조각이 아니라면
					rollCake[j] = i;		// 해당 사람이 롤케이크 조각을 선택함을 표시
					num++;					// 조각수 증가
				}
			}
			if(num > max) {					// 해당 사람이 가진 조각이 max보다 많다면
				max = num;					// 사람의 번호와 값을 저장
				maxIdx = i;
			}
		}
		System.out.println(hopeMaxIdx);		// 결과 출력
		System.out.println(maxIdx);
	}
}
