import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
/*
 * Date : 2021.10.06
 * Level : BaekJoon Gold 5
 * Thinking) 
 * 		- 처음 : 인접한 구역 없는 개수들 처리해야지 -> DisJoint로 그래프 개수 나눠줘서 처리해야지! 그래프 1개인 경우만 따로 안에서 찾아주면 되겠다!
 * 				단말노드의 경우, 이전 노드와 같은 곳에 소속되어야 규칙 안어기는데.. 규칙 안어기면서 어떻게 해야하지..?
 * 				고민하고 있는데 N의 개수가 최대 10개네. 부분집합 2 ^ 10 = 1024 -> 그냥 이걸로 하쟈
 * 		- 나중 : 2^N 부분집합 다 구하고 유효성 검사하자. 유효성 검사는 DisJoint 사용해서 그룹개수 구할까 하다가 버리고,
 * 				1번 구역부터 반복해서 bfs 돌려서 방문체크 + 그룹 0, 1도 방문체크
 * 				그룹 둘다 방문했는데 방문안한 곳이 나오면 -> 연결이 안된거(불가)
 * 				최종적으로 그룹 둘 중 방문체크 안된 곳이 있으면 -> (불가) 
 * 		- 아근데 시간제한 0.5초인데 초과안되려나..? -> 됨. 한번에 통과하니까 좋다...
 * Method : 부분집합, 인접행렬, dfs
 * Error1 : dfs돌릴 때 같은 그룹 애들만 방문하도록 해야함
 * Plus1 : 그룹 지정해줄 때 {1} {2} 나 {2} {1} 나 같은거지만 나는 처리 안해줘따... 기차나.. -> 이거 제끼려다가 괜히 더 복잡해질 수 있대
 * Plus2 : 그래프가 1일때는 해당 그래프 내에서 나눔, 2일때는 두개 각 그래프가 그룹, 3개 이상일 때는 그룹 나누기 불가.
 * 			이거 처음에 DisJoint로 그룹 개수 구해서 처리해줄 까 하다가(더 빨라질거 같아서) 그냥 다 부분집합 무조건 돌렸다.
 * Plus3 : 부분집합에서 백트래킹을 할 수 있을까..?
 * Plus4 : 부분집합을 비트로 할 수도 있음 -> 시간 준대 
 * Result : 2시간 반 걸린듯?
 */
public class BJ17471_개리맨더링 {
	static int[] pCnt, where;
	static int N, ans;
	static boolean[][] adjArr;
	static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(in.readLine());
		pCnt = new int[N];
		where = new int[N];
		adjArr = new boolean[N][N];
		ans = Integer.MAX_VALUE;
		
		st = new StringTokenizer(in.readLine());
		for(int i = 0 ; i < N ; i++) {
			pCnt[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(in.readLine());
			int num = Integer.parseInt(st.nextToken());
			for(int j = 0 ; j < num ; j++) {
				adjArr[i][Integer.parseInt(st.nextToken()) - 1] = true;
			}
		}
		
		subset(0, 0);
		if(ans == Integer.MAX_VALUE) ans = -1;
		
		System.out.println(ans);
		
	}
	
	static void subset(int idx, int sum) {
		if(idx == N) {					// 다 선택했고
			if(isValid()) {				// 그룹이 타당하면
//				System.out.println("결과: " + Arrays.toString(where) + " " + sum);
				ans = Math.min(ans, Math.abs(sum));			// 최솟값 갱신
			}
			return;
		}
		where[idx] = 0;							// 0번그룹으로 선택
		subset(idx + 1, sum + pCnt[idx]);
		where[idx] = 1;							// 1번그룹으로 선택
		subset(idx + 1, sum - pCnt[idx]);
	}
	
	static boolean isValid() {							// 그룹의 타당성 검증
		boolean[] groupVisit = new boolean[2];
		visit = new boolean[N];
		for(int i = 0 ; i < N ; i++) {					// 모든 구역에 대해
			if(!visit[i] && groupVisit[where[i]]) {		// 방문하지 않았는데 해당 그룹을 방문했다면 -> 연결되지 않은 것
				return false;							// 안됨
			}
			if(!visit[i]) {								// 방문하지 않았다면
				groupVisit[where[i]] = true;			// 해당 그룹에 방문처리
				dfs(i, where[i]);						// dfsㄱㄱ
			}
		}
		if(groupVisit[0] == false || groupVisit[1] == false) {	// 두 그룹이 아니라면
			return false;										// 안됨
		}
		
		return true;
	}
	static void dfs(int idx, int groupNum) {
		visit[idx] = true;										// 방문 저장
		for(int j = 0 ; j < N ; j++) {							// 이어졌고, 방문하지 않았고, 같은 그룹이면 ㄱㄱ
			if(adjArr[idx][j] && !visit[j] && where[j] == groupNum) dfs(j, groupNum);
		}
	}
	
}
