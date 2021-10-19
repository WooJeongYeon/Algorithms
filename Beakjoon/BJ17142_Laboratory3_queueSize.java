package day0829;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210829
 */

public class BJ17142_Laboratory3_queueSize {
	static int N, virusNum;
	static int[][] map;
	static ArrayList<Point> virus;
	static boolean[] selected;
	static int[] di = {-1, 1, 0, 0};	// 위, 아래, 왼, 오
	static int[] dj = {0, 0, -1, 1};
	static int minTime, simulTime;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());			// 배열의 크기
		virusNum = Integer.parseInt(st.nextToken());	// 활성 바이러스 개수
		map = new int[N][N];
		virus = new ArrayList<>();						// 바이러스 저장
		minTime = Integer.MAX_VALUE;					// 결과
		for(int i = 0 ; i < N ; i++) {					// 맵을 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {					// 2인 경우, 바이러스 리스트에 추가
					virus.add(new Point(i, j));
					map[i][j] = -2;						// 가시적으로 보기 위해 -2 저장
				}
			}
		}	
		selected = new boolean[virus.size()];	
		
		comb(0, 0);										// 활성 바이러스로 설정할 바이러스 결정
		if(minTime == Integer.MAX_VALUE) minTime = -1;	// minTime이 초기화 그대로라면, 바이러스가 모든 칸에 퍼지지는 못한 것이므로 -1 저장
		System.out.println(minTime);					// minTime 출력
	}
	
	static void comb(int idx, int cnt) {				// 바이러스 virusNum개 선택!
		if(cnt == virusNum) {							// virusNum만큼 바이러스가 선택된 경우
			int[][] simulMap = copyMap();				// 시뮬 돌릴 맵 생성
			
			simulTime = bfs(simulMap);					// bfs 실행해 바이러스 퍼뜨리는 시간 저장
			minTime = Integer.min(minTime, simulTime); 	// 더 작은 값으로 minTime을 갱신
			return;
			
		}
		if(idx == virus.size()) return;					// 모든 바이러스 칸에 대해 포함/미포함을 결정했지만 바이러스를 모두 결정하지 못한경우 리턴 
		selected[idx] = true;							// 해당 바이러스 칸 포함
		comb(idx + 1, cnt + 1);
		selected[idx] = false;							// 해당 바이러스 칸 미포함
		comb(idx + 1, cnt);
	}
	
	static int bfs(int[][] simulMap) {					// 너비 우선 탐색 - virusNum만큼의 바이러스들이 동시에 퍼지므로 FIFO 구조가 필요
		Queue<Point> queue = new LinkedList<Point>();
		int simulTime = 0;
		int time = 0;
		for(int i = 0 ; i < virus.size() ; i++) {		// 선택된 활성 바이러스들을 큐에 추가
			if(selected[i]) {
				simulMap[virus.get(i).i][virus.get(i).j] = -1;
				queue.add(new Point(virus.get(i).i, virus.get(i).j));
			}
		}
		while(!queue.isEmpty()) {						// 큐가 빌 때까지 반복
			int size = queue.size();
			time++;
			for(int i = 0 ; i < size ; i++) {
				Point now = queue.poll();					// 큐의 요소를 저장
				if(!isHaveZero(simulMap)) {					// 모든 map에 바이러스가 퍼졌다면
					return simulTime;						// 시간 반환
				}
				for(int d = 0 ; d < 4 ; d++) {				// 위아래왼오 에 위치한 칸에 대해 검사
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(ni >= 0 && ni < N && nj >= 0 && nj < N && (simulMap[ni][nj] == 0 || simulMap[ni][nj] == -2)) {	// 맵을 벗어나지 않고, 해당 칸이 0이거나 비활성 바이러스라면
						simulMap[ni][nj] = time;	// 시간을 저장
						simulTime = Integer.max(simulTime, time);		// simulTime을 최댓값으로 갱신
						queue.add(new Point(ni, nj));
					}
				}
			}
		}
		return Integer.MAX_VALUE;						// 만약 모든 map에 바이러스가 퍼지지 않았다면 최대 Integer값 반환
	}
	
	static int[][] copyMap() {			// 시뮬돌릴 맵을 생성해 반환
		int[][] simulMap = new int[N][];
		for(int i = 0 ; i < N ; i++)	// simulMap을 생성
			simulMap[i] = map[i].clone();
		return simulMap;
	}
	
	static boolean isHaveZero(int[][] simulMap) {		// simulMap에 0이 있는지 없는지를 반환
		for(int i = 0 ; i < N ; i++)
			for(int j = 0 ; j < N ; j++)
				if(simulMap[i][j] == 0) return true;
		return false;
	}
	
	static class Point {		// i, j, time을 저장하는 Point 클래스
		int i;
		int j;
		int time;
		public Point(int i, int j) {		// time을 저장할 필요 없을 때
			this.i = i;
			this.j = j;
		}
	}
	
	static void print(int[][] map) {		// map 출력
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}	
		System.out.println();
	}
}
