import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 2022.04.30(仙)
 * Level : BaekJoon Gold 2
 * Difficulty : 掻雌
 * Time : 40m
 * Method : 姥薄
 * Error1 : blockDfs拭 dfs 硲窒敗
 * Error2 : 旭精 収切 蕉級晦軒幻 鷺系 莫失
 * Error3 : answer人 now税 段奄 info 竺舛
 * Error4 : 巷走鯵澗 嬢巨拭蟹 匂敗吃 呪 赤製
 * Error5 : break稽 巷繕闇 魁蟹惟 背兜製...
 * Error6 : 滴奄亜 亜舌 笛 鷺系 益血 達澗暗!せせせせせせせせ
 * Error7 : 奄層 鷺系 - 析鋼 鷺系.... 
 * Result : 森穿拭 葱惟 希 薗懐廃汽..? 煽 搾嘘馬澗 採歳 Comparable稽 背辞 砧鯵 舛慶獣佃辞 岩 姥梅革
 */

public class BJ21609_雌嬢掻俳嘘_仙 {
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
