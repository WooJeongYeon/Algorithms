import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 2022.04.30(재)
 * Level : BaekJoon Gold 2
 * Difficulty : 중상
 * Time : 2h
 * Method : 구현
 * Error1 : blockDfs에 dfs 호출함
 * Error2 : 같은 숫자 애들끼리만 블록 형성
 * Error3 : answer와 now의 초기 info 설정
 * Error4 : 무지개는 어디에나 포함될 수 있음
 * Error5 : break로 무조건 끝나게 해놓음...
 * Error6 : 크기가 가장 큰 블록 그룹 찾는거!ㅋㅋㅋㅋㅋㅋㅋㅋ
 * Error7 : 기준 블록 - 일반 블록.... 
 * Result : 예전에 푼게 더 깔끔한데..? 저 비교하는 부분 Comparable로 해서 두개 정렬시켜서 답 구했네
 */

public class BJ21609_상어중학교_재 {
	static int N, M, ans;
	static int[][] map;
	static boolean[][] allVisited, visited;
	static GroupInfo nowInfo, answerInfo;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 0 ; j < N ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			if(!findBigGroup()) break;
			deleteBlockGroup();
			gravity();
			rotate();
			gravity();
		}
		System.out.println(ans);
	}
	private static boolean findBigGroup() {
		answerInfo = new GroupInfo(-1, -1, -1, -1);
		
		allVisited = new boolean[N][N];
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(allVisited[i][j] || map[i][j] <= 0) continue;
				nowInfo = new GroupInfo(0, 0, i, j);
				visited = new boolean[N][N];
				blockDfs(i, j, map[i][j]);
				
				if(nowInfo.cnt - nowInfo.rainbowCnt == 0 || nowInfo.cnt < 2) continue;
				if(answerInfo.cnt < nowInfo.cnt)
					answerInfo = nowInfo;
				else if(answerInfo.cnt == nowInfo.cnt) {
					if(answerInfo.rainbowCnt < nowInfo.rainbowCnt)
						answerInfo = nowInfo;
					else if(answerInfo.rainbowCnt == nowInfo.rainbowCnt) {
						if(answerInfo.i < nowInfo.i)
							answerInfo = nowInfo;
						else if(answerInfo.i == nowInfo.i) {
							if(answerInfo.j < nowInfo.j)
								answerInfo = nowInfo;
						}
					}
				}
				
			}
		}
		
		if(answerInfo.cnt == -1) return false;
		return true;
	}
	private static void blockDfs(int i, int j, int n) {
		allVisited[i][j] = true;
		visited[i][j] = true;
		nowInfo.cnt++;
		if(map[i][j] == 0) nowInfo.rainbowCnt++;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= N || visited[ni][nj] || (allVisited[ni][nj] && map[ni][nj] != 0) || (map[ni][nj] != 0 && map[ni][nj] != n)) 
				continue;
			blockDfs(ni, nj, n);
		}
	}
	private static void deleteBlockGroup() {
		ans += answerInfo.cnt * answerInfo.cnt;
		dfs(answerInfo.i, answerInfo.j, map[answerInfo.i][answerInfo.j]);
		
	}
	private static void dfs(int i, int j, int n) {
		map[i][j] = -2;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= N || (map[ni][nj] != 0 && map[ni][nj] != n)) 
				continue;
			dfs(ni, nj, n);	
		}
	}
	private static void gravity() {
		for(int j = 0 ; j < N ; j++) {
			int idx = N - 1;
			for(int i = N - 1 ; i >= 0 ; i--) {
				if(map[i][j] == -1) {
					for(int k = idx ; k > i ; k--) {
						map[k][j] = -2; 
					}
					idx = i - 1;
				}
				else if(map[i][j] != -2) map[idx--][j] = map[i][j]; 
			}
			for(int i = idx ; i >= 0 ; i--) {
				map[i][j] = -2;
			}
		}
	}
	
	private static void rotate() {
		int[][] copyMap = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				copyMap[N - 1 - j][i] = map[i][j];
			}
		}
		map = copyMap;
	}
	
	static class GroupInfo {
		int rainbowCnt, cnt, i, j;

		public GroupInfo(int rainbowCnt, int cnt, int i, int j) {
			this.rainbowCnt = rainbowCnt;
			this.cnt = cnt;
			this.i = i;
			this.j = j;
		}
	}
}
