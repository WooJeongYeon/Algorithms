package day0825;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 210825
 */
public class BJ16236_BabyShark {
	static int N;						// map의 크기
	static int[][] map;				
	static int ans;						// 상어가 몇 초동안 물고기를 먹을 수 있는지 저장
	static int[] di = {-1, 1, 0, 0};	// 델타배열 - 위, 아래, 왼, 오
	static int[] dj = {0, 0, -1, 1};
	static Shark shark;					// 상어
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {	// map을 저장
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) shark = new Shark(i, j, 2);	// 9라면 상어 객체 생성(크기는 2)
			}
		}
		while(true) {			// 물고기를 먹지 못할 때 까지 반복
			if(!findFish()) break;
		}
		System.out.println(ans);
	}
	
	static boolean findFish() {
		int[][] simulMap = copyMap();					// 시뮬 돌릴 맵
		Queue<Point> queue = new LinkedList<>();		// 물고기의 이동시간을 체크해야하므로 너비를 알기 위해 queue 사용
		ArrayList<Point> list = new ArrayList<>();		// 이동시간이 같은 물고기들의 위치 저장
		int upI = N, leftJ = N;							// 시간이 같은 물고기들 중 가장 위인 인덱스, 가장 왼쪽인 인덱스
		boolean find = false;							// 물고기를 찾았음을 표시
		int time = -1;									// 시간 저장
		Point next = null;								// 먹을 물고기 위치(상어의 다음 위치)
		
		queue.add(shark);								// 상어를 기준으로 BFS 시작!
		while(!queue.isEmpty()) {						// queue가 빌 때까지 반복 -> 큐가 비면 먹을 수 있는 물고기가 없는 것
			int size = queue.size();					
			time++;										// 시간 증가
			for(int i = 0 ; i < size ; i++) {			// 해당 너비의 위치들에 대해 반복
				Point now = queue.poll();				// 이번에 검사할 위치
				//System.out.println(now.i + " " + now.j + " " + simulMap[now.i][now.j]);
				// 물고기를 찾았다면 이번 너비에서 먹을 수 있는 물고기들을 모두 list에 추가(그 이후 너비에 대해서는 검사할 필요 없음)
				if(map[now.i][now.j] != 0 && map[now.i][now.j] < shark.size) {	// 만약 빈칸이 아니고, 상어가 먹을 수 있는 물고기라면!
					upI = Integer.min(upI, now.i);								// 가장 위의 인덱스(i) 저장
					list.add(now);												// 리스트에 물고기 추가
					find = true;												// 찾았음을 표시함
				}
				if(!find) {			// 물고기를 발견하지 못했다면 계속 물고기를 찾기 위해 가능한 주변 위치들 큐에 추가해줌
					for(int d = 0 ; d < 4 ; d++) {		// 위, 아래, 왼쪽, 오른쪽을 검사
						int ni = now.i + di[d];
						int nj = now.j + dj[d];
						// 맵을 벗어나지 않고, 상어가 지나갈 수 있는 위치라면
						if(ni >= 0 && ni < N && nj >= 0 && nj < N && simulMap[ni][nj] <= shark.size) {
							simulMap[ni][nj] = 100;		// 다음에 지나가지 않기 위해 지나갔음을 표시
							queue.offer(new Point(ni, nj));		// 해당 요소를 큐에 넣음
						}
					}
				}
			}
			if(find) break;			// 찾았다면 다음 너비에 대한 탐색을 할 필요 없으므로 반복문 중단
		}
		
		if(!find) return find;		// 찾지 못했다면 false 반환
		
		next = list.get(0);
		if(list.size() > 1) {		// 만약 찾은 물고기가 여럿이라면
			for(Point p : list) {
				if(p.i == upI && p.j < leftJ) {		// 가장 위의 물고기를 찾고, 여러마리면 가장 왼쪽의 물고기가 저장되도록 함
					leftJ = p.j;
					next = p;
				}
			}
		}
		
		ans += time;				// 물고기를 먹을 때까지의 시간을 더함
		shark.eat();				// 상어가 물고기를 먹었다..!	
		map[shark.i][shark.j] = 0;	// 상어의 이전 위치를 0으로 저장
		shark.i = next.i; shark.j = next.j;	// 상어의 위치를 물고기의 위치로 갱신
		map[shark.i][shark.j] = 101;	// 맵에 상어를 표시함(상어는 대략 20정도까지 커질 수 있으므로 큰 값 저장)
		
		return find;				// 찾았음을 반환
	}
	
	static int[][] copyMap() {	// 시뮬돌리기 위해 맵을 복사해서 반환
		int[][] simulMap = new int[N][];
		for(int i = 0 ; i < N ; i++) {
			simulMap[i] = map[i].clone();
		}
		return simulMap;
	}
	
	static class Point {		// i, j 인덱스를 저장하는 Point 클래스
		int i, j;
		public Point(int i, int j) {
			super();
			this.i = i;
			this.j = j;
		}
	}
	static class Shark extends Point {	// Point를 상속받는 Shark 클래스
		int size, fish;					// 크기, 먹은 물고기수

		public Shark(int i, int j, int size) {
			super(i, j);
			this.size = size;
			this.fish = 0;				// 물고기수는 처음에는 0
		}
		void eat() {					// 물고기를 먹으면
			if(++fish == size) {		// 먹은 물고기수를 증가하고 그 값이 크기와 같다면
				size++;					// 크기 증가
				fish = 0;				// 물고기수는 0으로
			}
		}
	}
}
