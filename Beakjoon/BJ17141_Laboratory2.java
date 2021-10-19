package day0816;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ17141_Laboratory2 {
	static int N, virusNum;		// 맵의 크기, 바이러스 개수
	static int[][] map, simulMap;	// 맵, 시뮬돌릴 맵
	static ArrayList<Point> canVirus;	// 바이러스를 놓을 수 있는 칸(2인 칸)
	static int canVirusNum;				// 2인 칸의 개수
	static boolean[] selected;			// 바이러스를 놓으려고 선택했는지 여부
	static Point[] virus;				// 바이러스 놓을 곳을 저장
	static int[] di = {-1, 1, 0, 0};	// 델타 배열 - 위, 아래, 왼, 오
	static int[] dj = {0, 0, -1, 1};
	static int minTime, simulTime;		// 최소 시간, 바이러스가 선택됬을 때마다 구해지는 시간
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		virusNum = Integer.parseInt(st.nextToken());
		virus = new Point[virusNum];
		map = new int[N][N];
		simulMap = new int[N][];
		canVirus = new ArrayList<>();
		minTime = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i++) {		// 맵을 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 2) {		// 2인 경우, 바이러스를 놓을 수 있는 칸 증가, 해당 칸 추가
					canVirusNum++;
					canVirus.add(new Point(i, j));
				}
			}
		}	
		selected = new boolean[canVirusNum];
		
		comb(0, 0);		// 바이러스를 놓을 칸 선택
		if(minTime == Integer.MAX_VALUE) minTime = -1;	// minTime이 초기화 그대로라면, 바이러스가 모든 칸에 퍼지지는 못한 것이므로 -1 저장
		System.out.println(minTime);		// minTime 출력
	}
	static void comb(int idx, int cnt) {	
		if(cnt == virusNum) {	// virusNum만큼 바이러스가 선택된 경우
			for(int i = 0 ; i < N ; i++)	// simulMap을 생성
				simulMap[i] = map[i].clone();
			int index = 0;		
			for(int i = 0 ; i < canVirusNum ; i++) {	// 모든 바이러스가 가능한 칸에 대해 
				if(!selected[i]) simulMap[canVirus.get(i).i][canVirus.get(i).j] = 0;		// 해당 칸이 선택되지 않았다면 바이러스를 퍼뜨리기 위해 0으로 저장  
				else {		// 선택되었다면
					simulMap[canVirus.get(i).i][canVirus.get(i).j] = -1;	// -1을 저장해 표시
					virus[index++] = canVirus.get(i);	// 해당 칸을 저장
				}
			}
			simulTime = 0;		// 처음 시간은 0으로 저장
			bfs();		// bfs 실행
			if(isHaveZero()) return;	// 0인 칸이 있다면(바이러스가 퍼지지 않은 곳이 있다면) 리턴
			if(simulTime < minTime) minTime = simulTime;	// 더 작은 값으로 minTime을 갱신
			return;
			
		}
		if(idx == canVirusNum) return;		// 모든 바이러스 칸에 대해 포함/미포함을 결정했지만 바이러스를 모두 결정하지 못한경우 리턴 
		selected[idx] = true;		// 해당 바이러스 칸 포함
		comb(idx + 1, cnt + 1);
		selected[idx] = false;		// 해당 바이러스 칸 미포함
		comb(idx + 1, cnt);
	}
	
	static void bfs() {	// 너비 우선 탐색 - virusNum만큼의 바이러스들이 동시에 퍼지므로 FIFO 구조가 필요
		Queue<Point> queue = new LinkedList<Point>();
		int time = 0;
		boolean isNext = false;	// 다음에 queue에 추가할 칸이 있는지
		for(int i = 0 ; i < virusNum ; i++) {	// 모든 바이러스 칸을 queue에 추가
			queue.add(new Point(virus[i].i, virus[i].j, 0));	// 시간은 0부터 시작
		}
		while(!queue.isEmpty()) {		// 큐가 빌 때까지 반복
			Point now = queue.poll();	// 큐의 요소를 저장
			isNext = false;				
			time = now.time;			// now의 시간으로 저장
			for(int d = 0 ; d < 4 ; d++) {	// 위아래왼오 에 위치한 칸에 대해 검사
				int ni = now.i + di[d];
				int nj = now.j + dj[d];
				if(ni >= 0 && ni < N && nj >= 0 && nj < N && simulMap[ni][nj] == 0) {	// 맵을 벗어나지 않고, 해당 칸이 0이라면
					isNext = true;		// 추가할 칸이 있음을 표시
					simulMap[ni][nj] = time + 1;	// 시간을 저장
					queue.add(new Point(ni, nj, time + 1));	// 큐에 시간을 1 더해 저장
				}
			}
			if(!isNext && simulTime < time) {		// 추가할 칸이 없고(막다른 길까지 감), simulTime보다 크다면
				simulTime = time;					// 시간 갱신
			}
		}
	}
	static boolean isHaveZero() {		// simulMap에 0이 있는지 없는지를 반환
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
		public Point(int i, int j, int time) {	// bfs에서 시간도 저장하기 위해 time필드를 사용
			this.i = i;
			this.j = j;
			this.time = time;
		}
	}
	static void print(int[][] map) {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}	
	}
}
