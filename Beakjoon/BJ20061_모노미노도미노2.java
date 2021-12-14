import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ20061_모노미노도미노2{
	static int N, t, i, j;
	static int score, cnt;
	static int[][][] board;
	static final int GREEN = 0;
	static final int BLUE = 1;
	static final int CNT = 2;
	static final int ROW = 6;
	static final int COL = 4;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		board = new int[CNT][ROW][COL];
		
		for(int n = 0 ; n < N ; n++) {
			st = new StringTokenizer(in.readLine(), " ");
			t = Integer.parseInt(st.nextToken());
			i = Integer.parseInt(st.nextToken());
			j = Integer.parseInt(st.nextToken());
			game();
		}
		
		System.out.println(score);
		System.out.println(getCnt());
	}
	private static void game() {
		int[][] blocks = makeBlock();
		for(int n = 0 ; n < 2 ; n++) {
			blockDown(n, blocks);
			remove(n);
			specialRemove(n);
		}
	}
	private static void specialRemove(int n) {
		int cnt = 0;
		for(int i = 0 ; i < 2 ; i++) {
			for(int j = 0 ; j < COL ; j++) {
				if(board[n][i][j] == 1) {
					cnt++;
					break;
				}
			}
		}
		while(cnt-- > 0) {
			move(n, ROW - 1);
		}
	}
	private static void remove(int n) {
		for(int i = 0 ; i < ROW ; i++) {
			int sum = 0;
			for(int j = 0 ; j < COL ; j++) {
				if(board[n][i][j] == 1) sum++;
			}
			if(sum < 4) continue;
			score++;
			move(n, i);
		}
	}
	private static void move(int n, int idx) {
		for(int i = idx ; i >= 1 ; i--) {
			for(int j = 0 ; j < COL ; j++) {
				board[n][i][j] = board[n][i - 1][j]; 
			}
		}
		for(int j = 0 ; j < COL ; j++) {
			board[n][0][j] = 0;
		}
	}
	private static void blockDown(int n, int[][] blocks) {
		int idx = -1;
		if(blocks.length == 1) {
			for(int i = 0 ; i < ROW ; i++) {			// (밑에서부터 가능한 자리 찾기 -> 이럼 안됨.) 위애서부터 가능한 자리 찾기
				if(board[n][i][blocks[0][1 - n]] != 0) {
					idx = i - 1;
					break;
				}
			}
			if(idx == -1) idx = ROW - 1;
			board[n][idx][blocks[0][1 - n]] = 1;
		} 
		else if(blocks[0][n] == blocks[1][n]) {	// i가 같으면 -> 가로블록
			for(int i = 0 ; i < ROW ; i++) {			
				if(board[n][i][blocks[0][1 - n]] != 0
						|| board[n][i][blocks[0][1 - n] + 1] != 0) {
					idx = i - 1;
					break;
				}
			}
			if(idx == -1) idx = ROW - 1;
			board[n][idx][blocks[0][1 - n]] = 1;
			board[n][idx][blocks[0][1 - n] + 1] = 1;
		} else if(blocks[0][1 - n] == blocks[1][1 - n]) {	// j가 같으면 -> 세로블록
			for(int i = 0 ; i < ROW ; i++) {			
				if(board[n][i][blocks[0][1 - n]] != 0) {
					idx = i - 1;
					break;
				}
			}
			if(idx == -1) idx = ROW - 1;
			board[n][idx][blocks[0][1 - n]] = 1;
			board[n][idx - 1][blocks[0][1 - n]] = 1;
		}
	}
	private static int[][] makeBlock() {
		int[][] blocks = null;
		switch(t) {
		case 1 :
			blocks = new int[][]{{i, j}};
			break;
		case 2 :
			blocks = new int[][]{{i, j}, {i, j + 1}};
			break;
		case 3 :
			blocks = new int[][]{{i, j}, {i + 1, j}};
			break;
		}
		return blocks;
	}
	private static int getCnt() {
		int sum = 0;
		for(int n = 0 ; n < 2 ; n++) {
			for(int i = 0 ; i < ROW ; i++) {
				for(int j = 0 ; j < COL ; j++) {
					sum += board[n][i][j];
				}
			}
		}
		return sum;
	}
}