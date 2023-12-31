import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * Date : 2021.09.30
 * Level : SW Expert D4
 * Thinking)
 * 	순환X, 모두 이어져있음
 *  인접행렬 사용
 * 	처음 - 모든 노드부터 시작해 해당 칸 중심으로 들어가는거 + 나가는 개수 저장할까? -> 들어오는거 계산할 때 중복값 발생 가능.. 
 * 		이 중복값을 어떻게 처리해ㅠ 특정한최단경로를 값 저장하려고 했을때랑 같은 문제네...
 * 	다시 - 모든 노드로부터 해당 노드 기준으로 위아래로 쭉 가면서 visit체크하고 방문노드개수 더하기
 * Method : 위아래로 쭉!! dfs!!
 * Error1 : ans 초기화 안해줘서...
 * Plus1 : 생각해보니 visit 사용해서도 들어오는것도 계산할 수 있을거같은데... 그러면 메모이제이션 쓸 수 있을거같은디...
 */

public class SW5643_키순서_memo {
	static int TC, N, M, ans, cnt;
	static int[][] adjArr;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= TC ; tc++) {					// TC만큼 반복
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adjArr = new int[N + 1][N + 1];				// 인접행렬
			ans = 0;										// 답을 저장할 변수
			
			for(int i = 0 ; i < M ; i++) {					// 간선 정보 받음
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjArr[from][to] = 1;						// 이어져있음만 표시
			}
			
			for(int i = 1 ; i <= N ; i++) {
				adjArr[i][0] = -1;
			}
			
			for(int i = 1 ; i <= N ; i++) {					
				if(adjArr[i][0] != -1) dfs(i);			// 자신보다 큰 학생 탐색(아직 탐색이 안된 학생만)
			}
			
			for(int i = 1 ; i <= N ; i++) {
				for(int j = 1 ; j <= N ; j++) {
					adjArr[0][j] += adjArr[i][j];		// 자신보다 작은 애들을 계산
				}
			}
			for(int i = 1 ; i <= N ; i++) {
				if(adjArr[i][0] + adjArr[0][i] == N - 1) ++ ans;
			}
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb);
	}
	static void dfs(int cur) {								
		for(int i = 1 ; i <= N ; i++) {				
			if(adjArr[cur][1] == 1) {		// 자신보다 큰 학생이면				
				if(adjArr[i][0] == -1) {	// 탐색전
					dfs(i);
				}
				// 자신보다 큰 학생을 탐색을 완료한 상태(메모가 되어있으므로 탐색 안하고 바로 내려옴)
				if(adjArr[i][0] > 0) {		// i보다 큰 학생이 존재
					// i의 인접행렬의 상태를 cur에 반영
					for(int j = 1 ; j <= N ; j++) {
						if(adjArr[i][j] == 1) adjArr[cur][j] = 1;
					}
				}
			}
		}
		
		int cnt = 0;
		for(int j = 1 ; j <= N ; j++) {
			cnt += adjArr[cur][j];
		}
		adjArr[cur][0] = cnt;
	}
}
