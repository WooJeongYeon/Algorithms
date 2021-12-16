import java.util.Scanner;
/*
 * Date : 2021.12.16
 * Level : BaekJoon Gold 2
 * Difficulty : 중상
 * URL : https://www.acmicpc.net/problem/17825
 * Thinking : 각 방향에 있는 애들 배열 한줄씩 저장시키고, DFS로 말 선택해서 1칸씩 이동시킴
 * 			- 이동 중에 배열 위치 변경해줌
 * Method : DFS
 * Error1 : /를 %로 씀
 * Error2 : 입력 다 안받음...
 * Error3 : 30이 두개 있는걸 몰랐음 -> 체크해주게
 * Error4 : 겹치는 말이 있는지를 확인하는 isPossible 위치를 잘못 놓음
 * Error5 : (테케 질문에서 찾아서 돌려봄) 같은 값일 때 겹치는 위치라고 했었음 -> 같은 위치일때 겹친다고 바꿈
 * Error6 : (5번 수정하니까됬던게 안되네)40 위치변경을 안해줘서 같은 위치인데도 다르게 판단 -> 배열 위치 바뀌도록
 * Result : 왤케 어렵지ㅠㅠㅠㅠ 그냥 생각이 부족한가보다. 다른 사람이랑 얘기하면서 풀어서 더 잘못 쓴 부분이 많은듯. 집중하자!
 * 		** 주의 **
 * 		- 변수 초기화 위치 주의!
 * 		- DFS에서 변수 값 원래대로 돌릴 때 주의!
 */
public class BJ17825_주사위윷놀이 {
	static int ans;
	static Point[] marker;
	static int[] dices;
	static int[][] board = {{0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, -1},
							{10, 13, 16, 19, 25},
							{20, 22, 24, 25},
							{30, 28, 27, 26, 25},
							{25, 30, 35, 40, -1}};
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		dices = new int[10];
		marker = new Point[4];
		for(int i = 0 ; i < marker.length ; i++) {
			marker[i] = new Point(0, 0); 
		}
		for(int i = 0 ; i < dices.length ; i++) {			// 2. 입력 다 안받었음...
			dices[i] = in.nextInt(); 
		}
		dfs(0, 0);
		
		System.out.println(ans);
	}
	private static void dfs(int idx, int score) {
//		 print(idx, score);
		if(idx >= dices.length) {
			ans = Math.max(ans, score);
			return;
		}
		for(int n = 0 ; n < marker.length ; n++) {
			Point last = new Point(marker[n].i, marker[n].j);
			boolean isPossible = true;								// 4. 위치 잘못놓음...
			if(board[marker[n].i][marker[n].j] == -1) continue;		// 이미 골에 들어간 말은 할필요 X	
			for(int i = 0 ; i < dices[idx] ; i++) {					// 이동시켜보면서
				marker[n].j++;
				if(board[marker[n].i][marker[n].j] == -1) break;	// 골이면 중단
				if(marker[n].j == (board[marker[n].i].length - 1)) { // 배열 위치 갱신해주고
					marker[n].i = 4;
					marker[n].j = 0;
				}
			}
			if(marker[n].i == 0 && board[marker[n].i][marker[n].j] == 40) {		// 6. 이거 추가해줌. 아래에 같은 위치 경우로 바꾸면서 40을 체크 못해줌
				marker[n].i = 4;
				marker[n].j = 3;
			}
			else if(marker[n].i == 0 && board[marker[n].i][marker[n].j] % 10 == 0) { // 3. 30이 2개 있었다...
				if(board[marker[n].i][marker[n].j] / 10 != 4) {		// 0번줄에서 10으로 나눠지면서 40이 아닐 때 실행 -> 위치갱신
					marker[n].i += board[marker[n].i][marker[n].j] / 10;	// 1. 얘 나머지로 했었음...
					marker[n].j = 0;
				}
			}
			for(int i = 0 ; i < marker.length ; i++) {				// 같은 위치에 있는 말이 있는지 체크
				if(n == i || board[marker[i].i][marker[i].j] == -1) continue;
				if(marker[n].i == marker[i].i && marker[n].j == marker[i].j) {	// 5. 테케 찾아서 돌려봄... 같은 값있는거 생각 못함.이건데 11%에서 틀리더니 왜 이제는 바로 틀렸다뜨냐...
					isPossible = false;
					break;
				}
			}	
			if(isPossible) {					// 갈 수 있는 칸이면
				if(board[marker[n].i][marker[n].j] == -1) dfs(idx + 1, score);	// 골이면 score 그대로 DFS
				else dfs(idx + 1, score + board[marker[n].i][marker[n].j]);		// 골 아니면 score 더해서 DFS
			}
			marker[n] = last;				// 다음 for문을 위해 이전값 다시 저장해줌
		}
		
	}
	static class Point {
		int i, j;
		public Point(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	static void print(int d, int score) {
		int[][] newBoard = new int[5][];
		for(int i = 0 ; i < board.length ; i++) {
			newBoard[i] = new int[board[i].length];
			for(int j = 0 ; j < board[i].length ; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		for(int n = 1 ; n <= 4 ; n++) {
			newBoard[marker[n - 1].i][marker[n - 1].j] = -n;
		}
		System.out.println("---------------------------");
		System.out.println("깊이 : " + d);
		System.out.println("점수 : " + score);
		for(int i = 0 ; i < newBoard.length ; i++) {
			for(int j = 0 ; j < newBoard[i].length ; j++) {
				System.out.printf("%2d ", newBoard[i][j]);
			}
			System.out.println();
		}
	}
}
