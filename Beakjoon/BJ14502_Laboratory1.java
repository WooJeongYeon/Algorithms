package day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;
/*
 * Date : 210816
 */
public class BJ14502_Laboratory1 {
	static int N, M;
	static int[][] map;
	static ArrayList<Point> blanks, virus;	// 0인칸(벽을 놓을 수 있는 칸)과 2인칸(바이러스가 있는 칸)을 각각 저장함
	static int blankNum, sWallNum, virusNum;	// 0인칸의 개수, 벽을 추가하는 개수, 2인칸의 개수
	static boolean[] selected;				// 0인칸들이 벽으로 선택되었는지의 여부를 저장
	static int[] di = {-1, 1, 0, 0};	// 델타배열 - 위, 아래, 왼, 오
	static int[] dj = {0, 0, -1, 1};
	static int maxArea;					// 안전영역 최댓값(최종적으로 0인칸의 개수)
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());		// map의 세로길이
		M = Integer.parseInt(st.nextToken());		// map의 가로길이
		map = new int[N][M];
		blanks = new ArrayList<>();		
		virus = new ArrayList<>();	
		sWallNum = 3;			// 세울 벽의 개수는 3개
		maxArea = Integer.MIN_VALUE;	
		for(int i = 0 ; i < N ; i++) {		// map을 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) {		// 0이면
					blankNum++;				// 빈칸 개수를 증가하고 list에 추가
					blanks.add(new Point(i, j));
				}
				else if(map[i][j] == 2) {	// 2이면
					virusNum++;				// 바이러스 개수를 증가하고 list에 추가
					virus.add(new Point(i, j));
				}
			}
		}	
		selected = new boolean[blankNum];
		
		comb(0, 0);							// 조합 함수를 실행(3개의 벽 뽑기)
		System.out.println(maxArea);		// 결과를 출력
	}
	static void comb(int idx, int cnt) {	// 3개의 벽 뽑기
		if(cnt == sWallNum) {				// 다 뽑았다면
			int[][] simulMap = new int[N][];	// 시뮬레이션 돌릴 map을 생성(DFS로 변형하기 위함)
			for(int i = 0 ; i < N ; i++)
				simulMap[i] = map[i].clone();
			for(int i = 0 ; i < blankNum ; i++) {	// 선택된 벽이라면 simulMap에 3으로 표시
				if(selected[i]) simulMap[blanks.get(i).i][blanks.get(i).j] = 3;
			}
			dfs(simulMap);					// dfs 실행
			int area = findSafeArea(simulMap);	// 0인 영역을 구함
			if(area > maxArea) maxArea = area;	// 구한 안전구역이 maxArea보다 크다면 저장
			return;
		}
		if(idx == blankNum) return;			// 모든 빈칸에 대해 끝났다면 리턴
		selected[idx] = true;			// 해당 빈칸 포함
		comb(idx + 1, cnt + 1);
		selected[idx] = false;			// 해당 빈칸 미포함
		comb(idx + 1, cnt);
	}
	static void dfs(int[][] simulMap) {		// 깊이 우선 탐색(BFS로 해도 상관없음) - 바이러스 퍼뜨리기!
		Stack<Point> stack = new Stack<>();
		for(int i = 0 ; i < virusNum ; i++) {	// 바이러스 개수만큼 실행
			stack.add(virus.get(i));			// 스택에 바이러스를 넣음
			while(!stack.isEmpty()) {			// 스택에 아무것도 남아있지 않을 때까지 반복
				Point now = stack.pop();		// 해당 스택 요소를 저장
				for(int d = 0 ; d < 4 ; d++) {	// 상하좌우에 대해 탐색
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(ni >= 0 && ni < N && nj >= 0 && nj < M && simulMap[ni][nj] == 0) {	// 배열을 벗어나지 않고, 0이라면(바이러스가 퍼질 수 있는 칸)
						simulMap[ni][nj] = 1;	// 바이러스가 지나가므로 1로 만듬(다시 탐색하지 않기 위함)
						stack.add(new Point(ni, nj));	// 스택에 추가
					}
				}
			}
		}
	}
	static int findSafeArea(int[][] simulMap) {		// simulMap에서 0인 칸의 개수 반환
		int area = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++)
				if(simulMap[i][j] == 0) area++;
		}
		return area;
	}
	static class Point {		// i, j값을 저장하는 Point 클래스
		int i;
		int j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static void print(int[][] map) {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}	
	}
}
