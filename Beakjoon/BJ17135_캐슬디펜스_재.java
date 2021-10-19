import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.10
 * Level : BaekJoon 골드 4
 * Select1 : 선택한 적을 LinkedList에 저장함 
 * Select2 : 남은 적의 수를 매번 계산하는게 아니라 처음에 계산해두고 적이 죽으면 빼주는 식으로 함 - 매번 map 돌려서 구하기 싫어서. 시간줄이려고, 근데 매번 빼주는 것도 귀찮. 
 * Thinking) 
 * 		- 맵입력받을 때 초기 적의 수 구해놓음
 * 		- 사용하는 변수 다 static으로 만들어놓음
 * 		1. 궁수 선택 - mC3, boolean[] isArcher
 * 		2. 시뮬레이션(모든 적이 없을 때까지)
 * 			a) 각 궁수의 공격 - 첫번째로 나오는 적 선택
 * 			b) 선택한 적 없앰
 * 			c) 적 한칸씩 아래로 이동. N-2 ~ 0행에 대해 진행
 * Method : 시뮬레이션, 배열탐색, 조합, BFS
 * Error1 : 처음에 적 탐색하라고 주어지는 위치가 거리 1일때임
 * Error2 : map 맨 윗값이 계속 증식함 -> 옮길 때 맨 위는 0으로 해줘야 함
 * Result : 글로 코드말고 순서, 변수들, 주의사항 적어놓고 함 -> 코드짤 때 생각할 게 많았다
 */
public class BJ17135_캐슬디펜스_재 {
	static int N, M, D, enemyCnt, nowEnemyCnt, killCnt, ans;
	static int[][] map, simulMap;
	static boolean[] isArcher;
	static LinkedList<Point> enemys;
	static int[] di = {0, -1, 0};			// 왼, 위, 오른
	static int[] dj = {-1, 0, 1};
	static final int ARCHER_CNT = 3;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		isArcher = new boolean[M];
		enemys = new LinkedList<>();
		for(int i = 0 ; i < N ; i++) {							// 입력받음
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) enemyCnt++;					// 적의 수 세기
			}
		}
		
		if(enemyCnt > 0) chooseArcher(0, 0);					// 적의 수가 0 초과이면 시뮬 진행
		
		System.out.println(ans);
	}
	
	static void chooseArcher(int idx, int cnt) {				// 아처 3명 뽑기 -> 부분집합 이용
		if(cnt == ARCHER_CNT) {									// 아처 3명 뽑으면
			simul();											// 시뮬돌려
			
			ans = Math.max(ans, killCnt);						// 답과 죽인 적수와 비교해 갱신
			return;
		}
		if(idx == M) return;									// M번째까지 갔으면 리턴
		isArcher[idx] = true;									// 해당 idx의 아처 선택
		chooseArcher(idx + 1, cnt + 1);
		isArcher[idx] = false;									// 해당 idx의 아처 선택 안함
		chooseArcher(idx + 1, cnt);
		
	}
	
	static void simul() {
		simulMap = makeSimulMap();								// 시뮬 돌릴 맵 생성
		nowEnemyCnt = enemyCnt;									// 남은 적 수 저장할 변수
		killCnt = 0;											// 죽인 적 수
		while(nowEnemyCnt > 0) {								// 남은 적이 있을 때까지 반복
			for(int j = 0 ; j < M ; j++) {
				if(isArcher[j]) {								// 선택된 아처에 대해
					if(simulMap[N - 1][j] == 1) {				// 에러 - 검사 첫 위치에 적이 있으면 죽일 적에 추가
						enemys.add(new Point(N - 1, j));
					}
					else findEnemy(j);								// 아니면 적 찾으로 bfs ㄱㄱ
				}
			}
			removeEnemy();										// 선택된 적 죽이고
			move();												// 적 이동
//			print(simulMap);
		}
	}
	static void findEnemy(int startJ) {							// 공격할 적 찾기
		Queue<Point> queue = new LinkedList<>();
		boolean[][] visit = new boolean[N][M];					// 방문표시
		int dist = 1;
		queue.offer(new Point(N - 1, startJ));
		while(!queue.isEmpty()) {								// 큐가 빌 때까지 반복
			if(dist++ == D) break;								// 검사할 수 있는 거리만큼 다 봤다면 중단
			int size = queue.size();
			for(int s = 0 ; s < size ; s++) {
				Point now = queue.poll();
				for(int d = 0 ; d < 3 ; d++) {					// 3 방향에 대해(왼, 위, 오)
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					if(!isOutOfIdx(ni, nj) && !visit[ni][nj]) {		// 인덱스 안나가고, 방문하지 않았다면
						if(simulMap[ni][nj] == 1) {					// 적이 있다면 -> 첫번째로 찾은 적!
							enemys.add(new Point(ni, nj));			// 추가하고 끝내버림
							return;
						}
						visit[ni][nj] = true;						// 방문표시
						queue.offer(new Point(ni, nj));
					}
				}
			}
		}
	}
	
	static void removeEnemy() {							// 적 제거
		for(Point e : enemys) {							// 선택된 최대 3명의 적에 대해
			if(simulMap[e.i][e.j] == 1) {				// 중복된 적이 아니면
				simulMap[e.i][e.j] = 0;					// 없애고
				nowEnemyCnt--;							// 남은 적 수 감소
				killCnt++;								// 현재 죽인 적 수 증가
			}
		}
		enemys.clear(); 								// 적 리스트 비워줌(다음에 추가하기 위해)
	}
	
	static void move() {								// 적 아래로 한칸씩 이동
		for(int j = 0 ; j < M ; j++) {					// 맨 아래에 위치한 적은 죽으므로 현재 남은 적 수 갱신
			if(simulMap[N - 1][j] == 1) nowEnemyCnt--;
		}
		for(int i = N - 2 ; i >= 0 ; i--) {				// 맵 한칸 씩 이동
			for(int j = 0 ; j < M ; j++) {
				simulMap[i + 1][j] = simulMap[i][j];
			}
		}
		for(int j = 0 ; j < M ; j++) {					// 에러2 - map 맨 위의 적은 이동하므로 없애줌
			simulMap[0][j] = 0;
		}
	}
	
	static int[][] makeSimulMap() {						// 시뮬 맵 생성 후 반환해줌
		int[][] simulMap = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			simulMap[i] = map[i].clone();
		}
		return simulMap;
	}
	
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= M;
	}
	
//	static void print(int[][] simulMap) {
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < M ; j++) {
//				System.out.print(simulMap[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("---------------------------------------------");
//	}
	
	static class Point {
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
