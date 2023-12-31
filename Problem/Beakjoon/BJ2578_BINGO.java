package day0811;

import java.util.Scanner;
/*
 * Date : 210811
 */
public class BJ2578_BINGO {
	public static void main(String[] args) {
		final int SIZE = 5;			// 빙고의 크기
		int[][] bingo = new int[SIZE][SIZE];
		int result = 0, num, now, bingoNum = 0;		
		Scanner in = new Scanner(System.in);
		for(int i = 0 ; i < SIZE ; i++) {		// 빙고판을 저장
			for(int j = 0 ; j < SIZE ; j++) {
				bingo[i][j] = in.nextInt();
			}
		}
		while(true) {					// 빙고판을 다 부를 떄까지 무한반복
			bingoNum = 0;				// 빙고의 개수
			num = in.nextInt();			// 사회자가 부른 수
			for(int i = 0 ; i < SIZE ; i++) {		// 해당 수를 찾아 0으로 저장
				for(int j = 0 ; j < SIZE ; j++) {
					if(bingo[i][j] == num)
						bingo[i][j] = 0;
				}
			}
			result++;					// 사회자가 몇개를 불렀는지
			// 가로 검사
			for(int i = 0 ; i < SIZE ; i++) {		
				now = bingo[i][0];		
				if(now == 0) {			// 첫 번째 수가 0이고, 
					for(int j = 1 ; j < SIZE ; j++) {
						if(now != bingo[i][j]) {		// 해당 수가 0이 아니면 중단
							break;							
						}
						if(j == SIZE - 1) bingoNum++;	// 해당 줄의 모든 수가 0이면 빙고수 증가
					}
				}
			}
			// 세로 검사
			for(int j = 0 ; j < SIZE ; j++) {
				now = bingo[0][j];
				if(now == 0) {
					for(int i = 1 ; i < SIZE ; i++) {
						if(now != bingo[i][j]) {
							break;							
						}
						if(i == SIZE - 1) bingoNum++;
					}
				}
			}
			// 대각선 검사(왼->오)
			now = bingo[0][0];
			if(now == 0) {
				for(int n = 1 ; n < SIZE ; n++) {
					if(now != bingo[n][n]) {
						break;							
					}
					if(n == SIZE - 1) bingoNum++;
				}
			}
			// 대각선 검사(오->왼)
			now = bingo[0][SIZE - 1];
			if(now == 0) {
				for(int n = 1 ; n < SIZE ; n++) {
					if(now != bingo[n][SIZE - n - 1]) {
						break;							
					}
					if(n == SIZE - 1) bingoNum++;
				}
			}
			if(bingoNum >= 3) 	// 빙고 개수가 3 이상이면 반복문 중단
				break;
			if(result == SIZE * SIZE) break;		// 모든 숫자를 불렀다면 중단
		}
		System.out.println(result);	// 결과 출력
	}
}
