import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.06
 * Level : BaekJoon Gold 5
 * Difficulty : 중
 * URL : https://www.acmicpc.net/problem/14500
 * Select1 : DFS vs 블록 다 입력 vs 블록회전/뒤집기(이거 선택)
 * Thinking : DFS로 할려다가 'ㅏ' 이 블록때매 깊이탐색 안되겠구나 하고 때려침
 * 			- 처음 블록 다 저장해두고 걔네를 모두 회전/뒤집기함(4 * 2) -> 이 경우 다시 계산하는 것도 생김
 * 			- 변형시키고 board에 차례대로 마스킹해 구한 값으로 최댓값 갱신
 * 			- 반복문이 많이 들어가서 함수로 뺌
 * Method : 배열회전
 * Error1 : masking함수 안호출함ㅋㅋㅋㅋㅋㅋㅋ
 * Error2 : board랑 block 헷갈림
 * Error3 : 변형한 블록 씌우려고 i, j로 배열탐색 할 때 끝나는 지점 잘못지정
 * Result : 블록 다 그려보면서 풀어서.. 오래걸린듯? 어케할지 고민 좀 했다.
 * Plus1 : DFS 되자너..!!! 이런 방법이 있다니. 블록 3개 먹고 i, j는 그대로, depth와 sum은 증가해서 dfs 돌리네..ㅠㅠㅠ
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
		
		for(int t = 0 ; t < tetrominos.length ; t++) {		// 모든 블록에 대해
			int[][] block = tetrominos[t];
			for(int m = 0 ; m < 4 ; m++) {					// 4번 회전
				block = rotate(block);
				for(int n = 0 ; n < 2 ; n++) {				// 2번 뒤집기
					block = reverse(block);
//					print(block);
					masking(block);							// 이거 추가안함. block이랑 board랑 헷갈림
				}
			}
		}
		
		System.out.println(ans);
	}
	
	static void masking(int[][] block) {
		for(int i = 0 ; i <= N - block.length ; i++) {					// board 행// 범위 잘못지정. board로 썼었음
			for(int j = 0 ; j <= M - block[0].length ; j++) {			// board 열
				int sum = 0;
				for(int m = 0 ; m < block.length ; m++) {				// 블록의 행
					for(int n = 0 ; n < block[m].length ; n++) {		// 블록의 열
						if(block[m][n] == 1)							// 블록이 있으면	
							sum += board[i + m][j + n];					// 더함
					}
				}
				ans = Math.max(ans, sum);								// 값 갱신
			}
		}
	}
	
	static int[][] rotate(int[][] block) {				// 회전(오른쪽으로)
		int[][] newBlock = new int[block[0].length][block.length];
		for(int i = 0 ; i < block.length ; i++) {
			for(int j = 0 ; j < block[0].length ; j++) {
				newBlock[j][block.length - 1 - i] = block[i][j];
			}
		}
		
		return newBlock;
		
	}
	static int[][] reverse(int[][] block) {				// 반전(위아래로)
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
