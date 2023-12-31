import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
/*
 * Date : 2021.11.01 ~ 2021.11.02
 * Level : BaekJoon Gold 2
 * Difficulty : 중상
 * Time : 이틀간 그냥 틈틈히 생각함
 * URL : https://www.acmicpc.net/problem/12100
 * Select1 : 배열회전 - 한방향으로 계속돌아가게
 * Select2 : 최댓값은 그냥 마지막에 구하도록 하자
 * Thinking : dfs로 상하좌우로 depth 5까지 이동하면 되겠다.
 * 			- 그리디하게 접근 -> 움직임이 없는 경우는 depth 안들어가게(재귀 타지 않게) -> 이거는 구현X
 * 			- 상하좌우 이동하게 하면 코드가 길어질거 같다 -> 배열을 돌리자(왼쪽90도, 오른쪽90도, 뒤집기)
 * 			- 저렇게 돌리면 코드 길어지니까 그냥 cnt 줘서 오른쪽으로 계속 돌리게 하자 -> 90도, 180도, 270도, 360도
 * 			- 배열 회전 어케하지... 하나씩 써보면서 인덱스 구함 -> 오른쪽으로 돌릴 때는 i, j -> j, N - i - 1
 * 			- 돌린 배열의 요소들을 왼쪽으로 이동시킴 
 * 			- 재귀 순서
 * 			1. 배열을 왼쪽으로 당기고
 * 			2. 만약 depth가 4이면 최댓값 저장하고 return
 * 			3. 4 방향으로 회전한 배열을 재귀로 넘겨줌
 * Method : DFS
 * Error1 : 배열 회전 없는 경우, 배열이 복사가 안되서 다 0으로 돌아감 -> 360도 회전하도록(무조건 회전돌리도록)
 * Error2 : 합쳐주는 과정에서 매 행마다 idx, last 초기화 안해줌
 * Error3 : 합쳐주는 과정에서 당길 때 남는 map 부분들 0으로 채워줘야 함(같은 배열 내에서 작업하므로)
 * Error4 : 남는 map 부분 0으로 채워줄 때 인덱스 틀림 idx -> j
 * Result : 배열 회전때매 걱정이 많았는데 변수로 어떻게 돌아가나 써보니까 생각보다 인덱스빠르게 구했네
 * 			- 코드 정확하게 쓰지는 않았지만 그래도 생각하고 했는데 생각이 부족했다
 * Plus1 : 움직임이 없는 경우는 depth 안들어가게(재귀 타지 않게)
 * Plus2 : 그대로인 경우는 배열 회전 말고 배열 복사만 하도록 할 수 있겠지..? 시간이 줄수도?
 */
public class BJ12100_2048_EASY {
	static int N, ans;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine(), " ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int n = 0; n < 4; n++) {			// 4 방향으로 돌린 배열들 각각으로 dfs 시작
			move(0, copyRotateMap(map, n));
		}

		System.out.println(ans);
	}

	static void move(int depth, int[][] map) {
		int idx = 0;
		int last = -1;
		for (int i = 0; i < N; i++) {				// map 오른쪽에서 왼쪽으로 당기기
			for (int j = 0; j < N; j++) {
				if (map[i][j] == 0)					// 0이면 옮길거 없음
					continue;
				if (last == map[i][j]) {			// 이전 값이랑 같으면 2배로 해서 저장해주기
					map[i][idx - 1] = last * 2;	
					last = -1;
				} else {							// 아니면 그냥 옮김
					map[i][idx++] = map[i][j];
					last = map[i][j];
				}
			}
			for(int j = idx ; j < N ; j++) {		// 옮기고 난 나머지를 0으로 저장
				map[i][j] = 0;		// 여기 인덱스를 j대신 idx로 했어서 복사하고 있었네
			}
			idx = 0;		// 이거 안했었음. 나머지도 채우게 해야하는디
			last = -1;		// 이것도 안했네
		}
		
//		
//		System.out.println("깊이 : " + depth);
//		print(map);

		
		if (depth == 4) {									// 5번 다 진행했다면
			ans = Math.max(ans, maxValue(map));				// 최댓값 저장하고
			return;											// 리턴
		}

		for (int n = 0; n < 4; n++) {						// 4 방향으로 dfs ㄱㄱ
			move(depth + 1, copyRotateMap(map, n));
		}
	}

	static int maxValue(int[][] map) {						// 최댓값 찾기
		int max = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				max = Math.max(max, map[i][j]);
			}
		}

		return max;
	}

	static int[][] copyRotateMap(int[][] map, int cnt) {	// 맵 회전해서 복사해줌
		int[][] simulMap = new int[N][N];
		cnt = 3 - cnt;
		for (int n = 0; n < cnt + 1; n++) {		// 무조건 돌리게(cnt 0 ~ 4 - 90, 180, 270, 360)
			simulMap = new int[N][N];
			for (int i = 0; i < N ; i++) {
				for (int j = 0; j < N; j++) {
					simulMap[j][N - i - 1] = map[i][j];
				}
			}
			map = simulMap;
		}
		return simulMap;
	}
//
//	static void print(int[][] map) {
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
//		System.out.println("----------------------------");
//	}
}
