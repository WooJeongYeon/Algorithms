package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
/*
 * Date : 210825
 */
public class BJ10026_RedGreen_DFS {
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		char[][] map1 = new char[N][];			// 색맹이 없을 때 구역나누기
		char[][] map2 = new char[N][N];			// 색맹이 있을 때 구역나누기
		for(int i = 0 ; i < N ; i++) {
			map1[i] = in.readLine().toCharArray();
		}
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				map2[i][j] = (map1[i][j] == 'G' ? 'R' : map1[i][j]);	// 요소가 초록인 경우 빨강으로 저장함 
			}
		}
		int area1 = 0; 							// 구역 수
		int area2 = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(map1[i][j] != '-') {			// 아직 지나가지 않았으면
					area1++;					// 구역수를 증가시키고
					DFS(map1, N, i, j, map1[i][j]);	// 해당 영역을 시작점으로 DFS 시작!
				}
				if(map2[i][j] != '-') {
					area2++;
					DFS(map2, N, i, j, map2[i][j]);
				}
			}
		}
		System.out.println(area1 + " " + area2);
	}
	
	// map, map크기, 현재 요소의 인덱스, 구역의 값
	static void DFS(char[][] map, int N, int si, int sj, char c) {
		map[si][sj] = '-';						// 지나갔음을 표지
		for(int d = 0 ; d < 4 ; d++) {			// 위-아래-왼-오에 대해
			int ni = si + di[d];
			int nj = sj + dj[d];
			if(ni >= 0 && ni < N && nj >= 0 && nj < N && map[ni][nj] == c)	// map을 벗어나지 않고, 같은값이면
				DFS(map, N, ni, nj, c);			// 계속 DFS 진행
		}
	}
}
