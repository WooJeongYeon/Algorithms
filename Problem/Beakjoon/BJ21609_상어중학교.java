import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ21609_상어중학교 {
	static int N, M, ans;
	static BlockGroup maxGroup, nowGroup;
	static int[][] board;
	static boolean[][] totalVisited, visited;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < N ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		autoPlay();
		System.out.println(ans);
	}
	private static void autoPlay() {
		while(true) {
			maxGroup = new BlockGroup(0, 0, -1, -1);
			findBlock();
			if(maxGroup.blockCnt == 0) break;
			ans += maxGroup.blockCnt * maxGroup.blockCnt;
			visited = new boolean[N][N];
			deleteBlock(board[maxGroup.stI][maxGroup.stJ], maxGroup.stI, maxGroup.stJ);
			gravity();
			rotateLeft();
			gravity();
		}
	}
	private static void findBlock() {
		totalVisited = new boolean[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				if(board[i][j] <= 0) continue;
				if(totalVisited[i][j]) continue;
				nowGroup = new BlockGroup(0, 0, i, j);
				boolean isPossible = false;
				for(int d = 0 ; d < 4 ; d++) {
					int ni = i + di[d];
					int nj = j + dj[d];
					if(!isOutOfIdx(ni, nj) && (board[ni][nj] == 0 || board[ni][nj] == board[i][j])) {
						isPossible = true;
						break;
					}
				}
				if(isPossible) {
					visited = new boolean[N][N];
					dfs(board[i][j], i, j);
					BlockGroup[] groups = new BlockGroup[]{maxGroup, nowGroup};
					Arrays.sort(groups);
					maxGroup = groups[1];
				}
			}
		}
	}
	private static void dfs(int num, int i, int j) {
		visited[i][j] = true;
		totalVisited[i][j] = true;
		nowGroup.blockCnt++;
		if(board[i][j] == 0) nowGroup.rCnt++;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(!isOutOfIdx(ni, nj) && !visited[ni][nj] && (board[ni][nj] == 0 || num == board[ni][nj])) {
				dfs(num, ni, nj);
			}
		}
	}
	private static void deleteBlock(int num, int i, int j) {
		visited[i][j] = true;
		board[i][j] = -2;
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(!isOutOfIdx(ni, nj) && !visited[ni][nj] && (board[ni][nj] == 0 || num == board[ni][nj])) {
				deleteBlock(num, ni, nj);		// 2) 여기 잘못
			}
		}
	}
	static boolean isOutOfIdx(int i, int j) {
		return i < 0 || i >= N || j < 0 || j >= N;
	}
	private static void rotateLeft() {
		int[][] newBoard = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				newBoard[N - j - 1][i ] = board[i][j];
			}
		}
		board = newBoard;
	}
	private static void gravity() {
		int[][] newBoard = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(newBoard[i], -2);
		}
		for(int j = 0 ; j < N ; j++) {
			int idx = N - 1;
			for(int i = N - 1 ; i >= 0 ; i--) {
				if(board[i][j] == -1) {
					newBoard[i][j] = -1;
					idx = i - 1;
				}
				else if(board[i][j] == -2) continue; 	// 3) 문제
				else newBoard[idx--][j] = board[i][j];
			}
		}
		board = newBoard;
	}
	static class BlockGroup implements Comparable<BlockGroup>{
		int blockCnt, rCnt, stI, stJ;
		public BlockGroup(int blockCnt, int rCnt, int stI, int stJ) {
			this.blockCnt = blockCnt;
			this.rCnt = rCnt;
			this.stI = stI;
			this.stJ = stJ;
		}
		@Override
		public int compareTo(BlockGroup o) {
			if(this.blockCnt == o.blockCnt) {
				if(this.rCnt == o.rCnt) {
					if(this.stI == o.stI) {
						return this.stJ - o.stJ;
					}
					return this.stI - o.stI;
				}
				return this.rCnt - o.rCnt;
			}
			return this.blockCnt - o.blockCnt;
		}
		
	}
}
