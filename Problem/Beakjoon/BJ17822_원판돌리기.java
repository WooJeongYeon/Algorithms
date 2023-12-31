import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
/*
 * Date : 2021.12.16
 * Level : BaekJoon Gold 3
 * Difficulty : 중상
 * URL : https://www.acmicpc.net/problem/17822
 * Select1 : 
 * Thinking : 주변에 같은 값이 있는지 체크하고 BFS 가도록 -> 같은 값이 있는 경우 다 0으로 저장해줘야되서...(이게 좀 어려웠다)
 * 			- 처음 블록 다 저장해두고 걔네를 모두 회전/뒤집기함(4 * 2) -> 이 경우 다시 계산하는 것도 생김
 * 			- 변형시키고 board에 차례대로 마스킹해 구한 값으로 최댓값 갱신
 * 			- 반복문이 많이 들어가서 함수로 뺌
 * Method : 배열회전
 * Error1 : 문제에서 주어진 배열과 원판이 반대로네... 시계방향이라서 -> 회전하는거 반대로해줌
 * Error2 : (디버깅하다 찾음!)평균이 정수일 수 있음 -> 같은값이면 +이나 -를 안해줌
 * Result : 엄청 어려운 문제는 아닌거같은데... 버그잡는데 좀 걸렸다.
 * 		- 분수에 안맞는 문제를 풀고 있는 듯하다. 나중에 삼성문제 다 다시풀어야겠다ㅠㅠ 구현하는 문제들 많이 풀어보자 + 복습
 * 		- 남의 테케 빠르게 찾아서 돌려보지 말고 내가 찾자!(엄청 생각했는데 안되면 해보는걸로..)
 * 		- 왜 틀렸는지 모르면
 * 			1. 문제를 다시 읽고
 * 			2. 내 코드를 나한테 설명하면서 다시 보고
 * 			3. 생각한대로 실행되는지 돌려보기
 * 		- 예제가 디버깅했는데 맞게 돌아가면 그 관련 논리는 맞는거니까 그거 빼고 생각해보기
 * 		- 내 코드가 맞다고 생각하지 말고 틀렸다고 생각하고 막 공격하자! 
 * Plus1 : dfs로 했을 때, 같은거 있으면 무조건 0저장해주고 boolean 변수로 체크한다음 dfs 끝나고 boolean변수 체크해서 첫번째 위치 0으로 만들어주네
 */
public class BJ17822_원판돌리기 {
	static int N, M, T, ans;
	static int[][] board;
	static int[] di = {-1, 1, 0, 0};
	static int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine(), " ");
		N = Integer.parseInt(st.nextToken()) + 1;		// 배수로 하기 위해 행은 1부터 유효
		M = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for(int i = 1 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for(int j = 0 ; j < M ; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int t = 0 ; t < T ; t++) {
			st = new StringTokenizer(in.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			for(int i = x ; i < N ; i += x) {			// 해당 배수의 행을
				for(int n = 0 ; n < k ; n++) {			// 한칸씩 해당 방향으로 이동시킴
					rotate(i, d);
				}
			}
			
			if(!DeleteBoard()) {						// 인접한 같은 값들 삭제하고, 불가능하면
				double avg = (double)getSum() / getCnt();	
				UpDownBoard(avg);						// 전체 평균 구해서 +-해줌
			}
			ans = getSum();						
			if(ans == 0) break;							// 더이상 할게 없으면 중단
		}
		System.out.println(ans);
	}
	
	private static boolean DeleteBoard() {
		boolean result = false;
		for(int i = 1 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(board[i][j] == 0) continue;
				boolean isSame = false;
				for(int d = 0 ; d < 4 ; d++) {		// 주변에 같은 값이 있으면
					int ni = i + di[d];
					int nj = (j + dj[d] + M) % M;	// 열은 왼쪽끝 오른쪽끝이 이어져있음(인덱스 나갈 일이 없음)
					if(ni < 1 || ni >= N) continue;
					if(board[ni][nj] == board[i][j]) {
						 isSame = true;
						 result = true;
						 break;
					}
				}
				if(isSame) {						// DFS 고고!
					bfs(i, j);
				}
			}
		}
		return result;
	}
	
	private static void bfs(int i, int j) {
		int num = board[i][j];
		board[i][j] = 0;
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[]{i, j});
		while(!queue.isEmpty()) {
			int[] now = queue.poll();
			for(int d = 0 ; d < 4 ; d++) {
				int ni = now[0] + di[d];
				int nj = (now[1] + dj[d] + M) % M;
				if(ni < 1 || ni >= N) continue;
				if(num == board[ni][nj]) {
					board[ni][nj] = 0;
					queue.offer(new int[] {ni, nj});
				}
			}
		}
	}
	private static void rotate(int i, int d) {
		int idx = 0, nextIdx = 0;
		int tmp = board[i][idx];
		d = d == 0 ? -1 : 1;			// 1) 이거 바꿨음
		for(int n = 0 ; n < M - 1 ; n++, idx = nextIdx) {
			nextIdx = ((idx + d) + M) % M;
			board[i][idx] = board[i][nextIdx];
		}
		board[i][nextIdx] = tmp;
	}
	private static void UpDownBoard(double avg) {			// 평균에 대해 업다운 해줌
		for(int i = 1 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(board[i][j] == 0) continue;
				if((double)board[i][j] > avg) board[i][j] -= 1;
				else if((double)board[i][j] < avg) board[i][j] += 1;	// 2) 같은 경우는 +-해줌 안됨!
			}
		}
	}
	private static int getCnt() {
		int cnt = 0;
		for(int i = 1 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				if(board[i][j] != 0) cnt++;
			}
		}
		return cnt;
	}
	private static int getSum() {
		int sum = 0;
		for(int i = 1 ; i < N ; i++) {
			for(int j = 0 ; j < M ; j++) {
				sum += board[i][j];
			}
		}
		return sum;
	}
}
