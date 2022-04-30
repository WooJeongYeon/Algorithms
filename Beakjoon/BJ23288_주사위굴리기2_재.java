import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

/*
 * Date : 2022.04.30(재)
 * Level : BaekJoon Gold 3
 * Difficulty : 중하
 * Time : 40m
 * Thinking : 미리 점수 scoreMap에 다 구해놓으니까 2배 빨라짐 + 메모링 공간
 *  		- 은이 방법으로 하니까 편하네... 주사위 다음 위치 구할 수 있게
 * Method : 구현
 * Result : 해시로 해봤는데 괜찮네ㅎㅎㅎ
 * Plus1 : 생각해보니까 i와 j 모두 작은 애들부터 시작하니까 
 *		- likeCnt, emptyCne같을 때 이전에 나온 애만 저장하면 되자너(안바뀜)
 *		- 행이 다르다 -> 이전에 탐색한 애
 * 		- 행이 같다 -> 열이 다르다 -> 이전에 탐색한 애
 */
public class BJ23288_주사위굴리기2_재 {
	static int N, M, K, nowI, nowJ, d, ans, cnt;
	static int[][] map, scoreMap;
	static boolean[][] visited;
	static LinkedList<int[]> list;
	static int[] di = {0, 1, 0, -1}; // 동남서북
	static int[] dj = {1, 0, -1, 0};
	static int[] dice = {2, 1, 5, 6, 4, 3};
	static int[][] nextDicePos = {{0, 4, 2, 5, 3, 1},
								{3, 0, 1, 2, 4, 5},
								{0, 5, 2, 4, 1, 3},
								{1, 2, 3, 0, 4, 5}};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		scoreMap = new int[N][M];
		list = new LinkedList<>();
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		setScoreMap();
		for(int k = 0 ; k < K ; k++) {
			moveDice();
			ans += scoreMap[nowI][nowJ];
			if(dice[3] > map[nowI][nowJ]) d = (d + 1) % 4;
			else if(dice[3] < map[nowI][nowJ]) d = (d + 3) % 4;
		}
		System.out.println(ans);
	}
	
	private static void moveDice() {
		int nextI = nowI + di[d];
		int nextJ = nowJ + dj[d];
		if(nextI < 0 || nextI >= N || nextJ < 0 || nextJ >= M) {
			d = (d + 2) % 4;
			nextI = nowI + di[d];
			nextJ = nowJ + dj[d]; 
		}
		nowI = nextI; nowJ = nextJ;
		int[] newDice = new int[6];
		for(int i = 0 ; i < 6 ; i++) {
			newDice[i] = dice[nextDicePos[d][i]];
		}
		dice = newDice;
	}

	static void setScoreMap() {
		visited = new boolean[N][M];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(visited[i][j]) continue;
				cnt = 0;
				list.clear();
				dfs(i, j);
				int sum = cnt * map[i][j];
				for(int[] pos : list) {
					scoreMap[pos[0]][pos[1]] = sum;
				}
			}
		}
	}
	static void dfs(int i, int j) {
		visited[i][j] = true;
		cnt++;
		list.offer(new int[] {i, j});
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= M || visited[ni][nj] || map[i][j] != map[ni][nj])
				continue;
			dfs(ni, nj);
		}
	}
}
