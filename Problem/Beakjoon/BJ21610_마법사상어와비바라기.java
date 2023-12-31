import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.09
 * Level : BaekJoon 골드 2
 * Select1 : 구름 생성할 때 이전 구름들을 빼기 위해 모든 map 요소 각각과 리스트의 clouds를 매번 비교해줄지 
 * 			VS visit배열에 체크해놓고 인덱스로 확인할건지(시간줄이려고 이걸로 선택)  
 * Thinking) 그냥 순서대로 진행
 * 		- 첫행 마지막행, 첫열 마지막열이 이어져 있으므로 구름 이동 시 겹칠 일이 없음
 * 		- 대각선 방향은 인덱스가 홀수가 되도록
 * 		1. 구름이동, 비내림 - 방향 오른쪽인 경우는 더하고 %N, 왼쪽인 경우는 마이너스이므로 플러스 되도록 더해주고 %N
 * 		2. 물복사
 * 		3. 구름생성
 * Method : 시뮬레이션, 배열탐색
 * Error1 : 구름 이동 시 방향이 왼쪽일 경우, -되는데 이동거리가 1 ~ 50값이 들어올 수 있으므로 왼쪽으로 가는게 map을 몇바퀴 돌 수 있음 -> 큰 값을 +해주고 %N함 
 * Result : 다 미리 코드 써놓고 해서 그런지 괜찮았음
 */
public class BJ21610_마법사상어와비바라기 {
	static int N, M, d, s, ans;
	static int[][] map;
	static boolean[][] visit;
	static LinkedList<Point> clouds;							// 구름정보 저장
	static int[] di = {0, -1, -1, -1, 0, 1, 1, 1};				// 좌, 좌위, 위, 오른위, 오른, 아래오른, 아래, 아래왼
	static int[] dj = {-1, -1, 0, 1, 1, 1, 0, -1};				// 왼쪽부터 시계방향
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());					// map 크기
		M = Integer.parseInt(st.nextToken());					// 마법 횟수
		map = new int[N][N];
		clouds = new LinkedList<>();							// 초기 구름 4개 추가함			
		clouds.add(new Point(N - 2, 0));	
		clouds.add(new Point(N - 2, 1));
		clouds.add(new Point(N - 1, 0));
		clouds.add(new Point(N - 1, 1));
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			d = Integer.parseInt(st.nextToken()) - 1;			// 방향
			s = Integer.parseInt(st.nextToken());				// 이동거리
			visit = new boolean[N][N];							// 해당 칸에 구름이 있었는지를 저장
			moveAndRain();
			copyWater();
			makeClouds();
		}
		
		ans = getAmount();
		System.out.println(ans);
	}
	static void moveAndRain() {									// 이동 및 비내리기
		for(Point c : clouds) {									// 각 구름을 이동시킴
			int ni = c.i + di[d] * s;
			int nj = c.j + dj[d] * s;
			c.i = ni >= N ? ni % N : (ni + N * 50) % N;			// N을 넘어가면 % N
			c.j = nj >= N ? nj % N : (nj + N * 50) % N;			// 마이너스이면 큰값인 N*50을 더한 후 나머지연산
			map[c.i][c.j]++;									// 해당 구름 위치에 물 1씩 증가
		}
	}
	static void copyWater() {									// 물복사
		for(Point c : clouds) {									// 각 구름에 대해
			int sum = 0;
			for(int d = 1 ; d < 8 ; d += 2) {					// 대각선 방향으로 탐색해
				int ni = c.i + di[d];
				int nj = c.j + dj[d];
				if(!isOutOfIdx(ni, nj) && map[ni][nj] >= 1) {	// 인덱스가 나가지 않고, 물의 양이 1 이상이면
					sum++;										// 개수 증가
				}
			}
			map[c.i][c.j] += sum;								// 대각선 방향에 있는 물의 양이 1이상인 개수를 더함
			visit[c.i][c.j] = true; 							// 해당 위치가 구름이 있었음을 표시
		}
		clouds.clear();											// 새로운 구름을 추가하기 위해 리스트 비움
	}
	static void makeClouds() {									// 구름 생성
		for(int i = 0 ; i < N ; i++) {							// 모든 map에 대해
			for(int j = 0 ; j < N ; j++) {
				if(!visit[i][j] && map[i][j] >= 2) {			// 구름 생성되지 않은 칸이고, 물이 2 이상이면
					map[i][j] -= 2;								// 물을 2만큼 감소시키고
					clouds.add(new Point(i, j));				// 구름을 추가함
				}
			}
		}
	}
	static int getAmount() {								// 물의 양 반환
		int sum = 0;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				sum += map[i][j];
			}
		}
		return sum;
	}
	static boolean isOutOfIdx(int i, int j) {				// 인덱스 나가는지
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
}
