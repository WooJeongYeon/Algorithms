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

public class SW5643_키순서 {
	static int TC, N, M, ans, num;
	static boolean[][] adjArr;
	static boolean[] visit;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		TC = Integer.parseInt(br.readLine());
		
		for(int tc = 1 ; tc <= TC ; tc++) {					// TC만큼 반복
			N = Integer.parseInt(br.readLine());
			M = Integer.parseInt(br.readLine());
			adjArr = new boolean[N + 1][N + 1];				// 인접행렬
			ans = 0;										// 답을 저장할 변수
			
			for(int i = 0 ; i < M ; i++) {					// 간선 정보 받음
				st = new StringTokenizer(br.readLine());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjArr[from][to] = true;						// 이어져있음만 표시
			}
			
			for(int i = 1 ; i <= N ; i++) {					// 모든 정점에서 시작해
				num = 0;									// 해당 정점과 위 아래로 연결되어있는 정점 개수
				visit = new boolean[N + 1];					// visit 체크
				up(i);										// 위로 쭉쭉~
				down(i);									// 아래로 쭉쭉~~
				if(num == N - 1) ans++;						// 나머지 모든 정점과 연결되어 있다면 몇번째 키인지 알 수 있음!
			}
			
			
			sb.append("#" + tc + " " + ans + "\n");
		}
		
		System.out.println(sb);
	}
	static void up(int now) {								// 위로 쭉쭉 가보자
		visit[now] = true;									// 현재 노드 방문을 true로
		for(int j = 1 ; j <= N ; j++) {						// 모든 노드들에 대해
			if(adjArr[j][now] && !visit[j]) {				// 현재 노드를 가리키고 있고, 방문 안했다면
				num++;										// 연결수 증가하고
				up(j);										// 위로 쭉쭉ㄱㄱ
			}
		}
	}
	static void down(int now) {								// 아래로 쭉쭉 가보자
		visit[now] = true;									// 현재 노드 방문을 true로
		for(int i = 1 ; i <= N ; i++) {
			if(adjArr[now][i] && !visit[i]) {				// 현재 노드가 가리키고 있고, 방문 안했다면
				num++;										// 연결수 증가하고
				down(i);									// 아래로 쭉쭉ㄱㄱ
			}
		}
	}
}
