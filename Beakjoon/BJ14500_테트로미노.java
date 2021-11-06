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
public class BJ14500_테트로미노 {
	static int N, M, ans;
	static int[][] board;
	static int[][][] tetrominos = { {{1, 1, 1, 1}}, 
									{{1, 1}, {1, 1}}, 
									{{1, 0}, {1, 0}, {1, 1}}, 
									{{1, 0}, {1, 1}, {0, 1}}, 
									{{1, 1, 1}, {0, 1, 0}} };
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int t = 0 ; t < tetrominos.length ; t++) {
			int[][] block = tetrominos[t];
			for(int m = 0 ; m < 4 ; m++) {
				block = rotate(block);
				for(int n = 0 ; n < 2 ; n++) {
					block = reverse(block);
//					print(block);
					masking(block);		// 이거 추가안함. block이랑 board랑 헷갈림
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void masking(int[][] block) {
		for(int i = 0 ; i <= N - block.length ; i++) {	// 범위 잘못지정. board로 썼었음
			for(int j = 0 ; j <= M - block[0].length ; j++) {
				int sum = 0;
				for(int m = 0 ; m < block.length ; m++) {
					for(int n = 0 ; n < block[m].length ; n++) {
						if(block[m][n] == 1)	// 여기 틀렸었구
							sum += board[i + m][j + n];
					}
				}
				ans = Math.max(ans, sum);
			}
		}
	}
	
	static int[][] rotate(int[][] block) {
		int[][] newBlock = new int[block[0].length][block.length];
		for(int i = 0 ; i < block.length ; i++) {
			for(int j = 0 ; j < block[0].length ; j++) {
				newBlock[j][block.length - 1 - i] = block[i][j];
			}
		}
		
		return newBlock;
		
	}
	static int[][] reverse(int[][] block) {
		int[][] newBlock = new int[block.length][block[0].length];
		for(int i = 0 ; i < block.length ; i++) {
			for(int j = 0 ; j < block[0].length ; j++) {
				newBlock[block.length - i - 1][j] = block[i][j];
			}
		}
		
		return newBlock;
	}
	
	static void print(int[][] block) {
		for(int i = 0 ; i < block.length ; i++) {
			for(int j = 0 ; j < block[i].length ; j++) {
				System.out.print(block[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println("----------------------------------");
	}
}
