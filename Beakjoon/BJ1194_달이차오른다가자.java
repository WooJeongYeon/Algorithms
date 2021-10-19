import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * Date : 210929
 * Level : Baekjoon Gold 1
 * Thinking)
 * 	1. 모든 출구를 가볼 필요 없음 -> 처음 간데가 가장 짧은길이
 * 	2. 열쇠는 하나만 있음 됨 + 여러개 있어도 상관없음
 * 	3. 가봤던 데는 다시 갈 수 있음
 * 	4. 문에 도착해도 없애면 안댐. 다음에 오는 애들이 문을 통과하려면 열쇠 있어야 함
 * 	5. 민식이 위치 따로 저장하고(출발지) 해당 칸을 빈곳으로 만들기
 * * visit 체크 -> 3차원 배열
 * * 열쇠 있는지 없는지를 비트 사용 - fedcba -> 열쇠 비트 순서 -> 0 ~ 63
 * Method : bfs로 ㄱㄱ. 3차원 배열에 현재 가지고 있는 열쇠들을 가지고 방문했었는지를 저장. 문에 도달하면 끝! 큐가 다 비었는데 안끝나면 불가능!
 * Error1 : 키 먹고 키 없애면 안됨. 나중에 다른 키 먹은 애가 이 키 가질러 방문할 수 있음
 */

public class BJ1194_달이차오른다가자 {
	static int N, M, ans;
	static Status start;
	static char[][] map;
	static boolean[][][] visit;						// 해당 칸에 특정 키들을 가지고 방문 했었는지
	static int[] di = { -1, 1, 0, 0 };
	static int[] dj = { 0, 0, -1, 1 };
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		visit = new boolean[N][M][64];				// 6개의 키의 부분집합이 2^6이므로 0 ~ 63에 대한 값을 저장
		
		for (int i = 0; i < N; i++) {	
			map[i] = in.readLine().toCharArray();
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == '0') {				// 민식이의 위치를 빈곳으로 바꾸고 따로 저장
					map[i][j] = '.';
					start = new Status(i, j, 0);
				}
			}
		}
		
		ans = bfs();								// bfs 시작
		
		System.out.println(ans);
	}
	
	static int bfs() {
		Queue<Status> queue = new LinkedList<>();
		queue.offer(start);							// 큐에 시작위치 넣음
		int move = 0;								// 이동횟수
		while(!queue.isEmpty()) {					// 큐가 빌때까지 반복
			int size = queue.size();				
			move++;									// 이동횟수 증가
			
//			System.out.println(move + " " + size);
			for(int s = 0 ; s < size ; s++) {		// 큐 사이즈마다 반복
				Status now = queue.poll();			// 검사할 status 큐에서 꺼냄
				for(int d = 0 ; d < 4 ; d++) {		// 상하좌우에 대해
					int ni = now.i + di[d];
					int nj = now.j + dj[d];
					int keys = now.haveKey;			// 해당 status의 가지고있는 키정보
					// 인덱스 벗어나거나, 현재 가지고 있는 키들로 이미 방문했었거나, 갈 수 없는 곳이면 패스
					if(isOutOfIndex(ni, nj) || visit[ni][nj][keys] || map[ni][nj] == '#') continue;
//					System.out.println(map[ni][nj] + " : " + ni + " " + nj + " " + keys);
					if(map[ni][nj] == '1') {		// 1이면 출구이므로 이동횟수 반환하고 끝
						return move;
					}
					else if(map[ni][nj] >= 'a' && map[ni][nj] <= 'f') {			// 열쇠면
						keys = keys | (1 << (map[ni][nj] - 'a'));				// keys에 열쇠 번호에 해당하는 비트를 1로 만들어줌
//						map[ni][nj] = '.';		이거땜시 틀림 - 키 없애면 안됨. 나중에 다른 키 먹은 애가 이 키 가질러 방문할 수 있음
						queue.offer(new Status(ni, nj, keys));					// 큐에 넣음
					}
					else if(map[ni][nj] >= 'A' && map[ni][nj] <= 'F') {			// 문이고
						if(((keys >> (map[ni][nj] - 'A')) & 1) == 1) {			// 해당 문에 맞는 열쇠가 있다면
//							System.out.println(ni + " " + nj + " " + keys + " " + move);	
							queue.offer(new Status(ni, nj, keys));				// 해당 위치도 큐에 넣음
						}
					}
					else {														// 빈곳이면
						queue.offer(new Status(ni, nj, keys));					// 큐에 넣음
					}
					visit[ni][nj][keys] = true;									// 현재 가지고있는 열쇠들로 해당 위치에 왔음을 표시
				}
			}
		}
		
		return -1;										// 가능한 모든 칸을 갔지만 출구를 못찾았다면 -1 반환
	}	
	
	static boolean isOutOfIndex(int i, int j) {			// 배열을 벗어나는지
		return i < 0 || i >= N || j < 0 || j >= M;
	}
	
	static class Status {								// 민식이의 상태를 저장(위치, 어떤 키를 가지고 있는지)
		int i, j, haveKey;

		public Status(int i, int j, int haveKey) {
			this.i = i;
			this.j = j;
			this.haveKey = haveKey;						// 비트로 사용(0 ~ 5비트만큼 -> 열쇠개수 6개)
		}
	}
}
