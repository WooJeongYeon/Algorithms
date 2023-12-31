package day0810;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210810
 */
public class BJ1012_OrganicCabbage_Recrusion {
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	static int[][] map;
	static int N, M;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(in.readLine());
		for(int tc = 0 ; tc < TC ; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			M = Integer.parseInt(st.nextToken());		// 가로 길이
			N = Integer.parseInt(st.nextToken());		// 세로 길이
			int K = Integer.parseInt(st.nextToken());
			int worm = 0;				// 지렁이 개수
			map = new int[N][M];
			for(int i = 0 ; i < K ; i++) {
				st = new StringTokenizer(in.readLine());
				int nowJ = Integer.parseInt(st.nextToken());
				int nowI = Integer.parseInt(st.nextToken());
				map[nowI][nowJ] = 1;
			}
			for(int i = 0 ; i < N ; i++) {		// 모든 map에 대해
				for(int j = 0 ; j < M ; j++) {
					if(map[i][j] == 1) {		// 해당 칸에 배추가 있으면
						worm++;					// 지렁이 추가
						recur(i, j);			// 연결된 배추를 모두 탐색하는 재귀메소드 호출
					}
				}
			}
			System.out.println(worm);			// 지렁이 출력
		}
	}
	private static void recur(int i, int j) {	// 현재 위치의 주변 칸들에 대해 검사
		for(int d = 0 ; d < 4 ; d++) {			// 상하좌우에 대해
			int ni = i + di[d];					// 해당 방향의 칸의 인덱스를 구함
			int nj = j + dj[d];
			// 인덱스 벗어나거나 지렁이가 없으면 다음 칸으로 continue
			if(ni < 0 || ni >= N || nj < 0 || nj >= M || map[ni][nj] == 0) continue;
			if(map[ni][nj] == 1) {	// 지렁이가 있다면, 해당 칸을 검사하므로 0을 저장(이 칸은 다시 탐색 안하도록)
				map[ni][nj] = 0;		// 재귀로하니까 반복문 전에 해도 시간초과 안나는데...?
				recur(ni, nj);		// 해당 칸을 탐색
			}
		}
	}
}
