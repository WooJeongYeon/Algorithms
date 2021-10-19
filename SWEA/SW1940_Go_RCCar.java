package day0809;

import java.util.Scanner;
/*
 * Date : 210809
 */
public class SW1940_Go_RCCar {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int TC = in.nextInt(); // 테스트 케이스 수를 입력받음
		for (int tc = 1; tc <= TC; tc++) { // TC번 반복
			int n = in.nextInt(), v = 0, d = 0, c, x; // n개의 명령, 속도, 거리, 입력받은 커맨드, 가속도 저장
			for (int i = 0; i < n; i++) { // n개의 명령에 대해
				c = in.nextInt(); // 커맨드를 입력받음
				if (c == 1 || c == 2) { // 가속 또는 감속이면
					x = in.nextInt(); // 가속도를 입력받고
					v = (c == 2 ? (v - x >= 0 ? v - x : 0) : v + x); // 가속일 때는 속도에 가속도를 더하고, 감속일 대는 속도에 가속도를 빼고 0 미만인 경우
																		// 0으로 저장
				}
				d += v; // 현재 속도만큼 거리를 증가
			}
			System.out.println("#" + tc + " " + d); // 결과를 출력
			
		}
	}
}