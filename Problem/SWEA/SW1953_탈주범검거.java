import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.09.30
 * Thinking : 대각선도 있는 줄 알았는데 아니네. 일반 bfs랑 같은데...? 이차원배열에 각 터널의 방향들 넣어줌. visit체크
 * Method : bfs돌려서 해당 칸의 터널들의 방향에 대해 탐색하고, 다음 위치가 갈 수 있는 방향이라면 ㄱㄱ 
 * Error1 : 왜 안되니... 나가는 방향과 들어가는 방향이 맞아야 함 ex) 상-하, 좌-우 -> 델타배열 순서 변경해줘서 인덱스 더했을 때 3되도록 함
 * Plus1 : tunnel을 문자열 배열로 만들면  bfs에서 돌릴 때 더 보기 쉽게 할 수 있네
 */
public class SW1953_탈주범검거 {
	static int TC, N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int ans;
	static int[] di = { -1, 0, 0, 1 };			// 상 좌 우 하 -> 상하, 좌우의 합이 각각 3이 되도록
	static int[] dj = { 0, -1, 1, 0 };
	static int[][] tunnel = {{}, {0, 1, 2, 3}, {0, 3}, {1, 2}, {0, 2}, {2, 3}, {1, 3}, {0, 1}};	// 터널 배열에 각 터널종류의 방향들을 저장해놓음

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(in.readLine());
		for(int tc = 1 ; tc <= TC ; tc++) {
			st = new StringTokenizer(in.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(in.readLine());
				for(int j = 0 ; j < M ; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			ans = bfs();								// 답을 반환
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		System.out.println(sb);
	}
	static int bfs() {									// bfs ㄱㄱ
		Queue<Point> queue = new LinkedList<>();
		queue.offer(new Point(R, C));					// 탈주범의 초기 위치를 넣음
		visited[R][C] = true;							// 지나갔음을 표시
		int time = 1;									// 처음시간 1
		int num = 1;									// 처음 위치포함하므로 장소 1
		
		while(!queue.isEmpty()) {						// 시간이 L이 되거나, 더이상 갈 수 없을 때까지 반복
			int size = queue.size();					// 각 깊이마다 실행되도록 size 저장
//			print();
			if(time == L) return num;					// 정해진 시간이 됬다면 장소 개수 반환
			time++;										// 시간 증가
			for(int i = 0 ; i < size ; i++) {			// size만큼 반복
				Point now = queue.poll();
				for(int d : tunnel[map[now.i][now.j]]) {		// 현재 위치의 터널의 방향들에 대해 반복
					int ni = now.i + di[d];						// 다음 위치
					int nj = now.j + dj[d];
					boolean isPossible = false;					// 다음 위치로 갈 수 있는지를 저장
					// 인덱스 벗어나거나, 이미 갔었거나, 갈 수 없는 곳이면 패스
					if(isOutOfIdx(ni, nj) || visited[ni][nj] || map[ni][nj] == 0) continue;
					for(int nextD : tunnel[map[ni][nj]]) {		// 다음 터널들의 방향을 가져와
						if(d + nextD == 3) {			// 두 방향이 '상하'거나 '좌우'인경우(인덱스의 합이 3)
							isPossible = true;			// 갈 수 있음
							break;
						}
					}
					if(isPossible) {					// 갈 수 있다면
						visited[ni][nj] = true;			// 현재 위치 지나감 표시하고
						num++;							// 장소 개수 증가
						queue.offer(new Point(ni, nj));	// 현재 위치를 큐에 넣음
					}
				}
			}
		}
		return num;
	}
	
	static boolean isOutOfIdx(int i, int j) {			// 인덱스 벗어나는지 반환
		return i < 0 || i >= N || j < 0 || j >= M;
	}
	
	static class Point {								// 해당 위치 인덱스 저장
		int i, j;

		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static void print() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    switch (map[i][j]) {
                    case 1:
                        System.out.print("┼ ");
                        break;
                    case 2:
                        System.out.print("│ ");
                        break;
                    case 3:
                        System.out.print("─ ");
                        break;
                    case 4:
                        System.out.print("└ ");
                        break;
                    case 5:
                        System.out.print("┌ ");
                        break;
                    case 6:
                        System.out.print("┐ ");
                        break;
                    case 7:
                        System.out.print("┘ ");
                        break;
                    default:
                        System.out.print("○ ");
                    }
                } else
                    System.out.print("X ");
            }
            System.out.println();
        }
    }
}
