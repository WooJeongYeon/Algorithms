import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
 * Date : 2021.10.06
 * Level : BaekJoon 골드 4
 * Thinking) 백트레킹 잘하면 순열써도 초과 안나겠지..?
 * 		- 중복순열 -> 위왼쪽부터 아래오른쪽까지 차례대로 확인하면서 순열 1 ~ 9 넣고, 가장 먼저 끝난게 답!(사전식으로 가장 앞서는 것임)
 * Method : 중복순열, 재귀, 백트레킹
 * Error1 : 맵에 계속 1넣어주고 있었다
 * Error2 : 3 X 3 어떤 영역에 속하는 지 계산할 때 (i / 3) * 3 해줘야 함(나머지연산 했었음)
 * Error3 : board에 값 넣을 경우, 자기자신은 비교에서 제외해줘야 함 -> n을 비교하고 유효하면 board에 넣도록 해서 자기자신 확인 X(짜피 0저장되어 있으므로)
 * 	(이거 상관없는디?)Error4 : return 해주려고 isValid함수로 빼면서 continue할 때 board 값에 이전 저장된 board값이 남아있게됨 -> Error2와 마찬가지로 바꿨는데... 이게 왜 문제됬었지..? 
 * Error5 : n으로 바꾸고 비교할 때 char형인거 생각 안함. 변형해서 비교해줘야 함
 * Plus1 : 아래처럼 하면 재귀가 81개 만큼 쌓임 -> 빈칸을 따로 List같은데 모아놔도 좋을거래. 그럼 빈칸만큼의 depth가 생길 것
 * Plus2 : 아래 for문 합칠 수 있네..!(행, 열 비교)
 * Result : 계획 잘 짰다고 생각했는데ㅠㅠㅠ디버깅 그만!!! 1시간 정도 걸린듯
 */
public class BJ2239_스도쿠 {
	static char[][] board;
	static final int N = 9;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		board = new char[N][N];		// 스도쿠 입력받고 작업할 board
		for(int i = 0 ; i < N ; i++) {	
			board[i] = in.readLine().toCharArray();
		}
		
		makeSudoku(0);				// 인덱스 0번부터 시작해 스도쿠 만들기
		
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(board[i][j] - '0');		// char형 배열이라 '0'빼서 출력
			}
			System.out.println();
		}
	}
	static boolean makeSudoku(int idx) {				// 해당 idx 자리에 맞는 값을 넣어주자~~
//		print();
		if(idx == N * N) return true;					// 끝까지 왔으면 스도쿠 완성됬으므로 true 반환
		int i = idx / N;								// 1차원 인덱스를 2차원으로 바꿔줌
		int j = idx % N;
		
		if(board[i][j] != '0') {							// 해당 칸에 값이 있으면 다음 idx로 ㄱㄱ
			if(makeSudoku(idx + 1)) return true;
		}
		else {											// 아니면
			for(int n = 1 ; n <= N ; n++) {				// 1 ~ N값을 모두 넣어봄
				if(!isValid(i, j, n)) {					// 못넣는 값이면 다음값 ㄱㄱ
					continue;
				}
				
				board[i][j] = (char)(n + '0');			// 넣을 수 있으면 해당 값을 넣어보고 error : 여기 n말고 1 썼었음
				
				if(makeSudoku(idx + 1)) return true;	// 스도쿠 돌려돌려
				
			}
			board[i][j] = '0';						// 넣었던 값을 빼줌
		}
		return false;
	}
	
	
	static boolean isValid(int i, int j, int n) {		// 해당 값이 유효한지를 검사(넣을 수 있는지)
		for(int t = 0 ; t < N ; t++) {					// 같은 행 or 같은 열에 대해 비교
			if(board[i][t] == (n + '0') || board[t][j] == (n + '0')) {				// 같은값이면 불가
				return false;
			}
		}
		for(int r = 0 ; r < N / 3 ; r++) {				// 3 * 3으로 나눈 구역에 대해 해당 구역의 값들과 비교 
			for(int c = 0 ; c < N / 3 ; c++) {
				if(board[(i / 3) * 3 + r][(j / 3) * 3 + c] == (n + '0')) {
					return false;
				}
			}
		}
		return true;									// 가능하면 true 반환
	}
	static void print() {
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				System.out.print(board[i][j] - '0');
			}
			System.out.println();
		}
		System.out.println("---------------------------------------");
	}
}
