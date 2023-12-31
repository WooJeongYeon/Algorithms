package day0814;

import java.util.Scanner;
/*
 * Date : 210814
 */
public class BJ2851_SuperMario {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int SIZE = 10;
		int sum = 0, n = 0;
		for(int i = 0 ; i < SIZE ; i++) {		// 10번까지 반복
			n = in.nextInt();			// 버섯의 점수를 입력받아서
			sum += n;					// 일단 총 점수에 더함
			if(sum >= 100) break;		// 총 점수가 100이상이면 더이상 점수를 입력받을 필요가 없으므로 중단
		}
		int last = sum - n;				// 총 점수에서 방금 더한 점수를 뺌
		if(100 - last < sum - 100)		// 이전 점수합이 100까지 더 가깝다면 저장(같은 경우에는 이미 sum 더 큰 값이므로 변동사항 X)
			sum = last;
		System.out.println(sum);
	}
}
