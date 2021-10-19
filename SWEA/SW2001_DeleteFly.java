package day0804;

import java.util.Scanner;
/*
 * Date : 210804
 */
public class SW2001_DeleteFly {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int TC = in.nextInt();		// 테스트케이스 수 입력받음
		for(int tc = 0 ; tc < TC ; tc++) {	// TC만큼 반복
			int N = in.nextInt();	// 배열의 크기(N * N)
			int M = in.nextInt();	// 파리채 크기(M * M)
			int max = -1;			// 출력할 죽인 fly의 최대 수
			int[][] map = new int[N][N];
			for(int i = 0 ; i < N ; i++) {	// 2차원 배열을 입력받음
				for(int j = 0 ; j < N ; j++) {
					map[i][j] = in.nextInt();
				}
			}
			for(int i = 0 ; i < N - M + 1 ; i++) {	// 행을 벗어나지 않도록 파리채로 검사
				for(int j = 0 ; j < N - M + 1 ; j++) {	// 열을 벗어나지 않도록 반복
					int nowSum = 0;	// 현재 위치에서의 fly합(파리채 맨 위, 맨왼쪽의 위치)
					for(int x = 0 ; x < M ; x++) {	// 현재 위치에서 (M - 1)까지의 합을 더함(파리채 크기만큼)
						for(int y = 0 ; y < M ; y++) {
							nowSum += map[i + x][j + y];
						}
					}
					if(nowSum > max) max = nowSum;		// 큰 값을 저장
				}
			}
			System.out.println("#" + (tc + 1) + " " + max);	// 결과값인 최대 죽인 수 출력
		}
	}
}
