package day0811;

import java.util.Arrays;
import java.util.Scanner;
/*
 * Date : 210811
 */
public class BJ16926_RotateArray {
	static int N, M, R, num;
	static int[][] map, newMap;
	static int[] di = {0, 1, 0, -1}; // 왼, 아래, 오, 위(반시계방향)
	static int[] dj = {-1, 0, 1, 0}; 
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		N = in.nextInt();
		M = in.nextInt();
		R = in.nextInt();
		map = new int[N][M];
		num = Math.min(N, M) / 2;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = in.nextInt();
			}
		}
		for(int r = 0 ; r < R ; r++) {
			rotate();
		}
		print();
	}
	static void rotate() {
		for(int n = 0 ; n < num ; n++) {
			int d = 1, i = n, j = n;
			while(true) {
				int ni = i + di[d];
				int nj = j + dj[d];
				if(ni < n || ni > (N - 1 - n )|| nj < n || nj > (M - 1 - n)) {
					d = (d + 1) % 4;
					continue;
				}
				newMap[ni][nj] = map[i][j];
				i = ni; 
				j = nj;
				if(i == n && j == n) break;
			}
		}
		for(int i = 0 ; i < N ; i++) {
			map[i] = newMap[i].clone();
		}
	}
	static void print() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(newMap[i][j] + " ");
			}
			System.out.println();
		}
	}
}