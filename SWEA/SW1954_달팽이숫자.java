package day0803;

import java.util.Scanner;
/*
 * Date : 2021.08.03
 * Level : SWEA D3
 * Method : 반복문
 */
public class SW1954_달팽이숫자 {
	// 델타배열 - 방향을 설정(오른쪽, 아래, 왼쪽, 위)
	private static int[] di = {0, 1, 0, -1};
	private static int[] dj = {1, 0, -1, 0};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		final int TC = in.nextInt();
		for(int tc = 0 ; tc < TC ; tc++) {
			int n = in.nextInt();			// 배열의 길이
			int[][] snail = new int[n][n];	// snail 2차원 배열
			int max = n * n;// 마지막 숫자
			int now = 1;	// 현재 숫자
			int d = 0;	// 방향
			int i = 0, j = 0;	// 행, 열 인덱스
			int nextI = 0, nextJ = 0;	// 다음이 될 행, 열 인덱스
			while(max >= now) {			// 마지막 숫자까지 다 배열에 저장하면 종료
				snail[i][j] = now++;	// 현재 숫자를 현재 위치의 배열에 저장
				nextI = i + di[d];		// 다음 위치가 될 행 인덱스
				nextJ = j + dj[d];		// 다음 위치가 될 열 인덱스
				// 다음 위치가 될 인덱스가 배열을 벗어나거나 값이 0이라면
				if(nextI < 0 || nextI >= n || nextJ < 0 || nextJ >= n || snail[nextI][nextJ] != 0)
					d = (d + 1) % 4;	// 방향을 바꿔줌
				i += di[d];	// 다음 위치가 될 행 인덱스 저장
				j += dj[d];	// 다음 위치가 될 열 인덱스 저장
			}
			System.out.println("#" + (tc + 1));	
			for(i = 0 ; i < n ; i++) {		// 달팽이 2차원 배열 출력!
				for(j = 0 ; j < n ; j++) {
					System.out.print(snail[i][j] + " ");
				}
				System.out.println();
			}
		}
	}
}
