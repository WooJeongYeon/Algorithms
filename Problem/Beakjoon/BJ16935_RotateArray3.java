package day0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 210812
 */
public class BJ16935_RotateArray3 {
	static int[][] map;	// 2차원 배열
	static int N, M, R;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());		// 행
		M = Integer.parseInt(st.nextToken());		// 열
		R = Integer.parseInt(st.nextToken());		// 연산 수
		map = new int[N][M];	// 맵 할당
		for(int i = 0 ; i < N ; i++) {	// 맵에 값들을 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(in.readLine());
		for(int r = 0 ; r < R ; r++) {		// 연산 수만큼 반복
			int command = Integer.parseInt(st.nextToken());	// 연산을 저장
			switch(command) {		// 연산에 대한 작업 수행
				case 1 :
					upDown();
					break;
				case 2 :
					leftRight();
					break;
				case 3 :
					rotateR();
					break;
				case 4 :
					rotateL();
					break;
				case 5 :
					moveR();
					break;
				case 6 :
					moveL();
					break;
			}
		}
		print();		// 결과 배열 출력
	}
	static void upDown() {		// 상하 반전
		for(int i = 0 ; i < N / 2 ; i++) {		// 행의 반에 대한 반복을 수행
			int newIdx = N - 1 - i;
			for(int j = 0 ; j < M ; j++) {
				int temp = map[i][j];			// 값을 교체
				map[i][j] = map[newIdx][j];
				map[newIdx][j] = temp;
			}
		}
	}
	static void leftRight() {	// 좌우 반전
		for(int j = 0 ; j < M / 2 ; j++) {		// 열의 반에 대한 반복을 수행
			int newIdx = M - 1 - j;
			for(int i = 0 ; i < N ; i++) {
				int temp = map[i][j];			// 값을 교체
				map[i][j] = map[i][newIdx];
				map[i][newIdx] = temp;
			}
		}
	}
	static void rotateR() {		// 오른쪽으로 90도 회전
		int[][] newMap = new int[M][N];		// 새로운 맵을 만들어서
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				//System.out.println(i + " " + j);
				newMap[j][N - 1 - i] = map[i][j];	// 90도 회전되는 곳에 맵에 값 저장
			}
		}
		int temp = N; N = M; M = temp;		// 가로크기와 세로크기도 바뀌무로 교환
		map = newMap;						// 만든 맵의 참조값 저장해줌
		
	}
	static void rotateL() {		// 왼쪽으로 90도 회전
		int[][] newMap = new int[M][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				newMap[M - j - 1][i] = map[i][j];
			}
		}
		int temp = N; N = M; M = temp;
		map = newMap;
	}
	static void moveR() {		// 오른쪽으로 이동
		int[] ni = {0, 0, N / 2, N / 2};		// 4가지 맵에 대한 이동
		int[] nj = {0, M / 2, M / 2, 0};
		for(int i = 0 ; i < N / 2 ; i++) {		// 작은맵에 대해 하나의 지점을 선택
			for(int j = 0 ; j < M / 2 ; j++) {
				int temp = map[i][j];			// 첫번째 맵의 원소를 저장 
				for(int k = 3 ; k > 0 ; k--) {	// 원소들을 각 지점으로 이동
					int nk = (k + 1) % 4;
					map[ni[nk] + i][nj[nk] + j] = map[ni[k] + i][nj[k] + j]; 
				}
				map[ni[1] + i][nj[1] + j] = temp; // 저장해두었던 원소 저장
			}
		}
	}
	static void moveL() {		// 왼쪽으로 이동
		int[] ni = {0, 0, N / 2, N / 2};
		int[] nj = {0, M / 2, M / 2, 0};
		for(int i = 0 ; i < N / 2 ; i++) {
			for(int j = 0 ; j < M / 2 ; j++) {
				int temp = map[i][j];
				for(int k = 0 ; k < 3 ; k++) {
					int nk = (k + 1) % 4;
					map[ni[k] + i][nj[k] + j] = map[ni[nk] + i][nj[nk] + j]; 
				}
				map[ni[3] + i][nj[3] + j] = temp; 
			}
		}
	}
	static void print() {		// 배열 출력
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}
}
