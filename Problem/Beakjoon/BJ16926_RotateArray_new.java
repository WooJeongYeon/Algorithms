package day0811;

import java.util.Arrays;
import java.util.Scanner;
/*
 * Date : 210811
 */
public class BJ16926_RotateArray_new {
	static int N, M, R, num;
	static int[][] map, newMap;
	static int[] di = {0, 1, 0, -1}; // 오, 아래, 왼, 위(당겨오므로 시계방향)
	static int[] dj = {1, 0, -1, 0}; 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();		// 세로길이
		M = in.nextInt();		// 가로길이
		R = in.nextInt();		// 회전수
		map = new int[N][M];	// map
		num = Math.min(N, M) / 2;	// 작은 길이 / 2번만큼 검사(각 회전하는 부분들의 개수)
		for(int i = 0 ; i < N ; i++) {			// 배열 입력 받음
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = in.nextInt();
			}
		}
		rotate();
		print();		// 배열 출력
	}
	static void rotate() {
		int area = N * M;
		for(int n = 0 ; n < num ; n++) {
			int r = area - (N - 2 * n - 2) * (M - 2 * n - 2);
			area -= r;
			r = R % r;
			int d = 0;
			for(int nowR = 0 ; nowR < r ; nowR++) {
				int i = n, j = n, temp = map[n][n];	// 처음 값을 저장해두고
				while(true) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if(ni < n || ni > (N - 1 - n)|| nj < n || nj > (M - 1 - n)) {
						d = (d + 1) % 4;
						continue;
					}
					map[i][j] = map[ni][nj];
					i = ni; j = nj;
					if(i == n + 1 && j == n) break;
				}
				map[i][j] = temp;		// 마지막에 저장해둔 처음 값을 옮김
			}
		}
	}
	static void print() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
