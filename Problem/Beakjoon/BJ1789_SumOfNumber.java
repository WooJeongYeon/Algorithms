package day0806;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 210806
 */
public class BJ1789_SumOfNumber {
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		long S = Long.parseLong(in.readLine());	// S의 값의 최댓값이 약 42억이므로 long
		int i = 1, num = 0;
		long sum = 0;		// S보다 클때 중단하므로 long
		// (i + 1) * i / 2와 S를 비교
		while (true) {		// 무한 반복
			if (sum + i > S)	// 1부터 n까지의 총 합이 S보다 클 때, 사용하든 최대 자연수 개수를 1 넘어가므로 중단
				break;
			sum += i++;		// 1부터 차례대로 sum에 더해감
			num++;			// 서로 다른 자연수 개수 +1
		}
		System.out.println(num);	// 출력
	}
}
