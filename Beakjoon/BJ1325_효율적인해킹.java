import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


/*
 * Date : 210922 ~ 210924
 * Thinking : DP로 풀어야하나...? 그래픈데 MST도 아니고 Dijkstra도 아니고 Floyd도 아니고... 인접리스트 만들어야 할듯. dfs? bfs? 메모이제이션이였네 내가쓴게. 
 * Method : 모든 정점에서 시작해서 BFS로 계속 이어진 정점 개수 찾기. 방문한 정점은 가지 않도록.
 * error1 : 시간초과ㅠㅠㅠㅠㅠ 순환있을때 문제있을듯...visit체크 추가
 * error2 : 틀렸는데...? 이게 중복으로 값이 들어가는 경우가 있어서 dp 안쓰려고 했는데 안쓰니까 시간초과나넹ㅋㅎㅎㅎㅎ
 * error3 : 시간초과ㅋㅋㅋㅋㅋㅋ
 * result : 쌤이 봐주심 - ArrayList는 짜피 삽입 뒤에 붙이는 거라 LinkedList말고 사용하고, 이 문제가 시간이 엄청 빠듯해서
 * 			dfs는 재귀호출 시간 더 걸릴 수 있어서 bfs로 푸는게 좋대. 사소한 부분까지 최적화 해야할거라구
 * 			시간 줄이는 방법 더 있을거같아서 쌤이 고민해보신대... 없을거래ㅠㅠㅠ
 * 			결국 BFS로 걍 다시풀었다...
 * Plus1 : 스트링 빌더로 붙여도 됬네
 */

public class BJ1325_효율적인해킹 {
	static int N, M, max, cnt;
	static int[] hackNum;
	static ArrayList<Integer>[] edgeList;
	static boolean[] visit;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		hackNum = new int[N + 1];
		edgeList = new ArrayList[N + 1];
		for(int i = 1 ; i <= N ; i++) {
			edgeList[i] = new ArrayList<>();
		}
		
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(in.readLine());
			int end = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());
			edgeList[start].add(end);
		}
		
		max = Integer.MIN_VALUE;
		for(int i = 1 ; i <= N ; i++) {
			visit = new boolean[N + 1];
			visit[i] = true;
			cnt = 0;
			
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(i);
			while(!queue.isEmpty()) {
				int start = queue.poll();
				cnt++;
				ArrayList<Integer> edges = edgeList[start];
				for(int end : edges) {
					if(visit[end]) continue;
					visit[end] = true;
					queue.offer(end);
				}
			}
			
			hackNum[i] = cnt;
			max = Math.max(max, hackNum[i]);
		}
		
		for(int i = 1 ; i <= N ; i++) {
			if(hackNum[i] == max) sb.append(i + " ");
		}
		
		System.out.println(sb);
	}
}
