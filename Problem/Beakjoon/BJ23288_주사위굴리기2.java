import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2022.01.31
 * Level : BaekJoon Gold 3
 * Difficulty : 중
 * Why : 주사위 굴릴때 면 바꿔주는 것만 헷갈렸음
 * Time : 1h
 * URL : https://www.acmicpc.net/problem/23288
 * Thinking - K번 반복
 * 		1. 이동 방향으로 감. 칸이 없으면 이동 방향 반대로 감
 * 		2. 도착한 칸에 대해 BFS 탐색으로 같은 해당 칸의 수 * 개수만큼 점수 더함
 * 		3. 주사위 아랫면 정수 A와 해당 칸의 정수 B를 비교해 이동방향 결정 -> 주사위 회전
 * Method : BFS, Dice 객체
 * Error1 : BFS에서 !isEmpty()일 때 돌아가도록
 * Error2 : 처음 시작을 visited 처리 안해줌
 * Result : 어려운거 풀고 푸니까 비교적 쉽네. 객체 뭔가 생각해서 만들었다.
 * Plus1 : 주사위 면 6개로 노가다로 옮겨줄 수 있네
 */
public class BJ23288_주사위굴리기2 {
	static int N, M, K, ans;
	static int[][] map;
	static int[] di = {0, 1, 0, -1};	// 동남서북
	static int[] dj = {1, 0, -1, 0};
	static Dice dice;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dice = new Dice();
		for(int k = 0 ; k < K ; k++) {
			dice.roll(N, M);
			getScore();
			dice.changeDirection();
		}
		System.out.println(ans);
	}
	private static void getScore() {
		int n = map[dice.i][dice.j];
		int cnt = 0;
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] visited = new boolean[N][M];
		queue.offer(new int[] {dice.i, dice.j});
		visited[dice.i][dice.j] = true;	//2. 추가
		while(!queue.isEmpty()) {	// 1. ! 달아줌
			int[] pos = queue.poll();
			cnt++;
			for(int d = 0 ; d < 4 ; d++) {
				int ni = pos[0] + di[d];
				int nj = pos[1] + dj[d];
				if(ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj] || map[ni][nj] != n) continue;
				visited[ni][nj] = true;
				queue.offer(new int[] {ni, nj});
			}
		}
		ans += n * cnt;
	}
	static class Dice {
		final int HORIZON = 0;
		final int VERTICAL = 1;
		int[][] planeArr = {{1, 3, 6, 4}, {1, 5, 6, 2}};
		int i = 0, j = 0, d = 0;
		void roll(int N, int M) {		// 주사위 회전
			int ni = i + di[d];
			int nj = j + dj[d];
			int rotateIdx = -1, num = -1;
			if(ni < 0 || ni >= N || nj < 0 || nj >= M) {
				d = (d + 2) % 4;
			}
			i += di[d];
			j += dj[d];
			switch(d) {
			case 0:
				rotateIdx = HORIZON;
				num = -1;
				break;
			case 1:
				rotateIdx = VERTICAL;
				num = -1;
				break;
			case 2:
				rotateIdx = HORIZON;
				num = 1;
				break;
			case 3:
				rotateIdx = VERTICAL;
				num = 1;
				break;
			}
			int tmp = planeArr[rotateIdx][0];
			int idx = 0;
			for(int i = 0 ; i < 3 ; i++) {			// 해당 면들 회전
				planeArr[rotateIdx][idx] = planeArr[rotateIdx][(idx + num + 4) % 4];
				idx = (idx + num + 4) % 4;
			}
			planeArr[rotateIdx][idx] = tmp;			// 윗면, 아랫면 똑같이 맞춰줌
			planeArr[1 - rotateIdx][0] = planeArr[rotateIdx][0]; 
			planeArr[1 - rotateIdx][2] = planeArr[rotateIdx][2]; 
		}
		void changeDirection() {					// 방향 바꾸기
			int n = map[i][j];
			if(getFloorNum() > n) d = (d + 1) % 4;
			else if(getFloorNum() < n) d = (d - 1 + 4) % 4;
		}
		int getFloorNum() {
			return planeArr[0][2];
		}
	}
}
