import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.06
 * Level : BaekJoon Gold 5
 * Difficulty : 중
 * URL : https://www.acmicpc.net/problem/14500
 * Select1 : 
 * Thinking : 
 * Method : 
 * Help : 
 * Error1 : 
 * Result : 
 * Plus1 : 
 */
public class BJ14500_테트로미노_DFS {
	static int N, M, ans;
	static int[][] board;
	static boolean[][] visit;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		visit = new boolean[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				visit[i][j] = true;
				dfs(i, j, 1, board[i][j]);
				visit[i][j] = false;
			}
		}
		
		System.out.println(ans);
	}
	
	static void dfs(int i, int j, int depth, int sum) {
		if(depth == 4) {
			ans = Math.max(ans, sum);
			return;
		}
		
		for(int d = 0 ; d < 4 ; d++) {
			int ni = i + di[d];
			int nj = j + dj[d];
			if(ni < 0 || ni >= N || nj < 0 || nj >= M || visit[ni][nj]) continue;
			visit[ni][nj] = true;
			if(depth == 2) dfs(i, j, depth + 1, sum + board[ni][nj]);
			dfs(ni, nj, depth + 1, sum + board[ni][nj]);
			visit[ni][nj] = false;
		}
		
	}
//	
//	static void print() {
//		for(int i = 0 ; i < N ; i++) {
//			for(int j = 0 ; j < M ; j++) {
//				System.out.print(visit[i][j] ? 1 + " " : 0 + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("-----------------------------------");
//	}
}
