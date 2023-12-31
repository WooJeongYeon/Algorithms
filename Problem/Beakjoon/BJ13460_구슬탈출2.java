import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : ~2021.12.15
 * Level : BaekJoon Gold 1
 * Difficulty : 상
 * URL : https://www.acmicpc.net/problem/13460
 * Select1 : 더 먼저 옮길 애를 선택해 옮기기 VS 둘다 옮길 수 있을 때까지 옮기기(선택)
 * Thinking : 4방향으로 깊이 10만큼 dfs 돌려서 가능한 min값 찾기
 * Method : DFS
 * Error1 : ...(기억이...)
 * Error2 : 다른 공이 골에 들어가도 위치는 골위치라 지금 공이 골로 갈 수 있어도 들어가지를 못함 -> 조건추가
 * Error3 : (반례테케 질문에서 찾아서 돌림)blue골 들어갔을 때, 위치 원상복귀 안해줌ㅠㅠㅠㅠ 엄청고생했네
 * Result : 하면서 계속 조건추가해주느라 놓친 부분이 많았나보다.. 너무 어려워ㅠㅠㅠ
 * 			- 안되서 포기하다가 이번에 다시 도움받아서 보면서 디버깅했다.
 * 			- 코드 반복문 덕지덕지 붙이지 말고 이쁘게 짜도록 생각해보자ㅠㅠ
 * Plus1 : 담에 더 먼저 옮길 애 선택해서 옮기자.. 이게 더 조건줄게 없네ㅠ
 */
public class BJ13460_구슬탈출2 {
	static int N, M, ans;
	static char[][] map;
	static Point[] pos;
	static int[] di = {-1, 1, 0, 0};	// 상하좌우
	static int[] dj = {0, 0, -1, 1};
	static final int RED = 0;
	static final int BLUE = 1;
	static final int MAX_MOVE = 10;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][];
		pos = new Point[2];
		ans = Integer.MAX_VALUE;
		for(int i = 0 ; i < N ; i++) {
			map[i] = in.readLine().toCharArray();
			for(int j = 0 ; j < M ; j++) {
				if(map[i][j] == 'R') {
					pos[RED] = new Point(i, j);
					map[i][j] = '.';
				} else if(map[i][j] == 'B') {
					pos[BLUE] = new Point(i, j);
					map[i][j] = '.';
				}
			}
		}
		moveMarble(1);
		if(ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
	}
	private static void moveMarble(int depth) {
		if(depth == MAX_MOVE + 1) return;
		Point[] lastPos = copyPos(pos);
		for(int d = 0 ; d < 4 ; d++) {
			boolean[] goals = new boolean[2];
			
			while(true) {
				Point[] nextPos = new Point[2];
				nextPos[RED] = new Point(pos[RED].i + di[d], pos[RED].j + dj[d]);
				nextPos[BLUE] = new Point(pos[BLUE].i + di[d], pos[BLUE].j + dj[d]);
				if((goals[RED] || (map[nextPos[RED].i][nextPos[RED].j] == '#') 
						|| (!goals[BLUE] && nextPos[RED].i == pos[BLUE].i && nextPos[RED].j == pos[BLUE].j)) 
					&& (goals[BLUE] || (map[nextPos[BLUE].i][nextPos[BLUE].j] == '#')
						|| (!goals[RED] && nextPos[BLUE].i == pos[RED].i && nextPos[BLUE] .j == pos[RED].j))) break;
				for(int n = 0 ; n < 2 ; n++) {		// 다음위치가 유효한지, 골인지를 체크 후 현재위치 갱신
					if(!goals[n]) {
						if(map[nextPos[n].i][nextPos[n].j] == 'O') {
							pos[n] =  nextPos[n];
							goals[n] = true;
						}
						else if(map[nextPos[n].i][nextPos[n].j] == '.' 
								&& (nextPos[n].i != pos[1 - n].i || nextPos[n].j != pos[1 - n].j)) {
							pos[n] = nextPos[n];
						}
					}
				}
			}
			if(goals[BLUE]) {
				pos = copyPos(lastPos);		// 이거 안넣어줘서 틀렸다 뜬거였다..ㅠㅠㅠ
				continue;					
			}
			else if(goals[RED] && !goals[BLUE]) {
				ans = Math.min(ans, depth);
				continue;
			} else	{// 둘다 골이 아니면 ㄱㄱ
				moveMarble(depth + 1);
			}
			pos = copyPos(lastPos);	// pos 원상복귀
		}
		
	}
	static Point[] copyPos(Point[] last) {
		Point[] now = new Point[2];
		now[RED] = new Point(last[RED].i, last[RED].j);
		now[BLUE] = new Point(last[BLUE].i, last[BLUE].j);
		return now;
	}
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	 private static void print(int depth) {
	        char r = map[pos[RED].i][pos[RED].j];
	        char b = map[pos[BLUE].i][pos[BLUE].j];
	        map[pos[RED].i][pos[RED].j] = 'R';
	        map[pos[BLUE].i][pos[BLUE].j] = 'B';

	        System.out.println("깊이 : " + depth);
	        for (int i = 0; i < map.length; i++) {
	            System.out.println(map[i]);
	        }
	        System.out.println();
	        map[pos[RED].i][pos[RED].j] = r;
	        map[pos[BLUE].i][pos[BLUE].j] = b;
	    }
}

